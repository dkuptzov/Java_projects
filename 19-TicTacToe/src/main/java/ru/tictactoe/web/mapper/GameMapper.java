package ru.tictactoe.web.mapper;
import ru.tictactoe.web.model.GameStateResponse;
import ru.tictactoe.domain.model.CurrentGame;
import ru.tictactoe.web.model.GameStatus;

public class GameMapper {
    public static GameStateResponse toResponse(CurrentGame game, GameStatus gameStatus) {
        return new GameStateResponse(
                game.getGameUUID(),
                game.getBoard().getBoard(),
                gameStatus,
                game.getHumanPlayer());
    }
}