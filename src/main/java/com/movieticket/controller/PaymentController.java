package com.movieticket.controller;

import com.movieticket.model.Booking;
import com.movieticket.model.Movie;
import com.movieticket.model.Payment;
import com.movieticket.model.Show;
import com.movieticket.model.Theatre;
import com.movieticket.model.User;
import com.movieticket.service.BookingService;
import com.movieticket.service.MovieService;
import com.movieticket.service.PaymentService;
import com.movieticket.service.ShowService;
import com.movieticket.service.TheatreService;
import com.movieticket.service.UserService;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class PaymentController {

    private static final Logger LOGGER =
            Logger.getLogger(PaymentController.class.getName());

    private final PaymentService paymentService;
    private final BookingService bookingService;
    private final MovieService movieService;
    private final TheatreService theatreService;
    private final ShowService showService;
    private final UserService userService;
    private final Scanner scanner;

    public PaymentController() {

        paymentService = new PaymentService();
        bookingService = new BookingService();
        movieService = new MovieService();
        theatreService = new TheatreService();
        showService = new ShowService();
        userService = new UserService();

        scanner = new Scanner(System.in);
    }

    public void showMenu() {

        while (true) {

            LOGGER.info("");
            LOGGER.info("===== PAYMENT MANAGEMENT =====");
            LOGGER.info("1. View Payments");
            LOGGER.info("2. Back");
            LOGGER.info("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    viewPayments();
                    break;

                case 2:
                    return;

                default:
                    LOGGER.warning("Invalid choice.");
            }
        }
    }

    private void viewPayments() {

        List<Payment> payments =
                paymentService.getAllPayments();

        LOGGER.info("");
        LOGGER.info("===== PAYMENTS =====");

        if (payments.isEmpty()) {
            LOGGER.info("No payments available.");
            return;
        }

        List<Booking> bookings =
                bookingService.getAllBookings();

        List<Show> shows =
                showService.getAllShows();

        List<Movie> movies =
                movieService.getAllMovies();

        List<Theatre> theatres =
                theatreService.getAllTheatres();

        for (Payment payment : payments) {

            Booking selectedBooking = null;

            for (Booking booking : bookings) {

                if (booking.getBookingId() ==
                        payment.getBookingId()) {

                    selectedBooking = booking;
                    break;
                }
            }

            String customerName = "Unknown Customer";
            String movieName = "Unknown Movie";
            String theatreName = "Unknown Theatre";
            String showDate = "Unknown Date";
            String startTime = "Unknown Time";
            String endTime = "Unknown Time";

            if (selectedBooking != null) {

                User customer =
                        userService.findUserById(
                                selectedBooking.getUserId()
                        );

                if (customer != null) {
                    customerName = customer.getName();
                }

                Show selectedShow = null;

                for (Show show : shows) {

                    if (show.getShowId() ==
                            selectedBooking.getShowId()) {

                        selectedShow = show;
                        break;
                    }
                }

                if (selectedShow != null) {

                    showDate =
                            String.valueOf(
                                    selectedShow.getShowDate()
                            );

                    startTime =
                            String.valueOf(
                                    selectedShow.getStartTime()
                            );

                    endTime =
                            String.valueOf(
                                    selectedShow.getEndTime()
                            );

                    for (Movie movie : movies) {

                        if (movie.getMovieId() ==
                                selectedShow.getMovieId()) {

                            movieName =
                                    movie.getTitle();

                            break;
                        }
                    }

                    for (Theatre theatre : theatres) {

                        if (theatre.getTheatreId() ==
                                selectedShow.getTheatreId()) {

                            theatreName =
                                    theatre.getName();

                            break;
                        }
                    }
                }
            }

            LOGGER.info("");
            LOGGER.info(
                    "Customer: " + customerName
            );
            LOGGER.info(
                    "Movie: " + movieName
            );
            LOGGER.info(
                    "Theatre: " + theatreName
            );
            LOGGER.info(
                    "Show Date: " + showDate
            );
            LOGGER.info(
                    "Time: " +
                            startTime +
                            " - " +
                            endTime
            );
            LOGGER.info(
                    "Amount: " +
                            payment.getAmount()
            );
            LOGGER.info(
                    "Payment Method: " +
                            payment.getPaymentMethod()
            );
            LOGGER.info(
                    "Payment Status: " +
                            payment.getPaymentStatus()
            );
            LOGGER.info(
                    "Payment Date: " +
                            payment.getPaymentDate()
            );
            LOGGER.info(
                    "-----------------------------"
            );
        }
    }
}