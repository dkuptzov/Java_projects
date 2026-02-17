package ru.tictactoe.datasource.mapper;

import ru.tictactoe.datasource.model.GameEntity;
import ru.tictactoe.domain.model.CurrentGame;

public class GameMapper {
    public GameEntity toEntity(CurrentGame domainGame) {
        int[][] board = domainGame.getBoard().getBoard();
        return new GameEntity(
                board,
                domainGame.getGameUUID(),
                domainGame.getCurrentPlayer(),
                domainGame.getHumanPlayer(),
                domainGame.getAiPlayer());
    }

    public CurrentGame toDomain(GameEntity entity) {
        return new CurrentGame(
                entity.getBoard(),
                entity.getGameUUID(),
                entity.getCurrentPlayer(),
                entity.getHumanPlayer(),
                entity.getAiPlayer()
        );
    }
}
