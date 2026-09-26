package com.movieticket;

import com.movieticket.dao.BookedSeatDAO;
import com.movieticket.model.BookedSeat;
import com.movieticket.service.BookedSeatService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookedSeatServiceTest {

    @Test
    void testGetBookedSeatsByBooking() {

        BookedSeatDAO bookedSeatDAO = mock(BookedSeatDAO.class);

        BookedSeat bookedSeat =
                new BookedSeat(1, 2, 1);

        when(bookedSeatDAO.getBookedSeatsByBooking(1))
                .thenReturn(List.of(bookedSeat));

        BookedSeatService bookedSeatService =
                new BookedSeatService(bookedSeatDAO);

        List<BookedSeat> result =
                bookedSeatService.getBookedSeatsByBooking(1);

        assertNotNull(result);
        assertEquals(1, result.size());

        BookedSeat resultBookedSeat = result.get(0);

        assertEquals(1, resultBookedSeat.getBookedSeatId());
        assertEquals(2, resultBookedSeat.getSeatId());
        assertEquals(1, resultBookedSeat.getBookingId());

        verify(bookedSeatDAO).getBookedSeatsByBooking(1);
    }


    @Test
    void testAddBookedSeatWithValidData() {

        BookedSeatDAO bookedSeatDAO = mock(BookedSeatDAO.class);

        BookedSeat bookedSeat =
                new BookedSeat(0, 2, 1);

        when(bookedSeatDAO.addBookedSeat(bookedSeat))
                .thenReturn(true);

        BookedSeatService bookedSeatService =
                new BookedSeatService(bookedSeatDAO);

        boolean result =
                bookedSeatService.addBookedSeat(bookedSeat);

        assertTrue(result);

        verify(bookedSeatDAO).addBookedSeat(bookedSeat);
    }


    @Test
    void testAddBookedSeatWithInvalidSeatId() {

        BookedSeatDAO bookedSeatDAO = mock(BookedSeatDAO.class);

        BookedSeat bookedSeat =
                new BookedSeat(0, 0, 1);

        BookedSeatService bookedSeatService =
                new BookedSeatService(bookedSeatDAO);

        boolean result =
                bookedSeatService.addBookedSeat(bookedSeat);

        assertFalse(result);

        verify(bookedSeatDAO, never())
                .addBookedSeat(bookedSeat);
    }


    @Test
    void testAddBookedSeatWithInvalidBookingId() {

        BookedSeatDAO bookedSeatDAO = mock(BookedSeatDAO.class);

        BookedSeat bookedSeat =
                new BookedSeat(0, 2, 0);

        BookedSeatService bookedSeatService =
                new BookedSeatService(bookedSeatDAO);

        boolean result =
                bookedSeatService.addBookedSeat(bookedSeat);

        assertFalse(result);

        verify(bookedSeatDAO, never())
                .addBookedSeat(bookedSeat);
    }


    @Test
    void testGetBookedSeatsWithInvalidBookingId() {

        BookedSeatDAO bookedSeatDAO = mock(BookedSeatDAO.class);

        BookedSeatService bookedSeatService =
                new BookedSeatService(bookedSeatDAO);

        List<BookedSeat> result =
                bookedSeatService.getBookedSeatsByBooking(0);

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(bookedSeatDAO, never())
                .getBookedSeatsByBooking(0);
    }
}