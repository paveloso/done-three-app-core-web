package io.done3app.api.controller.auth;

import lombok.Data;

@Data
public class AuthRequest {
    private String login;
    private String password;
}
