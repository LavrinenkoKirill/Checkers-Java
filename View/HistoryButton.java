package View;
import Model.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HistoryButton extends JMenuItem {
    private final int index;
    public HistoryButton(int moveNumber,String str,GameView menu){
        super(str);
        index = moveNumber;
        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Board b = BoardView.board.getPreviousTurn(index);
                if (b!=null){
                    BoardView.board = b;
                    menu.refreshMatchHistory(index);
                    repaint();
                }
                else {
                    JOptionPane.showMessageDialog(menu,"Match history is empty");
                }
            }
        });
    }



    public HistoryButton(int moveNumber,String str,GameView menu,CheckersAI computer){
        super(str);
        index = moveNumber;
        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Board b = BoardView.board.getPreviousTurn(index);
                if (b!=null){
                    BoardView.board = b;

                    if ((BoardView.board.isWhiteMove() && computer.getSide() == Board.WHITE) || (!BoardView.board.isWhiteMove() && computer.getSide() == Board.BLACK)) {
                        computer.doMove(BoardView.board);
                        menu.createMoveButton(BoardView.board.getLastMove().getSourceY(), BoardView.board.getLastMove().getSourceX(), BoardView.board.getLastMove().getDestinationY(), BoardView.board.getLastMove().getDestinationX(), computer.getSide(),computer);
                    }

                    menu.refreshMatchHistory(index);
                    repaint();


                }
                else {
                    JOptionPane.showMessageDialog(menu,"Match history is empty");
                }
            }
        });


    }

    public int getIndex(){
        return index;
    }


}
