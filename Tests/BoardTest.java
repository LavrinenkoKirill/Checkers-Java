package Tests;
import IO.BoardReader;
import Model.*;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @org.junit.jupiter.api.Test
    void startingPositionTest() {
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

    @org.junit.jupiter.api.Test
    void checkEnemiesTest() {
        Board board = new Board();
        board.startingPosition();
        board.doMove(board.getCell(5,0), board.getCell(4,1));
        board.doMove(board.getCell(2,3), board.getCell(3,2));
        assertTrue(board.checkEnemies(board.getCell(4, 1), board.getCell(2, 3), -1));
    }

    @org.junit.jupiter.api.Test
    void checkDistanceTest() {
        Board board = new Board();
        board.startingPosition();
        assertEquals(0,board.checkDistance(board.getCell(0,0),board.getCell(0,0),board.getCell(0,0)));
    }

    @org.junit.jupiter.api.Test
    void doQueenMoveTest() {
        File save = new File("C:\\saves\\queen_test");
        try {
            Board board;
            BoardReader br = new BoardReader(save);
            board = br.load();
            board.doQueenMove(board.getCell(7,4),board.getCell(4,7));
            boolean flag = board.getCell(4, 7).isBLACK() && board.getCell(4, 7).isQUEEN();
            assertTrue(flag);

        }
        catch (IOException e){
            System.out.println("Wrong path");
        }
    }

    @org.junit.jupiter.api.Test
    void checkQueenMoveTest() {
        File save = new File("C:\\saves\\queen_test");
        try {
            Board board;
            BoardReader br = new BoardReader(save);
            board = br.load();
            assertTrue(board.checkQueenMove(board.getCell(7, 4), board.getCell(4, 7)));

        }
        catch (IOException e){
            System.out.println("Wrong path");
        }
    }

    @org.junit.jupiter.api.Test
    void checkQueenEnemiesTest() {
        File save = new File("C:\\saves\\cut_queen_test");
        try {
            Board board;
            BoardReader br = new BoardReader(save);
            board = br.load();
            assertTrue(board.checkEnemies(board.getCell(2, 3), board.getCell(4, 1), 0));

        }
        catch (IOException e){
            System.out.println("Wrong path");
        }
    }

    @org.junit.jupiter.api.Test
    void cutQueenEnemiesTest() {
        File save = new File("C:\\saves\\cut_queen_test");
        try {
            Board board;
            BoardReader br = new BoardReader(save);
            board = br.load();
            int bc = board.getBlackCounter();
            board.doQueenMove(board.getCell(0,5),board.getCell(4,1));
            boolean flag = board.getCell(4, 1).isWHITE() && board.getCell(4, 1).isQUEEN();
            if (bc - board.getBlackCounter() != 2) flag = false;
            System.out.println(board.getWhiteCounter());
            assertTrue(flag);

        }
        catch (IOException e){
            System.out.println("Wrong path");
        }
    }

    @org.junit.jupiter.api.Test
    void canMoveTest() {
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

    @org.junit.jupiter.api.Test
    void isValidMoveTest() {
        Board board = new Board();
        board.startingPosition();
        assertTrue(board.isValidMove(board.getCell(5, 0), board.getCell(4, 1)));
    }

    @org.junit.jupiter.api.Test
    void doMoveTest() {
        Board board = new Board();
        board.startingPosition();
        board.doMove(board.getCell(5,0), board.getCell(4,1));
        boolean flag = board.getCell(4, 1).isWHITE() && !board.getCell(4, 1).isQUEEN();
        if (board.getCell(5,0).isWHITE() || board.getCell(5,0).isQUEEN()) flag = false;
        assertTrue(flag);
    }

    @org.junit.jupiter.api.Test
    void cutEnemiesTest() {

        Board board = new Board();
        board.startingPosition();
        board.doMove(board.getCell(5,0), board.getCell(4,1));
        board.doMove(board.getCell(2,3), board.getCell(3,2));
        int bc = board.getBlackCounter();
        board.cutEnemies(board.getCell(4,1),board.getCell(2,3));
        boolean flag = bc - board.getBlackCounter() == 1;
        assertTrue(flag);
    }

    @org.junit.jupiter.api.Test
    void isWinTest() {
        int B_WIN = 2;
        Board board = new Board();
        board.startingPosition();
        board.setWhiteCounter(0);
        assertEquals(B_WIN,board.isWin());
    }

    @org.junit.jupiter.api.Test
    void getBoardSizeTest() {
        Board board = new Board();
        board.startingPosition();
        assertEquals(8,board.getBoardSize());
    }

    @org.junit.jupiter.api.Test
    void getCellTest() {
        Board board = new Board();
        board.startingPosition();
        boolean flag = board.getCell(7, 0).isWHITE() && !board.getCell(7, 0).isQUEEN();
        assertTrue(flag);
    }

    @org.junit.jupiter.api.Test
    void isWhiteMoveTest() {
        Board board = new Board();
        board.startingPosition();
        assertTrue(board.isWhiteMove());
    }

    @org.junit.jupiter.api.Test
    void getBoardTest() {
        Board board1 = new Board();
        board1.startingPosition();
        Board board2;
        board2 = board1.getBoard();
        boolean flag = board1 == board2;
        assertTrue(flag);
    }

    @org.junit.jupiter.api.Test
    void setCellTest() {
        Board board = new Board();
        board.startingPosition();
        board.setCell(0,1,1,true);
        boolean flag = board.getCell(0, 1).isBLACK() && board.getCell(0, 1).isQUEEN();
        assertTrue(flag);
    }

    @org.junit.jupiter.api.Test
    void setMoveTest() {
        boolean black = true;
        Board board = new Board();
        board.startingPosition();
        board.setMove(black);
        assertEquals(black,!board.isWhiteMove());
    }

    @org.junit.jupiter.api.Test
    void setWhiteCounterTest() {
        Board board = new Board();
        board.startingPosition();
        board.setWhiteCounter(2);
        assertEquals(2,board.getWhiteCounter());
    }

    @org.junit.jupiter.api.Test
    void setBlackCounterTest() {
        Board board = new Board();
        board.startingPosition();
        board.setBlackCounter(2);
        assertEquals(2,board.getBlackCounter());
    }

    @org.junit.jupiter.api.Test
    void getWhiteCounterTest() {
        Board board = new Board();
        board.startingPosition();
        assertEquals(12,board.getWhiteCounter());
    }

    @org.junit.jupiter.api.Test
    void getBlackCounterTest() {
        Board board = new Board();
        board.startingPosition();
        assertEquals(12,board.getBlackCounter());
    }
}