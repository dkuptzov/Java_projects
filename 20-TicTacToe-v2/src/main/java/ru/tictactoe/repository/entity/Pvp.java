package ru.tictactoe.repository.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import ru.tictactoe.domain.model.Field;
import ru.tictactoe.util.MatrixJsonConverter;
import ru.tictactoe.web.model.GameStatus;

import java.util.Random;
import java.util.UUID;

@Entity
@Table(name = "pvp")
public class Pvp {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id1", nullable = false)
    private Users user1;

    @ManyToOne
    @JoinColumn(name = "user_id2")
    private Users user2;

    @Convert(converter = MatrixJsonConverter.class)
    @Column(nullable = false, columnDefinition = "TEXT")
    private int[][] fieldGame;

    @Column(nullable = false)
    private int currentPlayer;

    @Column(nullable = false)
    private int userPlayer1;

    @Column(nullable = false)
    private int userPlayer2;

    @Enumerated(EnumType.STRING)
    private GameStatus status;

    public Pvp() {}

    public Pvp(Users user1, int currentPlayer, int userPlayer1, int userPlayer2) {
        this.user1 = user1;
        this.currentPlayer = currentPlayer;
        this.userPlayer1 = userPlayer1;
        this.userPlayer2 = userPlayer2;
        this.status = GameStatus.WAITING;
    }

    public static Pvp createNewGame(Users user1) {
        int currentPlayer = new Random().nextBoolean() ?
                Field.PLAYER_X.getValue() : Field.PLAYER_O.getValue();
        int userPlayer1 = new Random().nextBoolean() ?
                Field.PLAYER_X.getValue() : Field.PLAYER_O.getValue();
        int userPlayer2 = (userPlayer1 == Field.PLAYER_X.getValue()) ? Field.PLAYER_O.getValue() : Field.PLAYER_X.getValue();

        Pvp game = new Pvp(user1, currentPlayer, userPlayer1, userPlayer2);
        // Создаем пустую доску
        game.fieldGame = new int[3][3];
        return game;
    }

    public UUID getId() { return id; }
    public Users getUser1() { return user1; }
    public Users getUser2() { return user2; }
    public int getUserPlayer1() { return userPlayer1; }
    public int getUserPlayer2() { return userPlayer2; }
    public GameStatus getStatus() { return status; }
    public int getCurrentPlayer() { return currentPlayer; }
    public void setCurrentPlayer(int currentPlayer) { this.currentPlayer = currentPlayer; }
    public void setUser2(Users user2) { this.user2 = user2; }
    public void setStatus(GameStatus newStatus) { this.status = newStatus; }

    public void setFieldGame(int[][] fieldGame) {
        this.fieldGame = fieldGame;
    }

    public int[][] getFieldGame() {
        return fieldGame;
    }
}
