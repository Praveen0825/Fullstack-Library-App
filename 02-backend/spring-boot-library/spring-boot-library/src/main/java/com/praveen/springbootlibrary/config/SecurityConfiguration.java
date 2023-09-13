package com.praveen.springbootlibrary.config;


import com.okta.spring.boot.oauth.Okta;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable());
        http.authorizeRequests(authorize -> authorize
                        .requestMatchers("/api/books/secure/**",
                                "/api/reviews/secure/**",
                                "/api/messages/secure/**",
                                "/api/admin/secure/**")
                        .authenticated())
               .oauth2ResourceServer((oauth2) -> oauth2
                        .jwt(withDefaults())
                );

        // Add CORS filters
        http.cors(withDefaults());

        // Add content negotiation strategy
        http.setSharedObject(ContentNegotiationStrategy.class,
               new HeaderContentNegotiationStrategy());

        // Force a non-empty response body for 401's to make the response friendly
        //Okta.configureResourceServer401ResponseBody(http);

        return http.build();
    }

}

/*
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/books/secure/tydfytftyf/**")
                        .authenticated())
               .oauth2ResourceServer((oauth2) -> oauth2
                        .jwt(withDefaults())
                ).cors(withDefaults());

        // Add CORS filters
       // http.cors();

        // Add content negotiation strategy
        http.setSharedObject(ContentNegotiationStrategy.class,
               new HeaderContentNegotiationStrategy());

        // Force a non-empty response body for 401's to make the response friendly
        //Okta.configureResourceServer401ResponseBody(http);

        return http.build();
    }
 */






