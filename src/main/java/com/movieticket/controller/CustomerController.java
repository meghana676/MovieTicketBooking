package com.movieticket.controller;

import com.movieticket.model.BookedSeat;
import com.movieticket.model.Booking;
import com.movieticket.model.Payment;
import com.movieticket.model.Show;
import com.movieticket.model.User;
import com.movieticket.service.BookedSeatService;
import com.movieticket.service.BookingService;
import com.movieticket.service.PaymentService;
import com.movieticket.service.ShowService;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class CustomerController {

    private static final Logger LOGGER =
            Logger.getLogger(CustomerController.class.getName());

    private final ShowService showService;
    private final BookingService bookingService;
    private final PaymentService paymentService;
    private final BookedSeatService bookedSeatService;
    private final Scanner scanner;
    private final User user;

    public CustomerController(User user) {
        this.user = user;
        this.showService = new ShowService();
        this.bookingService = new BookingService();
        this.paymentService = new PaymentService();
        this.bookedSeatService = new BookedSeatService();
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
        int seatId = scanner.nextInt();
        scanner.nextLine();

        boolean seatAlreadyBooked =
                bookedSeatService.isSeatBookedForShow(seatId, showId);

        if (seatAlreadyBooked) {
            LOGGER.warning("Seat is already booked for this show.");
            return;
        }

        Booking booking = new Booking(
                0,
                showId,
                user.getUserId(),
                null,
                totalAmount,
                "PENDING"
        );

        int bookingId = bookingService.addBooking(booking);

        if (bookingId <= 0) {
            LOGGER.warning("Booking could not be created.");
            return;
        }

        LOGGER.info("Booking created successfully!");
        LOGGER.info("Booking ID: " + bookingId);

        LOGGER.info("Enter payment method: ");
        String paymentMethod = scanner.nextLine();

        LOGGER.info("Enter payment status (SUCCESS/FAILED): ");
        String paymentStatus = scanner.nextLine();

        Payment payment = new Payment(
                0,
                bookingId,
                totalAmount,
                paymentMethod,
                paymentStatus,
                null
        );

        boolean paymentAdded = paymentService.addPayment(payment);

        if (!paymentAdded) {
            LOGGER.warning("Payment could not be added.");
            return;
        }

        LOGGER.info("Payment added successfully!");

        if ("SUCCESS".equalsIgnoreCase(paymentStatus)) {

            BookedSeat bookedSeat = new BookedSeat(
                    0,
                    seatId,
                    bookingId
            );

            boolean seatBooked =
                    bookedSeatService.addBookedSeat(bookedSeat);

            if (!seatBooked) {
                LOGGER.warning("Seat could not be booked.");
                return;
            }

            boolean bookingConfirmed =
                    bookingService.updateBookingStatus(
                            bookingId,
                            "CONFIRMED"
                    );

            if (bookingConfirmed) {
                LOGGER.info("Payment successful!");
                LOGGER.info("Seat booked successfully!");
                LOGGER.info("Booking confirmed!");
            } else {
                LOGGER.warning("Booking could not be confirmed.");
            }

        } else {

            LOGGER.warning("Payment failed.");
            LOGGER.info("Booking remains PENDING.");
            LOGGER.info("Seat was not booked.");
        }
    }
}