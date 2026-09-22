package com.movieticket;

import com.movieticket.dao.SeatDAO;
import com.movieticket.model.Seat;

import java.util.List;

public class SeatReadTest {

    public static void main(String[] args) {

        SeatDAO seatDAO = new SeatDAO();

        List<Seat> seats = seatDAO.getSeatsByTheatre(2);

        System.out.println("Seats found:");

        for (Seat seat : seats) {

            System.out.println(
                    seat.getSeatId() + " | " +
                            seat.getTheatreId() + " | " +
                            seat.getSeatNumber() + " | " +
                            seat.getSeatType() + " | " +
                            seat.getPrice()
            );
        }
    }
}
