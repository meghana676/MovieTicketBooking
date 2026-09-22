package com.movieticket;

import com.movieticket.model.Show;
import com.movieticket.service.ShowService;

import java.util.List;

public class ShowServiceTest {

    public static void main(String[] args) {

        ShowService showService = new ShowService();

        List<Show> shows = showService.getAllShows();

        System.out.println("Shows found:");

        for (Show show : shows) {
            System.out.println(
                    show.getShowId() + " | " +
                            show.getTheatreId() + " | " +
                            show.getMovieId() + " | " +
                            show.getShowDate() + " | " +
                            show.getStartTime() + " | " +
                            show.getEndTime()
            );
        }
    }
}