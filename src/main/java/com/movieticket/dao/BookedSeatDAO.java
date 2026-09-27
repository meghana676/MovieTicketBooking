package com.movieticket.dao;

import com.movieticket.model.BookedSeat;
import com.movieticket.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class BookedSeatDAO {

    private static final Logger LOGGER =
            Logger.getLogger(BookedSeatDAO.class.getName());

    private static final String INSERT_BOOKED_SEAT =
            "INSERT INTO booked_seats " +
                    "(seat_id, booking_id) " +
                    "VALUES (?, ?)";

    private static final String GET_BOOKED_SEATS_BY_BOOKING =
            "SELECT * FROM booked_seats WHERE booking_id = ?";

    private static final String CHECK_SEAT_BOOKED_FOR_SHOW =
            "SELECT COUNT(*) " +
                    "FROM booked_seats bs " +
                    "JOIN bookings b ON bs.booking_id = b.booking_id " +
                    "WHERE bs.seat_id = ? " +
                    "AND b.show_id = ? " +
                    "AND b.booking_status = 'CONFIRMED'";


    public boolean addBookedSeat(BookedSeat bookedSeat) {

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(INSERT_BOOKED_SEAT)) {

            statement.setInt(1, bookedSeat.getSeatId());
            statement.setInt(2, bookedSeat.getBookingId());

            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {

            LOGGER.severe("Error adding booked seat: " + e.getMessage());

            return false;
        }
    }


    public List<BookedSeat> getBookedSeatsByBooking(int bookingId) {

        List<BookedSeat> bookedSeats = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(
                             GET_BOOKED_SEATS_BY_BOOKING)) {

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

            LOGGER.severe("Database error: " + e.getMessage());
        }

        return bookedSeats;
    }


    public boolean isSeatBookedForShow(int seatId, int showId) {

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(
                             CHECK_SEAT_BOOKED_FOR_SHOW)) {

            statement.setInt(1, seatId);
            statement.setInt(2, showId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }

        } catch (SQLException e) {

            LOGGER.severe(
                    "Error checking seat availability: "
                            + e.getMessage()
            );
        }

        return false;
    }
}