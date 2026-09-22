package com.movieticket.service;

import com.movieticket.dao.SeatDAO;
import com.movieticket.model.Seat;

import java.util.List;

public class SeatService {

    private final SeatDAO seatDAO;

    public SeatService() {
        this.seatDAO = new SeatDAO();
    }

    public boolean addSeat(Seat seat) {
        return seatDAO.addSeat(seat);
    }

    public List<Seat> getSeatsByTheatre(int theatreId) {
        return seatDAO.getSeatsByTheatre(theatreId);
    }

    public boolean updateSeat(Seat seat) {
        return seatDAO.updateSeat(seat);
    }

    public boolean deleteSeat(int seatId) {
        return seatDAO.deleteSeat(seatId);
    }
}
