package com.movieticket.dao;

import com.movieticket.model.Show;
import com.movieticket.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ShowDAO {

    public boolean addShow(Show show) {

        String sql = "INSERT INTO shows " +
                "(theatre_id, movie_id, show_date, start_time, end_time) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, show.getTheatreId());
            statement.setInt(2, show.getMovieId());
            statement.setDate(
                    3,
                    java.sql.Date.valueOf(show.getShowDate())
            );
            statement.setTime(
                    4,
                    java.sql.Time.valueOf(show.getStartTime())
            );
            statement.setTime(
                    5,
                    java.sql.Time.valueOf(show.getEndTime())
            );

            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            return false;
        }
    }
    public List<Show> getAllShows() {

        List<Show> shows = new ArrayList<>();

        String sql = "SELECT * FROM shows";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Show show = new Show(
                        resultSet.getInt("show_id"),
                        resultSet.getInt("theatre_id"),
                        resultSet.getInt("movie_id"),
                        resultSet.getDate("show_date").toLocalDate(),
                        resultSet.getTime("start_time").toLocalTime(),
                        resultSet.getTime("end_time").toLocalTime()
                );

                shows.add(show);
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }

        return shows;
    }
    public boolean updateShow(Show show) {

        String sql = "UPDATE shows SET " +
                "theatre_id = ?, " +
                "movie_id = ?, " +
                "show_date = ?, " +
                "start_time = ?, " +
                "end_time = ? " +
                "WHERE show_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, show.getTheatreId());
            statement.setInt(2, show.getMovieId());
            statement.setDate(
                    3,
                    java.sql.Date.valueOf(show.getShowDate())
            );
            statement.setTime(
                    4,
                    java.sql.Time.valueOf(show.getStartTime())
            );
            statement.setTime(
                    5,
                    java.sql.Time.valueOf(show.getEndTime())
            );
            statement.setInt(6, show.getShowId());

            int rowsUpdated = statement.executeUpdate();

            return rowsUpdated > 0;

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            return false;
        }
    }
    public boolean deleteShow(int showId) {

        String sql = "DELETE FROM shows WHERE show_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, showId);

            int rowsDeleted = statement.executeUpdate();

            return rowsDeleted > 0;

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            return false;
        }
    }
}
