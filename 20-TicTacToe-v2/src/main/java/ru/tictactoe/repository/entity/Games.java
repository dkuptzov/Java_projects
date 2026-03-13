package ru.tictactoe.repository.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import ru.tictactoe.domain.model.Field;
import ru.tictactoe.util.MatrixJsonConverter;
import ru.tictactoe.web.model.GameStatus;

import java.io.IOException;
import java.util.Random;
import java.util.UUID;

@Entity
@Table(name = "games")
public class Games {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @Convert(converter = MatrixJsonConverter.class)
    @Column(nullable = false, columnDefinition = "TEXT")
    private int[][] fieldGame;

    @Column(nullable = false)
    private int currentPlayer;

    @Column(nullable = false)
    private int userPlayer;

    @Column(nullable = false)
    private int aiPlayer;

    @Enumerated(EnumType.STRING)
    private GameStatus status;

    public Games() {}

    public Games(Users user, int currentPlayer, int userPlayer, int aiPlayer) {
        this.user = user;
        this.currentPlayer = currentPlayer;
        this.userPlayer = userPlayer;
        this.aiPlayer = aiPlayer;
        this.status = GameStatus.IN_PROGRESS;
    }

    public static Games createNewGame(Users user) {
        Random random = new Random();
//        int currentPlayer = selectRandomPlayer();
        int currentPlayer = new Random().nextBoolean() ?
                Field.PLAYER_X.getValue() : Field.PLAYER_O.getValue();
        int userPlayer = new Random().nextBoolean() ?
                Field.PLAYER_X.getValue() : Field.PLAYER_O.getValue();
        int aiPlayer = (userPlayer == Field.PLAYER_X.getValue()) ? Field.PLAYER_O.getValue() : Field.PLAYER_X.getValue();

        Games game = new Games(user, currentPlayer, userPlayer, aiPlayer);

        // Создаем пустую доску
        int[][] emptyBoard = new int[3][3];
        // Если ход AI. Сразу ходим
        if (currentPlayer == aiPlayer) emptyBoard[0][0] = aiPlayer;
//        game.setMatrix(emptyBoard);
        game.setFieldGame(emptyBoard);
        return game;
    }

    public UUID getId() { return id; }
    public Users getUser() { return user; }
    public void setUser(Users user) { this.user = user; }
    public int getCurrentPlayer() { return currentPlayer; }
    public void setCurrentPlayer(int currentPlayer) { this.currentPlayer = currentPlayer; }
    public int getUserPlayer() { return userPlayer; }
    public void setHumanPlayer(int humanPlayer) { this.userPlayer = humanPlayer; }
    public int getAiPlayer() { return aiPlayer; }
    public void setAiPlayer(int aiPlayer) { this.aiPlayer = aiPlayer; }
    public void setStatus(GameStatus newStatus) { this.status = newStatus; }

    public void setFieldGame(int[][] fieldGame) {
        this.fieldGame = fieldGame;
    }

    public int[][] getFieldGame() {
        return fieldGame;
    }

//    public void setMatrix(int[][] matrix) {
//        try {
//            this.fieldGame = mapper.writeValueAsString(matrix);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException("Failed to serialize matrix", e);
//        }
//    }

//    public int[][] getMatrix() {
//        try {
//            return mapper.readValue(this.fieldGame, int[][].class);
//        } catch (IOException e) {
//            throw new RuntimeException("Failed to deserialize matrix", e);
//        }
//    }

//    private static int selectRandomPlayer() {
//        return new Random().nextBoolean() ?
//                Field.PLAYER_X.getValue() : Field.PLAYER_O.getValue();
//    }
}
