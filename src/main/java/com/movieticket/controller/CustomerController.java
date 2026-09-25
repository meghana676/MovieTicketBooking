package com.movieticket.controller;

import com.movieticket.model.Show;
import com.movieticket.model.User;
import com.movieticket.service.ShowService;
import com.movieticket.model.Booking;
import com.movieticket.service.BookingService;
import com.movieticket.model.BookedSeat;
import com.movieticket.service.BookedSeatService;

import java.util.List;
import java.util.Scanner;

public class CustomerController {

    private final ShowService showService;
    private final BookingService bookingService;
    private final BookedSeatService bookedSeatService;
    private final Scanner scanner;
    private final User user;

    public CustomerController(User user) {
        this.user = user;
        this.showService = new ShowService();
        this.bookingService = new BookingService();
        this.bookedSeatService = new BookedSeatService();
        this.scanner = new Scanner(System.in);
    }
    public void showMenu() {

        while (true) {

            System.out.println();
            System.out.println("===== CUSTOMER MENU =====");
            System.out.println("1. View Shows");
            System.out.println("2. Book Show");
            System.out.println("3. Back");
            System.out.print("Enter your choice: ");

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
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void viewShows() {

        List<Show> shows = showService.getAllShows();

        System.out.println();
        System.out.println("===== AVAILABLE SHOWS =====");

        for (Show show : shows) {

            System.out.println(
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

        System.out.print("Enter show ID: ");
        int showId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter total amount: ");
        double totalAmount = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter seat ID: ");
        int seatId = scanner.nextInt();
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
            System.out.println("Booking added successfully!");
            System.out.println("Booking ID: " + bookingId);
        } else {
            System.out.println("Booking could not be added.");
        }
    }
}