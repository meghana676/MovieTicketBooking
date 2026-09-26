package com.movieticket.controller;

import com.movieticket.model.Show;
import com.movieticket.model.User;
import com.movieticket.service.ShowService;
import com.movieticket.model.Booking;
import com.movieticket.service.BookingService;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class CustomerController {

    private static final Logger LOGGER =
            Logger.getLogger(CustomerController.class.getName());

    private final ShowService showService;
    private final BookingService bookingService;
    private final Scanner scanner;
    private final User user;

    public CustomerController(User user) {
        this.user = user;
        this.showService = new ShowService();
        this.bookingService = new BookingService();
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {

        while (true) {

            LOGGER.info("");
            LOGGER.info("===== CUSTOMER MENU =====");
            LOGGER.info("1. View Shows");
            LOGGER.info("2. Book Show");
            LOGGER.info("3. Back");
            LOGGER.info("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    viewShows();
                    break;

                case 2:
                    bookShow();
                    break;

                case 3:
                    return;

                default:
                    LOGGER.warning("Invalid choice.");
            }
        }
    }

    private void viewShows() {

        List<Show> shows = showService.getAllShows();

        LOGGER.info("");
        LOGGER.info("===== AVAILABLE SHOWS =====");

        for (Show show : shows) {

            LOGGER.info(
                    show.getShowId() + " | " +
                            show.getTheatreId() + " | " +
                            show.getMovieId() + " | " +
                            show.getShowDate() + " | " +
                            show.getStartTime() + " | " +
                            show.getEndTime()
            );
        }
    }

    private void bookShow() {

        LOGGER.info("Enter show ID: ");
        int showId = scanner.nextInt();
        scanner.nextLine();

        LOGGER.info("Enter total amount: ");
        double totalAmount = scanner.nextDouble();
        scanner.nextLine();

        LOGGER.info("Enter seat ID: ");
        scanner.nextInt();
        scanner.nextLine();

        Booking booking = new Booking(
                0,
                showId,
                user.getUserId(),
                null,
                totalAmount,
                "CONFIRMED"
        );

        int bookingId = bookingService.addBooking(booking);

        if (bookingId > 0) {

            LOGGER.info("Booking added successfully!");
            LOGGER.info("Booking ID: " + bookingId);

        } else {

            LOGGER.warning("Booking could not be added.");
        }
    }
}