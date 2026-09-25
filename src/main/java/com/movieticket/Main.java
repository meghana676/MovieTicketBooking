package com.movieticket;

import com.movieticket.controller.AdminController;
import com.movieticket.controller.LoginController;
import com.movieticket.model.User;
import com.movieticket.controller.AdminController;
import com.movieticket.controller.CustomerController;
import com.movieticket.controller.LoginController;
import com.movieticket.model.User;

public class Main {

    public static void main(String[] args) {

        LoginController loginController = new LoginController();

        User user = loginController.login();

        if (user == null) {
            System.out.println("Application closed.");
            return;
        }

        System.out.println();
        System.out.println("Welcome to Movie Ticket System!");
        System.out.println("Logged in as: " + user.getRole());

        if (user.getRole().equals("ADMIN")) {

            AdminController adminController = new AdminController();
            adminController.showMenu();

        } else if (user.getRole().equals("CUSTOMER")) {

            CustomerController customerController = new CustomerController(user);
            customerController.showMenu();
        }
    }
}