package com.movieticket.service;

import com.movieticket.dao.PaymentDAO;
import com.movieticket.model.Payment;

public class PaymentService {

    private final PaymentDAO paymentDAO;

    public PaymentService() {
        this.paymentDAO = new PaymentDAO();
    }

    public boolean addPayment(Payment payment) {
        return paymentDAO.addPayment(payment);
    }
}
