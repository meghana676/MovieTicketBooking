package com.movieticket.dao;

import com.movieticket.model.Seat;
import com.movieticket.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SeatDAO {

    private static final Logger LOGGER =
            Logger.getLogger(SeatDAO.class.getName());

    private static final String INSERT_SEAT =
            "INSERT INTO seats (theatre_id, seat_number, seat_type, price) " +
                    "VALUES (?, ?, ?, ?)";

    private static final String GET_SEATS_BY_THEATRE =
            "SELECT * FROM seats WHERE theatre_id = ?";

    private static final String UPDATE_SEAT =
            "UPDATE seats SET " +
                    "seat_number = ?, " +
                    "seat_type = ?, " +
                    "price = ? " +
                    "WHERE seat_id = ?";

    private static final String DELETE_SEAT =
            "DELETE FROM seats WHERE seat_id = ?";


    public boolean addSeat(Seat seat) {

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(INSERT_SEAT)) {

            statement.setInt(1, seat.getTheatreId());
            statement.setString(2, seat.getSeatNumber());
            statement.setString(3, seat.getSeatType());
            statement.setDouble(4, seat.getPrice());

            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {

            LOGGER.severe("Error adding seat: " + e.getMessage());

            return false;
        }
    }


    public List<Seat> getSeatsByTheatre(int theatreId) {

        List<Seat> seats = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(GET_SEATS_BY_THEATRE)) {

            statement.setInt(1, theatreId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Seat seat = new Seat(
                        resultSet.getInt("seat_id"),
                        resultSet.getInt("theatre_id"),
                        resultSet.getString("seat_number"),
                        resultSet.getString("seat_type"),
                        resultSet.getDouble("price")
                );

                seats.add(seat);
            }

        } catch (SQLException e) {

            LOGGER.severe("Database error: " + e.getMessage());
        }

        return seats;
    }


    public boolean updateSeat(Seat seat) {

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(UPDATE_SEAT)) {

            statement.setString(1, seat.getSeatNumber());
            statement.setString(2, seat.getSeatType());
            statement.setDouble(3, seat.getPrice());
            statement.setInt(4, seat.getSeatId());

            int rowsUpdated = statement.executeUpdate();

            return rowsUpdated > 0;

        } catch (SQLException e) {

            LOGGER.severe("Error updating seat: " + e.getMessage());

            return false;
        }
    }


    public boolean deleteSeat(int seatId) {

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(DELETE_SEAT)) {

            statement.setInt(1, seatId);

            int rowsDeleted = statement.executeUpdate();

            return rowsDeleted > 0;

        } catch (SQLException e) {

            LOGGER.severe("Error deleting seat: " + e.getMessage());

            return false;
        }
    }
}