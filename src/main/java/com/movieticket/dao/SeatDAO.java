package com.movieticket.dao;

import com.movieticket.model.Seat;
import com.movieticket.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SeatDAO {

    public boolean addSeat(Seat seat) {

        String sql = "INSERT INTO seats " +
                "(theatre_id, seat_number, seat_type, price) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, seat.getTheatreId());
            statement.setString(2, seat.getSeatNumber());
            statement.setString(3, seat.getSeatType());
            statement.setDouble(4, seat.getPrice());

            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            return false;
        }
    }
    public List<Seat> getSeatsByTheatre(int theatreId) {

        List<Seat> seats = new ArrayList<>();

        String sql = "SELECT * FROM seats WHERE theatre_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

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
            System.err.println("Database error: " + e.getMessage());
        }

        return seats;
    }
    public boolean updateSeat(Seat seat) {

        String sql = "UPDATE seats SET " +
                "seat_number = ?, " +
                "seat_type = ?, " +
                "price = ? " +
                "WHERE seat_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, seat.getSeatNumber());
            statement.setString(2, seat.getSeatType());
            statement.setDouble(3, seat.getPrice());
            statement.setInt(4, seat.getSeatId());

            int rowsUpdated = statement.executeUpdate();

            return rowsUpdated > 0;

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            return false;
        }
    }
    public boolean deleteSeat(int seatId) {

        String sql = "DELETE FROM seats WHERE seat_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, seatId);

            int rowsDeleted = statement.executeUpdate();

            return rowsDeleted > 0;

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            return false;
        }
    }
}
