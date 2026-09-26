package com.movieticket.controller;

import com.movieticket.model.Seat;
import com.movieticket.service.SeatService;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class SeatController {

    private static final Logger LOGGER =
            Logger.getLogger(SeatController.class.getName());

    private final SeatService seatService;
    private final Scanner scanner;

    public SeatController() {
        seatService = new SeatService();
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

        LOGGER.info("Enter theatre ID: ");
        int theatreId = scanner.nextInt();
        scanner.nextLine();

        LOGGER.info("Enter seat number: ");
        String seatNumber = scanner.nextLine();

        LOGGER.info("Enter seat type: ");
        String seatType = scanner.nextLine();

        LOGGER.info("Enter price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        Seat seat = new Seat(
                0,
                theatreId,
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

        LOGGER.info("Enter theatre ID: ");
        int theatreId = scanner.nextInt();
        scanner.nextLine();

        List<Seat> seats = seatService.getSeatsByTheatre(theatreId);

        LOGGER.info("");
        LOGGER.info("===== SEATS =====");

        for (Seat seat : seats) {

            LOGGER.info(
                    seat.getSeatId() + " | " +
                            seat.getTheatreId() + " | " +
                            seat.getSeatNumber() + " | " +
                            seat.getSeatType() + " | " +
                            seat.getPrice()
            );
        }
    }

    private void updateSeat() {

        LOGGER.info("Enter seat ID: ");
        int seatId = scanner.nextInt();
        scanner.nextLine();

        LOGGER.info("Enter theatre ID: ");
        int theatreId = scanner.nextInt();
        scanner.nextLine();

        LOGGER.info("Enter new seat number: ");
        String seatNumber = scanner.nextLine();

        LOGGER.info("Enter new seat type: ");
        String seatType = scanner.nextLine();

        LOGGER.info("Enter new price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        Seat seat = new Seat(
                seatId,
                theatreId,
                seatNumber,
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

        LOGGER.info("Enter seat ID: ");
        int seatId = scanner.nextInt();
        scanner.nextLine();

        if (seatService.deleteSeat(seatId)) {

            LOGGER.info("Seat deleted successfully!");

        } else {

            LOGGER.warning("Seat could not be deleted.");
        }
    }
}