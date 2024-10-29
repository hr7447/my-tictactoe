package tictactoe;

public class Board {

    private char[][] board;
    private static final char EMPTY = '-';

    public Board() {
        board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    public boolean makeMove(int row, int col, char player) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != EMPTY) {
            return false;
        }
        board[row][col] = player;
        return true;
    }

    public void undoMove(int row, int col) {
        board[row][col] = EMPTY;
    }

    public char getCell(int row, int col) {
        return board[row][col];
    }

    public boolean hasEmptyCell() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasWon(char player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
        }

        for (int j = 0; j < 3; j++) {
            if (board[0][j] == player && board[1][j] == player && board[2][j] == player) {
                return true;
            }
        }
        
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }

        return false;
    }
}
