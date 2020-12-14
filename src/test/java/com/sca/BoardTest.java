package com.sca;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    void shouldInitTheBoardWithDefaultEmptyRowsAndColumns() {
        board.init();
        Cell[][] cells = board.getCells();
        int rowsNumber = cells.length;
        int colsNumber = cells[0].length;
        assertEquals(Board.ROWS, rowsNumber);
        assertEquals(Board.COLS, colsNumber);

        for (int row = 0; row < Board.ROWS; ++row) {
            for (int col = 0; col < Board.COLS; ++col) {
                assertEquals(Seed.EMPTY, cells[row][col].getContent());
            }
        }
    }

    @Test
    void shouldBeDraw() {
        fillTheBoard(board.getCells());
        assertTrue(board.isDraw());
    }

    @Test
    void shouldNotBeDraw() {
        emptyBoard(board.getCells());
        assertFalse(board.isDraw());
    }

    @Test
    void shouldWinInRow() {
        Cell[][] cells = board.getCells();
        setWinnerInRow(cells);
        boolean hasWon = board.hasWon(Seed.CROSS);
        assertTrue(hasWon);
    }

    @Test
    void shouldWinInColumn() {
        Cell[][] cells = board.getCells();
        setWinnerInColumn(cells);
        boolean hasWon = board.hasWon(Seed.CROSS);
        assertTrue(hasWon);
    }

    @Test
    void shouldWinInDiagonal() {
        Cell[][] cells = board.getCells();
        setWinnerInDiagonal(cells);
        boolean hasWon = board.hasWon(Seed.CROSS);
        assertTrue(hasWon);
    }

    @Test
    void shouldWinInOppositeDiagonal() {
        Cell[][] cells = board.getCells();
        setWinnerInOppositeDiagonal(cells);
        boolean hasWon = board.hasWon(Seed.CROSS);
        assertTrue(hasWon);
    }

    private void emptyBoard(Cell [][] cells) {
        for (int row = 0; row < Board.ROWS; ++row) {
            for (int col = 0; col < Board.COLS; ++col) {
                cells[row][col].setContent(Seed.EMPTY);
            }
        }
    }

    private void fillTheBoard(Cell [][] cells) {
        for (int row = 0; row < Board.ROWS; ++row) {
            for (int col = 0; col < Board.COLS; ++col) {
                cells[row][col].setContent(Seed.CROSS);
            }
        }
    }

    private void setWinnerInRow(final Cell [][] cells) {
        cells[0][0].setContent(Seed.CROSS);
        cells[0][1].setContent(Seed.CROSS);
        cells[0][2].setContent(Seed.CROSS);
    }

    private void setWinnerInColumn(final Cell [][] cells) {
        cells[0][0].setContent(Seed.CROSS);
        cells[1][0].setContent(Seed.CROSS);
        cells[2][0].setContent(Seed.CROSS);
    }

    private void setWinnerInDiagonal(final Cell [][] cells) {
        board.setCurrentRow(1);
        board.setCurrentCol(1);
        cells[0][0].setContent(Seed.CROSS);
        cells[1][1].setContent(Seed.CROSS);
        cells[2][2].setContent(Seed.CROSS);
    }

    private void setWinnerInOppositeDiagonal(final Cell [][] cells) {
        board.setCurrentRow(1);
        board.setCurrentCol(1);
        cells[0][2].setContent(Seed.CROSS);
        cells[1][1].setContent(Seed.CROSS);
        cells[2][0].setContent(Seed.CROSS);
    }
}
