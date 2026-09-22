package com.movieticket;

import com.movieticket.model.Movie;
import com.movieticket.service.MovieService;

import java.util.List;

public class MovieServiceTest {

    public static void main(String[] args) {

        MovieService movieService = new MovieService();

        List<Movie> movies = movieService.getAllMovies();

        System.out.println("Movies found:");

        for (Movie movie : movies) {
            System.out.println(
                    movie.getMovieId() + " | " +
                            movie.getTitle() + " | " +
                            movie.getLanguage() + " | " +
                            movie.getGenre()
            );
        }
    }
}
