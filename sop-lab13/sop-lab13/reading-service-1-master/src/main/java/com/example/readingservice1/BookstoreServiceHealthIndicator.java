package com.example.readingservice1;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.net.Socket;

@Component
public class BookstoreServiceHealthIndicator implements HealthIndicator {

    private static final String URL = "http://127.0.0.1:8090/recommended";

    @Override
    public Health health(){
        try{
            new Socket(new java.net.URL(URL).getHost(), 8090);
        } catch (Exception e){
            return Health.down()
                    .withDetail("error", e.getMessage())
                    .build();
        }
        return Health.up().build();
    }
}
