package com.movieticket.service;

import com.movieticket.dao.UserDAO;
import com.movieticket.model.User;

public class UserService {

    private final UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public User findUserByEmail(String email) {
        return userDAO.findUserByEmail(email);
    }
}