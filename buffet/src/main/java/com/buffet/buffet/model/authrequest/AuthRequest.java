package com.buffet.buffet.model.authrequest;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AuthRequest {
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "El correo no debe contener caracteres especiales")
    private String email;
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "La contraseña no debe contener caracteres especiales")
    private String password;
}