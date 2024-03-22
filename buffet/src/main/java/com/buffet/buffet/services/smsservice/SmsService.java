package com.buffet.buffet.services.smsservice;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SmsService {
    @Value("${TWILIO_ACCOUNT_SID}")
    String sid;
    @Value("${TWILIO_AUTH_TOKEN}")
    String token;
    @Value("${TWILIO_NUMBER}")
    String phone;
    Random random = new Random();
    public ResponseEntity<String> authSMS(){
        try {
            Twilio.init(sid,token);
            int randomNumber = 10000 + random.nextInt(90000);
            String auth02= String.valueOf(randomNumber);
            String phoneVerified = "+527775486764";
            Message.creator(
                    new PhoneNumber(phoneVerified),
                    new PhoneNumber(phone),
                    "Bienvenido a Arlindo Buffet! Ingresa este codigo en la aplicacion para verificar tu cuenta" + auth02
            ).create();
            return new ResponseEntity<String>("Hola", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
        }

    }}
