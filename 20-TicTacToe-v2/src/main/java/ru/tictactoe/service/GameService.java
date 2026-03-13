package ru.tictactoe.service;

import org.springframework.stereotype.Service;
import ru.tictactoe.repository.GameJpaRepository;
import ru.tictactoe.repository.PVPRepository;
import ru.tictactoe.repository.UsersRepository;
import ru.tictactoe.repository.entity.Games;
import ru.tictactoe.repository.entity.Pvp;
import ru.tictactoe.repository.entity.Users;
import ru.tictactoe.web.model.GameStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GameService {
    private final GameJpaRepository gameRepository;
    private final UsersRepository usersRepository;
    private final PVPRepository pvpRepository;

    public GameService(GameJpaRepository gameRepository,
                       UsersRepository usersRepository,
                       PVPRepository pvpRepository) {
        this.gameRepository = gameRepository;
        this.usersRepository = usersRepository;
        this.pvpRepository = pvpRepository;
    }

    public Games saveGame(Games game) {
        return gameRepository.save(game);
    }

    public Games createNewGame(UUID userId) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Games newGame = Games.createNewGame(user);
        return gameRepository.save(newGame);
    }

    public Games findGame(UUID gameId) {
        return gameRepository.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Game not found"));
    }

//    public void removeGame(Games game) {
//        gameRepository.delete(game);
//    }

    public Pvp createNewGamePVP(UUID userId1) {
        Users user1 = usersRepository.findById(userId1)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Pvp newGame = Pvp.createNewGame(user1);
        return pvpRepository.save(newGame);
    }

    public void joinNewGamePVP(UUID game, UUID userId2) {
        Users user2 = usersRepository.findById(userId2)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Pvp newGame = pvpRepository.findById(game)
                .orElseThrow(() -> new RuntimeException("Game not found"));
        if (newGame.getUser1().getId() == userId2)
            throw new RuntimeException("Cannot join your own game");
        else if (newGame.getUser2() != null)
            throw new RuntimeException("Game already has second player");
        newGame.setUser2(user2);
        newGame.setStatus(GameStatus.IN_PROGRESS);
        pvpRepository.save(newGame);
    }

    public Pvp savePVPGame(Pvp game) {
        return pvpRepository.save(game);
    }

    public Pvp getGame(UUID gameId) {
        return pvpRepository.findById(gameId)
                .orElse(null);
    }

    public Optional<Pvp> getGame(UUID gameId, UUID userId) {
        return pvpRepository.findByIdAndUser1Id(gameId, userId)
                .or(() -> pvpRepository.findByIdAndUser2Id(gameId, userId));
    }

    public List<Pvp> getAllWaitingGame(GameStatus status) {
        return pvpRepository.findByStatus(status);
    }

    public List<Pvp> getCurrentGame(UUID userId) {
        List<Pvp> pvpGames = new ArrayList<>();
        pvpGames.addAll(pvpRepository.findByUser1Id(userId));
        pvpGames.addAll(pvpRepository.findByUser2Id(userId));
        return pvpGames;
    }
}