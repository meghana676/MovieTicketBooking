package com.movieticket.controller;

import com.movieticket.model.Booking;
import com.movieticket.model.Movie;
import com.movieticket.model.Show;
import com.movieticket.model.Theatre;
import com.movieticket.model.User;
import com.movieticket.service.BookingService;
import com.movieticket.service.MovieService;
import com.movieticket.service.ShowService;
import com.movieticket.service.TheatreService;
import com.movieticket.service.UserService;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class BookingController {

    private static final Logger LOGGER =
            Logger.getLogger(BookingController.class.getName());

    private final BookingService bookingService;
    private final MovieService movieService;
    private final TheatreService theatreService;
    private final ShowService showService;
    private final UserService userService;
    private final Scanner scanner;

    public BookingController() {

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

        List<Booking> bookings =
                bookingService.getAllBookings();

        LOGGER.info("");
        LOGGER.info("===== BOOKINGS =====");

        if (bookings.isEmpty()) {
            LOGGER.info("No bookings available.");
            return;
        }

        List<Movie> movies =
                movieService.getAllMovies();

        List<Theatre> theatres =
                theatreService.getAllTheatres();

        List<Show> shows =
                showService.getAllShows();

        for (Booking booking : bookings) {

            Show selectedShow = null;

            for (Show show : shows) {

                if (show.getShowId() ==
                        booking.getShowId()) {

                    selectedShow = show;
                    break;
                }
            }

            User customer =
                    userService.findUserById(
                            booking.getUserId()
                    );

            String customerName = "Unknown Customer";
            String movieName = "Unknown Movie";
            String theatreName = "Unknown Theatre";

            if (customer != null) {
                customerName = customer.getName();
            }

            String showDate = "Unknown Date";
            String startTime = "Unknown Time";
            String endTime = "Unknown Time";

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
                    "Booking Date: " +
                            booking.getBookingDate()
            );
            LOGGER.info(
                    "Total Amount: " +
                            booking.getTotalAmount()
            );
            LOGGER.info(
                    "Booking Status: " +
                            booking.getBookingStatus()
            );
            LOGGER.info(
                    "-----------------------------"
            );
        }
    }
}