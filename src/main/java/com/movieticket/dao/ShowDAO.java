package com.movieticket.dao;

import com.movieticket.model.Show;
import com.movieticket.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ShowDAO {

    private static final Logger LOGGER =
            Logger.getLogger(ShowDAO.class.getName());

    private static final String INSERT_SHOW =
            "INSERT INTO shows " +
                    "(theatre_id, movie_id, show_date, start_time, end_time) " +
                    "VALUES (?, ?, ?, ?, ?)";

    private static final String GET_ALL_SHOWS =
            "SELECT * FROM shows";

    private static final String GET_SHOWS_BY_MOVIE_AND_THEATRE =
            "SELECT * FROM shows " +
                    "WHERE movie_id = ? " +
                    "AND theatre_id = ?";

    private static final String UPDATE_SHOW =
            "UPDATE shows SET " +
                    "theatre_id = ?, " +
                    "movie_id = ?, " +
                    "show_date = ?, " +
                    "start_time = ?, " +
                    "end_time = ? " +
                    "WHERE show_id = ?";

    private static final String DELETE_SHOW =
            "DELETE FROM shows WHERE show_id = ?";


    public boolean addShow(Show show) {

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(INSERT_SHOW)) {

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

            LOGGER.severe("Error adding show: " + e.getMessage());

            return false;
        }
    }


    public List<Show> getAllShows() {

        List<Show> shows = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(GET_ALL_SHOWS);
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

            LOGGER.severe("Database error: " + e.getMessage());
        }

        return shows;
    }


    public List<Show> getShowsByMovieAndTheatre(
            int movieId,
            int theatreId) {

        List<Show> shows = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(
                             GET_SHOWS_BY_MOVIE_AND_THEATRE)) {

            statement.setInt(1, movieId);
            statement.setInt(2, theatreId);

            ResultSet resultSet = statement.executeQuery();

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

            LOGGER.severe(
                    "Error finding shows: " + e.getMessage()
            );
        }

        return shows;
    }


    public boolean updateShow(Show show) {

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(UPDATE_SHOW)) {

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

            LOGGER.severe("Error updating show: " + e.getMessage());

            return false;
        }
    }


    public boolean deleteShow(int showId) {

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(DELETE_SHOW)) {

            statement.setInt(1, showId);

            int rowsDeleted = statement.executeUpdate();

            return rowsDeleted > 0;

        } catch (SQLException e) {

            LOGGER.severe("Error deleting show: " + e.getMessage());

            return false;
        }
    }
}