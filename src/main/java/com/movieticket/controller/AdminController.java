package com.movieticket.controller;

import java.util.Scanner;
import java.util.logging.Logger;

public class AdminController {

    private static final Logger LOGGER =
            Logger.getLogger(AdminController.class.getName());

    public void showMenu() {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            LOGGER.info("");
            LOGGER.info("===== ADMIN MENU =====");
            LOGGER.info("1. Manage Movies");
            LOGGER.info("2. Manage Theatres");
            LOGGER.info("3. Manage Seats");
            LOGGER.info("4. Manage Shows");
            LOGGER.info("5. View Bookings");
            LOGGER.info("6. View Payments");
            LOGGER.info("7. Logout");

            LOGGER.info("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    MovieController movieController = new MovieController();
                    movieController.showMenu();
                    break;

                case 2:
                    TheatreController theatreController = new TheatreController();
                    theatreController.showMenu();
                    break;

                case 3:
                    SeatController seatController = new SeatController();
                    seatController.showMenu();
                    break;

                case 4:
                    ShowController showController = new ShowController();
                    showController.showMenu();
                    break;

                case 5:
                    BookingController bookingController = new BookingController();
                    bookingController.showMenu();
                    break;

                case 6:
                    PaymentController paymentController = new PaymentController();
                    paymentController.showMenu();
                    break;

                case 7:
                    LOGGER.info("Logging out...");
                    return;

                default:
                    LOGGER.warning("Invalid choice.");
            }
        }
    }
}