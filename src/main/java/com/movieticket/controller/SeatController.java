package com.movieticket.controller;

import com.movieticket.model.Seat;
import com.movieticket.service.SeatService;

import java.util.List;
import java.util.Scanner;

public class SeatController {

    private final SeatService seatService;
    private final Scanner scanner;

    public SeatController() {
        seatService = new SeatService();
        scanner = new Scanner(System.in);
    }

    public void showMenu() {

        while (true) {

            System.out.println();
            System.out.println("===== SEAT MANAGEMENT =====");
            System.out.println("1. Add Seat");
            System.out.println("2. View Seats");
            System.out.println("3. Update Seat");
            System.out.println("4. Delete Seat");
            System.out.println("5. Back");
            System.out.print("Enter your choice: ");

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
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void addSeat() {

        System.out.print("Enter theatre ID: ");
        int theatreId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter seat number: ");
        String seatNumber = scanner.nextLine();

        System.out.print("Enter seat type: ");
        String seatType = scanner.nextLine();

        System.out.print("Enter price: ");
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
            System.out.println("Seat added successfully!");
        } else {
            System.out.println("Seat could not be added.");
        }
    }

    private void viewSeats() {

        System.out.print("Enter theatre ID: ");
        int theatreId = scanner.nextInt();
        scanner.nextLine();

        List<Seat> seats = seatService.getSeatsByTheatre(theatreId);

        System.out.println();
        System.out.println("===== SEATS =====");

        for (Seat seat : seats) {

            System.out.println(
                    seat.getSeatId() + " | " +
                            seat.getTheatreId() + " | " +
                            seat.getSeatNumber() + " | " +
                            seat.getSeatType() + " | " +
                            seat.getPrice()
            );
        }
    }

    private void updateSeat() {

        System.out.print("Enter seat ID: ");
        int seatId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter theatre ID: ");
        int theatreId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter new seat number: ");
        String seatNumber = scanner.nextLine();

        System.out.print("Enter new seat type: ");
        String seatType = scanner.nextLine();

        System.out.print("Enter new price: ");
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
            System.out.println("Seat updated successfully!");
        } else {
            System.out.println("Seat could not be updated.");
        }
    }

    private void deleteSeat() {

        System.out.print("Enter seat ID: ");
        int seatId = scanner.nextInt();
        scanner.nextLine();

        if (seatService.deleteSeat(seatId)) {
            System.out.println("Seat deleted successfully!");
        } else {
            System.out.println("Seat could not be deleted.");
        }
    }
}