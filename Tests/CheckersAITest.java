package Tests;

import IO.BoardReader;
import Model.Board;
import Model.CheckersAI;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class CheckersAITest {

    @Test
    public void getSideTest() {
        CheckersAI computer = new CheckersAI(Board.WHITE,3);
        assertEquals(Board.WHITE,computer.getSide());
    }

    @Test
    public void doMoveTest() {
        File save = new File("C:\\saves\\AITest");
        try {
            Board board;
            BoardReader br = new BoardReader(save);
            board = br.load();
            CheckersAI computer = new CheckersAI(Board.WHITE,3);
            board.doMove(board.getCell(3,2),board.getCell(4,1));
            computer.doMove(board);
            boolean flag = false;
            if (board.getCell(6,3).isWHITE() || (board.getCell(3,0).isWHITE() && board.getBlackCounter() == 11)) flag = true;
            assertTrue(flag);
        }
        catch (IOException e){
            System.out.println("Wrong path");
        }
    }
}