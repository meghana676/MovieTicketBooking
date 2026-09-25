package com.movieticket.controller;

import com.movieticket.model.Booking;
import com.movieticket.service.BookingService;

import java.util.List;
import java.util.Scanner;

public class BookingController {

    private final BookingService bookingService;
    private final Scanner scanner;

    public BookingController() {
        bookingService = new BookingService();
        scanner = new Scanner(System.in);
    }

    public void showMenu() {

        while (true) {

            System.out.println();
            System.out.println("===== BOOKING MANAGEMENT =====");
            System.out.println("1. View Bookings");
            System.out.println("2. Back");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    viewBookings();
                    break;

                case 2:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void viewBookings() {

        List<Booking> bookings = bookingService.getAllBookings();

        System.out.println();
        System.out.println("===== BOOKINGS =====");

        for (Booking booking : bookings) {

            System.out.println(
                    booking.getBookingId() + " | " +
                            booking.getShowId() + " | " +
                            booking.getUserId() + " | " +
                            booking.getBookingDate() + " | " +
                            booking.getTotalAmount() + " | " +
                            booking.getBookingStatus()
            );
        }
    }
}