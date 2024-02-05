package com.buffet.buffet.model.AuthRequest;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}