package com.movieticket;

import com.movieticket.model.Payment;
import com.movieticket.service.PaymentService;

public class PaymentServiceTest {

    public static void main(String[] args) {

        PaymentService paymentService = new PaymentService();

        Payment payment = new Payment(
                0,
                1,
                300.00,
                "CARD",
                "SUCCESS",
                null
        );

        boolean result = paymentService.addPayment(payment);

        if (result) {
            System.out.println("Payment added through Service!");
        } else {
            System.out.println("Payment not added.");
        }
    }
}
