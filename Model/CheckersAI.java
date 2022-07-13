package Model;
import java.util.LinkedList;
import java.awt.Point;

public class CheckersAI {
    private final boolean black = true;
    private final boolean white = false;
    private boolean side;
    private LinkedList<Cell> checkers = new LinkedList<Cell>();
    private LinkedList<Move> moves = new LinkedList<Move>();
    private Move bestMove = new Move();

    public CheckersAI(boolean side,Board b){
        if (side == true) this.side = black;
        else this.side = white;

        if (this.side == white) {
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
    }

    public void generateAvailableMoves(Board board){
        for (int i = 0; i < checkers.size();i++){
            for (int j = 0; j < board.getBoardSize(); j++){
                for (int k = 0; k < board.getBoardSize(); k++){
                    if (board.isValidMove(checkers.get(i),board.getCell(j,k))){
                        Point src = new Point(checkers.get(i).row,checkers.get(i).column);
                        Point dest = new Point(j,k);
                        Move mv = new Move(src,dest);
                        moves.add(mv);
                    }
                }
            }
        }
    }

    public void chooseMove(Board b){
        int difference = 0;
        int max_distance = 0;
        for (int i = 0; i < moves.size();i++){
            if (moves.get(i).getDestinationX() - moves.get(i).getSourceX() > 1) {
                difference = moves.get(i).getDestinationX() - moves.get(i).getSourceX();
                if (difference > max_distance) bestMove = moves.get(i);
                max_distance = difference;
            }
        }
        if (max_distance != 0) {
            b.doMove(b.getCell(bestMove.getSourceX(),bestMove.getSourceY()),b.getCell(bestMove.getDestinationX(),bestMove.getDestinationY()));
            return;
        }
        else {
            if (side == black){
                for (int i = 0; i < checkers.size(); i++){
                    if (b.getCell(checkers.get(i).row + 1,checkers.get(i).column -1).isWHITE()){
                        for (int j = 0;j < moves.size();j++){
                            if (moves.get(j).getDestinationX() == checkers.get(i).row - 1 && moves.get(j).getDestinationY() == checkers.get(i).column + 1){
                                b.doMove(b.getCell(moves.get(j).getSourceX(),moves.get(j).getSourceY()),b.getCell(moves.get(j).getDestinationX(),moves.get(j).getDestinationY()));
                                return;
                            }
                        }
                    }

                    if (b.getCell(checkers.get(i).row + 1,checkers.get(i).column + 1).isWHITE()){
                        for (int j = 0;j < moves.size();j++){
                            if (moves.get(j).getDestinationX() == checkers.get(i).row - 1 && moves.get(j).getDestinationY() == checkers.get(i).column - 1){
                                b.doMove(b.getCell(moves.get(j).getSourceX(),moves.get(j).getSourceY()),b.getCell(moves.get(j).getDestinationX(),moves.get(j).getDestinationY()));
                                return;
                            }
                        }
                    }
                }
            }
            else {
                for (int i = 0; i < checkers.size(); i++){
                    if (b.getCell(checkers.get(i).row - 1,checkers.get(i).column -1).isBLACK()){
                        for (int j = 0;j < moves.size();j++){
                            if (moves.get(j).getDestinationX() == checkers.get(i).row + 1 && moves.get(j).getDestinationY() == checkers.get(i).column + 1){
                                b.doMove(b.getCell(moves.get(j).getSourceX(),moves.get(j).getSourceY()),b.getCell(moves.get(j).getDestinationX(),moves.get(j).getDestinationY()));
                                return;
                            }
                        }
                    }

                    if (b.getCell(checkers.get(i).row - 1,checkers.get(i).column + 1).isBLACK()){
                        for (int j = 0;j < moves.size();j++){
                            if (moves.get(j).getDestinationX() == checkers.get(i).row + 1 && moves.get(j).getDestinationY() == checkers.get(i).column - 1){
                                b.doMove(b.getCell(moves.get(j).getSourceX(),moves.get(j).getSourceY()),b.getCell(moves.get(j).getDestinationX(),moves.get(j).getDestinationY()));
                                return;
                            }
                        }
                    }
                }

            }


        }

        int random = (int) (Math.random() * (moves.size()));
        b.doMove(b.getCell(moves.get(random).getSourceX(),moves.get(random).getSourceY()),b.getCell(moves.get(random).getDestinationX(),moves.get(random).getDestinationY()));

    }
}
