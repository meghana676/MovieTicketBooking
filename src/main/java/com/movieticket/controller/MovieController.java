package com.movieticket.controller;

import com.movieticket.model.Movie;
import com.movieticket.service.MovieService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MovieController {

    private final MovieService movieService;
    private final Scanner scanner;

    public MovieController() {
        movieService = new MovieService();
        scanner = new Scanner(System.in);
    }

    public void showMenu() {

        while (true) {

            System.out.println();
            System.out.println("===== MOVIE MANAGEMENT =====");
            System.out.println("1. Add Movie");
            System.out.println("2. View Movies");
            System.out.println("3. Update Movie");
            System.out.println("4. Delete Movie");
            System.out.println("5. Back");

            System.out.print("Enter your choice: ");

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
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void addMovie() {

        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        System.out.print("Enter language: ");
        String language = scanner.nextLine();

        System.out.print("Enter genre: ");
        String genre = scanner.nextLine();

        System.out.print("Enter duration in minutes: ");
        int duration = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter release date (YYYY-MM-DD): ");
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
            System.out.println("Movie added successfully!");
        } else {
            System.out.println("Movie could not be added.");
        }
    }

    private void viewMovies() {

        List<Movie> movies = movieService.getAllMovies();

        System.out.println();
        System.out.println("===== MOVIES =====");

        for (Movie movie : movies) {
            System.out.println(
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

        System.out.print("Enter movie ID: ");
        int movieId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter new title: ");
        String title = scanner.nextLine();

        System.out.print("Enter new language: ");
        String language = scanner.nextLine();

        System.out.print("Enter new genre: ");
        String genre = scanner.nextLine();

        System.out.print("Enter new duration in minutes: ");
        int duration = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter new release date (YYYY-MM-DD): ");
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
            System.out.println("Movie updated successfully!");
        } else {
            System.out.println("Movie could not be updated.");
        }
    }

    private void deleteMovie() {

        System.out.print("Enter movie ID: ");
        int movieId = scanner.nextInt();
        scanner.nextLine();

        if (movieService.deleteMovie(movieId)) {
            System.out.println("Movie deleted successfully!");
        } else {
            System.out.println("Movie could not be deleted.");
        }
    }
}
