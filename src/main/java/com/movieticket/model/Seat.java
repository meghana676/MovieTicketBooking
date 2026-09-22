package com.movieticket.model;

public class Seat {

    private int seatId;
    private int theatreId;
    private String seatNumber;
    private String seatType;
    private double price;

    public Seat() {
    }

    public Seat(int seatId, int theatreId, String seatNumber,
                String seatType, double price) {
        this.seatId = seatId;
        this.theatreId = theatreId;
        this.seatNumber = seatNumber;
        this.seatType = seatType;
        this.price = price;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public int getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(int theatreId) {
        this.theatreId = theatreId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
