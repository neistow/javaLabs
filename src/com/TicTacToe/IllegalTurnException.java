package com.TicTacToe;

public class IllegalTurnException extends Exception {
    public IllegalTurnException(String msg) {
        super(msg);
    }
}
