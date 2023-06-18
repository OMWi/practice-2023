package org.example.service;

import org.example.repository.UserDataRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;

@Service
public class PaymentService {
    private final UserDataRepository userDataRepository;

    public PaymentService(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    public boolean isSubscriber(Long userId) {
        // todo: implement
        var userData = userDataRepository.findById(userId).orElseThrow();
        return false;
    }

    public void setSubscription(Long userId) {
        var userData = userDataRepository.findById(userId).orElseThrow();

        var today = new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DATE, 30);
        Date subscriptionExpirationDate = new Date(calendar.getTime().getTime());

        userData.setSubscriptionExpirationDate(subscriptionExpirationDate);
        userDataRepository.save(userData);
    }
}
