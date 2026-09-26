package com.movieticket;

import com.movieticket.dao.PaymentDAO;
import com.movieticket.model.Payment;
import com.movieticket.service.PaymentService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PaymentServiceTest {

    @Test
    void testAddPaymentWithValidData() {

        PaymentDAO paymentDAO = mock(PaymentDAO.class);

        Payment payment = new Payment(
                0,
                1,
                300,
                "UPI",
                "SUCCESS",
                null
        );

        when(paymentDAO.addPayment(payment))
                .thenReturn(true);

        PaymentService paymentService =
                new PaymentService(paymentDAO);

        boolean result =
                paymentService.addPayment(payment);

        assertTrue(result);

        verify(paymentDAO).addPayment(payment);
    }


    @Test
    void testAddPaymentWithInvalidBookingId() {

        PaymentDAO paymentDAO = mock(PaymentDAO.class);

        Payment payment = new Payment(
                0,
                0,
                300,
                "UPI",
                "SUCCESS",
                null
        );

        PaymentService paymentService =
                new PaymentService(paymentDAO);

        boolean result =
                paymentService.addPayment(payment);

        assertFalse(result);

        verify(paymentDAO, never())
                .addPayment(payment);
    }


    @Test
    void testAddPaymentWithInvalidAmount() {

        PaymentDAO paymentDAO = mock(PaymentDAO.class);

        Payment payment = new Payment(
                0,
                1,
                0,
                "UPI",
                "SUCCESS",
                null
        );

        PaymentService paymentService =
                new PaymentService(paymentDAO);

        boolean result =
                paymentService.addPayment(payment);

        assertFalse(result);

        verify(paymentDAO, never())
                .addPayment(payment);
    }


    @Test
    void testAddPaymentWithEmptyPaymentMethod() {

        PaymentDAO paymentDAO = mock(PaymentDAO.class);

        Payment payment = new Payment(
                0,
                1,
                300,
                "",
                "SUCCESS",
                null
        );

        PaymentService paymentService =
                new PaymentService(paymentDAO);

        boolean result =
                paymentService.addPayment(payment);

        assertFalse(result);

        verify(paymentDAO, never())
                .addPayment(payment);
    }


    @Test
    void testAddPaymentWithEmptyPaymentStatus() {

        PaymentDAO paymentDAO = mock(PaymentDAO.class);

        Payment payment = new Payment(
                0,
                1,
                300,
                "UPI",
                "",
                null
        );

        PaymentService paymentService =
                new PaymentService(paymentDAO);

        boolean result =
                paymentService.addPayment(payment);

        assertFalse(result);

        verify(paymentDAO, never())
                .addPayment(payment);
    }
}