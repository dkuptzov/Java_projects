package ru.tictactoe.web.controller;

import ru.tictactoe.domain.model.MoveResult;
import ru.tictactoe.domain.service.ValidateGameService;
import ru.tictactoe.web.mapper.GameMapper;
import ru.tictactoe.web.model.ErrorResponse;
import ru.tictactoe.web.model.GameStateRequest;
import ru.tictactoe.web.model.GameStateResponse;
import ru.tictactoe.domain.service.TicTacToeService;
import ru.tictactoe.datasource.repository.GameRepository;
import ru.tictactoe.domain.model.CurrentGame;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tictactoe.web.model.GameStatus;

import java.util.UUID;

@RestController
@RequestMapping("/game")
public class TicTacToeController {
    private final TicTacToeService ticTacToeService;
    private final GameRepository gameRepository;
    private final ValidateGameService validateGame;

    public TicTacToeController(TicTacToeService ticTacToeService,
                               GameRepository gameRepository,
                               ValidateGameService validateGame) {
        this.ticTacToeService = ticTacToeService;
        this.gameRepository = gameRepository;
        this.validateGame = validateGame;
    }

    @PostMapping
    public ResponseEntity<GameStateResponse> startNewGame() {
        CurrentGame newGame = new CurrentGame();
        if (newGame.getCurrentPlayer() == newGame.getAiPlayer()) {
            newGame.getBoard().setPos(0, 0, newGame.getCurrentPlayer());
        }
        newGame.setCurrentPlayer(newGame.getAiPlayer());
        gameRepository.saveGame(newGame);
        GameStateResponse response = new GameStateResponse(
                newGame.getGameUUID(),
                newGame.getBoard().getBoard(),
                GameStatus.IN_PROGRESS,
                newGame.getHumanPlayer());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{gameId}")
    public ResponseEntity<?> makeMove(
            @PathVariable UUID gameId,
            @RequestBody GameStateRequest request) {
        System.out.println("=== NEW REQUEST ===");
        System.out.println("Game ID: " + gameId);
        System.out.println("Board received:");
        int[][] newBoard = deepCopy(request.getBoard());
        CurrentGame previousGame = gameRepository.getGame(gameId);
        if (previousGame == null) {
            ErrorResponse error = new ErrorResponse("Game not found with ID: " + gameId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        boolean isValid = validateGame.validateGame(
                newBoard,
                previousGame.getBoard().getBoard(),
                previousGame.getHumanPlayer());
        System.out.println("isValid: " + isValid);
        if (!isValid) {
            ErrorResponse error = new ErrorResponse("The game has been changed: " + gameId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        isValid = validateGame.validateBoard(request.getBoard());
        if (!isValid) {
            ErrorResponse error = new ErrorResponse("Wrong field: " + gameId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        int gameStatus = ticTacToeService.isGameFinished(newBoard);
        GameStateResponse response = new GameStateResponse();
        if (gameStatus == 0) {
            MoveResult aiMoveResult = ticTacToeService.getAIMove(newBoard, previousGame.getAiPlayer(), previousGame.getHumanPlayer(), previousGame.getCurrentPlayer());
            newBoard[aiMoveResult.getRow()][aiMoveResult.getCol()] = previousGame.getAiPlayer();
            CurrentGame updatedGame = new CurrentGame(
                    newBoard,
                    gameId,
                    previousGame.getCurrentPlayer(),
                    previousGame.getHumanPlayer(),
                    previousGame.getAiPlayer());
            gameRepository.saveGame(updatedGame);
            response = GameMapper.toResponse(updatedGame, GameStatus.IN_PROGRESS);
            gameStatus = ticTacToeService.isGameFinished(newBoard);
        }
        if (gameStatus != 0) {
            CurrentGame updatedGame = new CurrentGame(
                    newBoard,
                    gameId,
                    previousGame.getCurrentPlayer(),
                    previousGame.getHumanPlayer(),
                    previousGame.getAiPlayer());
            if (gameStatus == 1)
                response = GameMapper.toResponse(updatedGame, GameStatus.PLAYER_X_WON);
            else if (gameStatus == 2)
                response = GameMapper.toResponse(updatedGame, GameStatus.PLAYER_O_WON);
            else response = GameMapper.toResponse(updatedGame, GameStatus.DRAW);
            gameRepository.removeGame(gameId);
        }
        return ResponseEntity.ok(response);
    }

    private int[][] deepCopy(int[][] original) {
        int[][] copy = new int[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(original[i], 0, copy[i], 0, 3);
        }
        return copy;
    }
}