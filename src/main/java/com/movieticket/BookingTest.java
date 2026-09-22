package com.movieticket;

import com.movieticket.dao.BookingDAO;
import com.movieticket.model.Booking;

import java.util.List;

public class BookingTest {

    public static void main(String[] args) {

        BookingDAO bookingDAO = new BookingDAO();

        List<Booking> bookings = bookingDAO.getAllBookings();

        System.out.println("Bookings found:");

        for (Booking booking : bookings) {

            System.out.println(
                    booking.getBookingId() + " | " +
                            booking.getShowId() + " | " +
                            booking.getUserId() + " | " +
                            booking.getBookingDate() + " | " +
                            booking.getTotalAmount() + " | " +
                            booking.getBookingStatus()
            );
        }
    }
}