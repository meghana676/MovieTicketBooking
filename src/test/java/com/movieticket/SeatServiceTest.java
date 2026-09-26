package com.movieticket;

import com.movieticket.dao.SeatDAO;
import com.movieticket.model.Seat;
import com.movieticket.service.SeatService;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SeatServiceTest {

    @Test
    void testGetSeatsByTheatre() {

        SeatDAO seatDAO = mock(SeatDAO.class);

        Seat seat = new Seat(
                1,
                2,
                "A1",
                "PREMIUM",
                200
        );

        List<Seat> seats = Arrays.asList(seat);

        when(seatDAO.getSeatsByTheatre(2)).thenReturn(seats);

        SeatService seatService = new SeatService(seatDAO);

        List<Seat> result = seatService.getSeatsByTheatre(2);

        assertNotNull(result);
        assertEquals(1, result.size());

        Seat resultSeat = result.get(0);

        assertEquals(1, resultSeat.getSeatId());
        assertEquals(2, resultSeat.getTheatreId());
        assertEquals("A1", resultSeat.getSeatNumber());
        assertEquals("PREMIUM", resultSeat.getSeatType());
        assertEquals(200, resultSeat.getPrice());

        verify(seatDAO).getSeatsByTheatre(2);
    }

    @Test
    void testAddSeatWithValidData() {

        SeatDAO seatDAO = mock(SeatDAO.class);

        Seat seat = new Seat(
                0,
                2,
                "B1",
                "REGULAR",
                150
        );

        when(seatDAO.addSeat(seat)).thenReturn(true);

        SeatService seatService = new SeatService(seatDAO);

        boolean result = seatService.addSeat(seat);

        assertTrue(result);

        verify(seatDAO).addSeat(seat);
    }

    @Test
    void testAddSeatWithInvalidPrice() {

        SeatDAO seatDAO = mock(SeatDAO.class);

        Seat seat = new Seat(
                0,
                2,
                "B1",
                "REGULAR",
                0
        );

        SeatService seatService = new SeatService(seatDAO);

        boolean result = seatService.addSeat(seat);

        assertFalse(result);

        verify(seatDAO, never()).addSeat(seat);
    }

    @Test
    void testAddSeatWithEmptySeatNumber() {

        SeatDAO seatDAO = mock(SeatDAO.class);

        Seat seat = new Seat(
                0,
                2,
                "",
                "REGULAR",
                150
        );

        SeatService seatService = new SeatService(seatDAO);

        boolean result = seatService.addSeat(seat);

        assertFalse(result);

        verify(seatDAO, never()).addSeat(seat);
    }

    @Test
    void testDeleteSeatWithInvalidId() {

        SeatDAO seatDAO = mock(SeatDAO.class);

        SeatService seatService = new SeatService(seatDAO);

        boolean result = seatService.deleteSeat(0);

        assertFalse(result);

        verify(seatDAO, never()).deleteSeat(0);
    }
}
