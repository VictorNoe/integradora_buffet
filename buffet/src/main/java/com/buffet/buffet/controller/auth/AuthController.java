package com.buffet.buffet.controller.auth;

import com.buffet.buffet.model.authrequest.AuthRequest;
import com.buffet.buffet.services.auth.AuthService;
import com.buffet.buffet.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {"http://localhost:5173/"})
public class AuthController {
    private final AuthService service;
    @Autowired
    public AuthController(AuthService service) {
        this.service = service;
    }
    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<CustomResponse> login(@RequestBody AuthRequest authRequest) {
        return service.login(authRequest);
    }

}
