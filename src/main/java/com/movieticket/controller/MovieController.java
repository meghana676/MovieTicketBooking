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

        LOGGER.info("Enter title:");
        String title = scanner.nextLine();

        if (title.trim().isEmpty()) {
            LOGGER.warning("Movie title cannot be empty.");
            return;
        }

        LOGGER.info("Enter language:");
        String language = scanner.nextLine();

        LOGGER.info("Enter genre:");
        String genre = scanner.nextLine();

        LOGGER.info("Enter duration in minutes:");
        int duration = scanner.nextInt();
        scanner.nextLine();

        LOGGER.info("Enter release date (YYYY-MM-DD):");
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

        if (movies.isEmpty()) {
            LOGGER.info("No movies available.");
            return;
        }

        for (Movie movie : movies) {

            LOGGER.info(
                    movie.getTitle() + " | " +
                            movie.getLanguage() + " | " +
                            movie.getGenre() + " | " +
                            movie.getDuration() + " minutes | " +
                            movie.getReleaseDate()
            );
        }
    }

    private void updateMovie() {

        LOGGER.info("Enter movie name:");

        String movieName = scanner.nextLine();

        if (movieName.trim().isEmpty()) {
            LOGGER.warning("Movie name cannot be empty.");
            return;
        }

        List<Movie> movies =
                movieService.searchMoviesByName(movieName);

        if (movies.isEmpty()) {
            LOGGER.warning("Movie not found.");
            return;
        }

        Movie selectedMovie;

        if (movies.size() == 1) {

            selectedMovie = movies.get(0);

        } else {

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

                LOGGER.warning("Invalid movie selection.");
                return;
            }

            selectedMovie = movies.get(movieChoice - 1);
        }

        LOGGER.info(
                "Selected movie: " +
                        selectedMovie.getTitle()
        );

        LOGGER.info("Enter new title:");
        String title = scanner.nextLine();

        LOGGER.info("Enter new language:");
        String language = scanner.nextLine();

        LOGGER.info("Enter new genre:");
        String genre = scanner.nextLine();

        LOGGER.info("Enter new duration in minutes:");
        int duration = scanner.nextInt();
        scanner.nextLine();

        LOGGER.info("Enter new release date (YYYY-MM-DD):");
        LocalDate releaseDate =
                LocalDate.parse(scanner.nextLine());

        Movie movie = new Movie(
                selectedMovie.getMovieId(),
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

        LOGGER.info("Enter movie name:");

        String movieName = scanner.nextLine();

        if (movieName.trim().isEmpty()) {
            LOGGER.warning("Movie name cannot be empty.");
            return;
        }

        List<Movie> movies =
                movieService.searchMoviesByName(movieName);

        if (movies.isEmpty()) {
            LOGGER.warning("Movie not found.");
            return;
        }

        Movie selectedMovie;

        if (movies.size() == 1) {

            selectedMovie = movies.get(0);

        } else {

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

                LOGGER.warning("Invalid movie selection.");
                return;
            }

            selectedMovie = movies.get(movieChoice - 1);
        }

        if (movieService.deleteMovie(
                selectedMovie.getMovieId())) {

            LOGGER.info("Movie deleted successfully!");

        } else {

            LOGGER.warning("Movie could not be deleted.");
        }
    }
}