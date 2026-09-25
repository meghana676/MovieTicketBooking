package com.movieticket.controller;

import com.movieticket.model.User;
import com.movieticket.service.UserService;

import java.util.Scanner;

public class LoginController {

    private final UserService userService;

    public LoginController() {
        this.userService = new UserService();
    }

    public User login() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("===== Movie Ticket Login =====");

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = userService.findUserByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Login successful!");
            System.out.println("Welcome, " + user.getName());
            return user;
        }

        System.out.println("Invalid email or password.");
        return null;
    }
}
