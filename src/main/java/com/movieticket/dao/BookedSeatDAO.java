package com.movieticket.dao;

import com.movieticket.model.BookedSeat;
import com.movieticket.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookedSeatDAO {

    public boolean addBookedSeat(BookedSeat bookedSeat) {

        String sql = "INSERT INTO booked_seats " +
                "(seat_id, booking_id) " +
                "VALUES (?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, bookedSeat.getSeatId());
            statement.setInt(2, bookedSeat.getBookingId());

            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            return false;
        }
    }
    public List<BookedSeat> getBookedSeatsByBooking(int bookingId) {

        List<BookedSeat> bookedSeats = new ArrayList<>();

        String sql = "SELECT * FROM booked_seats WHERE booking_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, bookingId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                BookedSeat bookedSeat = new BookedSeat(
                        resultSet.getInt("booked_seat_id"),
                        resultSet.getInt("seat_id"),
                        resultSet.getInt("booking_id")
                );

                bookedSeats.add(bookedSeat);
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }

        return bookedSeats;
    }
}