package com.movieticket.dao;

import com.movieticket.model.Booking;
import com.movieticket.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

public class BookingDAO {

    public int addBooking(Booking booking) {

        String sql = "INSERT INTO bookings " +
                "(show_id, user_id, total_amount, booking_status) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     sql,
                     Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, booking.getShowId());
            statement.setInt(2, booking.getUserId());
            statement.setDouble(3, booking.getTotalAmount());
            statement.setString(4, booking.getBookingStatus());

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {

                ResultSet resultSet = statement.getGeneratedKeys();

                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }

        return 0;
    }
    public List<Booking> getAllBookings() {

        List<Booking> bookings = new ArrayList<>();

        String sql = "SELECT * FROM bookings";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Booking booking = new Booking(
                        resultSet.getInt("booking_id"),
                        resultSet.getInt("show_id"),
                        resultSet.getInt("user_id"),
                        resultSet.getTimestamp("booking_date").toLocalDateTime(),
                        resultSet.getDouble("total_amount"),
                        resultSet.getString("booking_status")
                );

                bookings.add(booking);
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }

        return bookings;
    }
}
