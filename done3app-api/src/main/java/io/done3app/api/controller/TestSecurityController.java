package io.done3app.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestSecurityController {

    @GetMapping("/app/resource")
    public String getResource() {
        return "Welcome Authorized User";
    }
}
