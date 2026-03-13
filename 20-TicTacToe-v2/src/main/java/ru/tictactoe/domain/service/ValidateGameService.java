package ru.tictactoe.domain.service;

public interface ValidateGameService {
    boolean validateGame(int[][] newBoard, int[][] previousBoard, int player);
    boolean validateBoard(int[][] board);
}
