package View;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import Model.*;
import IO.*;

public class GameView extends JFrame {
    public GameView(){
        super("CHECKERS");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        createOptionalMenu();
        Board brd = new Board();
        BoardView board = new BoardView(brd);
        setContentPane(board);
        setUndecorated(true);
        setVisible(true);
    }


    public void createOptionalMenu(){
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Save/Load Match");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        JMenuItem save = new JMenuItem("Save to File");
        fileMenu.add(save);
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Save file");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int result = fileChooser.showSaveDialog(fileChooser);
                if (result == JFileChooser.APPROVE_OPTION ) {
                    try {
                        BoardWriter bw = new BoardWriter(fileChooser.getSelectedFile());
                        bw.write(BoardView.board.getBoard());
                        bw.flush();
                        bw.close();
                        JOptionPane.showMessageDialog(fileChooser, "File '" + fileChooser.getSelectedFile() + " saved");

                    }
                    catch (IOException b){
                        System.out.println("File not found");
                    }
                }
            }
        });
        JMenuItem load = new JMenuItem("Load from File");
        fileMenu.add(load);
        menuBar.add(fileMenu);
        JButton turn = new JButton("Previous turn");
        menuBar.add(turn);
        setJMenuBar(menuBar);

    }

}
