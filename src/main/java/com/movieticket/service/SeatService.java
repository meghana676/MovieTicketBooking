package com.movieticket.service;

import com.movieticket.dao.SeatDAO;
import com.movieticket.model.Seat;

import java.util.List;

public class SeatService {

    private final SeatDAO seatDAO;

    public SeatService() {
        this.seatDAO = new SeatDAO();
    }

    public SeatService(SeatDAO seatDAO) {
        this.seatDAO = seatDAO;
    }

    public boolean addSeat(Seat seat) {

        if (seat.getTheatreId() <= 0) {
            return false;
        }

        if (seat.getSeatNumber() == null ||
                seat.getSeatNumber().trim().isEmpty()) {
            return false;
        }

        if (seat.getSeatType() == null ||
                seat.getSeatType().trim().isEmpty()) {
            return false;
        }

        if (seat.getPrice() <= 0) {
            return false;
        }

        return seatDAO.addSeat(seat);
    }

    public List<Seat> getSeatsByTheatre(int theatreId) {

        if (theatreId <= 0) {
            return List.of();
        }

        return seatDAO.getSeatsByTheatre(theatreId);
    }

    public boolean updateSeat(Seat seat) {

        if (seat.getSeatId() <= 0) {
            return false;
        }

        if (seat.getTheatreId() <= 0) {
            return false;
        }

        if (seat.getSeatNumber() == null ||
                seat.getSeatNumber().trim().isEmpty()) {
            return false;
        }

        if (seat.getSeatType() == null ||
                seat.getSeatType().trim().isEmpty()) {
            return false;
        }

        if (seat.getPrice() <= 0) {
            return false;
        }

        return seatDAO.updateSeat(seat);
    }

    public boolean deleteSeat(int seatId) {

        if (seatId <= 0) {
            return false;
        }

        return seatDAO.deleteSeat(seatId);
    }
}