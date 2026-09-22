package com.movieticket.service;

import com.movieticket.dao.BookedSeatDAO;
import com.movieticket.model.BookedSeat;

import java.util.List;

public class BookedSeatService {

    private final BookedSeatDAO bookedSeatDAO;

    public BookedSeatService() {
        this.bookedSeatDAO = new BookedSeatDAO();
    }

    public boolean addBookedSeat(BookedSeat bookedSeat) {
        return bookedSeatDAO.addBookedSeat(bookedSeat);
    }

    public List<BookedSeat> getBookedSeatsByBooking(int bookingId) {
        return bookedSeatDAO.getBookedSeatsByBooking(bookingId);
    }
}