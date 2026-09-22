package com.movieticket;

import com.movieticket.model.Booking;
import com.movieticket.service.BookingService;

import java.util.List;

public class BookingServiceTest {

    public static void main(String[] args) {

        BookingService bookingService = new BookingService();

        List<Booking> bookings = bookingService.getAllBookings();

        System.out.println("Bookings found:");

        for (Booking booking : bookings) {
            System.out.println(
                    booking.getBookingId() + " | " +
                            booking.getShowId() + " | " +
                            booking.getUserId() + " | " +
                            booking.getTotalAmount() + " | " +
                            booking.getBookingStatus()
            );
        }
    }
}