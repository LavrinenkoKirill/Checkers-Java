package Tests;

import IO.BoardReader;
import Model.Board;
import Model.Move;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class BoardTest {

    @org.junit.Test
    public void startingPositionTest() {
        Board board = new Board();
        board.startingPosition();
        int white_checkers = 0;
        int black_checkers = 0;
        for (int i = 0; i < board.getBoardSize();i++){
            for (int j = 0; j < board.getBoardSize(); j++){
                if (board.getCell(i,j).isWHITE() && !board.getCell(i,j).isQUEEN()){
                    white_checkers++;
                }
                else if (board.getCell(i,j).isBLACK() && !board.getCell(i,j).isQUEEN()){
                    black_checkers++;
                }
            }
        }
        boolean flag = white_checkers == 12 && black_checkers == 12;
        assertTrue(flag);
    }


    @org.junit.Test
    public void canMoveTest() {
        File save = new File("C:\\saves\\cant_move_test");
        try {
            Board board;
            BoardReader br = new BoardReader(save);
            board = br.load();
            board.doMove(board.getCell(2,3),board.getCell(0,5));
            boolean flag = board.canMove();
            assertFalse(flag);

        }
        catch (IOException e){
            System.out.println("Wrong path");
        }
    }

    @org.junit.Test
    public void isValidMoveTest() {
        Board board = new Board();
        board.startingPosition();
        assertTrue(board.isValidMove(board.getCell(5, 0), board.getCell(4, 1)));
    }

    @org.junit.Test
    public void doMoveTest() {
        Board board = new Board();
        board.startingPosition();
        board.doMove(board.getCell(5,0), board.getCell(4,1));
        boolean flag = board.getCell(4, 1).isWHITE() && !board.getCell(4, 1).isQUEEN();
        if (board.getCell(5,0).isWHITE() || board.getCell(5,0).isQUEEN()) flag = false;
        assertTrue(flag);
    }


    @org.junit.Test
    public void isWinTest() {
        int B_WIN = 2;
        Board board = new Board();
        board.startingPosition();
        board.setWhiteCounter(0);
        assertEquals(B_WIN,board.isWin());
    }

    @org.junit.Test
    public void getBoardSizeTest() {
        Board board = new Board();
        board.startingPosition();
        assertEquals(8,board.getBoardSize());
    }

    @org.junit.Test
    public void getCellTest() {
        Board board = new Board();
        board.startingPosition();
        boolean flag = board.getCell(7, 0).isWHITE() && !board.getCell(7, 0).isQUEEN();
        assertTrue(flag);
    }

    @org.junit.Test
    public void isWhiteMoveTest() {
        Board board = new Board();
        board.startingPosition();
        assertTrue(board.isWhiteMove());
    }

    @org.junit.Test
    public void getBoardTest() {
        Board board1 = new Board();
        board1.startingPosition();
        Board board2;
        board2 = board1.getBoard();
        boolean flag = board1 == board2;
        assertTrue(flag);
    }

    @org.junit.Test
    public void setCellTest() {
        Board board = new Board();
        board.startingPosition();
        board.setCell(0,1,1,true);
        boolean flag = board.getCell(0, 1).isBLACK() && board.getCell(0, 1).isQUEEN();
        assertTrue(flag);
    }

    @org.junit.Test
    public void setMoveTest() {
        boolean black = true;
        Board board = new Board();
        board.startingPosition();
        board.setMove(black);
        assertEquals(black,!board.isWhiteMove());
    }

    @org.junit.Test
    public void setWhiteCounterTest() {
        Board board = new Board();
        board.startingPosition();
        board.setWhiteCounter(2);
        assertEquals(2,board.getWhiteCounter());
    }

    @org.junit.Test
    public void setBlackCounterTest() {
        Board board = new Board();
        board.startingPosition();
        board.setBlackCounter(2);
        assertEquals(2,board.getBlackCounter());
    }

    @org.junit.Test
    public void getWhiteCounterTest() {
        Board board = new Board();
        board.startingPosition();
        assertEquals(12,board.getWhiteCounter());
    }

    @org.junit.Test
    public void getBlackCounterTest() {
        Board board = new Board();
        board.startingPosition();
        assertEquals(12,board.getBlackCounter());
    }


    @org.junit.Test
    public void isHistoryEmptyTest() {
        Board board = new Board();
        board.startingPosition();
        assertTrue(board.isHistoryEmpty());
    }


    @org.junit.Test
    public void addMoveTest() {
        Board board = new Board();
        board.startingPosition();
        board.doMove(board.getCell(5,0),board.getCell(4,1));
        Point src = new Point(5,0);
        Point dest = new Point(4,1);
        Move mv = new Move(src,dest);
        board.addMove(mv);
        assertFalse(board.isHistoryEmpty());
    }

    @org.junit.Test
    public void HistorySizeTest() {
        File save = new File("C:\\saves\\History_size_test");
        try {
            Board board;
            BoardReader br = new BoardReader(save);
            board = br.load();
            assertEquals(14,board.HistorySize());
        }
        catch (IOException e){
            System.out.println("Wrong path");
        }
    }

    @org.junit.Test
    public void getHistoryMoveTest() {
        File save = new File("C:\\saves\\getHistoryMoveTest");
        try {
            Board board;
            BoardReader br = new BoardReader(save);
            board = br.load();
            boolean flag = false;
            Move mv = board.getHistoryMove(2);
            if (mv.getSourceX() == 4 && mv.getSourceY() == 3 && mv.getDestinationX() == 2 && mv.getDestinationY() == 5) flag = true;
            assertTrue(flag);
        }
        catch (IOException e){
            System.out.println("Wrong path");
        }
    }


    @org.junit.Test
    public void getPreviousTurnTest() {
        Board board = new Board();
        board.startingPosition();
        board.doMove(board.getCell(5,0),board.getCell(4,1));
        Point src = new Point(5,0);
        Point dest = new Point(4,1);
        Move mv = new Move(src,dest);
        board.addMove(mv);
        assertFalse(board.isHistoryEmpty());
    }


    @org.junit.Test
    public void getPreviousTurnWithIndexTest() {
        File save = new File("C:\\saves\\getHistoryMoveTest");
        try {
            Board board;
            BoardReader br = new BoardReader(save);
            board = br.load();
            boolean flag = false;
            Board b = board.getPreviousTurn(0);
            if(b.getCell(4,3).isWHITE() && !b.getCell(4,3).isQUEEN()) flag = true;
            assertTrue(flag);
        }
        catch (IOException e){
            System.out.println("Wrong path");
        }
    }
}