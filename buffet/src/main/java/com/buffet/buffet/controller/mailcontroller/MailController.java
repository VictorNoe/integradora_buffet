package com.buffet.buffet.controller.mailcontroller;

import com.buffet.buffet.services.mailservice.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mail")
public class MailController {
    @Autowired
    private MailService mailService;
    @GetMapping(value = "/sendMail")
    public ResponseEntity<String> sendMail(){
        mailService.sendMail("20213tn050@utez.edu.mx","Saludos joto","Este es un mensaje para decirte lo gay que eres");
        return ResponseEntity.ok("Correo enviado");
    }
}
