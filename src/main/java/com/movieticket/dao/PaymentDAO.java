package com.movieticket.dao;

import com.movieticket.model.Payment;
import com.movieticket.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PaymentDAO {

    private static final Logger LOGGER =
            Logger.getLogger(PaymentDAO.class.getName());

    private static final String INSERT_PAYMENT =
            "INSERT INTO payments " +
                    "(booking_id, amount, payment_method, payment_status) " +
                    "VALUES (?, ?, ?, ?)";

    private static final String GET_ALL_PAYMENTS =
            "SELECT * FROM payments";


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

            LOGGER.severe(
                    "Error adding payment: " + e.getMessage()
            );

            return false;
        }
    }


    public List<Payment> getAllPayments() {

        List<Payment> payments = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(GET_ALL_PAYMENTS);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Payment payment = new Payment(
                        resultSet.getInt("payment_id"),
                        resultSet.getInt("booking_id"),
                        resultSet.getDouble("amount"),
                        resultSet.getString("payment_method"),
                        resultSet.getString("payment_status"),
                        resultSet.getTimestamp("payment_date")
                                .toLocalDateTime()
                );

                payments.add(payment);
            }

        } catch (SQLException e) {

            LOGGER.severe(
                    "Error getting payments: " + e.getMessage()
            );
        }

        return payments;
    }
}