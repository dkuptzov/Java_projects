package ru.tictactoe.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.tictactoe.domain.model.Field;
import ru.tictactoe.domain.service.TicTacToeService;
import ru.tictactoe.domain.service.ValidateGameService;
import ru.tictactoe.repository.entity.Pvp;
import ru.tictactoe.service.AuthService;
import ru.tictactoe.service.GameService;
import ru.tictactoe.web.model.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pvp")
public class PVPController {
    private final ValidateGameService validateGame;
    private final GameService gameService;
    private final AuthService authService;
    private final TicTacToeService ticTacToeService;

    public PVPController (GameService gameService,
                          ValidateGameService validateGame,
                          AuthService authService,
                          TicTacToeService ticTacToeService) {
        this.gameService = gameService;
        this.validateGame = validateGame;
        this.authService = authService;
        this.ticTacToeService = ticTacToeService;
    }

    @PostMapping("/new")
    public ResponseEntity<NewPVPStateResponse> startNewPVPGame(@AuthenticationPrincipal UUID userId) {
        Pvp newGame = gameService.createNewGamePVP(userId);
        Pvp savedGame = gameService.savePVPGame(newGame);
        NewPVPStateResponse response = new NewPVPStateResponse(
                savedGame.getId(),
                savedGame.getFieldGame(),
                GameStatus.WAITING,
                savedGame.getCurrentPlayer(),
                savedGame.getUserPlayer1()
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{gameId}/join")
    public ResponseEntity<?> joinNewGame(
            @PathVariable UUID gameId,
            @AuthenticationPrincipal UUID userId) {
        try {
            gameService.joinNewGamePVP(gameId, userId);
            UserJoinGameStateResponse response = new UserJoinGameStateResponse(
                    "You have successfully joined the game",
                    gameId
            );
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
                ErrorResponse error = new ErrorResponse(e.getMessage(), gameId);
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
        }
    }

    @GetMapping("/find")
    public ResponseEntity<?> findPVPGame(@AuthenticationPrincipal UUID userId) {
        List<Pvp> pvpList = gameService.getAllWaitingGame(GameStatus.WAITING);
        return ResponseEntity.status(HttpStatus.FOUND).body(pvpList.stream().
                filter(pvp -> {
                    boolean isUser1 = pvp.getUser1().getId().equals(userId);
                    boolean isUser2 = pvp.getUser2() != null && pvp.getUser2().getId().equals(userId);
                    return !isUser1 && !isUser2;
                    }).map(Pvp::getId).toList());
    }

    @GetMapping("/{gameId}/status")
    public ResponseEntity<?> getGameStatus(
            @PathVariable UUID gameId) {
        Pvp game = gameService.getGame(gameId);
        if (game == null) {
            return errorResponse(gameId);
        }
        return stateResponse(game.getStatus(), game);
    }

    @GetMapping("/current_game")
    public ResponseEntity<?> getCurrentGame(@AuthenticationPrincipal UUID userId) {
        List<Pvp> pvpList = gameService.getCurrentGame(userId);
        return ResponseEntity.ok(pvpList);
    }

    @PostMapping("/{gameId}")
    public ResponseEntity<?> makeMove(
            @PathVariable UUID gameId,
            @RequestBody PVPStateRequest request,
            @AuthenticationPrincipal UUID userId) {
        // Получаем игру по id игры и id игрока
        Pvp game = gameService.getGame(gameId, userId).orElse(null);
        // Игры не существует
        if (game == null) {
            return errorResponse(gameId);
        }
        // Проверка, что второй игрок присоединился к игре
        // Проверка, что игра еще продолжается
        if (game.getStatus() == GameStatus.WAITING) {
            return stateResponse(game.getStatus(), game);
        } else if (game.getStatus() != GameStatus.IN_PROGRESS) {
            return stateResponse(game.getStatus(), game);
        }
        // Проверка очередности хода
        if ((game.getUser1().getId() == userId && game.getCurrentPlayer() != 1) ||
        (game.getUser2().getId() == userId && game.getCurrentPlayer() != 2)) {
            return errorForbiddenResponse("It's not your turn", gameId);
        }
        // Проверка, что игра еще продолжается
        int[][] newBoard = deepCopy(request.getBoard());
        // Проверка текущего хода
        boolean isValid = validateGame.validateGame(
                newBoard,
                game.getFieldGame(),
                game.getCurrentPlayer());
        if (!isValid) {
            return errorForbiddenResponse("The game state has been modified", gameId);
        }
        // Проверка поля
        isValid = validateGame.validateBoard(game.getFieldGame());
        if (!isValid) {
            return errorForbiddenResponse("Board must be 3x3", gameId);
        }
        // Изменение текущей игры
        game.setFieldGame(newBoard);
        // Проверка закончилась ли игра
        GameStatus gameStatus = ticTacToeService.isGameFinished(newBoard);
        if (gameStatus == GameStatus.IN_PROGRESS) {
            int currentPlayer = game.getCurrentPlayer() == Field.PLAYER_X.getValue() ?
                    Field.PLAYER_O.getValue() : Field.PLAYER_X.getValue();
            game.setCurrentPlayer(currentPlayer);
        }
        game.setStatus(gameStatus);
        Pvp saveGame = gameService.savePVPGame(game);
        return stateResponse(game.getStatus(), saveGame);
    }

    private int[][] deepCopy(int[][] original) {
        int[][] copy = new int[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(original[i], 0, copy[i], 0, 3);
        }
        return copy;
    }

    private ResponseEntity<?> errorForbiddenResponse(String string, UUID gameId) {
        ErrorResponse error = new ErrorResponse(string, gameId);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

    private ResponseEntity<?> errorResponse(UUID gameId) {
        ErrorResponse error = new ErrorResponse("Game not found", gameId);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    private ResponseEntity<?> stateResponse(GameStatus gameStatus, Pvp game) {
        if (gameStatus == GameStatus.WAITING) {
            PVPIsWaitingStateResponse response = new PVPIsWaitingStateResponse(
                    game.getId(),
                    game.getFieldGame(),
                    game.getStatus(),
                    game.getCurrentPlayer()
            );
            return ResponseEntity.ok(response);
        } else if (gameStatus == GameStatus.PLAYER_X_WON || gameStatus == GameStatus.PLAYER_O_WON) {
            PVPIsEndAndWonStateResponse response = new PVPIsEndAndWonStateResponse(
                    game.getId(),
                    game.getFieldGame(),
                    playerWon(game),
                    game.getStatus()
            );
            return ResponseEntity.ok(response);
        } else if (gameStatus == GameStatus.DRAW) {
            PVPIsEndAndDrawStateResponse response = new PVPIsEndAndDrawStateResponse(
                    game.getId(),
                    game.getFieldGame(),
                    game.getStatus()
            );
            return ResponseEntity.ok(response);
        }
        else {
            PVPStateResponse response = new PVPStateResponse(
                    game.getId(),
                    game.getFieldGame(),
                    game.getStatus(),
                    game.getCurrentPlayer(),
                    currentPlayer(game)
            );
            return ResponseEntity.ok(response);
        }
    }

    private UUID playerWon(Pvp game) {
        if (game.getStatus() == GameStatus.PLAYER_X_WON && game.getUserPlayer1() == 1)
            return game.getUser1().getId();
        else if (game.getStatus() == GameStatus.PLAYER_O_WON && game.getUserPlayer1() == 2)
            return game.getUser1().getId();
        else if (game.getStatus() == GameStatus.PLAYER_X_WON && game.getUserPlayer2() == 1)
            return game.getUser2().getId();
        else if (game.getStatus() == GameStatus.PLAYER_O_WON && game.getUserPlayer2() == 2)
            return game.getUser2().getId();
        return null;
    }

    private UUID currentPlayer(Pvp game) {
        if (game.getCurrentPlayer() == game.getUserPlayer1())
            return game.getUser1().getId();
        else if (game.getCurrentPlayer() == game.getUserPlayer2())
            return game.getUser2().getId() == null ? null : game.getUser2().getId();
        return null;
    }
}