package com.example.readingservice1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@SpringBootApplication
public class ReadingService1Application {

    @Autowired
    CircuitBreakerFactory circuitBreakerFactory;

    public static void main(String[] args) {
        SpringApplication.run(ReadingService1Application.class, args);
    }


    @RequestMapping("/to-read")
    public String toRead(){
        return circuitBreakerFactory.create("recommended").run(
                () -> new RestTemplate().getForObject("http://127.0.0.1:8090/recommended", String.class),
                throwable -> "Cloud Native Java (0'Reilly)"
        );
    }
}
