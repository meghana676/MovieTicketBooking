package com.movieticket.service;

import com.movieticket.dao.MovieDAO;
import com.movieticket.model.Movie;

import java.util.List;

public class MovieService {

    private final MovieDAO movieDAO;

    public MovieService() {
        this.movieDAO = new MovieDAO();
    }

    public MovieService(MovieDAO movieDAO) {
        this.movieDAO = movieDAO;
    }

    public boolean addMovie(Movie movie) {
        return movieDAO.addMovie(movie);
    }

    public List<Movie> getAllMovies() {
        return movieDAO.getAllMovies();
    }

    public List<Movie> searchMoviesByName(String movieName) {

        if (movieName == null || movieName.trim().isEmpty()) {
            return List.of();
        }

        return movieDAO.searchMoviesByName(movieName);
    }

    public boolean updateMovie(Movie movie) {
        return movieDAO.updateMovie(movie);
    }

    public boolean deleteMovie(int movieId) {
        return movieDAO.deleteMovie(movieId);
    }
}