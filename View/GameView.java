package View;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.LinkedList;

import Model.*;
import IO.*;

public class GameView extends JFrame {
    private static final int DEFAULT_WIDTH = 500;
    private static final int DEFAULT_HEIGHT = 600;
    private int movesIndex = 0;
    private static JMenu matchHistory = new JMenu("Match History");
    private LinkedList<HistoryButton> buttons = new LinkedList<>();
    public GameView(){
        super("CHECKERS");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        super.setLocationByPlatform(true);
        createOptionalMenu();
        Board brd = new Board();
        brd.startingPosition();
        BoardView board = new BoardView(brd,this);
        setContentPane(board);
        setUndecorated(true);
        setVisible(true);
    }

    public GameView(boolean side){
        super("CHECKERS");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        createOptionalMenu();
        Board brd = new Board();
        brd.startingPosition();
        BoardView board = new BoardView(brd,side,this);
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
        load.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Load file");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int result = fileChooser.showSaveDialog(fileChooser);
                if (result == JFileChooser.APPROVE_OPTION ) {
                    try {
                        BoardReader br = new BoardReader(fileChooser.getSelectedFile());
                        Board b = br.load();
                        if (b != null) {
                            matchHistory.removeAll();
                            BoardView.board = b;
                            boolean turn = false;
                            for(int i = 0; i < BoardView.board.HistorySize(); i++){
                                createMoveButton(BoardView.board.getHistoryMove(i).getSourceX(),BoardView.board.getHistoryMove(i).getSourceY(),BoardView.board.getHistoryMove(i).getDestinationX(),BoardView.board.getHistoryMove(i).getDestinationY(),turn);
                                if (turn == false) turn = true;
                                else turn = false;
                            }


                            br.close();
                            JOptionPane.showMessageDialog(fileChooser, "File '" + fileChooser.getSelectedFile() + " loaded");
                        }
                        else {
                             JOptionPane.showMessageDialog(load,"Load was failed");
                        }
                    }
                    catch (IOException b){
                        System.out.println("File not found");
                    }
                }
            }
        });
        menuBar.add(fileMenu);
        JButton turn = new JButton("Previous turn");
        turn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Board b = BoardView.board.getPreviousTurn();
                if (b!=null){
                    refreshMatchHistory(buttons.size() - 1);
                    BoardView.board = b;
                    repaint();
                }
                else JOptionPane.showMessageDialog(turn,"Match History is empty");
            }
        });
        menuBar.add(turn);

        setJMenuBar(menuBar);
        matchHistory.setMnemonic(KeyEvent.VK_F);
        menuBar.add(matchHistory);
    }

    public void createMoveButton(int sourceRow,int sourceColumn,int destRow,int destColumn,boolean turn){
        String str = new String("");
        if (turn == true){
            str += "Black move: ";
        }
        else str+= "White move: ";

        if (sourceColumn == 0) str+="A";
        else if (sourceColumn == 1) str+="B";
        else if (sourceColumn == 2) str+="C";
        else if (sourceColumn == 3) str+="D";
        else if (sourceColumn == 4) str+="E";
        else if (sourceColumn == 5) str+="F";
        else if (sourceColumn == 6) str+="G";
        else if (sourceColumn == 7) str+="H";
        str+=String.valueOf(sourceRow);
        str+=" - ";
        if (destColumn == 0) str+="A";
        else if (destColumn == 1) str+="B";
        else if (destColumn == 2) str+="C";
        else if (destColumn == 3) str+="D";
        else if (destColumn == 4) str+="E";
        else if (destColumn == 5) str+="F";
        else if (destColumn == 6) str+="G";
        else if (destColumn == 7) str+="H";
        str+=String.valueOf(destRow);
        HistoryButton button = new HistoryButton(movesIndex,str,this);
        movesIndex++;
        matchHistory.add(button);
        buttons.add(button);
    }

    public void refreshMatchHistory(int lastIndex){
        for (int i = 0; i < buttons.size(); i++){
            if (buttons.get(i).getIndex() > lastIndex){
                matchHistory.remove(buttons.get(i));
                buttons.remove(i);
                matchHistory.revalidate();
                matchHistory.repaint();
            }
        }
    }


}
