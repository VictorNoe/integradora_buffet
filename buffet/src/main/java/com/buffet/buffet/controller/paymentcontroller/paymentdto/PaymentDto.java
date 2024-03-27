package com.buffet.buffet.controller.paymentcontroller.paymentdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentDto {
    private double price = 50;
    private String currency = "USD";
    private String method ="Paypal";
    private String intent = "SALE";
    private String description = "Pay";
}
