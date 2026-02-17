package ru.tictactoe.datasource.repository;

import ru.tictactoe.domain.model.CurrentGame;

import java.util.UUID;

public interface GameRepository {
    void saveGame(CurrentGame game);
    CurrentGame getGame(UUID gameId);
    void removeGame(UUID gameUUId);
}
