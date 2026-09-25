package com.movieticket.controller;

import com.movieticket.model.Show;
import com.movieticket.service.ShowService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class ShowController {

    private final ShowService showService;
    private final Scanner scanner;

    public ShowController() {
        showService = new ShowService();
        scanner = new Scanner(System.in);
    }

    public void showMenu() {

        while (true) {

            System.out.println();
            System.out.println("===== SHOW MANAGEMENT =====");
            System.out.println("1. Add Show");
            System.out.println("2. View Shows");
            System.out.println("3. Update Show");
            System.out.println("4. Delete Show");
            System.out.println("5. Back");
            System.out.print("Enter your choice: ");

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
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void addShow() {

        System.out.print("Enter theatre ID: ");
        int theatreId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter movie ID: ");
        int movieId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter show date (YYYY-MM-DD): ");
        LocalDate showDate = LocalDate.parse(scanner.nextLine());

        System.out.print("Enter start time (HH:MM): ");
        LocalTime startTime = LocalTime.parse(scanner.nextLine());

        System.out.print("Enter end time (HH:MM): ");
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
            System.out.println("Show added successfully!");
        } else {
            System.out.println("Show could not be added.");
        }
    }

    private void viewShows() {

        List<Show> shows = showService.getAllShows();

        System.out.println();
        System.out.println("===== SHOWS =====");

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

    private void updateShow() {

        System.out.print("Enter show ID: ");
        int showId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter theatre ID: ");
        int theatreId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter movie ID: ");
        int movieId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter new show date (YYYY-MM-DD): ");
        LocalDate showDate = LocalDate.parse(scanner.nextLine());

        System.out.print("Enter new start time (HH:MM): ");
        LocalTime startTime = LocalTime.parse(scanner.nextLine());

        System.out.print("Enter new end time (HH:MM): ");
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
            System.out.println("Show updated successfully!");
        } else {
            System.out.println("Show could not be updated.");
        }
    }

    private void deleteShow() {

        System.out.print("Enter show ID: ");
        int showId = scanner.nextInt();
        scanner.nextLine();

        if (showService.deleteShow(showId)) {
            System.out.println("Show deleted successfully!");
        } else {
            System.out.println("Show could not be deleted.");
        }
    }
}