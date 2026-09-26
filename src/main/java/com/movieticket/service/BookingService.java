package com.movieticket.service;

import com.movieticket.dao.BookingDAO;
import com.movieticket.model.Booking;

import java.util.List;

public class BookingService {

    private final BookingDAO bookingDAO;

    public BookingService() {
        this.bookingDAO = new BookingDAO();
    }

    public BookingService(BookingDAO bookingDAO) {
        this.bookingDAO = bookingDAO;
    }

    public int addBooking(Booking booking) {

        if (booking.getShowId() <= 0) {
            return 0;
        }

        if (booking.getUserId() <= 0) {
            return 0;
        }

        if (booking.getTotalAmount() <= 0) {
            return 0;
        }

        if (booking.getBookingStatus() == null ||
                booking.getBookingStatus().trim().isEmpty()) {
            return 0;
        }

        return bookingDAO.addBooking(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingDAO.getAllBookings();
    }
    public boolean updateBookingStatus(int bookingId, String status) {
        if (bookingId <= 0) return false;
        if (status == null || status.trim().isEmpty()) return false;

        return bookingDAO.updateBookingStatus(bookingId, status);
    }
}