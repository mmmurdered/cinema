package com.murdered.cinema.model;

public class Seat {
    private int id;
    private int rowSeat;
    private int colSeat;

    public Seat(){}

    public Seat(int id, int rowSeat, int colSeat) {
        this.rowSeat = rowSeat;
        this.colSeat = colSeat;
        this.id = id;
    }

    public int getRowSeat() {return rowSeat;}

    public void setRowSeat(int rowSeat) {
        this.rowSeat = rowSeat;
    }

    public int getColSeat() {
        return colSeat;
    }

    public void setColSeat(int colSeat) {
        this.colSeat = colSeat;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}
}
