package com.buffet.buffet.controller.userAccount;

import com.buffet.buffet.controller.userAccount.userAccountDTO.UserDTO;
import com.buffet.buffet.model.AuthRequest.AuthRequest;
import com.buffet.buffet.services.useraccount.UserAccountServices;
import com.buffet.buffet.utils.CustomResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/userAccount")
public class UserAccountController {
    @Autowired
    private UserAccountServices userAccountService;
    @PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity<CustomResponse> register(@Valid @RequestBody UserDTO user) {
        return userAccountService.registerUser(user);
    }
    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<CustomResponse> login(@RequestBody AuthRequest authRequest) {
        return userAccountService.login(authRequest);
    }
}
