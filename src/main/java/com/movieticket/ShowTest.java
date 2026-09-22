package com.movieticket;

import com.movieticket.dao.ShowDAO;

public class ShowTest {

    public static void main(String[] args) {

        ShowDAO showDAO = new ShowDAO();

        boolean result = showDAO.deleteShow(1);

        if (result) {
            System.out.println("Show deleted successfully!");
        } else {
            System.out.println("Show not deleted.");
        }
    }
}