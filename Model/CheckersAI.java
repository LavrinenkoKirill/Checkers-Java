package Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class CheckersAI {
    private int depth;
    private boolean side;

    public CheckersAI(boolean sd,int depth){
        this.side = sd;
        this.depth = depth;
    }

    public boolean getSide(){
        return this.side;
    }


    public void doMove(Board board)
    {
        Move m = minimaxStart(board, depth, getSide(), true);
        board.doMove(board.getCell(m.getSourceX(),m.getSourceY()),board.getCell(m.getDestinationX(),m.getDestinationY()));
    }

    private LinkedList<Move> getAvailableMoves(Board board,boolean side){
        LinkedList<Move> moves = new LinkedList<>();
        int colour;
        if (side == board.WHITE) colour = Board.WHITE_PLAYER;
        else colour = Board.BLACK_PLAYER;

        for (int i = 0; i < board.getBoardSize(); i++){
            for (int j = 0; j < board.getBoardSize(); j++){
                if (board.getCell(i,j).state == colour){
                    for (int k = 0; k < board.getBoardSize(); k++){
                        for (int l = 0; l < board.getBoardSize(); l++){
                            if (board.isValidMove(board.getCell(i,j),board.getCell(k,l))){
                                Point src = new Point(i,j);
                                Point dest = new Point(k,l);
                                Move mv = new Move(src,dest);
                                moves.add(mv);
                            }
                        }
                    }
                }
            }
        }

        return moves;
    }

    private Move minimaxStart(Board board, int depth, boolean side, boolean maximizingPlayer)
    {
        double alpha = Double.NEGATIVE_INFINITY;
        double beta = Double.POSITIVE_INFINITY;

        LinkedList<Move> possibleMoves = getAvailableMoves(board,side);

        ArrayList<Double> heuristics = new ArrayList<>();
        if(possibleMoves.isEmpty())
            return null;
        Board tempBoard = null;
        for(int i = 0; i < possibleMoves.size(); i++)
        {
            tempBoard = board.clone();
            tempBoard.doMove(tempBoard.getCell(possibleMoves.get(i).getSourceX(),possibleMoves.get(i).getSourceY()),tempBoard.getCell(possibleMoves.get(i).getDestinationX(),possibleMoves.get(i).getDestinationY()));
            heuristics.add(minimax(tempBoard, depth - 1, flipSide(side), !maximizingPlayer, alpha, beta));
        }

        double maxHeuristics = Double.NEGATIVE_INFINITY;

        Random rand = new Random();
        for(int i = heuristics.size() - 1; i >= 0; i--) {
            if (heuristics.get(i) >= maxHeuristics) {
                maxHeuristics = heuristics.get(i);
            }
        }
        for(int i = 0; i < heuristics.size(); i++)
        {
            if(heuristics.get(i) < maxHeuristics)
            {
                heuristics.remove(i);
                possibleMoves.remove(i);
                i--;
            }
        }

        return possibleMoves.get(rand.nextInt(possibleMoves.size()));
    }


    private double minimax(Board board, int depth, boolean side, boolean maximizingPlayer, double alpha, double beta)
    {
        if(depth == 0) {
            return getHeuristic(board);
        }
        LinkedList<Move> possibleMoves = getAvailableMoves(board,side);
        double initial = 0;
        Board tempBoard = null;
        if(maximizingPlayer)
        {
            initial = Double.NEGATIVE_INFINITY;

            for(int i = 0; i < possibleMoves.size(); i++)
            {
                tempBoard = board.clone();
                tempBoard.doMove(tempBoard.getCell(possibleMoves.get(i).getSourceX(),possibleMoves.get(i).getSourceY()),tempBoard.getCell(possibleMoves.get(i).getDestinationX(),possibleMoves.get(i).getDestinationY()));

                double result = minimax(tempBoard, depth - 1, flipSide(side), !maximizingPlayer, alpha, beta);

                initial = Math.max(result, initial);
                alpha = Math.max(alpha, initial);

                if(alpha >= beta)
                    break;
            }
        }
        //minimizing
        else
        {
            initial = Double.POSITIVE_INFINITY;
            for(int i = 0; i < possibleMoves.size(); i++)
            {
                tempBoard = board.clone();
                tempBoard.doMove(tempBoard.getCell(possibleMoves.get(i).getSourceX(),possibleMoves.get(i).getSourceY()),tempBoard.getCell(possibleMoves.get(i).getDestinationX(),possibleMoves.get(i).getDestinationY()));

                double result = minimax(tempBoard, depth - 1, flipSide(side), !maximizingPlayer, alpha, beta);

                initial = Math.min(result, initial);
                alpha = Math.min(alpha, initial);

                if(alpha >= beta)
                    break;
            }
        }

        return initial;
    }


    private double getHeuristic(Board board)
    {

        double kingWeight = 1.2;
        double result = 0;
        if(side == Board.WHITE)
            result = getWhiteQueenNumber(board) * kingWeight + getWhiteUsualCheckersNumber(board) - getBlackQueenNumber(board) *
                    kingWeight -
                    getBlackUsualCheckersNumber(board);
        else
            result = getBlackQueenNumber(board) * kingWeight + getBlackUsualCheckersNumber(board) - getWhiteQueenNumber(board) *
                    kingWeight -
                    getWhiteUsualCheckersNumber(board);
        return result;

    }

    private int getWhiteQueenNumber(Board board){
        int number = 0;
        for (int i = 0; i < board.getBoardSize(); i++){
            for (int j = 0; j < board.getBoardSize(); j++){
                if (board.getCell(i,j).isWHITE() && board.getCell(i,j).isQUEEN()){
                    number++;
                }
            }
        }
        return number;
    }

    private int getBlackQueenNumber(Board board){
        int number = 0;
        for (int i = 0; i < board.getBoardSize(); i++){
            for (int j = 0; j < board.getBoardSize(); j++){
                if (board.getCell(i,j).isBLACK() && board.getCell(i,j).isQUEEN()){
                    number++;
                }
            }
        }
        return number;
    }

    private int getWhiteUsualCheckersNumber(Board board){
        int number = 0;
        for (int i = 0; i < board.getBoardSize(); i++){
            for (int j = 0; j < board.getBoardSize(); j++){
                if (board.getCell(i,j).isWHITE() && !board.getCell(i,j).isQUEEN()){
                    number++;
                }
            }
        }
        return number;
    }


    private int getBlackUsualCheckersNumber(Board board){
        int number = 0;
        for (int i = 0; i < board.getBoardSize(); i++){
            for (int j = 0; j < board.getBoardSize(); j++){
                if (board.getCell(i,j).isBLACK() && !board.getCell(i,j).isQUEEN()){
                    number++;
                }
            }
        }
        return number;
    }






    private boolean flipSide(boolean side)
    {
        if(side == Board.BLACK)
            return Board.WHITE;
        return Board.BLACK;
    }

}
