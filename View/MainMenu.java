package View;
import Model.Board;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    public MainMenu(){
        super("CHECKERS");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,400);
        JPanel contents = new JPanel();
        JLabel name = new JLabel(" CHECKERS ");
        name.setFont(new Font("Serif", Font.PLAIN, 50));
        name.setPreferredSize(new Dimension(300,100));
        JButton button1 = new JButton("PLAYER VS PLAYER");
        button1.setPreferredSize(new Dimension(500,100));
        JButton button2 = new JButton("PLAYER VS COMPUTER");
        button2.setPreferredSize(new Dimension(500,100));
        contents.add(name);
        contents.add(button1);
        contents.add(button2);
        ActionListener actionListener = new TestActionListener();
        button1.addActionListener(actionListener);
        ActionListener computerListener = new TestChooseMenuListener();
        button2.addActionListener(computerListener);
        setContentPane(contents);
        setVisible(true);

    }
    public class TestActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            dispose();
            new GameView();
        }
    }

    public class TestChooseMenuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            dispose();
            JFrame chooseMenu = new JFrame();
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setSize(600,400);
            JPanel contents = new JPanel();
            JLabel name = new JLabel("Choose side");
            name.setFont(new Font("Serif", Font.PLAIN, 50));
            name.setPreferredSize(new Dimension(300,100));
            JButton button1 = new JButton("WHITE CHECKERS");
            button1.setPreferredSize(new Dimension(500,100));
            ActionListener black_side = new ComputerBlackCreateListener();
            button1.addActionListener(black_side);
            JButton button2 = new JButton("BLACK CHECKERS");
            button2.setPreferredSize(new Dimension(500,100));
            ActionListener white_side = new ComputerWhiteCreateListener();
            button2.addActionListener(white_side);
            contents.add(name);
            contents.add(button1);
            contents.add(button2);
            setContentPane(contents);
            setVisible(true);
        }
    }

    public class ComputerBlackCreateListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            dispose();
            new GameView(Board.BLACK);
        }
    }

    public class ComputerWhiteCreateListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            dispose();
            new GameView(Board.WHITE);
        }
    }

}
