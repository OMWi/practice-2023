package org.example.controller;

import com.stripe.Stripe;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.LineItemCollection;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import com.stripe.param.checkout.SessionCreateParams;
import com.stripe.param.checkout.SessionListLineItemsParams;
import com.stripe.param.checkout.SessionRetrieveParams;
import org.example.security.UserDetailsImpl;
import org.example.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {
    @Value("${org.app.stripeSecretKeyTest}")
    private String stripeSKTest;

    @Value("${org.app.stripePriceId}")
    private String stripePriceId;

    @Value("${org.app.stripeEndpointSecret}")
    private String stripeEndpointSecret;

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    @Secured({"ADMIN", "USER"})
    public String getCheckoutUrl() throws StripeException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();


        String CLIENT_DOMAIN = "http://localhost:5173";

        Stripe.apiKey = stripeSKTest;

        SessionCreateParams params =
                SessionCreateParams.builder()
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .setSuccessUrl(CLIENT_DOMAIN + "/profile")
                        .setCancelUrl(CLIENT_DOMAIN + "/profile")
                        .setClientReferenceId(userDetails.getId().toString())
                        .addLineItem(
                                SessionCreateParams.LineItem.builder()
                                        .setQuantity(1L)
                                        // Provide the exact Price ID (for example, pr_1234) of the product you want to sell
                                        .setPrice(stripePriceId)
                                        .build())
                        .build();
        Session session = Session.create(params);

        return session.getUrl();
    }

    @PostMapping
    public String stripeWebhook(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader) throws StripeException {
        Event event = null;
        try {
            event = Webhook.constructEvent(payload, sigHeader, stripeEndpointSecret);
        } catch (SignatureVerificationException e) {
            System.out.println("Failed signature verification");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // Handle the checkout.session.completed event
        if ("checkout.session.completed".equals(event.getType())) {
            Session sessionEvent= (Session) event.getDataObjectDeserializer().getObject().orElseThrow();
            SessionRetrieveParams params =
                    SessionRetrieveParams.builder()
                            .addExpand("line_items")
                            .build();

            Session session = Session.retrieve(sessionEvent.getId(), params, null);

            SessionListLineItemsParams listLineItemsParams =
                    SessionListLineItemsParams.builder()
                            .build();

            // Retrieve the session. If you require line items in the response, you may include them by expanding line_items.
            LineItemCollection lineItems = session.listLineItems(listLineItemsParams);
            // Fulfill the purchase...
            try {
                Long userId = Long.parseLong(sessionEvent.getClientReferenceId());
                paymentService.setSubscription(userId);
            } catch (NoSuchElementException e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        }

        return "";
    }
}
