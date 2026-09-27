package com.movieticket.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MovieTicketExceptionTest {

    @Test
    void shouldThrowMovieTicketException() {

        MovieTicketException exception = assertThrows(
                MovieTicketException.class,
                () -> {
                    throw new MovieTicketException(
                            "Seat is already booked"
                    );
                }
        );

        assertEquals(
                "Seat is already booked",
                exception.getMessage()
        );
    }
}