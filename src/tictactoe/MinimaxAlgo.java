package tictactoe;

public class MinimaxAlgo {

    public static Move findBestMove(Board board, char player) {
        Move bestMove = new Move(-1, -1);
        int bestValue = Integer.MIN_VALUE;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.makeMove(i, j, player)) {
                    int moveValue = minimax(board, 0, false, player);
                    board.undoMove(i, j);

                    if (moveValue > bestValue) {
                        bestMove.row = i;
                        bestMove.col = j;
                        bestValue = moveValue;
                    }
                }
            }
        }

        return bestMove;
    }

    private static int minimax(Board board, int depth, boolean isMaximizing, char player) {
        char opponent = (player == 'X') ? 'O' : 'X';

        if (board.hasWon(player))
            return 10 - depth;
        if (board.hasWon(opponent))
            return depth - 10;
        if (!board.hasEmptyCell())
            return 0;

        if (isMaximizing) {
            int bestValue = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.makeMove(i, j, player)) {
                        bestValue = Math.max(bestValue, minimax(board, depth + 1, false, player));
                        board.undoMove(i, j);
                    }
                }
            }
            return bestValue;
        } else {
            int bestValue = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.makeMove(i, j, opponent)) {
                        bestValue = Math.min(bestValue, minimax(board, depth + 1, true, player));
                        board.undoMove(i, j);
                    }
                }
            }
            return bestValue;
        }
    }

}
