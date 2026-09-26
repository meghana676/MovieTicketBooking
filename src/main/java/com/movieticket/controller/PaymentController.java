package com.movieticket.controller;

import com.movieticket.model.Payment;
import com.movieticket.service.PaymentService;

import java.util.Scanner;
import java.util.logging.Logger;

public class PaymentController {

    private static final Logger LOGGER =
            Logger.getLogger(PaymentController.class.getName());

    private final PaymentService paymentService;
    private final Scanner scanner;

    public PaymentController() {
        paymentService = new PaymentService();
        scanner = new Scanner(System.in);
    }

    public void showMenu() {

        while (true) {

            LOGGER.info("");
            LOGGER.info("===== PAYMENT MANAGEMENT =====");
            LOGGER.info("1. Add Payment");
            LOGGER.info("2. Back");
            LOGGER.info("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    addPayment();
                    break;

                case 2:
                    return;

                default:
                    LOGGER.warning("Invalid choice.");
            }
        }
    }

    private void addPayment() {

        LOGGER.info("Enter booking ID: ");
        int bookingId = scanner.nextInt();
        scanner.nextLine();

        LOGGER.info("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        LOGGER.info("Enter payment method: ");
        String paymentMethod = scanner.nextLine();

        LOGGER.info("Enter payment status: ");
        String paymentStatus = scanner.nextLine();

        Payment payment = new Payment(
                0,
                bookingId,
                amount,
                paymentMethod,
                paymentStatus,
                null
        );

        if (paymentService.addPayment(payment)) {

            LOGGER.info("Payment added successfully!");

        } else {

            LOGGER.warning("Payment could not be added.");
        }
    }
}