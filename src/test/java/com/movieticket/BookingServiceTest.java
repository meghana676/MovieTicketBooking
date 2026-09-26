package com.movieticket;

import com.movieticket.dao.BookingDAO;
import com.movieticket.model.Booking;
import com.movieticket.service.BookingService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookingServiceTest {

    @Test
    void testGetAllBookings() {

        BookingDAO bookingDAO = mock(BookingDAO.class);

        Booking booking = new Booking(
                1,
                2,
                1,
                null,
                300,
                "CONFIRMED"
        );

        when(bookingDAO.getAllBookings()).thenReturn(List.of(booking));

        BookingService bookingService =
                new BookingService(bookingDAO);

        List<Booking> result =
                bookingService.getAllBookings();

        assertNotNull(result);
        assertEquals(1, result.size());

        Booking resultBooking = result.get(0);

        assertEquals(1, resultBooking.getBookingId());
        assertEquals(2, resultBooking.getShowId());
        assertEquals(1, resultBooking.getUserId());
        assertEquals(300, resultBooking.getTotalAmount());
        assertEquals("CONFIRMED", resultBooking.getBookingStatus());

        verify(bookingDAO).getAllBookings();
    }


    @Test
    void testAddBookingWithValidData() {

        BookingDAO bookingDAO = mock(BookingDAO.class);

        Booking booking = new Booking(
                0,
                2,
                1,
                null,
                300,
                "CONFIRMED"
        );

        when(bookingDAO.addBooking(booking)).thenReturn(1);

        BookingService bookingService =
                new BookingService(bookingDAO);

        int result =
                bookingService.addBooking(booking);

        assertEquals(1, result);

        verify(bookingDAO).addBooking(booking);
    }


    @Test
    void testAddBookingWithInvalidShowId() {

        BookingDAO bookingDAO = mock(BookingDAO.class);

        Booking booking = new Booking(
                0,
                0,
                1,
                null,
                300,
                "CONFIRMED"
        );

        BookingService bookingService =
                new BookingService(bookingDAO);

        int result =
                bookingService.addBooking(booking);

        assertEquals(0, result);

        verify(bookingDAO, never()).addBooking(booking);
    }


    @Test
    void testAddBookingWithInvalidUserId() {

        BookingDAO bookingDAO = mock(BookingDAO.class);

        Booking booking = new Booking(
                0,
                2,
                0,
                null,
                300,
                "CONFIRMED"
        );

        BookingService bookingService =
                new BookingService(bookingDAO);

        int result =
                bookingService.addBooking(booking);

        assertEquals(0, result);

        verify(bookingDAO, never()).addBooking(booking);
    }


    @Test
    void testAddBookingWithInvalidAmount() {

        BookingDAO bookingDAO = mock(BookingDAO.class);

        Booking booking = new Booking(
                0,
                2,
                1,
                null,
                0,
                "CONFIRMED"
        );

        BookingService bookingService =
                new BookingService(bookingDAO);

        int result =
                bookingService.addBooking(booking);

        assertEquals(0, result);

        verify(bookingDAO, never()).addBooking(booking);
    }
}