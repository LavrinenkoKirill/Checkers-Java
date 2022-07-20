package View;

import Model.Board;

public class MyThread implements Runnable{
    Thread thread;
    GameView view;
    MyThread(GameView view){
        this.thread = new Thread(this);
        this.thread.start();
        this.view = view;
    }

    public void run(){
        if (BoardView.board.isWhiteMove() && BoardView.computer.getSide() == Board.WHITE) {
            BoardView.computer.doMove(BoardView.board);
            view.createMoveButton(BoardView.board.getLastMove().getSourceX(),BoardView.board.getLastMove().getSourceY(),BoardView.board.getLastMove().getDestinationX(),BoardView.board.getLastMove().getDestinationY(),BoardView.computer.getSide(),BoardView.computer);
        }
        else if (!BoardView.board.isWhiteMove() && BoardView.computer.getSide() == Board.BLACK) {
            BoardView.computer.doMove(BoardView.board);
            view.createMoveButton(BoardView.board.getLastMove().getSourceX(),BoardView.board.getLastMove().getSourceY(),BoardView.board.getLastMove().getDestinationX(),BoardView.board.getLastMove().getDestinationY(),BoardView.computer.getSide(),BoardView.computer);
        }
        view.repaint();
    }
}
