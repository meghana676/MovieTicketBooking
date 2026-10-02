package com.movieticket.dao;

import com.movieticket.model.Theatre;
import com.movieticket.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TheatreDAO {

    private static final Logger LOGGER =
            Logger.getLogger(TheatreDAO.class.getName());

    private static final String INSERT_THEATRE =
            "INSERT INTO theatres (name, city, address, total_seats) " +
                    "VALUES (?, ?, ?, ?)";

    private static final String GET_ALL_THEATRES =
            "SELECT * FROM theatres";

    private static final String GET_THEATRES_BY_MOVIE =
            "SELECT DISTINCT t.* " +
                    "FROM theatres t " +
                    "JOIN shows s ON t.theatre_id = s.theatre_id " +
                    "WHERE s.movie_id = ?";

    private static final String UPDATE_THEATRE =
            "UPDATE theatres SET " +
                    "name = ?, " +
                    "city = ?, " +
                    "address = ?, " +
                    "total_seats = ? " +
                    "WHERE theatre_id = ?";

    private static final String DELETE_THEATRE =
            "DELETE FROM theatres WHERE theatre_id = ?";


    public boolean addTheatre(Theatre theatre) {

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(INSERT_THEATRE)) {

            statement.setString(1, theatre.getName());
            statement.setString(2, theatre.getCity());
            statement.setString(3, theatre.getAddress());
            statement.setInt(4, theatre.getTotalSeats());

            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {

            LOGGER.severe("Error adding theatre: " + e.getMessage());

            return false;
        }
    }


    public List<Theatre> getAllTheatres() {

        List<Theatre> theatres = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(GET_ALL_THEATRES);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Theatre theatre = new Theatre(
                        resultSet.getInt("theatre_id"),
                        resultSet.getString("name"),
                        resultSet.getString("city"),
                        resultSet.getString("address"),
                        resultSet.getInt("total_seats")
                );

                theatres.add(theatre);
            }

        } catch (SQLException e) {

            LOGGER.severe("Database error: " + e.getMessage());
        }

        return theatres;
    }


    public List<Theatre> getTheatresByMovie(int movieId) {

        List<Theatre> theatres = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(GET_THEATRES_BY_MOVIE)) {

            statement.setInt(1, movieId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Theatre theatre = new Theatre(
                        resultSet.getInt("theatre_id"),
                        resultSet.getString("name"),
                        resultSet.getString("city"),
                        resultSet.getString("address"),
                        resultSet.getInt("total_seats")
                );

                theatres.add(theatre);
            }

        } catch (SQLException e) {

            LOGGER.severe(
                    "Error finding theatres for movie: "
                            + e.getMessage()
            );
        }

        return theatres;
    }


    public boolean updateTheatre(Theatre theatre) {

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(UPDATE_THEATRE)) {

            statement.setString(1, theatre.getName());
            statement.setString(2, theatre.getCity());
            statement.setString(3, theatre.getAddress());
            statement.setInt(4, theatre.getTotalSeats());
            statement.setInt(5, theatre.getTheatreId());

            int rowsUpdated = statement.executeUpdate();

            return rowsUpdated > 0;

        } catch (SQLException e) {

            LOGGER.severe("Error updating theatre: " + e.getMessage());

            return false;
        }
    }


    public boolean deleteTheatre(int theatreId) {

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(DELETE_THEATRE)) {

            statement.setInt(1, theatreId);

            int rowsDeleted = statement.executeUpdate();

            return rowsDeleted > 0;

        } catch (SQLException e) {

            LOGGER.severe("Error deleting theatre: " + e.getMessage());

            return false;
        }
    }
}