package com.movieticket.dao;

import com.movieticket.model.Movie;
import com.movieticket.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {

    public boolean addMovie(Movie movie) {

        String sql = "INSERT INTO movies " +
                "(title, language, genre, duration, release_date) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

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
            e.printStackTrace();
            return false;
        }
    }
    public List<Movie> getAllMovies() {

        List<Movie> movies = new ArrayList<>();

        String sql = "SELECT * FROM movies";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
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
            e.printStackTrace();
        }

        return movies;
    }
    public boolean updateMovie(Movie movie) {

        String sql = "UPDATE movies SET " +
                "title = ?, " +
                "language = ?, " +
                "genre = ?, " +
                "duration = ?, " +
                "release_date = ? " +
                "WHERE movie_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

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
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteMovie(int movieId) {

        String sql = "DELETE FROM movies WHERE movie_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, movieId);

            int rowsDeleted = statement.executeUpdate();

            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
