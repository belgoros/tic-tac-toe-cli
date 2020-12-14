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
            printCurrentGameState();
            currentPlayer = (currentPlayer == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
        } while (currentState == GameState.PLAYING);
    }

    private void printCurrentGameState() {
        if (currentState == GameState.CROSS_WINS) {
            System.out.println("'X' won! Bye!");
        } else if (currentState == GameState.NOUGHT_WINS) {
            System.out.println("'O' won! Bye!");
        } else if (currentState == GameState.DRAW) {
            System.out.println("It's Draw! Bye!");
        }
    }

    public void playerMove(Seed seed) {
        boolean validInput = false;
        do {
            if (seed == Seed.CROSS) {
                System.out.print("Player 'X', please enter your move (row[1-3] column[1-3]): ");
            } else {
                System.out.print("Player 'O', please enter your move (row[1-3] column[1-3]): ");
            }
            int row = in.nextInt() - 1;
            int col = in.nextInt() - 1;
            if (isValidCell(row, col)) {
                board.getCells()[row][col].setContent(seed);
                board.setCurrentRow(row);
                board.setCurrentCol(col);
                validInput = true;
            } else{
                System.out.println("The move at [" + (row + 1) + "," + (col + 1)
                        + "] is not valid. Try again...");
            }
        } while (!validInput);
    }

    private boolean isValidCell(int playedRow, int playedCol) {
        return isValidRow(playedRow) && isValidColumn(playedCol) && isCellEmpty(playedRow, playedCol);
    }

    private boolean isCellEmpty(int playedRow, int playedCol) {
        return board.getCells()[playedRow][playedCol].getContent() == Seed.EMPTY;
    }

    private boolean isValidColumn(int playedCol) {
        return playedCol >= 0 && playedCol < Board.COLS;
    }

    private boolean isValidRow(int playedRow) {
        return playedRow >= 0 && playedRow < Board.ROWS;
    }

    public void updateGame(Seed theSeed) {
        if (board.hasWon(theSeed)) {
            currentState = (theSeed == Seed.CROSS) ? GameState.CROSS_WINS : GameState.NOUGHT_WINS;
        } else if (board.isDraw()) {
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
