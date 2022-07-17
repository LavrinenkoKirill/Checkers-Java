package Model;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.awt.Point;
import java.util.Random;

public class CheckersAI {
    private Point skippingPoint;
    private int depth;
    private int pruned = 0;
    private boolean side;
    private LinkedList<Cell> checkers = new LinkedList<Cell>();
    private LinkedList<Move> possibleMoves = new LinkedList<>();
    private int numWhiteNormalCheckers = 0;
    private int numBlackNormalCheckers = 0;
    private int numBlackKingCheckers = 0;
    private int numWhiteKingCheckers = 0;

    public CheckersAI(boolean sd){
        if (sd == true) this.side = Board.BLACK;
        else this.side = Board.WHITE;
    }

    public CheckersAI(boolean sd, int depth)
    {
        if (sd == true) this.side = Board.BLACK;
        else this.side = Board.WHITE;
        this.depth = depth;
    }


    public void makeMove(Board board)
    {
        Move m = minimaxStart(board, depth, side, true);
        board.doMove(board.getCell(m.getSourceX(),m.getSourceY()),board.getCell(m.getDestinationX(),m.getDestinationY()));
    }

    public void getValues(Board b){
        numWhiteNormalCheckers = 0;
        numBlackNormalCheckers = 0;
        numBlackKingCheckers = 0;
        numWhiteKingCheckers = 0;
        for (int i = 0; i < b.getBoardSize(); i++){
            for (int j = 0; j < b.getBoardSize(); j++){
                if (b.getCell(i,j).isWHITE() && b.getCell(i,j).isQUEEN() == false) numWhiteNormalCheckers++;
                if (b.getCell(i,j).isWHITE() && b.getCell(i,j).isQUEEN()) numWhiteKingCheckers++;
                if (b.getCell(i,j).isBLACK() && b.getCell(i,j).isQUEEN() == false) numBlackNormalCheckers++;
                if (b.getCell(i,j).isBLACK() && b.getCell(i,j).isQUEEN()) numBlackKingCheckers++;
            }
        }
    }


    public void getAllValidMoves(Board b, boolean side){
        checkers.clear();
        if (side == Board.WHITE) {
            for (int i = 0; i < b.getBoardSize(); i++) {
                for (int j = 0; j < b.getBoardSize(); j++) {
                    if (b.getCell(i,j).isWHITE()) checkers.add(b.getCell(i,j));
                }
            }
        }
        else {
            for (int i = 0; i < b.getBoardSize(); i++) {
                for (int j = 0; j < b.getBoardSize(); j++) {
                    if (b.getCell(i,j).isBLACK()) checkers.add(b.getCell(i,j));
                }
            }
        }




        for (int i = 0; i < checkers.size(); i++){
            for (int j = 0; j < b.getBoardSize(); j++){
                for (int k = 0; k < b.getBoardSize(); k++){
                    if (b.isValidMove(checkers.get(i),b.getCell(j,k))){
                        Point src = new Point(checkers.get(i).row,checkers.get(i).column);
                        Point dest = new Point(j,k);
                        Move mv = new Move(src,dest);
                        possibleMoves.add(mv);
                    }
                }
            }
        }
    }


    public LinkedList<Move> getValidSkipMoves(int row, int col,Board b,boolean side) {
        int enemy;
        if (this.side == Board.WHITE){
            enemy = Board.BLACK_PLAYER;
        }
        else enemy = Board.WHITE_PLAYER;

        LinkedList<Move> move = new LinkedList<>();
        Point start = new Point(row, col);

        List<Point> possibilities = new ArrayList<>();

        if(side == Board.WHITE && b.getCell(row, col).state == Board.WHITE_PLAYER)
        {
            possibilities.add(new Point(row + 2, col + 2));
            possibilities.add(new Point(row + 2, col - 2));
        }
        else if(side == Board.BLACK && b.getCell(row,col).state == Board.BLACK_PLAYER)
        {
            possibilities.add(new Point(row - 2, col + 2));
            possibilities.add(new Point(row - 2, col - 2));
        }
        else if(b.getCell(row,col).queen = true)
        {
            possibilities.add(new Point(row + 2, col + 2));
            possibilities.add(new Point(row + 2, col - 2));
            possibilities.add(new Point(row - 2, col + 2));
            possibilities.add(new Point(row - 2, col - 2));
        }

        for (int i = 0; i < possibilities.size(); i++) {
            Point temp = possibilities.get(i);
            Move m = new Move(start, temp);
            Point pt = findMidSquare(m);
            if (temp.x < b.getBoardSize() && temp.x >= 0 && temp.y < b.getBoardSize() && temp.y >= 0 && b.getCell(temp.x, temp.y).state == Board.FREE
                    && b.getCell(pt.x,pt.y).state == enemy) {
                move.add(m);
            }
        }
        return move;
    }


    private Point findMidSquare(Move move) {

        Point ret = new Point((move.getSource().x + move.getDestination().x) / 2,
                (move.getSource().y + move.getDestination().y) / 2);

        return ret;
    }







    private Move minimaxStart(Board board, int depth, boolean maximizingPlayer,boolean size)
    {
        double alpha = Double.NEGATIVE_INFINITY;
        double beta = Double.POSITIVE_INFINITY;

        if(skippingPoint == null)
            getAllValidMoves(board,side);
        else
        {
            possibleMoves = getValidSkipMoves(skippingPoint.x, skippingPoint.y, board,side);
            skippingPoint = null;
        }

        List<Double> heuristics = new ArrayList<>();
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
        getAllValidMoves(board,side);

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


    private double getHeuristic(Board b)
    {
        getValues(b);
        double kingWeight = 1.2;
        double result = 0;
        if(this.side == Board.WHITE)
            result = numWhiteKingCheckers * kingWeight + numWhiteNormalCheckers - numBlackKingCheckers *
                    kingWeight -
                    numBlackNormalCheckers;
        else
            result = numBlackKingCheckers * kingWeight + numBlackNormalCheckers - numWhiteKingCheckers *
                    kingWeight -
                    numWhiteNormalCheckers;
        return result;

    }

    public boolean getSide(){
        return this.side;
    }


    private boolean flipSide(boolean side)
    {
        if(side == Board.BLACK) return Board.WHITE;
        else return Board.BLACK;
    }






}
