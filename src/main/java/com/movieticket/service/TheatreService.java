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
        return theatreDAO.addTheatre(theatre);
    }

    public List<Theatre> getAllTheatres() {
        return theatreDAO.getAllTheatres();
    }

    public boolean updateTheatre(Theatre theatre) {
        return theatreDAO.updateTheatre(theatre);
    }

    public boolean deleteTheatre(int theatreId) {
        return theatreDAO.deleteTheatre(theatreId);
    }
}