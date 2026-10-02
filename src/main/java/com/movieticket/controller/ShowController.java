package com.movieticket.controller;

import com.movieticket.model.Movie;
import com.movieticket.model.Show;
import com.movieticket.model.Theatre;
import com.movieticket.service.MovieService;
import com.movieticket.service.ShowService;
import com.movieticket.service.TheatreService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class ShowController {

    private static final Logger LOGGER =
            Logger.getLogger(ShowController.class.getName());

    private final ShowService showService;
    private final MovieService movieService;
    private final TheatreService theatreService;
    private final Scanner scanner;

    public ShowController() {

        showService = new ShowService();
        movieService = new MovieService();
        theatreService = new TheatreService();
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
            LOGGER.info("Enter your choice:");

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

        LOGGER.info("Enter movie name:");
        String movieName = scanner.nextLine();

        Movie selectedMovie =
                findMovieByName(movieName);

        if (selectedMovie == null) {
            LOGGER.warning("Movie not found.");
            return;
        }

        LOGGER.info("Enter theatre name:");
        String theatreName = scanner.nextLine();

        Theatre selectedTheatre =
                findTheatreByName(theatreName);

        if (selectedTheatre == null) {
            LOGGER.warning("Theatre not found.");
            return;
        }

        LocalDate showDate =
                readDate("Enter show date (YYYY-MM-DD):");

        LocalTime startTime =
                readTime("Enter start time (HH:MM):");

        LocalTime endTime =
                readTime("Enter end time (HH:MM):");

        if (!endTime.isAfter(startTime)) {
            LOGGER.warning(
                    "End time must be after start time."
            );
            return;
        }

        Show show = new Show(
                0,
                selectedTheatre.getTheatreId(),
                selectedMovie.getMovieId(),
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

        List<Show> shows =
                showService.getAllShows();

        List<Movie> movies =
                movieService.getAllMovies();

        List<Theatre> theatres =
                theatreService.getAllTheatres();

        LOGGER.info("");
        LOGGER.info("===== SHOWS =====");

        if (shows.isEmpty()) {
            LOGGER.info("No shows available.");
            return;
        }

        for (Show show : shows) {

            String movieName = "Unknown Movie";
            String theatreName = "Unknown Theatre";

            for (Movie movie : movies) {

                if (movie.getMovieId() ==
                        show.getMovieId()) {

                    movieName = movie.getTitle();
                    break;
                }
            }

            for (Theatre theatre : theatres) {

                if (theatre.getTheatreId() ==
                        show.getTheatreId()) {

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

    private void updateShow() {

        LOGGER.info("Enter movie name:");
        String movieName = scanner.nextLine();

        Movie selectedMovie =
                findMovieByName(movieName);

        if (selectedMovie == null) {
            LOGGER.warning("Movie not found.");
            return;
        }

        LOGGER.info("Enter theatre name:");
        String theatreName = scanner.nextLine();

        Theatre selectedTheatre =
                findTheatreByName(theatreName);

        if (selectedTheatre == null) {
            LOGGER.warning("Theatre not found.");
            return;
        }

        LocalDate showDate =
                readDate("Enter show date (YYYY-MM-DD):");

        LocalTime startTime =
                readTime("Enter start time (HH:MM):");

        List<Show> matchingShows =
                findShows(
                        selectedMovie.getMovieId(),
                        selectedTheatre.getTheatreId(),
                        showDate,
                        startTime
                );

        if (matchingShows.isEmpty()) {
            LOGGER.warning("Show not found.");
            return;
        }

        Show selectedShow;

        if (matchingShows.size() == 1) {

            selectedShow = matchingShows.get(0);

        } else {

            LOGGER.info("");
            LOGGER.info("===== MATCHING SHOWS =====");

            for (int i = 0; i < matchingShows.size(); i++) {

                Show show = matchingShows.get(i);

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

            if (showChoice <= 0 ||
                    showChoice > matchingShows.size()) {

                LOGGER.warning("Invalid show selection.");
                return;
            }

            selectedShow =
                    matchingShows.get(showChoice - 1);
        }

        LOGGER.info("Selected show:");
        LOGGER.info(
                selectedMovie.getTitle() + " | " +
                        selectedTheatre.getName() + " | " +
                        selectedShow.getShowDate() + " | " +
                        selectedShow.getStartTime() + " - " +
                        selectedShow.getEndTime()
        );

        LOGGER.info("Enter new movie name:");
        String newMovieName = scanner.nextLine();

        Movie newMovie =
                findMovieByName(newMovieName);

        if (newMovie == null) {
            LOGGER.warning("Movie not found.");
            return;
        }

        LOGGER.info("Enter new theatre name:");
        String newTheatreName = scanner.nextLine();

        Theatre newTheatre =
                findTheatreByName(newTheatreName);

        if (newTheatre == null) {
            LOGGER.warning("Theatre not found.");
            return;
        }

        LocalDate newShowDate =
                readDate(
                        "Enter new show date (YYYY-MM-DD):"
                );

        LocalTime newStartTime =
                readTime(
                        "Enter new start time (HH:MM):"
                );

        LocalTime newEndTime =
                readTime(
                        "Enter new end time (HH:MM):"
                );

        if (!newEndTime.isAfter(newStartTime)) {
            LOGGER.warning(
                    "End time must be after start time."
            );
            return;
        }

        Show show = new Show(
                selectedShow.getShowId(),
                newTheatre.getTheatreId(),
                newMovie.getMovieId(),
                newShowDate,
                newStartTime,
                newEndTime
        );

        if (showService.updateShow(show)) {

            LOGGER.info("Show updated successfully!");

        } else {

            LOGGER.warning("Show could not be updated.");
        }
    }

    private void deleteShow() {

        LOGGER.info("Enter movie name:");
        String movieName = scanner.nextLine();

        Movie selectedMovie =
                findMovieByName(movieName);

        if (selectedMovie == null) {
            LOGGER.warning("Movie not found.");
            return;
        }

        LOGGER.info("Enter theatre name:");
        String theatreName = scanner.nextLine();

        Theatre selectedTheatre =
                findTheatreByName(theatreName);

        if (selectedTheatre == null) {
            LOGGER.warning("Theatre not found.");
            return;
        }

        LocalDate showDate =
                readDate("Enter show date (YYYY-MM-DD):");

        LocalTime startTime =
                readTime("Enter start time (HH:MM):");

        List<Show> matchingShows =
                findShows(
                        selectedMovie.getMovieId(),
                        selectedTheatre.getTheatreId(),
                        showDate,
                        startTime
                );

        if (matchingShows.isEmpty()) {
            LOGGER.warning("Show not found.");
            return;
        }

        Show selectedShow;

        if (matchingShows.size() == 1) {

            selectedShow = matchingShows.get(0);

        } else {

            LOGGER.info("");
            LOGGER.info("===== MATCHING SHOWS =====");

            for (int i = 0; i < matchingShows.size(); i++) {

                Show show = matchingShows.get(i);

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

            if (showChoice <= 0 ||
                    showChoice > matchingShows.size()) {

                LOGGER.warning("Invalid show selection.");
                return;
            }

            selectedShow =
                    matchingShows.get(showChoice - 1);
        }

        if (showService.deleteShow(
                selectedShow.getShowId())) {

            LOGGER.info("Show deleted successfully!");

        } else {

            LOGGER.warning(
                    "Show could not be deleted."
            );
        }
    }

    private Movie findMovieByName(String movieName) {

        if (movieName == null ||
                movieName.trim().isEmpty()) {

            return null;
        }

        List<Movie> movies =
                movieService.searchMoviesByName(
                        movieName.trim()
                );

        if (movies.isEmpty()) {
            return null;
        }

        if (movies.size() == 1) {
            return movies.get(0);
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

        LOGGER.info("Select movie number:");

        int movieChoice = scanner.nextInt();
        scanner.nextLine();

        if (movieChoice <= 0 ||
                movieChoice > movies.size()) {

            return null;
        }

        return movies.get(movieChoice - 1);
    }

    private Theatre findTheatreByName(
            String theatreName) {

        if (theatreName == null ||
                theatreName.trim().isEmpty()) {

            return null;
        }

        List<Theatre> theatres =
                theatreService.getAllTheatres();

        List<Theatre> matches =
                new ArrayList<>();

        for (Theatre theatre : theatres) {

            if (theatre.getName()
                    .equalsIgnoreCase(
                            theatreName.trim()
                    )) {

                matches.add(theatre);
            }
        }

        if (matches.isEmpty()) {
            return null;
        }

        if (matches.size() == 1) {
            return matches.get(0);
        }

        LOGGER.info("");
        LOGGER.info("===== THEATRES FOUND =====");

        for (int i = 0; i < matches.size(); i++) {

            Theatre theatre = matches.get(i);

            LOGGER.info(
                    (i + 1) + ". " +
                            theatre.getName() + " | " +
                            theatre.getCity() + " | " +
                            theatre.getAddress()
            );
        }

        LOGGER.info("Select theatre number:");

        int theatreChoice = scanner.nextInt();
        scanner.nextLine();

        if (theatreChoice <= 0 ||
                theatreChoice > matches.size()) {

            return null;
        }

        return matches.get(theatreChoice - 1);
    }

    private List<Show> findShows(
            int movieId,
            int theatreId,
            LocalDate showDate,
            LocalTime startTime) {

        List<Show> allShows =
                showService.getShowsByMovieAndTheatre(
                        movieId,
                        theatreId
                );

        List<Show> matchingShows =
                new ArrayList<>();

        for (Show show : allShows) {

            if (show.getShowDate()
                    .equals(showDate)
                    && show.getStartTime()
                    .equals(startTime)) {

                matchingShows.add(show);
            }
        }

        return matchingShows;
    }

    private LocalDate readDate(String message) {

        while (true) {

            LOGGER.info(message);

            String input =
                    scanner.nextLine().trim();

            try {

                return LocalDate.parse(input);

            } catch (Exception e) {

                LOGGER.warning(
                        "Invalid date. Please use YYYY-MM-DD."
                );
            }
        }
    }

    private LocalTime readTime(String message) {

        while (true) {

            LOGGER.info(message);

            String input =
                    scanner.nextLine().trim();

            try {

                return LocalTime.parse(input);

            } catch (Exception e) {

                LOGGER.warning(
                        "Invalid time. Please use HH:MM."
                );
            }
        }
    }
}