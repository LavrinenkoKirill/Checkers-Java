package Tests;
import IO.BoardReader;
import java.io.*;
import Model.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class BoardReaderTest {

    @org.junit.jupiter.api.Test
    void loadCorrectFileTest() {
        try {
            File save = new File("C:\\saves\\queen_test");
            BoardReader br = new BoardReader(save);
            Board board = new Board();
            board = br.load();
            boolean flag = false;
            if (board.getCell(7,4).isBLACK() && board.getCell(7,4).isQUEEN()) flag = true;
            assertEquals(true,flag);
        }
        catch(IOException e){
            System.out.println("Wrong path");
        }
    }

    @org.junit.jupiter.api.Test
    void loadIncorrectFileTest() {
        try {
            File save = new File("C:\\saves\\incorrect_test");
            BoardReader br = new BoardReader(save);
            Board board = new Board();
            board = br.load();
            assertEquals(null,board);
        }
        catch(IOException e){
            System.out.println("Wrong path");
        }
    }


}