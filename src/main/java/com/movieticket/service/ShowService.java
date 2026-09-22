package com.movieticket.service;

import com.movieticket.dao.ShowDAO;
import com.movieticket.model.Show;

import java.util.List;

public class ShowService {

    private final ShowDAO showDAO;

    public ShowService() {
        this.showDAO = new ShowDAO();
    }

    public boolean addShow(Show show) {
        return showDAO.addShow(show);
    }

    public List<Show> getAllShows() {
        return showDAO.getAllShows();
    }

    public boolean updateShow(Show show) {
        return showDAO.updateShow(show);
    }

    public boolean deleteShow(int showId) {
        return showDAO.deleteShow(showId);
    }
}
