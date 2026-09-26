package com.movieticket.controller;

import com.movieticket.model.Movie;
import com.movieticket.service.MovieService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class MovieController {

    private static final Logger LOGGER =
            Logger.getLogger(MovieController.class.getName());

    private final MovieService movieService;
    private final Scanner scanner;

    public MovieController() {
        movieService = new MovieService();
        scanner = new Scanner(System.in);
    }

    public void showMenu() {

        while (true) {

            LOGGER.info("");
            LOGGER.info("===== MOVIE MANAGEMENT =====");
            LOGGER.info("1. Add Movie");
            LOGGER.info("2. View Movies");
            LOGGER.info("3. Update Movie");
            LOGGER.info("4. Delete Movie");
            LOGGER.info("5. Back");

            LOGGER.info("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    addMovie();
                    break;

                case 2:
                    viewMovies();
                    break;

                case 3:
                    updateMovie();
                    break;

                case 4:
                    deleteMovie();
                    break;

                case 5:
                    return;

                default:
                    LOGGER.warning("Invalid choice.");
            }
        }
    }

    private void addMovie() {

        LOGGER.info("Enter title: ");
        String title = scanner.nextLine();

        LOGGER.info("Enter language: ");
        String language = scanner.nextLine();

        LOGGER.info("Enter genre: ");
        String genre = scanner.nextLine();

        LOGGER.info("Enter duration in minutes: ");
        int duration = scanner.nextInt();
        scanner.nextLine();

        LOGGER.info("Enter release date (YYYY-MM-DD): ");
        LocalDate releaseDate = LocalDate.parse(scanner.nextLine());

        Movie movie = new Movie(
                0,
                title,
                language,
                genre,
                duration,
                releaseDate
        );

        if (movieService.addMovie(movie)) {

            LOGGER.info("Movie added successfully!");

        } else {

            LOGGER.warning("Movie could not be added.");
        }
    }

    private void viewMovies() {

        List<Movie> movies = movieService.getAllMovies();

        LOGGER.info("");
        LOGGER.info("===== MOVIES =====");

        for (Movie movie : movies) {

            LOGGER.info(
                    movie.getMovieId() + " | " +
                            movie.getTitle() + " | " +
                            movie.getLanguage() + " | " +
                            movie.getGenre() + " | " +
                            movie.getDuration() + " minutes | " +
                            movie.getReleaseDate()
            );
        }
    }

    private void updateMovie() {

        LOGGER.info("Enter movie ID: ");
        int movieId = scanner.nextInt();
        scanner.nextLine();

        LOGGER.info("Enter new title: ");
        String title = scanner.nextLine();

        LOGGER.info("Enter new language: ");
        String language = scanner.nextLine();

        LOGGER.info("Enter new genre: ");
        String genre = scanner.nextLine();

        LOGGER.info("Enter new duration in minutes: ");
        int duration = scanner.nextInt();
        scanner.nextLine();

        LOGGER.info("Enter new release date (YYYY-MM-DD): ");
        LocalDate releaseDate = LocalDate.parse(scanner.nextLine());

        Movie movie = new Movie(
                movieId,
                title,
                language,
                genre,
                duration,
                releaseDate
        );

        if (movieService.updateMovie(movie)) {

            LOGGER.info("Movie updated successfully!");

        } else {

            LOGGER.warning("Movie could not be updated.");
        }
    }

    private void deleteMovie() {

        LOGGER.info("Enter movie ID: ");
        int movieId = scanner.nextInt();
        scanner.nextLine();

        if (movieService.deleteMovie(movieId)) {

            LOGGER.info("Movie deleted successfully!");

        } else {

            LOGGER.warning("Movie could not be deleted.");
        }
    }
}