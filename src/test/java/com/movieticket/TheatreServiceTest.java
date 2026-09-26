package com.movieticket;

import com.movieticket.dao.TheatreDAO;
import com.movieticket.model.Theatre;
import com.movieticket.service.TheatreService;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TheatreServiceTest {

    @Test
    void testGetAllTheatres() {

        TheatreDAO theatreDAO = mock(TheatreDAO.class);

        Theatre theatre = new Theatre(
                1,
                "PVR Cinemas",
                "Hyderabad",
                "Banjara Hills",
                100
        );

        List<Theatre> theatres = Arrays.asList(theatre);

        when(theatreDAO.getAllTheatres()).thenReturn(theatres);

        TheatreService theatreService = new TheatreService(theatreDAO);

        List<Theatre> result = theatreService.getAllTheatres();

        assertNotNull(result);
        assertEquals(1, result.size());

        Theatre resultTheatre = result.get(0);

        assertEquals(1, resultTheatre.getTheatreId());
        assertEquals("PVR Cinemas", resultTheatre.getName());
        assertEquals("Hyderabad", resultTheatre.getCity());
        assertEquals("Banjara Hills", resultTheatre.getAddress());
        assertEquals(100, resultTheatre.getTotalSeats());

        verify(theatreDAO).getAllTheatres();
    }

    @Test
    void testAddTheatreWithValidData() {

        TheatreDAO theatreDAO = mock(TheatreDAO.class);

        Theatre theatre = new Theatre(
                0,
                "INOX",
                "Hyderabad",
                "Madhapur",
                120
        );

        when(theatreDAO.addTheatre(theatre)).thenReturn(true);

        TheatreService theatreService = new TheatreService(theatreDAO);

        boolean result = theatreService.addTheatre(theatre);

        assertTrue(result);

        verify(theatreDAO).addTheatre(theatre);
    }

    @Test
    void testAddTheatreWithInvalidTotalSeats() {

        TheatreDAO theatreDAO = mock(TheatreDAO.class);

        Theatre theatre = new Theatre(
                0,
                "INOX",
                "Hyderabad",
                "Madhapur",
                0
        );

        TheatreService theatreService = new TheatreService(theatreDAO);

        boolean result = theatreService.addTheatre(theatre);

        assertFalse(result);

        verify(theatreDAO, never()).addTheatre(theatre);
    }

    @Test
    void testAddTheatreWithEmptyName() {

        TheatreDAO theatreDAO = mock(TheatreDAO.class);

        Theatre theatre = new Theatre(
                0,
                "",
                "Hyderabad",
                "Madhapur",
                120
        );

        TheatreService theatreService = new TheatreService(theatreDAO);

        boolean result = theatreService.addTheatre(theatre);

        assertFalse(result);

        verify(theatreDAO, never()).addTheatre(theatre);
    }

    @Test
    void testDeleteTheatreWithInvalidId() {

        TheatreDAO theatreDAO = mock(TheatreDAO.class);

        TheatreService theatreService = new TheatreService(theatreDAO);

        boolean result = theatreService.deleteTheatre(0);

        assertFalse(result);

        verify(theatreDAO, never()).deleteTheatre(0);
    }
}