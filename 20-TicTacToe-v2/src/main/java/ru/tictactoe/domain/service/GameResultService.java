package ru.tictactoe.domain.service;

import ru.tictactoe.web.model.GameStatus;

public interface GameResultService {
    GameStatus theEnd(int[][] board);
}
