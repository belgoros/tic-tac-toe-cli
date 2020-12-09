package com.sca;

public class Board {
    public static final int ROWS = 3;
    public static final int COLS = 3;

    private final Cell[][] cells;
    private int currentCol, currentRow;

    public Board() {
        cells = new Cell[ROWS][COLS];
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                cells[row][col] = new Cell(row, col);
            }
        }
    }

    public void init() {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                cells[row][col].clear();
            }
        }
    }

    /**
     * Returns true if it is a draw (i.e., no more EMPTY cell)
     */
    public boolean isFull() {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                if (cells[row][col].getContent() == Seed.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns true if the player has won after placing at
     * (currentRow, currentCol)
     */
    public boolean hasWon(Seed theSeed) {
        return threeInTheRow(theSeed)
                || threeInTheColumn(theSeed)
                || threeInTheDiagonal(theSeed)
                || threeInTheOppositeDiagonal(theSeed);
    }

    private boolean threeInTheColumn(Seed _seed) {
        return cells[0][currentCol].getContent() == _seed
                && cells[1][currentCol].getContent() == _seed
                && cells[2][currentCol].getContent() == _seed;
    }

    private boolean threeInTheRow(Seed _seed) {
        return cells[currentRow][0].getContent() == _seed
                && cells[currentRow][1].getContent() == _seed
                && cells[currentRow][2].getContent() == _seed;
    }

    private boolean threeInTheDiagonal(Seed _seed) {
        return currentRow == currentCol
                && cells[0][0].getContent() == _seed
                && cells[1][1].getContent() == _seed
                && cells[2][2].getContent() == _seed;
    }

    private boolean threeInTheOppositeDiagonal(Seed _seed) {
        return currentRow + currentCol == 2
                && cells[0][2].getContent() == _seed
                && cells[1][1].getContent() == _seed
                && cells[2][0].getContent() == _seed;
    }

    /**
     * Paints the playing board
     */
    public void paint() {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                cells[row][col].paint();
                if (col < COLS - 1) System.out.print("|");
            }
            System.out.println();
            if (row < ROWS - 1) {
                System.out.println("-----------");
            }
        }
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCurrentCol(int currentCol) {
        this.currentCol = currentCol;
    }

    public void setCurrentRow(int currentRow) {
        this.currentRow = currentRow;
    }
}
