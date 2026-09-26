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
import java.util.logging.Logger;

public class BookingDAO {

    private static final Logger LOGGER =
            Logger.getLogger(BookingDAO.class.getName());

    private static final String UPDATE_BOOKING_STATUS =
            "UPDATE bookings SET booking_status = ? WHERE booking_id = ?";

    private static final String INSERT_BOOKING =
            "INSERT INTO bookings " +
                    "(show_id, user_id, total_amount, booking_status) " +
                    "VALUES (?, ?, ?, ?)";

    private static final String GET_ALL_BOOKINGS =
            "SELECT * FROM bookings";


    public int addBooking(Booking booking) {

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(
                             INSERT_BOOKING,
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

            LOGGER.severe("Error adding booking: " + e.getMessage());
        }

        return 0;
    }


    public List<Booking> getAllBookings() {

        List<Booking> bookings = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(GET_ALL_BOOKINGS);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Booking booking = new Booking(
                        resultSet.getInt("booking_id"),
                        resultSet.getInt("show_id"),
                        resultSet.getInt("user_id"),
                        resultSet.getTimestamp("booking_date")
                                .toLocalDateTime(),
                        resultSet.getDouble("total_amount"),
                        resultSet.getString("booking_status")
                );

                bookings.add(booking);
            }

        } catch (SQLException e) {

            LOGGER.severe("Database error: " + e.getMessage());
        }

        return bookings;
    }
    public boolean updateBookingStatus(int bookingId, String status) {

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(UPDATE_BOOKING_STATUS)) {

            statement.setString(1, status);
            statement.setInt(2, bookingId);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            LOGGER.severe("Error updating booking status: " + e.getMessage());
            return false;
        }
    }

}