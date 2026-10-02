package com.movieticket.controller;

import com.movieticket.model.Seat;
import com.movieticket.model.Theatre;
import com.movieticket.service.SeatService;
import com.movieticket.service.TheatreService;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class SeatController {

    private static final Logger LOGGER =
            Logger.getLogger(SeatController.class.getName());

    private final SeatService seatService;
    private final TheatreService theatreService;
    private final Scanner scanner;

    public SeatController() {
        seatService = new SeatService();
        theatreService = new TheatreService();
        scanner = new Scanner(System.in);
    }

    public void showMenu() {

        while (true) {

            LOGGER.info("");
            LOGGER.info("===== SEAT MANAGEMENT =====");
            LOGGER.info("1. Add Seat");
            LOGGER.info("2. View Seats");
            LOGGER.info("3. Update Seat");
            LOGGER.info("4. Delete Seat");
            LOGGER.info("5. Back");
            LOGGER.info("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    addSeat();
                    break;

                case 2:
                    viewSeats();
                    break;

                case 3:
                    updateSeat();
                    break;

                case 4:
                    deleteSeat();
                    break;

                case 5:
                    return;

                default:
                    LOGGER.warning("Invalid choice.");
            }
        }
    }

    private void addSeat() {

        LOGGER.info("Enter theatre name:");
        String theatreName = scanner.nextLine();

        Theatre selectedTheatre =
                findTheatreByName(theatreName);

        if (selectedTheatre == null) {
            LOGGER.warning("Theatre not found.");
            return;
        }

        LOGGER.info("Enter seat number:");
        String seatNumber = scanner.nextLine();

        LOGGER.info("Enter seat type:");
        String seatType = scanner.nextLine();

        LOGGER.info("Enter price:");
        double price = scanner.nextDouble();
        scanner.nextLine();

        Seat seat = new Seat(
                0,
                selectedTheatre.getTheatreId(),
                seatNumber,
                seatType,
                price
        );

        if (seatService.addSeat(seat)) {

            LOGGER.info("Seat added successfully!");

        } else {

            LOGGER.warning("Seat could not be added.");
        }
    }

    private void viewSeats() {

        LOGGER.info("Enter theatre name:");
        String theatreName = scanner.nextLine();

        Theatre selectedTheatre =
                findTheatreByName(theatreName);

        if (selectedTheatre == null) {
            LOGGER.warning("Theatre not found.");
            return;
        }

        List<Seat> seats =
                seatService.getSeatsByTheatre(
                        selectedTheatre.getTheatreId()
                );

        LOGGER.info("");
        LOGGER.info(
                "===== SEATS - " +
                        selectedTheatre.getName() +
                        " ====="
        );

        if (seats.isEmpty()) {
            LOGGER.info("No seats available.");
            return;
        }

        for (Seat seat : seats) {

            LOGGER.info(
                    seat.getSeatNumber() + " | " +
                            seat.getSeatType() + " | " +
                            seat.getPrice()
            );
        }
    }

    private void updateSeat() {

        LOGGER.info("Enter theatre name:");
        String theatreName = scanner.nextLine();

        Theatre selectedTheatre =
                findTheatreByName(theatreName);

        if (selectedTheatre == null) {
            LOGGER.warning("Theatre not found.");
            return;
        }

        LOGGER.info("Enter seat number:");
        String seatNumber = scanner.nextLine();

        List<Seat> seats =
                seatService.getSeatsByTheatre(
                        selectedTheatre.getTheatreId()
                );

        Seat selectedSeat = null;

        for (Seat seat : seats) {

            if (seat.getSeatNumber()
                    .equalsIgnoreCase(seatNumber.trim())) {

                selectedSeat = seat;
                break;
            }
        }

        if (selectedSeat == null) {
            LOGGER.warning("Seat not found.");
            return;
        }

        LOGGER.info(
                "Selected seat: " +
                        selectedSeat.getSeatNumber()
        );

        LOGGER.info("Enter new seat number:");
        String newSeatNumber = scanner.nextLine();

        LOGGER.info("Enter new seat type:");
        String seatType = scanner.nextLine();

        LOGGER.info("Enter new price:");
        double price = scanner.nextDouble();
        scanner.nextLine();

        Seat seat = new Seat(
                selectedSeat.getSeatId(),
                selectedTheatre.getTheatreId(),
                newSeatNumber,
                seatType,
                price
        );

        if (seatService.updateSeat(seat)) {

            LOGGER.info("Seat updated successfully!");

        } else {

            LOGGER.warning("Seat could not be updated.");
        }
    }

    private void deleteSeat() {

        LOGGER.info("Enter theatre name:");
        String theatreName = scanner.nextLine();

        Theatre selectedTheatre =
                findTheatreByName(theatreName);

        if (selectedTheatre == null) {
            LOGGER.warning("Theatre not found.");
            return;
        }

        LOGGER.info("Enter seat number:");
        String seatNumber = scanner.nextLine();

        List<Seat> seats =
                seatService.getSeatsByTheatre(
                        selectedTheatre.getTheatreId()
                );

        Seat selectedSeat = null;

        for (Seat seat : seats) {

            if (seat.getSeatNumber()
                    .equalsIgnoreCase(seatNumber.trim())) {

                selectedSeat = seat;
                break;
            }
        }

        if (selectedSeat == null) {
            LOGGER.warning("Seat not found.");
            return;
        }

        if (seatService.deleteSeat(
                selectedSeat.getSeatId())) {

            LOGGER.info("Seat deleted successfully!");

        } else {

            LOGGER.warning("Seat could not be deleted.");
        }
    }

    private Theatre findTheatreByName(String theatreName) {

        if (theatreName == null ||
                theatreName.trim().isEmpty()) {
            return null;
        }

        List<Theatre> theatres =
                theatreService.getAllTheatres();

        for (Theatre theatre : theatres) {

            if (theatre.getName()
                    .equalsIgnoreCase(theatreName.trim())) {

                return theatre;
            }
        }

        return null;
    }
}