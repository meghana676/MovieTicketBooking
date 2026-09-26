package com.movieticket.controller;

import com.movieticket.model.Show;
import com.movieticket.service.ShowService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class ShowController {

    private static final Logger LOGGER =
            Logger.getLogger(ShowController.class.getName());

    private final ShowService showService;
    private final Scanner scanner;

    public ShowController() {
        showService = new ShowService();
        scanner = new Scanner(System.in);
    }

    public void showMenu() {

        while (true) {

            LOGGER.info("");
            LOGGER.info("===== SHOW MANAGEMENT =====");
            LOGGER.info("1. Add Show");
            LOGGER.info("2. View Shows");
            LOGGER.info("3. Update Show");
            LOGGER.info("4. Delete Show");
            LOGGER.info("5. Back");
            LOGGER.info("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    addShow();
                    break;

                case 2:
                    viewShows();
                    break;

                case 3:
                    updateShow();
                    break;

                case 4:
                    deleteShow();
                    break;

                case 5:
                    return;

                default:
                    LOGGER.warning("Invalid choice.");
            }
        }
    }

    private void addShow() {

        LOGGER.info("Enter theatre ID: ");
        int theatreId = scanner.nextInt();
        scanner.nextLine();

        LOGGER.info("Enter movie ID: ");
        int movieId = scanner.nextInt();
        scanner.nextLine();

        LOGGER.info("Enter show date (YYYY-MM-DD): ");
        LocalDate showDate = LocalDate.parse(scanner.nextLine());

        LOGGER.info("Enter start time (HH:MM): ");
        LocalTime startTime = LocalTime.parse(scanner.nextLine());

        LOGGER.info("Enter end time (HH:MM): ");
        LocalTime endTime = LocalTime.parse(scanner.nextLine());

        Show show = new Show(
                0,
                theatreId,
                movieId,
                showDate,
                startTime,
                endTime
        );

        if (showService.addShow(show)) {

            LOGGER.info("Show added successfully!");

        } else {

            LOGGER.warning("Show could not be added.");
        }
    }

    private void viewShows() {

        List<Show> shows = showService.getAllShows();

        LOGGER.info("");
        LOGGER.info("===== SHOWS =====");

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

    private void updateShow() {

        LOGGER.info("Enter show ID: ");
        int showId = scanner.nextInt();
        scanner.nextLine();

        LOGGER.info("Enter theatre ID: ");
        int theatreId = scanner.nextInt();
        scanner.nextLine();

        LOGGER.info("Enter movie ID: ");
        int movieId = scanner.nextInt();
        scanner.nextLine();

        LOGGER.info("Enter new show date (YYYY-MM-DD): ");
        LocalDate showDate = LocalDate.parse(scanner.nextLine());

        LOGGER.info("Enter new start time (HH:MM): ");
        LocalTime startTime = LocalTime.parse(scanner.nextLine());

        LOGGER.info("Enter new end time (HH:MM): ");
        LocalTime endTime = LocalTime.parse(scanner.nextLine());

        Show show = new Show(
                showId,
                theatreId,
                movieId,
                showDate,
                startTime,
                endTime
        );

        if (showService.updateShow(show)) {

            LOGGER.info("Show updated successfully!");

        } else {

            LOGGER.warning("Show could not be updated.");
        }
    }

    private void deleteShow() {

        LOGGER.info("Enter show ID: ");
        int showId = scanner.nextInt();
        scanner.nextLine();

        if (showService.deleteShow(showId)) {

            LOGGER.info("Show deleted successfully!");

        } else {

            LOGGER.warning("Show could not be deleted.");
        }
    }
}