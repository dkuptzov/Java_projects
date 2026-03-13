package ru.tictactoe.domain.model;

public enum Field {
    EMPTY(0),
    PLAYER_X(1),
    PLAYER_O(2);

    private final int value;

    Field(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
