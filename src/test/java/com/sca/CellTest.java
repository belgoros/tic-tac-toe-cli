package com.sca;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CellTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private Cell cell;

    @BeforeEach
    void setUp() {
        cell = new Cell(3, 3);
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void shouldClearTheCell() {
        cell.clear();
        assertEquals(Seed.EMPTY, cell.getContent());
    }

    @Test
    public void shouldPaintCross() {
        cell.setContent(Seed.CROSS);
        cell.paint();
        assertEquals("X", outputStreamCaptor.toString()
                .trim());
    }

    @Test
    public void shouldPaintNought() {
        cell.setContent(Seed.NOUGHT);
        cell.paint();
        assertEquals("O", outputStreamCaptor.toString()
                .trim());
    }

    @Test
    public void shouldPaintNothing() {
        cell.setContent(Seed.EMPTY);
        cell.paint();
        assertEquals("", outputStreamCaptor.toString()
                .trim());
    }
}
