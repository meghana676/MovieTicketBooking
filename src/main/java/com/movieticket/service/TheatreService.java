package com.movieticket.service;

import com.movieticket.dao.TheatreDAO;
import com.movieticket.model.Theatre;

import java.util.List;

public class TheatreService {

    private final TheatreDAO theatreDAO;

    public TheatreService() {
        this.theatreDAO = new TheatreDAO();
    }

    public TheatreService(TheatreDAO theatreDAO) {
        this.theatreDAO = theatreDAO;
    }

    public boolean addTheatre(Theatre theatre) {

        if (theatre.getName() == null || theatre.getName().trim().isEmpty()) {
            return false;
        }

        if (theatre.getCity() == null || theatre.getCity().trim().isEmpty()) {
            return false;
        }

        if (theatre.getAddress() == null || theatre.getAddress().trim().isEmpty()) {
            return false;
        }

        if (theatre.getTotalSeats() <= 0) {
            return false;
        }

        return theatreDAO.addTheatre(theatre);
    }

    public List<Theatre> getAllTheatres() {
        return theatreDAO.getAllTheatres();
    }

    public boolean updateTheatre(Theatre theatre) {

        if (theatre.getTheatreId() <= 0) {
            return false;
        }

        if (theatre.getName() == null || theatre.getName().trim().isEmpty()) {
            return false;
        }

        if (theatre.getCity() == null || theatre.getCity().trim().isEmpty()) {
            return false;
        }

        if (theatre.getAddress() == null || theatre.getAddress().trim().isEmpty()) {
            return false;
        }

        if (theatre.getTotalSeats() <= 0) {
            return false;
        }

        return theatreDAO.updateTheatre(theatre);
    }

    public boolean deleteTheatre(int theatreId) {

        if (theatreId <= 0) {
            return false;
        }

        return theatreDAO.deleteTheatre(theatreId);
    }
}