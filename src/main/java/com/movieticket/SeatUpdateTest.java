package com.movieticket;

import com.movieticket.dao.SeatDAO;
import com.movieticket.model.Seat;

public class SeatUpdateTest {

    public static void main(String[] args) {

        Seat seat = new Seat(
                1,
                2,
                "A1",
                "PREMIUM",
                200.00
        );

        SeatDAO seatDAO = new SeatDAO();

        boolean result = seatDAO.updateSeat(seat);

        if (result) {
            System.out.println("Seat updated successfully!");
        } else {
            System.out.println("Seat not updated.");
        }
    }
}