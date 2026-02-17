package ru.tictactoe.web.model;

public enum GameStatus {
    IN_PROGRESS(0, "Игра продолжается"),
    PLAYER_X_WON(1, "Победили крестики (X)"),
    PLAYER_O_WON(2, "Победили нолики (O)"),
    DRAW(3, "Ничья");

    private final int code;
    private final String description;

    GameStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static GameStatus fromCode(int code) {
        for (GameStatus status : values()) {
            if (status.code == code) return status;
        }
        return IN_PROGRESS;
    }
}