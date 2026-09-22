package com.movieticket;

import com.movieticket.dao.SeatDAO;

public class SeatDeleteTest {

    public static void main(String[] args) {

        SeatDAO seatDAO = new SeatDAO();

        boolean result = seatDAO.deleteSeat(1);

        if (result) {
            System.out.println("Seat deleted successfully!");
        } else {
            System.out.println("Seat not deleted.");
        }
    }
}