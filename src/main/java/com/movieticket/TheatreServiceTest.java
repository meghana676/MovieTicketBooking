package com.movieticket;

import com.movieticket.model.Theatre;
import com.movieticket.service.TheatreService;

import java.util.List;

public class TheatreServiceTest {

    public static void main(String[] args) {

        TheatreService theatreService = new TheatreService();

        List<Theatre> theatres = theatreService.getAllTheatres();

        System.out.println("Theatres found:");

        for (Theatre theatre : theatres) {
            System.out.println(
                    theatre.getTheatreId() + " | " +
                            theatre.getName() + " | " +
                            theatre.getCity()
            );
        }
    }
}