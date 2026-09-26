package com.movieticket.dao;

import com.movieticket.model.Movie;
import com.movieticket.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MovieDAO {

    private static final Logger LOGGER =
            Logger.getLogger(MovieDAO.class.getName());

    private static final String INSERT_MOVIE =
            "INSERT INTO movies " +
                    "(title, language, genre, duration, release_date) " +
                    "VALUES (?, ?, ?, ?, ?)";

    private static final String GET_ALL_MOVIES =
            "SELECT * FROM movies";

    private static final String UPDATE_MOVIE =
            "UPDATE movies SET " +
                    "title = ?, " +
                    "language = ?, " +
                    "genre = ?, " +
                    "duration = ?, " +
                    "release_date = ? " +
                    "WHERE movie_id = ?";

    private static final String DELETE_MOVIE =
            "DELETE FROM movies WHERE movie_id = ?";


    public boolean addMovie(Movie movie) {

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(INSERT_MOVIE)) {

            statement.setString(1, movie.getTitle());
            statement.setString(2, movie.getLanguage());
            statement.setString(3, movie.getGenre());
            statement.setInt(4, movie.getDuration());
            statement.setDate(
                    5,
                    java.sql.Date.valueOf(movie.getReleaseDate())
            );

            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {

            LOGGER.severe("Error adding movie: " + e.getMessage());

            return false;
        }
    }


    public List<Movie> getAllMovies() {

        List<Movie> movies = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(GET_ALL_MOVIES);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Movie movie = new Movie(
                        resultSet.getInt("movie_id"),
                        resultSet.getString("title"),
                        resultSet.getString("language"),
                        resultSet.getString("genre"),
                        resultSet.getInt("duration"),
                        resultSet.getDate("release_date").toLocalDate()
                );

                movies.add(movie);
            }

        } catch (SQLException e) {

            LOGGER.severe("Database error: " + e.getMessage());
        }

        return movies;
    }


    public boolean updateMovie(Movie movie) {

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(UPDATE_MOVIE)) {

            statement.setString(1, movie.getTitle());
            statement.setString(2, movie.getLanguage());
            statement.setString(3, movie.getGenre());
            statement.setInt(4, movie.getDuration());
            statement.setDate(
                    5,
                    java.sql.Date.valueOf(movie.getReleaseDate())
            );
            statement.setInt(6, movie.getMovieId());

            int rowsUpdated = statement.executeUpdate();

            return rowsUpdated > 0;

        } catch (SQLException e) {

            LOGGER.severe("Error updating movie: " + e.getMessage());

            return false;
        }
    }


    public boolean deleteMovie(int movieId) {

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(DELETE_MOVIE)) {

            statement.setInt(1, movieId);

            int rowsDeleted = statement.executeUpdate();

            return rowsDeleted > 0;

        } catch (SQLException e) {

            LOGGER.severe("Error deleting movie: " + e.getMessage());

            return false;
        }
    }
}