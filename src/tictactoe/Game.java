package tictactoe;

import java.util.Scanner;

public class Game {

    private Board board;
    private Scanner scanner;
    private char playerSymbol;
    private char computerSymbol;
    private boolean isGameOver;

    public Game() {

        board = new Board();
        scanner = new Scanner(System.in);
        isGameOver = false;
    }

    public void start() {
        System.out.println("Welcome to Tic Tac Toe!");
        System.out.println("Would you like to play as X or O? (X goes first)");

        String choice = scanner.nextLine().toUpperCase();
        if (choice.equals("O")) {
            playerSymbol = 'O';
        } else {
            playerSymbol = 'X';
        }
        
        if (playerSymbol == 'X') {
            computerSymbol = 'O';
        } else {
            computerSymbol = 'X';
        }

        while (!isGameOver) {
            if (playerSymbol == 'X') {
                playerTurn();
                if (checkGameEnd())
                    continue;
                computerTurn();
                if (checkGameEnd())
                    continue;
            } else {
                computerTurn();
                if (checkGameEnd())
                    continue;
                playerTurn();
                if (checkGameEnd())
                    continue;
            }
        }

        scanner.close();
    }

    private void playerTurn() {
        boolean validMove = false;
        int row, col;

        displayBoard();
        System.out.println("Your turn! Enter row (0-2) and column (0-2) separated by space:");

        while (!validMove) {
            try {
                String[] input = scanner.nextLine().split(" ");
                row = Integer.parseInt(input[0]);
                col = Integer.parseInt(input[1]);

                if (row >= 0 && row < 3 && col >= 0 && col < 3) {
                    validMove = board.makeMove(row, col, playerSymbol);
                    if (!validMove) {
                        System.out.println("That spot is already taken! Try again:");
                    }
                } else {
                    System.out.println("Invalid input! Row and column must be between 0 and 2:");
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter row and column as numbers separated by space:");
            }
        }
    }

    private void computerTurn() {
        System.out.println("\nComputer is thinking...");
        Move bestMove = MinimaxAlgo.findBestMove(board, computerSymbol);
        board.makeMove(bestMove.row, bestMove.col, computerSymbol);
        System.out.println("Computer placed " + computerSymbol + " at position (" + bestMove.row + ", " + bestMove.col + ")");
    }

    private boolean checkGameEnd() {
        if (board.hasWon(playerSymbol)) {
            displayBoard();
            System.out.println("Congratulations! You won!");
            isGameOver = true;
            return true;
        } else if (board.hasWon(computerSymbol)) {
            displayBoard();
            System.out.println("Computer wins! Better luck next time!");
            isGameOver = true;
            return true;
        } else if (!board.hasEmptyCell()) {
            displayBoard();
            System.out.println("It's a draw!");
            isGameOver = true;
            return true;
        }
        return false;
    }

    private void displayBoard() {
        System.out.println("\nCurrent board:");
        System.out.println("  0 1 2");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board.getCell(i, j) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}