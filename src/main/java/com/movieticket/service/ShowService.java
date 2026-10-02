package com.movieticket.service;

import com.movieticket.dao.ShowDAO;
import com.movieticket.model.Show;

import java.util.List;

public class ShowService {

    private final ShowDAO showDAO;

    public ShowService() {
        this.showDAO = new ShowDAO();
    }

    public ShowService(ShowDAO showDAO) {
        this.showDAO = showDAO;
    }

    public boolean addShow(Show show) {

        if (show.getTheatreId() <= 0) {
            return false;
        }

        if (show.getMovieId() <= 0) {
            return false;
        }

        if (show.getShowDate() == null) {
            return false;
        }

        if (show.getStartTime() == null) {
            return false;
        }

        if (show.getEndTime() == null) {
            return false;
        }

        return showDAO.addShow(show);
    }

    public List<Show> getAllShows() {
        return showDAO.getAllShows();
    }

    public boolean updateShow(Show show) {

        if (show.getShowId() <= 0) {
            return false;
        }

        if (show.getTheatreId() <= 0) {
            return false;
        }

        if (show.getMovieId() <= 0) {
            return false;
        }

        if (show.getShowDate() == null) {
            return false;
        }

        if (show.getStartTime() == null) {
            return false;
        }

        if (show.getEndTime() == null) {
            return false;
        }

        return showDAO.updateShow(show);
    }
    public List<Show> getShowsByMovieAndTheatre(int movieId, int theatreId) {

        if (movieId <= 0 || theatreId <= 0) {
            return List.of();
        }

        return showDAO.getShowsByMovieAndTheatre(movieId, theatreId);
    }

    public boolean deleteShow(int showId) {

        if (showId <= 0) {
            return false;
        }

        return showDAO.deleteShow(showId);
    }
}