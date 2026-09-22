package com.movieticket;

import com.movieticket.dao.MovieDAO;

public class MovieTest {

    public static void main(String[] args) {

        MovieDAO movieDAO = new MovieDAO();

        boolean result = movieDAO.deleteMovie(1);

        if (result) {
            System.out.println("Movie deleted successfully!");
        } else {
            System.out.println("Movie deletion failed.");
        }
    }
}