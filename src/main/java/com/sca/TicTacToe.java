package com.sca;

import java.util.Scanner;

public class TicTacToe {
    private Board board;
    private GameState currentState;
    private Seed currentPlayer;

    private static Scanner in = new Scanner(System.in);

    public TicTacToe() {
        board = new Board();
        initGame();
    }

    void play() {
        do {
            playerMove(currentPlayer);
            board.paint();
            updateGame(currentPlayer);
            if (currentState == GameState.CROSS_WINS) {
                System.out.println("'X' won! Bye!");
            } else if (currentState == GameState.ZERO_WINS) {
                System.out.println("'O' won! Bye!");
            } else if (currentState == GameState.DRAW) {
                System.out.println("It's Draw! Bye!");
            }
            currentPlayer = (currentPlayer == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
        } while (currentState == GameState.PLAYING);
    }

    public void playerMove(Seed theSeed) {
        boolean validInput = false;
        do {
            if (theSeed == Seed.CROSS) {
                System.out.print("Player 'X', please enter your move (row[1-3] column[1-3]): ");
            } else {
                System.out.print("Player 'O', please enter your move (row[1-3] column[1-3]): ");
            }
            int row = in.nextInt() - 1;
            int col = in.nextInt() - 1;
            if (row >= 0 && row < Board.ROWS && col >= 0 && col < Board.COLS
                    && board.getCells()[row][col].getContent() == Seed.EMPTY) {
                board.getCells()[row][col].setContent(theSeed);
                board.setCurrentRow(row);
                board.setCurrentCol(col);
                validInput = true;
            } else {
                System.out.println("The move at [" + (row + 1) + "," + (col + 1)
                        + "] is not valid. Try again...");
            }
        } while (!validInput);
    }

    public void updateGame(Seed theSeed) {
        if (board.hasWon(theSeed)) {
            currentState = (theSeed == Seed.CROSS) ? GameState.CROSS_WINS : GameState.ZERO_WINS;
        } else if (board.isFull()) {
            currentState = GameState.DRAW;
        }
    }

    /**
     * Initializes the game-board content, current player and the current state
     */
    private void initGame() {
        board.init();
        currentPlayer = Seed.CROSS;
        currentState = GameState.PLAYING;
    }

    public static void main(String[] args) {
        new TicTacToe().play();
    }
}
