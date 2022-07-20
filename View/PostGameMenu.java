package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PostGameMenu extends JDialog {
    public PostGameMenu(GameView view,String str){
        super(view,"Post game menu");
        this.setLayout( new FlowLayout() );
        JButton restart = new JButton("Restart");
        restart.setPreferredSize(new Dimension(200,100));
        restart.addActionListener ( new ActionListener()
        {
            public void actionPerformed( ActionEvent e )
            {

                if (BoardView.soloMode) {
                    boolean side = BoardView.computer.getSide();
                    view.clearHistory();
                    view.setVisible(false);
                    view.dispose();
                    new GameView(side);
                }
                else {
                    view.clearHistory();
                    view.setVisible(false);
                    view.dispose();
                    new GameView();
                }
            }
        });
        JButton mainMenu = new JButton("Return to Main Menu");
        mainMenu.setPreferredSize(new Dimension(200,100));
        mainMenu.addActionListener ( new ActionListener()
        {
            public void actionPerformed( ActionEvent e )
            {
                view.clearHistory();
                view.setVisible(false);
                view.dispose();
                new MainMenu();
            }
        });

        this.add( new JLabel (str));
        this.add(restart);
        this.add(mainMenu);
        this.setSize(300,300);
        this.setVisible(true);

    }
}
