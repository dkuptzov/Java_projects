package ru.tictactoe.domain.model;

public class TicTacToe {
    private final int[][] board;
    private static final int SIZE = 3;

    public TicTacToe() {
        board = new int[SIZE][SIZE];
        initializeBoard();
    }

    public TicTacToe(int[][] savedBoard) {
        validateBoard(savedBoard);
        this.board = deepCopy(savedBoard);
    }

    public int[][] getBoard() {
        int[][] copy = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            System.arraycopy(board[i], 0, copy[i], 0, SIZE);
        }
        return copy;
    }

    public void setPos (int x, int y, int value) {
        board[x][y] = value;
    }

    private void validateBoard(int[][] board) {
        if (board == null) throw new IllegalArgumentException("Поле не может быть пустым!");
        if (board.length != SIZE) throw new IllegalArgumentException("Не правильный размер строк");
        for (int i = 0; i < SIZE; i++) {
            if (board[i].length != SIZE) throw new IllegalArgumentException("Неправильное количество столбцов в строке " + i);
        }
    }

    private int[][] deepCopy(int[][] board) {
        int[][] newBoard = new int[SIZE][SIZE];
        for (int i = 0; i < 3; i++)
            System.arraycopy(board[i], 0, newBoard[i], 0, SIZE);
        return newBoard;
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = Field.EMPTY.getValue();
    }
}
