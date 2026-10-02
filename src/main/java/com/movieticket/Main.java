package com.movieticket;

import com.movieticket.controller.AdminController;
import com.movieticket.controller.CustomerController;
import com.movieticket.controller.LoginController;
import com.movieticket.model.User;

import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class Main {

    private static final Logger LOGGER =
            Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        configureLogging();

        LoginController loginController = new LoginController();

        User user = loginController.login();

        if (user == null) {

            LOGGER.info("Application closed.");
            return;
        }

        LOGGER.info("");
        LOGGER.info("Welcome to Movie Ticket System!");
        LOGGER.info("Logged in as: " + user.getRole());

        if (user.getRole().equals("ADMIN")) {

            AdminController adminController =
                    new AdminController();

            adminController.showMenu();

        } else if (user.getRole().equals("CUSTOMER")) {

            CustomerController customerController =
                    new CustomerController(user);

            customerController.showMenu();
        }
    }

    private static void configureLogging() {

        Logger rootLogger = Logger.getLogger("");

        for (Handler handler : rootLogger.getHandlers()) {

            handler.setFormatter(new Formatter() {

                @Override
                public String format(LogRecord record) {

                    return record.getMessage()
                            + System.lineSeparator();
                }
            });
        }
    }
}