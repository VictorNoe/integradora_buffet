package com.buffet.buffet.controller.userAccount;

import com.buffet.buffet.controller.userAccount.userAccountDTO.UserDTO;
import com.buffet.buffet.model.AuthRequest.AuthRequest;
import com.buffet.buffet.model.UpdateStatus.UpdateStatus;
import com.buffet.buffet.services.useraccount.UserAccountServices;
import com.buffet.buffet.utils.CustomResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/userAccount")
@CrossOrigin(origins = {"*"})
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
    @PostMapping(value = "/registerWorker", produces = "application/json")
    public ResponseEntity<CustomResponse> registerWorker(@Valid @RequestBody UserDTO user) {
        return userAccountService.registerWorker(user);
    }
    @PutMapping(value = "/updateStatus", produces = "application/json")
    public ResponseEntity<CustomResponse> updateStatus(@Valid @RequestBody UpdateStatus updateStatus){
        System.out.println(updateStatus);
        return userAccountService.updateStatus(updateStatus);
    }
    @GetMapping(value = "/getWorkers", produces = "application/json")
    public ResponseEntity<CustomResponse> getAllWorkers( ){
        return this.userAccountService.getAllWorkers();
    }
    @GetMapping(value = "/getClients", produces = "application/json")
    public ResponseEntity<CustomResponse> getAllClients( ){
        return this.userAccountService.getAllClients();
    }
    @PostMapping(value = "/getInfoUser", produces = "application/json")
    public ResponseEntity<CustomResponse> getAllClients(@RequestBody String email ){
        return this.userAccountService.getInfoUser(email);
    }
}
