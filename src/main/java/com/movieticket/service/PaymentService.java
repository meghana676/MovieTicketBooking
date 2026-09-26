package com.movieticket.service;

import com.movieticket.dao.PaymentDAO;
import com.movieticket.model.Payment;

public class PaymentService {

    private final PaymentDAO paymentDAO;

    public PaymentService() {
        this.paymentDAO = new PaymentDAO();
    }

    public PaymentService(PaymentDAO paymentDAO) {
        this.paymentDAO = paymentDAO;
    }

    public boolean addPayment(Payment payment) {

        if (payment.getBookingId() <= 0) {
            return false;
        }

        if (payment.getAmount() <= 0) {
            return false;
        }

        if (payment.getPaymentMethod() == null ||
                payment.getPaymentMethod().trim().isEmpty()) {
            return false;
        }

        if (payment.getPaymentStatus() == null ||
                payment.getPaymentStatus().trim().isEmpty()) {
            return false;
        }

        return paymentDAO.addPayment(payment);
    }
}
