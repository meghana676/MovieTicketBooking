package com.movieticket.controller;

import java.util.Scanner;
import com.movieticket.controller.MovieController;

public class AdminController {

    public void showMenu() {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println();
            System.out.println("===== ADMIN MENU =====");
            System.out.println("1. Manage Movies");
            System.out.println("2. Manage Theatres");
            System.out.println("3. Manage Seats");
            System.out.println("4. Manage Shows");
            System.out.println("5. View Bookings");
            System.out.println("6. View Payments");
            System.out.println("7. Logout");

            System.out.print("Enter your choice: ");

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
                    System.out.println("Logging out...");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}