package com.buffet.buffet.controller.paymentcontroller;

import com.buffet.buffet.controller.paymentcontroller.paymentdto.PaymentDto;
import com.buffet.buffet.services.payment.PaymentService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin(origins = {"http://localhost:5173/"})
@RequiredArgsConstructor
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    public static final String successUrl = "http://localhost:8080/payment/success";
    public static final String cancelUrl = "http://localhost:8080/payment/cancel";

    @PostMapping(value = "/pay", produces = "application/json")
    public String payment(@RequestBody PaymentDto theOrder) {
        try {
            Payment thePayment = paymentService.createPayment(theOrder.getPrice());
            for (Links links: thePayment.getLinks()){
                if(links.getRel().equals("approval_url")){
                    System.out.println(links.getHref());
                    return links.getHref();
                }
            }
        }
        catch (PayPalRESTException payPalRESTException) {
            log.error(payPalRESTException.getMessage());
        }
        return null;
    }

    @GetMapping("/success")
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = paymentService.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                return "success";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/";
    }

    @GetMapping(value = "/cancel", produces = "application/json")
    public ResponseEntity<String> paymentCancel(@RequestParam("paymentId")String paymentId,
                                                @RequestParam("payerId")String payerId){
        try {
            Payment payment = paymentService.executePayment(paymentId,payerId);
            if (payment.getState().equals("approved")){
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }catch (PayPalRESTException e){
            log.error(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
