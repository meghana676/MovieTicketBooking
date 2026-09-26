package com.movieticket.service;

import com.movieticket.dao.BookedSeatDAO;
import com.movieticket.model.BookedSeat;

import java.util.List;

public class BookedSeatService {

    private final BookedSeatDAO bookedSeatDAO;

    public BookedSeatService() {
        this.bookedSeatDAO = new BookedSeatDAO();
    }

    public BookedSeatService(BookedSeatDAO bookedSeatDAO) {
        this.bookedSeatDAO = bookedSeatDAO;
    }

    public boolean addBookedSeat(BookedSeat bookedSeat) {

        if (bookedSeat.getSeatId() <= 0) {
            return false;
        }

        if (bookedSeat.getBookingId() <= 0) {
            return false;
        }

        return bookedSeatDAO.addBookedSeat(bookedSeat);
    }

    public List<BookedSeat> getBookedSeatsByBooking(int bookingId) {

        if (bookingId <= 0) {
            return List.of();
        }

        return bookedSeatDAO.getBookedSeatsByBooking(bookingId);
    }
}