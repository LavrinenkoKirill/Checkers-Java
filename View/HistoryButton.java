package View;
import Model.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HistoryButton extends JMenuItem {
    private int index = 0;
    public HistoryButton(int moveNumber,String str,GameView menu){
        super(str);
        index = ++moveNumber;
        this.addMouseListener(new MouseAdapter() {
            public void actionPerformed(MouseEvent e) {
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

    public int getIndex(){
        return index;
    }


}