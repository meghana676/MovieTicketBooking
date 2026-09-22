package com.movieticket.dao;

import com.movieticket.model.Theatre;
import com.movieticket.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TheatreDAO {

    public boolean addTheatre(Theatre theatre) {

        String sql = "INSERT INTO theatres " +
                "(name, city, address, total_seats) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, theatre.getName());
            statement.setString(2, theatre.getCity());
            statement.setString(3, theatre.getAddress());
            statement.setInt(4, theatre.getTotalSeats());

            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<Theatre> getAllTheatres() {

        List<Theatre> theatres = new ArrayList<>();

        String sql = "SELECT * FROM theatres";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Theatre theatre = new Theatre(
                        resultSet.getInt("theatre_id"),
                        resultSet.getString("name"),
                        resultSet.getString("city"),
                        resultSet.getString("address"),
                        resultSet.getInt("total_seats")
                );

                theatres.add(theatre);
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }

        return theatres;
    }

    public boolean updateTheatre(Theatre theatre) {

        String sql = "UPDATE theatres SET " +
                "name = ?, " +
                "city = ?, " +
                "address = ?, " +
                "total_seats = ? " +
                "WHERE theatre_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, theatre.getName());
            statement.setString(2, theatre.getCity());
            statement.setString(3, theatre.getAddress());
            statement.setInt(4, theatre.getTotalSeats());
            statement.setInt(5, theatre.getTheatreId());

            int rowsUpdated = statement.executeUpdate();

            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteTheatre(int theatreId) {

        String sql = "DELETE FROM theatres WHERE theatre_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, theatreId);

            int rowsDeleted = statement.executeUpdate();

            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
