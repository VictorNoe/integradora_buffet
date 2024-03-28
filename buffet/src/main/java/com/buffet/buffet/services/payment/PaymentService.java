package com.buffet.buffet.services.payment;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final APIContext apiContext;
    public Payment createPayment(Double total) throws PayPalRESTException {
        Amount theAmount = new Amount();
        theAmount.setCurrency("USD");
        total = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP).doubleValue();
        theAmount.setTotal(String.format("%.2f", total));

        Transaction transaction = new Transaction();
        transaction.setDescription("Pago realizado");
        transaction.setAmount(theAmount);

        List<Transaction> theTransactions = new ArrayList<>();
        theTransactions.add(transaction);

        Payer thePayer = new Payer();
        thePayer.setPaymentMethod("Paypal");

        Payment thePayment = new Payment();
        thePayment.setIntent("sale");
        thePayment.setTransactions(theTransactions);
        thePayment.setPayer(thePayer);

        RedirectUrls theRedirectUrls = new RedirectUrls();
        theRedirectUrls.setCancelUrl("http://localhost:5173/cancelarPago");
        theRedirectUrls.setReturnUrl("http://localhost:5173/s");
        thePayment.setRedirectUrls(theRedirectUrls);

        return thePayment.create(apiContext);
    }
    public Payment executePayment(String paymentId,String payerId) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);

        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);

        return payment.execute(apiContext,paymentExecution);
    }
}
