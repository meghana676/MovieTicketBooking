package com.movieticket.controller;

import com.movieticket.exception.MovieTicketException;
import com.movieticket.model.BookedSeat;
import com.movieticket.model.Booking;
import com.movieticket.model.Movie;
import com.movieticket.model.Payment;
import com.movieticket.model.Seat;
import com.movieticket.model.Show;
import com.movieticket.model.Theatre;
import com.movieticket.model.User;
import com.movieticket.service.BookedSeatService;
import com.movieticket.service.BookingService;
import com.movieticket.service.MovieService;
import com.movieticket.service.PaymentService;
import com.movieticket.service.SeatService;
import com.movieticket.service.ShowService;
import com.movieticket.service.TheatreService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class CustomerController {

    private static final Logger LOGGER =
            Logger.getLogger(CustomerController.class.getName());

    private final MovieService movieService;
    private final TheatreService theatreService;
    private final ShowService showService;
    private final SeatService seatService;
    private final BookingService bookingService;
    private final PaymentService paymentService;
    private final BookedSeatService bookedSeatService;
    private final Scanner scanner;
    private final User user;

    public CustomerController(User user) {

        this.user = user;

        this.movieService = new MovieService();
        this.theatreService = new TheatreService();
        this.showService = new ShowService();
        this.seatService = new SeatService();
        this.bookingService = new BookingService();
        this.paymentService = new PaymentService();
        this.bookedSeatService = new BookedSeatService();

        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {

        while (true) {

            LOGGER.info("");
            LOGGER.info("===== CUSTOMER MENU =====");
            LOGGER.info("1. View Movies");
            LOGGER.info("2. View Shows");
            LOGGER.info("3. Book Tickets");
            LOGGER.info("4. Back");
            LOGGER.info("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    viewMovies();
                    break;

                case 2:
                    viewShows();
                    break;

                case 3:
                    bookTickets();
                    break;

                case 4:
                    return;

                default:
                    LOGGER.warning("Invalid choice.");
            }
        }
    }

    private void viewMovies() {

        List<Movie> movies = movieService.getAllMovies();

        LOGGER.info("");
        LOGGER.info("===== AVAILABLE MOVIES =====");

        if (movies.isEmpty()) {
            LOGGER.info("No movies available.");
            return;
        }

        for (Movie movie : movies) {

            LOGGER.info(
                    movie.getTitle() + " | " +
                            movie.getLanguage() + " | " +
                            movie.getGenre() + " | " +
                            movie.getDuration() + " minutes"
            );
        }
    }

    private void viewShows() {

        List<Show> shows = showService.getAllShows();
        List<Movie> movies = movieService.getAllMovies();
        List<Theatre> theatres = theatreService.getAllTheatres();

        LOGGER.info("");
        LOGGER.info("===== AVAILABLE SHOWS =====");

        if (shows.isEmpty()) {
            LOGGER.info("No shows available.");
            return;
        }

        for (Show show : shows) {

            String movieName = "Unknown Movie";
            String theatreName = "Unknown Theatre";

            for (Movie movie : movies) {

                if (movie.getMovieId() == show.getMovieId()) {
                    movieName = movie.getTitle();
                    break;
                }
            }

            for (Theatre theatre : theatres) {

                if (theatre.getTheatreId() == show.getTheatreId()) {
                    theatreName = theatre.getName();
                    break;
                }
            }

            LOGGER.info(
                    movieName + " | " +
                            theatreName + " | " +
                            show.getShowDate() + " | " +
                            show.getStartTime() + " - " +
                            show.getEndTime()
            );
        }
    }

    private void bookTickets() {

        LOGGER.info("");
        LOGGER.info("Enter movie name:");

        String movieName = scanner.nextLine();

        if (movieName == null || movieName.trim().isEmpty()) {
            LOGGER.warning("Movie name cannot be empty.");
            return;
        }

        List<Movie> movies =
                movieService.searchMoviesByName(movieName);

        if (movies.isEmpty()) {
            LOGGER.warning("No movie found.");
            return;
        }

        LOGGER.info("");
        LOGGER.info("===== MOVIES FOUND =====");

        for (int i = 0; i < movies.size(); i++) {

            Movie movie = movies.get(i);

            LOGGER.info(
                    (i + 1) + ". " +
                            movie.getTitle() + " | " +
                            movie.getLanguage() + " | " +
                            movie.getGenre()
            );
        }

        Movie selectedMovie;

        if (movies.size() == 1) {

            selectedMovie = movies.get(0);

        } else {

            LOGGER.info("Select movie number:");

            int movieChoice = scanner.nextInt();
            scanner.nextLine();

            if (movieChoice <= 0 || movieChoice > movies.size()) {
                LOGGER.warning("Invalid movie selection.");
                return;
            }

            selectedMovie = movies.get(movieChoice - 1);
        }

        LOGGER.info(
                "Selected movie: " + selectedMovie.getTitle()
        );

        List<Theatre> theatres =
                theatreService.getTheatresByMovie(
                        selectedMovie.getMovieId()
                );

        if (theatres.isEmpty()) {
            LOGGER.warning(
                    "No theatres are showing this movie."
            );
            return;
        }

        LOGGER.info("");
        LOGGER.info("===== THEATRES =====");

        for (int i = 0; i < theatres.size(); i++) {

            Theatre theatre = theatres.get(i);

            LOGGER.info(
                    (i + 1) + ". " +
                            theatre.getName() + " | " +
                            theatre.getCity() + " | " +
                            theatre.getAddress()
            );
        }

        LOGGER.info("Enter theatre name:");

        String theatreName = scanner.nextLine();

        Theatre selectedTheatre = null;

        for (Theatre theatre : theatres) {

            if (theatre.getName()
                    .equalsIgnoreCase(theatreName.trim())) {

                selectedTheatre = theatre;
                break;
            }
        }

        if (selectedTheatre == null) {
            LOGGER.warning("Theatre not found.");
            return;
        }

        List<Show> shows =
                showService.getShowsByMovieAndTheatre(
                        selectedMovie.getMovieId(),
                        selectedTheatre.getTheatreId()
                );

        if (shows.isEmpty()) {
            LOGGER.warning(
                    "No shows available at this theatre."
            );
            return;
        }

        LOGGER.info("");
        LOGGER.info("===== AVAILABLE SHOWS =====");

        for (int i = 0; i < shows.size(); i++) {

            Show show = shows.get(i);

            LOGGER.info(
                    (i + 1) + ". " +
                            show.getShowDate() + " | " +
                            show.getStartTime() + " - " +
                            show.getEndTime()
            );
        }

        LOGGER.info("Select show number:");

        int showChoice = scanner.nextInt();
        scanner.nextLine();

        if (showChoice <= 0 || showChoice > shows.size()) {
            LOGGER.warning("Invalid show selection.");
            return;
        }

        Show selectedShow = shows.get(showChoice - 1);

        List<Seat> seats =
                seatService.getSeatsByTheatre(
                        selectedTheatre.getTheatreId()
                );

        if (seats.isEmpty()) {
            LOGGER.warning("No seats found.");
            return;
        }

        LOGGER.info("");
        LOGGER.info("===== AVAILABLE SEATS =====");

        List<Seat> availableSeats = new ArrayList<>();

        for (Seat seat : seats) {

            boolean booked =
                    bookedSeatService.isSeatBookedForShow(
                            seat.getSeatId(),
                            selectedShow.getShowId()
                    );

            if (!booked) {

                availableSeats.add(seat);

                LOGGER.info(
                        seat.getSeatNumber() + " | " +
                                seat.getSeatType() + " | " +
                                seat.getPrice()
                );
            }
        }

        if (availableSeats.isEmpty()) {
            LOGGER.info("No seats are available for this show.");
            return;
        }

        LOGGER.info("Enter number of seats:");

        int numberOfSeats = scanner.nextInt();
        scanner.nextLine();

        if (numberOfSeats <= 0) {
            LOGGER.warning("Invalid number of seats.");
            return;
        }

        if (numberOfSeats > availableSeats.size()) {
            LOGGER.warning(
                    "Requested seats are more than available seats."
            );
            return;
        }

        List<Integer> selectedSeatIds = new ArrayList<>();
        double totalAmount = 0;

        for (int i = 1; i <= numberOfSeats; i++) {

            LOGGER.info("Enter seat number " + i + ":");

            String seatNumber = scanner.nextLine();

            Seat selectedSeat = null;

            for (Seat seat : availableSeats) {

                if (seat.getSeatNumber()
                        .equalsIgnoreCase(seatNumber.trim())) {

                    selectedSeat = seat;
                    break;
                }
            }

            if (selectedSeat == null) {
                LOGGER.warning("Seat is not available.");
                return;
            }

            try {

                boolean seatAlreadyBooked =
                        bookedSeatService.isSeatBookedForShow(
                                selectedSeat.getSeatId(),
                                selectedShow.getShowId()
                        );

                if (seatAlreadyBooked) {

                    throw new MovieTicketException(
                            "Seat " +
                                    selectedSeat.getSeatNumber() +
                                    " is already booked for this show."
                    );
                }

            } catch (MovieTicketException e) {

                LOGGER.warning(e.getMessage());
                return;
            }

            if (selectedSeatIds.contains(
                    selectedSeat.getSeatId())) {

                LOGGER.warning(
                        "Seat selected more than once."
                );

                return;
            }

            selectedSeatIds.add(
                    selectedSeat.getSeatId()
            );

            totalAmount += selectedSeat.getPrice();
        }

        LOGGER.info("Total amount: " + totalAmount);

        Booking booking = new Booking(
                0,
                selectedShow.getShowId(),
                user.getUserId(),
                null,
                totalAmount,
                "PENDING"
        );

        int bookingId =
                bookingService.addBooking(booking);

        if (bookingId <= 0) {

            LOGGER.warning(
                    "Booking could not be created."
            );

            return;
        }

        LOGGER.info(
                "Booking created successfully."
        );

        LOGGER.info("Enter payment method:");

        String paymentMethod = scanner.nextLine();

        // Payment is automatically successful
        String paymentStatus = "SUCCESS";

        Payment payment = new Payment(
                0,
                bookingId,
                totalAmount,
                paymentMethod,
                paymentStatus,
                null
        );

        boolean paymentAdded =
                paymentService.addPayment(payment);

        if (!paymentAdded) {

            LOGGER.warning(
                    "Payment could not be added."
            );

            return;
        }

        LOGGER.info(
                "Payment completed successfully."
        );

        for (int seatId : selectedSeatIds) {

            BookedSeat bookedSeat =
                    new BookedSeat(
                            0,
                            seatId,
                            bookingId
                    );

            boolean seatBooked =
                    bookedSeatService.addBookedSeat(
                            bookedSeat
                    );

            if (!seatBooked) {

                LOGGER.warning(
                        "Could not book selected seat."
                );

                return;
            }
        }

        boolean bookingConfirmed =
                bookingService.updateBookingStatus(
                        bookingId,
                        "CONFIRMED"
                );

        if (bookingConfirmed) {

            LOGGER.info(
                    "All selected seats booked successfully!"
            );

            LOGGER.info("Booking confirmed!");

        } else {

            LOGGER.warning(
                    "Booking could not be confirmed."
            );
        }
    }
}