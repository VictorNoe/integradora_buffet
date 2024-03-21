package com.buffet.buffet.controller.useraccount;

import com.buffet.buffet.controller.useraccount.useraccountdto.UserDTO;
import com.buffet.buffet.model.authrequest.AuthRequest;
import com.buffet.buffet.model.updatestatus.UpdateStatus;
import com.buffet.buffet.services.useraccount.UserAccountServices;
import com.buffet.buffet.utils.CustomResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/userAccount")
@CrossOrigin(origins = {"http://localhost:5173/"})
public class UserAccountController {
    private final UserAccountServices userAccountService;
    @Autowired

    public UserAccountController(UserAccountServices userAccountService) {
        this.userAccountService = userAccountService;
    }

    @PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity<CustomResponse> register(@Valid @RequestBody UserDTO user) {
        return userAccountService.registerUser(user);
    }
    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<CustomResponse> login(@RequestBody AuthRequest authRequest) {
        return userAccountService.login(authRequest);
    }

    @PutMapping(value = "/updateStatus", produces = "application/json")
    public ResponseEntity<CustomResponse> updateStatus(@Valid @RequestBody UpdateStatus updateStatus){
        return userAccountService.updateStatus(updateStatus);
    }
    @GetMapping(value = "/getClients", produces = "application/json")
    public ResponseEntity<CustomResponse> getAllClients( ){
        return this.userAccountService.getAllClients();
    }
    @PostMapping(value = "/getInfoUser", produces = "application/json")
    public ResponseEntity<CustomResponse> getInfoUser(@RequestParam String email ){
        return this.userAccountService.getInfoUser(email);
    }
    @GetMapping(value = "/getCountClients",produces = "application/json")
    public ResponseEntity<CustomResponse> getCountClients(){
        return this.userAccountService.getCountClients();
    }
}
