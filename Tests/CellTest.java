package Tests;

import IO.BoardReader;
import Model.Board;
import Model.Cell;
import org.junit.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class CellTest {
    @Test
    public void isFREE() {
        Board board = new Board();
        board.startingPosition();
        assertTrue(board.getCell(4,1).isFREE());
    }

    @Test
    public void isBLACK() {
        Board board = new Board();
        board.startingPosition();
        assertTrue(board.getCell(0,1).isBLACK());
    }

    @Test
    public void isWHITE() {
        Board board = new Board();
        board.startingPosition();
        assertTrue(board.getCell(5,0).isWHITE());
    }

    @Test
    public void isQUEEN() {
        File save = new File("C:\\saves\\queen_test");
        try {
            Board board;
            BoardReader br = new BoardReader(save);
            board = br.load();
            assertTrue(board.getCell(0,7).isQUEEN());

        }
        catch (IOException e){
            System.out.println("Wrong path");
        }
    }

    @Test
    public void isValidCellTest() {
        Point ptr = new Point(-1, 1);
        assertFalse(Cell.isValidCell(ptr));
    }
}