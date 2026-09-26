package com.movieticket.controller;

import com.movieticket.model.Theatre;
import com.movieticket.service.TheatreService;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class TheatreController {

    private static final Logger LOGGER =
            Logger.getLogger(TheatreController.class.getName());

    private final TheatreService theatreService;
    private final Scanner scanner;

    public TheatreController() {
        theatreService = new TheatreService();
        scanner = new Scanner(System.in);
    }

    public void showMenu() {

        while (true) {

            LOGGER.info("");
            LOGGER.info("===== THEATRE MANAGEMENT =====");
            LOGGER.info("1. Add Theatre");
            LOGGER.info("2. View Theatres");
            LOGGER.info("3. Update Theatre");
            LOGGER.info("4. Delete Theatre");
            LOGGER.info("5. Back");
            LOGGER.info("Enter your choice: ");

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
                    LOGGER.warning("Invalid choice.");
            }
        }
    }

    private void addTheatre() {

        LOGGER.info("Enter theatre name: ");
        String name = scanner.nextLine();

        LOGGER.info("Enter city: ");
        String city = scanner.nextLine();

        LOGGER.info("Enter address: ");
        String address = scanner.nextLine();

        LOGGER.info("Enter total seats: ");
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

            LOGGER.info("Theatre added successfully!");

        } else {

            LOGGER.warning("Theatre could not be added.");
        }
    }

    private void viewTheatres() {

        List<Theatre> theatres = theatreService.getAllTheatres();

        LOGGER.info("");
        LOGGER.info("===== THEATRES =====");

        for (Theatre theatre : theatres) {

            LOGGER.info(
                    theatre.getTheatreId() + " | " +
                            theatre.getName() + " | " +
                            theatre.getCity() + " | " +
                            theatre.getAddress() + " | " +
                            theatre.getTotalSeats() + " seats"
            );
        }
    }

    private void updateTheatre() {

        LOGGER.info("Enter theatre ID: ");
        int theatreId = scanner.nextInt();
        scanner.nextLine();

        LOGGER.info("Enter new theatre name: ");
        String name = scanner.nextLine();

        LOGGER.info("Enter new city: ");
        String city = scanner.nextLine();

        LOGGER.info("Enter new address: ");
        String address = scanner.nextLine();

        LOGGER.info("Enter new total seats: ");
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

            LOGGER.info("Theatre updated successfully!");

        } else {

            LOGGER.warning("Theatre could not be updated.");
        }
    }

    private void deleteTheatre() {

        LOGGER.info("Enter theatre ID: ");
        int theatreId = scanner.nextInt();
        scanner.nextLine();

        if (theatreService.deleteTheatre(theatreId)) {

            LOGGER.info("Theatre deleted successfully!");

        } else {

            LOGGER.warning("Theatre could not be deleted.");
        }
    }
}