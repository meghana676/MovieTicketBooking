package com.movieticket;

import com.movieticket.dao.PaymentDAO;
import com.movieticket.model.Payment;

public class PaymentTest {

    public static void main(String[] args) {

        Payment payment = new Payment(
                0,
                1,
                300.00,
                "CARD",
                "SUCCESS",
                null
        );

        PaymentDAO paymentDAO = new PaymentDAO();

        boolean result = paymentDAO.addPayment(payment);

        if (result) {
            System.out.println("Payment added successfully!");
        } else {
            System.out.println("Payment not added.");
        }
    }
}
