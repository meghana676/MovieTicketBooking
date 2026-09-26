package com.movieticket.dao;

import com.movieticket.model.User;
import com.movieticket.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class UserDAO {

    private static final Logger LOGGER =
            Logger.getLogger(UserDAO.class.getName());

    private static final String FIND_USER_BY_EMAIL =
            "SELECT * FROM users WHERE email = ?";


    public User findUserByEmail(String email) {

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(FIND_USER_BY_EMAIL)) {

            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                return new User(
                        resultSet.getInt("user_id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        resultSet.getString("password"),
                        resultSet.getString("role")
                );
            }

        } catch (SQLException e) {

            LOGGER.severe("Database error: " + e.getMessage());
        }

        return null;
    }
}