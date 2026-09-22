package com.movieticket;

import com.movieticket.model.User;
import com.movieticket.service.UserService;

public class DatabaseTest {

    public static void main(String[] args) {

        UserService userService = new UserService();

        User user = userService.findUserByEmail(
                "admin@movieticket.com"
        );

        if (user != null) {

            System.out.println("User found!");
            System.out.println("ID: " + user.getUserId());
            System.out.println("Name: " + user.getName());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Role: " + user.getRole());

        } else {

            System.out.println("User not found!");
        }
    }
}