package com.movieticket;

import com.movieticket.dao.ShowDAO;
import com.movieticket.model.Show;
import com.movieticket.service.ShowService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ShowServiceTest {

    @Test
    void testGetAllShows() {

        ShowDAO showDAO = mock(ShowDAO.class);

        Show show = new Show(
                1,
                2,
                2,
                LocalDate.of(2026, 9, 25),
                LocalTime.of(19, 0),
                LocalTime.of(21, 45)
        );

        when(showDAO.getAllShows()).thenReturn(List.of(show));

        ShowService showService = new ShowService(showDAO);

        List<Show> result = showService.getAllShows();

        assertNotNull(result);
        assertEquals(1, result.size());

        Show resultShow = result.get(0);

        assertEquals(1, resultShow.getShowId());
        assertEquals(2, resultShow.getTheatreId());
        assertEquals(2, resultShow.getMovieId());
        assertEquals(LocalDate.of(2026, 9, 25), resultShow.getShowDate());
        assertEquals(LocalTime.of(19, 0), resultShow.getStartTime());
        assertEquals(LocalTime.of(21, 45), resultShow.getEndTime());

        verify(showDAO).getAllShows();
    }

    @Test
    void testAddShowWithValidData() {

        ShowDAO showDAO = mock(ShowDAO.class);

        Show show = new Show(
                0,
                2,
                2,
                LocalDate.of(2026, 9, 26),
                LocalTime.of(19, 0),
                LocalTime.of(21, 45)
        );

        when(showDAO.addShow(show)).thenReturn(true);

        ShowService showService = new ShowService(showDAO);

        boolean result = showService.addShow(show);

        assertTrue(result);

        verify(showDAO).addShow(show);
    }

    @Test
    void testAddShowWithInvalidTheatreId() {

        ShowDAO showDAO = mock(ShowDAO.class);

        Show show = new Show(
                0,
                0,
                2,
                LocalDate.of(2026, 9, 26),
                LocalTime.of(19, 0),
                LocalTime.of(21, 45)
        );

        ShowService showService = new ShowService(showDAO);

        boolean result = showService.addShow(show);

        assertFalse(result);

        verify(showDAO, never()).addShow(show);
    }

    @Test
    void testDeleteShowWithInvalidId() {

        ShowDAO showDAO = mock(ShowDAO.class);

        ShowService showService = new ShowService(showDAO);

        boolean result = showService.deleteShow(0);

        assertFalse(result);

        verify(showDAO, never()).deleteShow(0);
    }
}