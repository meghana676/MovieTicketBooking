package com.movieticket.controller;

import com.movieticket.model.Theatre;
import com.movieticket.service.TheatreService;

import java.util.List;
import java.util.Scanner;

public class TheatreController {

    private final TheatreService theatreService;
    private final Scanner scanner;

    public TheatreController() {
        theatreService = new TheatreService();
        scanner = new Scanner(System.in);
    }

    public void showMenu() {

        while (true) {

            System.out.println();
            System.out.println("===== THEATRE MANAGEMENT =====");
            System.out.println("1. Add Theatre");
            System.out.println("2. View Theatres");
            System.out.println("3. Update Theatre");
            System.out.println("4. Delete Theatre");
            System.out.println("5. Back");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    addTheatre();
                    break;

                case 2:
                    viewTheatres();
                    break;

                case 3:
                    updateTheatre();
                    break;

                case 4:
                    deleteTheatre();
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void addTheatre() {

        System.out.print("Enter theatre name: ");
        String name = scanner.nextLine();

        System.out.print("Enter city: ");
        String city = scanner.nextLine();

        System.out.print("Enter address: ");
        String address = scanner.nextLine();

        System.out.print("Enter total seats: ");
        int totalSeats = scanner.nextInt();
        scanner.nextLine();

        Theatre theatre = new Theatre(
                0,
                name,
                city,
                address,
                totalSeats
        );

        if (theatreService.addTheatre(theatre)) {
            System.out.println("Theatre added successfully!");
        } else {
            System.out.println("Theatre could not be added.");
        }
    }

    private void viewTheatres() {

        List<Theatre> theatres = theatreService.getAllTheatres();

        System.out.println();
        System.out.println("===== THEATRES =====");

        for (Theatre theatre : theatres) {

            System.out.println(
                    theatre.getTheatreId() + " | " +
                            theatre.getName() + " | " +
                            theatre.getCity() + " | " +
                            theatre.getAddress() + " | " +
                            theatre.getTotalSeats() + " seats"
            );
        }
    }

    private void updateTheatre() {

        System.out.print("Enter theatre ID: ");
        int theatreId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter new theatre name: ");
        String name = scanner.nextLine();

        System.out.print("Enter new city: ");
        String city = scanner.nextLine();

        System.out.print("Enter new address: ");
        String address = scanner.nextLine();

        System.out.print("Enter new total seats: ");
        int totalSeats = scanner.nextInt();
        scanner.nextLine();

        Theatre theatre = new Theatre(
                theatreId,
                name,
                city,
                address,
                totalSeats
        );

        if (theatreService.updateTheatre(theatre)) {
            System.out.println("Theatre updated successfully!");
        } else {
            System.out.println("Theatre could not be updated.");
        }
    }

    private void deleteTheatre() {

        System.out.print("Enter theatre ID: ");
        int theatreId = scanner.nextInt();
        scanner.nextLine();

        if (theatreService.deleteTheatre(theatreId)) {
            System.out.println("Theatre deleted successfully!");
        } else {
            System.out.println("Theatre could not be deleted.");
        }
    }
}