package ru.tictactoe.di;

import ru.tictactoe.domain.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tictactoe.repository.UsersRepository;
import ru.tictactoe.service.AuthService;
import ru.tictactoe.service.GameService;
import ru.tictactoe.service.UserService;
import ru.tictactoe.web.controller.TicTacToeController;
import ru.tictactoe.web.controller.UserController;

@Configuration
public class AppConfig {

    @Bean
    public MiniMax miniMax() {
        return new MiniMax();
    }

    @Bean
    public GameResult gameResult() {
        return new GameResult();
    }

    @Bean
    public ValidateGame validateGame() {
        return new ValidateGame();
    }

    @Bean
    public MiniMaxService miniMaxService() {
        return new MiniMax();
    }

    @Bean
    public GameResultService gameResultService() {
        return new GameResult();
    }

    @Bean
    public ValidateGame validationService() {
        return new ValidateGame();
    }

    @Bean
    public TicTacToeService ticTacToeService(
            MiniMax miniMaxService,
            GameResult gameResultService,
            ValidateGame validationService) {
        return new TicTacToeImpl(miniMaxService, gameResultService, validationService);
    }

    @Bean
    public TicTacToeController ticTacToeController(
            TicTacToeService ticTacToeService,
            GameService gameService,
            ValidateGameService validateGame,
            AuthService authService) {
        return new TicTacToeController(ticTacToeService, gameService, validateGame, authService);
    }

    @Bean
    public UserService userService(UsersRepository usersRepository) {
        return new UserService(usersRepository);
    }

    @Bean
    public AuthService authService(UsersRepository usersRepository) {
        return new AuthService(usersRepository);
    }

    @Bean
    public UserController userController(UserService userService, AuthService authService) {
        return new UserController(userService, authService);
    }
}