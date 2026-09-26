package com.movieticket.dao;

import com.movieticket.model.Payment;
import com.movieticket.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

public class PaymentDAO {

    private static final Logger LOGGER =
            Logger.getLogger(PaymentDAO.class.getName());

    private static final String INSERT_PAYMENT =
            "INSERT INTO payments " +
                    "(booking_id, amount, payment_method, payment_status) " +
                    "VALUES (?, ?, ?, ?)";


    public boolean addPayment(Payment payment) {

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(INSERT_PAYMENT)) {

            statement.setInt(1, payment.getBookingId());
            statement.setDouble(2, payment.getAmount());
            statement.setString(3, payment.getPaymentMethod());
            statement.setString(4, payment.getPaymentStatus());

            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {

            LOGGER.severe("Error adding payment: " + e.getMessage());

            return false;
        }
    }
}