package com.movieticket;

import com.movieticket.dao.SeatDAO;
import com.movieticket.model.Seat;

public class SeatTest {

    public static void main(String[] args) {

        Seat seat = new Seat(
                0,
                2,
                "A1",
                "REGULAR",
                150.00
        );

        SeatDAO seatDAO = new SeatDAO();

        boolean result = seatDAO.addSeat(seat);

        if (result) {
            System.out.println("Seat added successfully!");
        } else {
            System.out.println("Seat not added.");
        }
    }
}
