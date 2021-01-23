package com.TicTacToe;

import java.util.Scanner;

public class Game {
    private final Cell[][] field = new Cell[3][3];

    private int round = 0;
    private boolean isRunning = true;

    public void Start() {
        InitGame();

        while (isRunning) {
            try {
                PrintField();
                UpdateGame();
                TryFindWinner();
            } catch (IllegalTurnException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private void InitGame() {
        InitField();
    }

    private void InitField() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = new Cell();
            }
        }
    }

    private void PrintField() {
        var builder = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    builder.append("|");
                }
                builder.append(" ");
                builder.append(field[i][j].getState() == CellState.Empty ? " " : field[i][j].getState());
                builder.append(" ");
                builder.append("|");
            }
            builder.append("\n");
        }
        System.out.println(builder.toString());
    }

    private int GetCurrentPlayerNumber() {
        return round % 2 == 0 ? 1 : 2;
    }

    private void UpdateGame() throws IllegalTurnException {
        var playerNumber = GetCurrentPlayerNumber();
        System.out.println("Player " + playerNumber + ", it's your turn!");

        var scanner = new Scanner(System.in);
        var i = scanner.nextInt();
        var j = scanner.nextInt();

        if (i > 2 || i < 0 || j > 2 || j < 0) {
            throw new IllegalTurnException("Illegal turn:" + i + " " + j);
        }

        if (field[i][j].getState() != CellState.Empty) {
            throw new IllegalTurnException("Cell is already marked!");
        }

        var state = playerNumber == 1 ? CellState.X : CellState.O;
        field[i][j].setState(state);

        round++;
    }

    private void TryFindWinner() {
        for (int i = 0; i < 3; i++) {
            if ((field[i][0].getState() == field[i][1].getState()) && (field[i][0].getState() == field[i][2].getState())) {
                if (field[i][0].getState() != CellState.Empty) {
                    EndGame(field[i][0].getState().toString() + " is winner");
                    return;
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            if ((field[0][i].getState() == field[1][i].getState()) && (field[0][i].getState() == field[2][i].getState())) {
                if (field[0][i].getState() != CellState.Empty) {
                    EndGame(field[0][i].getState().toString() + " is winner");
                    return;
                }
            }
        }

        if ((field[0][0].getState() == field[1][1].getState()) && (field[0][0].getState() == field[2][2].getState())) {
            if (field[0][0].getState() != CellState.Empty) {
                EndGame(field[0][0].getState().toString() + " is winner");
                return;
            }
        }

        if ((field[0][2].getState() == field[1][1].getState()) && (field[0][2].getState() == field[2][0].getState())) {
            if (field[0][2].getState() != CellState.Empty) {
                EndGame(field[0][2].getState().toString() + " is winner");
                return;
            }
        }

        int emptyCells = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                emptyCells = field[i][j].getState() == CellState.Empty ? ++emptyCells : emptyCells;
            }
        }

        if(emptyCells == 0){
            EndGame("Draw!");
        }
    }

    private void EndGame(String msg) {
        System.out.println(msg);
        isRunning = false;
        PrintField();
    }
}
