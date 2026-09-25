package com.movieticket;

import com.movieticket.dao.MovieDAO;
import com.movieticket.model.Movie;
import com.movieticket.service.MovieService;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MovieServiceTest {

    @Test
    void testGetAllMovies() {

        MovieDAO movieDAO = mock(MovieDAO.class);

        Movie movie = new Movie(
                1,
                "Avatar",
                "English",
                "Sci-Fi",
                162,
                null
        );

        List<Movie> movies = Arrays.asList(movie);

        when(movieDAO.getAllMovies()).thenReturn(movies);

        MovieService movieService = new MovieService(movieDAO);

        List<Movie> result = movieService.getAllMovies();

        assertNotNull(result);
        assertEquals(1, result.size());

        Movie resultMovie = result.get(0);

        assertEquals(1, resultMovie.getMovieId());
        assertEquals("Avatar", resultMovie.getTitle());
        assertEquals("English", resultMovie.getLanguage());
        assertEquals("Sci-Fi", resultMovie.getGenre());
        assertEquals(162, resultMovie.getDuration());

        verify(movieDAO).getAllMovies();
    }

    @Test
    void testAddMovie() {

        MovieDAO movieDAO = mock(MovieDAO.class);

        Movie movie = new Movie(
                0,
                "Inception",
                "English",
                "Sci-Fi",
                148,
                null
        );

        when(movieDAO.addMovie(movie)).thenReturn(true);

        MovieService movieService = new MovieService(movieDAO);

        boolean result = movieService.addMovie(movie);

        assertTrue(result);

        verify(movieDAO).addMovie(movie);
    }
}