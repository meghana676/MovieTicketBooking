package com.movieticket;

import com.movieticket.model.BookedSeat;
import com.movieticket.service.BookedSeatService;

import java.util.List;

public class BookedSeatServiceTest {

    public static void main(String[] args) {

        BookedSeatService bookedSeatService = new BookedSeatService();

        List<BookedSeat> bookedSeats =
                bookedSeatService.getBookedSeatsByBooking(1);

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
