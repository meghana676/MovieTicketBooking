package com.movieticket;

import com.movieticket.controller.LoginController;
import com.movieticket.model.User;

public class LoginControllerTest {

    public static void main(String[] args) {

        LoginController loginController = new LoginController();

        User user = loginController.login();

        if (user != null) {
            System.out.println("Logged in as: " + user.getRole());
        }
    }
}