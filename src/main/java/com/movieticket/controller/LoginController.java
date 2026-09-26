package com.movieticket.controller;

import com.movieticket.model.User;
import com.movieticket.service.UserService;

import java.util.Scanner;
import java.util.logging.Logger;

public class LoginController {

    private static final Logger LOGGER =
            Logger.getLogger(LoginController.class.getName());

    private final UserService userService;

    public LoginController() {
        this.userService = new UserService();
    }

    public User login() {

        Scanner scanner = new Scanner(System.in);

        LOGGER.info("===== Movie Ticket Login =====");

        LOGGER.info("Enter email: ");
        String email = scanner.nextLine();

        LOGGER.info("Enter password: ");
        String password = scanner.nextLine();

        User user = userService.findUserByEmail(email);

        if (user != null && user.getPassword().equals(password)) {

            LOGGER.info("Login successful!");
            LOGGER.info("Welcome, " + user.getName());

            return user;
        }

        LOGGER.warning("Invalid email or password.");

        return null;
    }
}
