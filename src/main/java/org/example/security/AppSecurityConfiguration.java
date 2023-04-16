package org.example.security;

import org.example.service.UserCredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class AppSecurityConfiguration {
    @Autowired
    public UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/auth/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/words").permitAll()
                .anyRequest().authenticated();

        httpSecurity.authenticationProvider(authenticationProvider());

        httpSecurity.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);


        //        httpSecurity.cors().and().csrf().disable()
//                .authorizeHttpRequests()
//                .requestMatchers("/api/v1/auth/login", "/api/v1/auth/register").permitAll()
////                .anyRequest().authenticated();
//                .anyRequest().permitAll();

//        httpSecurity
//                .authorizeHttpRequests()
//                .requestMatchers("/api/v1/user-credentials/register").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
////                .loginPage("/api/v1/auth/login")
////                .defaultSuccessUrl("/api/v1/auth/welcome")
////                .failureUrl("/api/v1/auth/login?error")
//                .and()
//                .logout();
        return httpSecurity.build();
    }

//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userCredentialsService).passwordEncoder(passwordEncoder);
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
//        return configuration.getAuthenticationManager();
//    }

}
