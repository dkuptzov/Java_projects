package ru.tictactoe.web.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import ru.tictactoe.domain.model.MoveResult;
import ru.tictactoe.domain.service.ValidateGameService;
import ru.tictactoe.repository.entity.Games;
import ru.tictactoe.service.AuthService;
import ru.tictactoe.service.GameService;
import ru.tictactoe.web.model.*;
import ru.tictactoe.domain.service.TicTacToeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/game")
public class TicTacToeController {
    private final TicTacToeService ticTacToeService;
    private final ValidateGameService validateGame;
    private final GameService gameService;
    private final AuthService authService;

    public TicTacToeController(TicTacToeService ticTacToeService,
                               GameService gameService,
                               ValidateGameService validateGame,
                               AuthService authService) {
        this.ticTacToeService = ticTacToeService;
        this.gameService = gameService;
        this.validateGame = validateGame;
        this.authService = authService;
    }

    @PostMapping("/new")
    public ResponseEntity<GameStateResponse> startNewGame(@AuthenticationPrincipal UUID userId) {
        Games newGame = gameService.createNewGame(userId);
        Games savedGame = gameService.saveGame(newGame);
        GameStateResponse response = new GameStateResponse(
                savedGame.getId(),
                savedGame.getFieldGame(),
                GameStatus.IN_PROGRESS,
                savedGame.getUserPlayer());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{gameId}")
    public ResponseEntity<?> makeMove(
            @PathVariable UUID gameId,
            @RequestBody GameStateRequest request,
            @AuthenticationPrincipal UUID userId) {
        System.out.println("=== NEW REQUEST ===");
        System.out.println("Game ID: " + gameId);
        System.out.println("Board received:");
        int[][] newBoard = deepCopy(request.getBoard());
        Games previousGame = gameService.findGame(gameId);
        System.out.println(userId);
        if (previousGame == null) {
            ErrorResponse error = new ErrorResponse("Game not found", gameId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } else if (!previousGame.getUser().getId().equals(userId)) {
            System.out.println(previousGame.getUser().getId());
            ErrorResponse error = new ErrorResponse("Game not found", gameId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        boolean isValid = validateGame.validateGame(
                newBoard,
                previousGame.getFieldGame(),
                previousGame.getUserPlayer());
        if (!isValid) {
            ErrorResponse error = new ErrorResponse("The game state has been modified", gameId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        isValid = validateGame.validateBoard(request.getBoard());
        if (!isValid) {
            ErrorResponse error = new ErrorResponse("Board must be 3x3", gameId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        GameStatus gameStatus = ticTacToeService.isGameFinished(newBoard);
        if (gameStatus == GameStatus.IN_PROGRESS) {
            MoveResult aiMoveResult = ticTacToeService.getAIMove(
                    newBoard,
                    previousGame.getAiPlayer(),
                    previousGame.getUserPlayer(),
                    previousGame.getAiPlayer());
            newBoard[aiMoveResult.getRow()][aiMoveResult.getCol()] = previousGame.getAiPlayer();
            previousGame.setFieldGame(newBoard);
            previousGame = gameService.saveGame(previousGame);
            gameStatus = ticTacToeService.isGameFinished(newBoard);
        }
        if (gameStatus != GameStatus.IN_PROGRESS) {
            previousGame.setStatus(gameStatus);
            previousGame = gameService.saveGame(previousGame);
        }
        GameStateResponse response =  new GameStateResponse(
                previousGame.getId(),
                previousGame.getFieldGame(),
                gameStatus,
                previousGame.getUserPlayer()
        );
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