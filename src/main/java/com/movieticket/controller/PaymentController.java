package com.movieticket.controller;

import com.movieticket.model.Payment;
import com.movieticket.service.PaymentService;

import java.util.Scanner;

public class PaymentController {

    private final PaymentService paymentService;
    private final Scanner scanner;

    public PaymentController() {
        paymentService = new PaymentService();
        scanner = new Scanner(System.in);
    }

    public void showMenu() {

        while (true) {

            System.out.println();
            System.out.println("===== PAYMENT MANAGEMENT =====");
            System.out.println("1. Add Payment");
            System.out.println("2. Back");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    addPayment();
                    break;

                case 2:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void addPayment() {

        System.out.print("Enter booking ID: ");
        int bookingId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter payment method: ");
        String paymentMethod = scanner.nextLine();

        System.out.print("Enter payment status: ");
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
            System.out.println("Payment added successfully!");
        } else {
            System.out.println("Payment could not be added.");
        }
    }
}