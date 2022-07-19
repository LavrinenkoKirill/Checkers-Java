package Tests;

import IO.BoardReader;
import Model.Board;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class BoardReaderTest {

    @Test
    public void loadCorrectFileTest() {
        try {
            File save = new File("C:\\saves\\queen_test");
            BoardReader br = new BoardReader(save);
            Board board;
            board = br.load();
            boolean flag = board.getCell(7, 4).isBLACK() && board.getCell(7, 4).isQUEEN();
            assertTrue(flag);
        }
        catch(IOException e){
            System.out.println("Wrong path");
        }
    }

    @Test
    public void loadIncorrectFileTest() {
        try {
            File save = new File("C:\\saves\\incorrect_test");
            BoardReader br = new BoardReader(save);
            Board board;
            board = br.load();
            assertNull(board);
        }
        catch(IOException e){
            System.out.println("Wrong path");
        }
    }
}