package com.movieticket.service;

import com.movieticket.dao.BookingDAO;
import com.movieticket.model.Booking;

import java.util.List;

public class BookingService {

    private final BookingDAO bookingDAO;

    public BookingService() {
        this.bookingDAO = new BookingDAO();
    }

    public int addBooking(Booking booking) {
        return bookingDAO.addBooking(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingDAO.getAllBookings();
    }
}