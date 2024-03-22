package com.buffet.buffet.controller.smscontroller;

import com.buffet.buffet.services.smsservice.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sms")
public class SmsController {

    @Autowired
    private SmsService smsService;
    @GetMapping(value = "/authSMS")
    public ResponseEntity<String> authSMS(){
        return this.smsService.authSMS();
    }
}
