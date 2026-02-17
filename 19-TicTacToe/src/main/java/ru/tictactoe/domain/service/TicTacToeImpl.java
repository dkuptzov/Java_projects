package ru.tictactoe.domain.service;

import ru.tictactoe.domain.model.MoveResult;

public class TicTacToeImpl implements TicTacToeService {
    private final MiniMax moveStrategy;
    private final ValidateGame validator;
    private final GameResult gameResult;

    public TicTacToeImpl(MiniMax miniMaxService,
                         GameResult gameResultService,
                         ValidateGame validationService) {
        this.moveStrategy = miniMaxService;
        this.validator = validationService;
        this.gameResult = gameResultService;
    }

    public MoveResult getAIMove(int[][] board, int aiPlayer, int humanPlayer, int curPlayer) {
        return moveStrategy.nextStep(board, aiPlayer, humanPlayer, curPlayer);
    }

    public int isGameFinished(int[][] board) {
        return gameResult.theEnd(board);
    }
}