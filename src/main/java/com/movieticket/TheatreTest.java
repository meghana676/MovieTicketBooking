package com.movieticket;

import com.movieticket.dao.TheatreDAO;

public class TheatreTest {

    public static void main(String[] args) {

        TheatreDAO theatreDAO = new TheatreDAO();

        boolean result = theatreDAO.deleteTheatre(1);

        if (result) {
            System.out.println("Theatre deleted successfully!");
        } else {
            System.out.println("Theatre deletion failed.");
        }
    }
}
