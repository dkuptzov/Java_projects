package ru.tictactoe.di;

import ru.tictactoe.domain.service.*;
import ru.tictactoe.datasource.repository.GameRepository;
import ru.tictactoe.datasource.repository.impl.InMemoryGameRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tictactoe.web.controller.TicTacToeController;

@Configuration
public class AppConfig {

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
    public GameRepository gameRepository() {
        return new InMemoryGameRepository();
    }

    @Bean
    public TicTacToeController ticTacToeController(
            TicTacToeService ticTacToeService,
            GameRepository gameRepository,
            ValidateGameService validateGame) {
        return new TicTacToeController(ticTacToeService, gameRepository, validateGame);
    }
}