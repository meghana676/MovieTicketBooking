package com.movieticket.controller;

import com.movieticket.model.Booking;
import com.movieticket.service.BookingService;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class BookingController {

    private static final Logger LOGGER =
            Logger.getLogger(BookingController.class.getName());

    private final BookingService bookingService;
    private final Scanner scanner;

    public BookingController() {
        bookingService = new BookingService();
        scanner = new Scanner(System.in);
    }

    public void showMenu() {

        while (true) {

            LOGGER.info("");
            LOGGER.info("===== BOOKING MANAGEMENT =====");
            LOGGER.info("1. View Bookings");
            LOGGER.info("2. Back");
            LOGGER.info("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    viewBookings();
                    break;

                case 2:
                    return;

                default:
                    LOGGER.warning("Invalid choice.");
            }
        }
    }

    private void viewBookings() {

        List<Booking> bookings = bookingService.getAllBookings();

        LOGGER.info("");
        LOGGER.info("===== BOOKINGS =====");

        for (Booking booking : bookings) {

            LOGGER.info(
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