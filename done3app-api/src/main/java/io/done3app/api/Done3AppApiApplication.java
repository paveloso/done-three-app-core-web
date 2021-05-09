package io.done3app.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class Done3AppApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(Done3AppApiApplication.class, args);
    }
}
