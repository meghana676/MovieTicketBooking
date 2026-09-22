package com.movieticket;

import com.movieticket.dao.BookedSeatDAO;
import com.movieticket.model.BookedSeat;

import java.util.List;

public class BookedSeatTest {

    public static void main(String[] args) {

        BookedSeatDAO bookedSeatDAO = new BookedSeatDAO();

        List<BookedSeat> bookedSeats =
                bookedSeatDAO.getBookedSeatsByBooking(1);

        System.out.println("Booked seats found:");

        for (BookedSeat bookedSeat : bookedSeats) {

            System.out.println(
                    bookedSeat.getBookedSeatId() + " | " +
                            bookedSeat.getSeatId() + " | " +
                            bookedSeat.getBookingId()
            );
        }
    }
}