package com.TicTacToe;



public class Cell {
    private CellState State = CellState.Empty;

    public CellState getState() {
        return State;
    }

    public void setState(CellState state) {
        State = state;
    }
}
