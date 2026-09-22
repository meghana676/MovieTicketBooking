package com.movieticket;

import com.movieticket.model.Seat;
import com.movieticket.service.SeatService;

import java.util.List;

public class SeatServiceTest {

    public static void main(String[] args) {

        SeatService seatService = new SeatService();

        List<Seat> seats = seatService.getSeatsByTheatre(2);

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