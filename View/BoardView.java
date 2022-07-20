package View;
import Model.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class BoardView extends JButton {
    protected static Board board;
    private static final int PADDING = 16;
    private Point selected;
    private GameView view;
    protected static CheckersAI computer;
    protected static boolean soloMode;


    public BoardView(Board b,GameView vw){
        board = b;
        board.startingPosition();
        selected = new Point(-1,-1);
        repaint();
        view = vw;
        this.addActionListener(new MouseListener());
        soloMode = false;
    }

    public BoardView(Board b,boolean side,GameView vw){
        soloMode = true;
        board = b;
        board.startingPosition();
        selected = new Point(-1,-1);
        view = vw;
        computer = new CheckersAI(side,3);
        repaint();
        if (side == Board.WHITE) {
            computer.doMove(board);
            view.createMoveButton(board.getLastMove().getSourceY(),board.getLastMove().getSourceX(),board.getLastMove().getDestinationY(),board.getLastMove().getDestinationX(),computer.getSide());
        }
        repaint();
        this.addActionListener(new MouseListener());
    }


    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

        final int BOX_PADDING = 4;
        final int W = getWidth(), H = getHeight();
        final int DIM = W < H? W : H, BOX_SIZE = (DIM - 2 * PADDING) / 8;
        final int OFFSET_X = (W - BOX_SIZE * 8) / 2;
        final int OFFSET_Y = (H - BOX_SIZE * 8) / 2;
        final int CHECKER_SIZE = Math.max(0, BOX_SIZE - 2 * BOX_PADDING);

        g.setColor(Color.BLACK);
        g.drawRect(OFFSET_X - 1, OFFSET_Y - 1, BOX_SIZE * 8 + 1, BOX_SIZE * 8 + 1);
        g.setColor(Color.WHITE);
        g.fillRect(OFFSET_X, OFFSET_Y, BOX_SIZE * 8, BOX_SIZE * 8);
        g.setColor(Color.BLACK);
        for (int y = 0; y < 8; y ++) {
            for (int x = (y + 1) % 2; x < 8; x += 2) {
                g.fillRect(OFFSET_X + x * BOX_SIZE, OFFSET_Y + y * BOX_SIZE,
                        BOX_SIZE, BOX_SIZE);
            }
        }

        for (int i = 0; i < board.getBoardSize(); i++){
            int cy = OFFSET_X + i * BOX_SIZE + BOX_PADDING;
            for (int j = 0; j < board.getBoardSize(); j++){
                int cx = OFFSET_Y + j * BOX_SIZE + BOX_PADDING;
                if (board.getCell(j, i).isFREE()) {
                    continue;
                }
                if (board.getCell(j, i).isBLACK()) {
                    g.setColor(Color.DARK_GRAY);
                    g.fillOval(cy + 2, cx + 1, CHECKER_SIZE, CHECKER_SIZE);
                    g.setColor(Color.LIGHT_GRAY);
                    g.drawOval(cy + 2, cx + 1, CHECKER_SIZE, CHECKER_SIZE);

                    g.setColor(Color.BLACK);
                    g.fillOval(cy, cx, CHECKER_SIZE, CHECKER_SIZE);
                    g.setColor(Color.LIGHT_GRAY);
                    g.drawOval(cy, cx, CHECKER_SIZE, CHECKER_SIZE);
                }

                if (board.getCell(j, i).isBLACK() && board.getCell(j, i).isQUEEN()) {
                    g.setColor(Color.DARK_GRAY);
                    g.fillOval(cy + 2, cx + 1, CHECKER_SIZE, CHECKER_SIZE);
                    g.setColor(Color.LIGHT_GRAY);
                    g.drawOval(cy + 2, cx + 1, CHECKER_SIZE, CHECKER_SIZE);
                    g.setColor(Color.DARK_GRAY);
                    g.fillOval(cy, cx, CHECKER_SIZE, CHECKER_SIZE);
                    g.setColor(Color.LIGHT_GRAY);
                    g.drawOval(cy, cx, CHECKER_SIZE, CHECKER_SIZE);
                    g.setColor(Color.BLACK);
                    g.fillOval(cy - 2, cx - 1, CHECKER_SIZE, CHECKER_SIZE);
                }

                if (board.getCell(j, i).isWHITE()) {
                    g.setColor(Color.LIGHT_GRAY);
                    g.fillOval(cy + 2, cx + 1, CHECKER_SIZE, CHECKER_SIZE);
                    g.setColor(Color.DARK_GRAY);
                    g.drawOval(cy + 2, cx + 1, CHECKER_SIZE, CHECKER_SIZE);
                    g.setColor(Color.WHITE);
                    g.fillOval(cy, cx, CHECKER_SIZE, CHECKER_SIZE);
                    g.setColor(Color.DARK_GRAY);
                    g.drawOval(cy, cx, CHECKER_SIZE, CHECKER_SIZE);
                }

                if (board.getCell(j, i).isWHITE() && board.getCell(j, i).isQUEEN()) {
                    g.setColor(Color.LIGHT_GRAY);
                    g.fillOval(cy + 2, cx + 1, CHECKER_SIZE, CHECKER_SIZE);
                    g.setColor(Color.DARK_GRAY);
                    g.drawOval(cy + 2, cx + 1, CHECKER_SIZE, CHECKER_SIZE);
                    g.setColor(Color.LIGHT_GRAY);
                    g.fillOval(cy, cx, CHECKER_SIZE, CHECKER_SIZE);
                    g.setColor(Color.DARK_GRAY);
                    g.drawOval(cy, cx, CHECKER_SIZE, CHECKER_SIZE);
                    g.setColor(Color.WHITE);
                    g.fillOval(cy - 2, cx - 1, CHECKER_SIZE, CHECKER_SIZE);
                }


                if (board.getCell(j, i).isQUEEN()) {
                    g.setColor(new Color(255, 240, 0));
                    g.drawOval(cy - 2, cx - 1, CHECKER_SIZE, CHECKER_SIZE);
                    g.drawOval(cy + 1, cx, CHECKER_SIZE - 4, CHECKER_SIZE - 4);
                }
            }
            String player = board.isWhiteMove()? "WHITE TURN" : "BLACK TURN";
            int width = g.getFontMetrics().stringWidth(player);
            g.setColor(Color.BLACK);
            g.drawString(player, W / 2 - width / 2, OFFSET_Y + 8 * BOX_SIZE + 2 + 11);


            if (Cell.isValidCell(selected)) {
                if ((board.isWhiteMove() && board.getCell(this.selected.y,this.selected.x).isWHITE()) || (!board.isWhiteMove() && board.getCell(this.selected.y,this.selected.x).isBLACK())) {
                    g.setColor(Color.GREEN);
                    g.fillRect(OFFSET_X + selected.x * BOX_SIZE, OFFSET_Y + selected.y * BOX_SIZE, BOX_SIZE, BOX_SIZE);
                }
                else {
                    this.selected.x = -1;
                    this.selected.y = -1;
                }
            }
            else {
                this.selected.x = -1;
                this.selected.y = -1;
            }




        }


    }

    private class MouseListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Point m = BoardView.this.getMousePosition();
            if (m != null) {
                MouseClick(m.x, m.y);
            }
        }
    }

    private void MouseClick(int x, int y) {

            if (board.isWin() != Board.CONTINUE) {
                return;
            }
            final int W = getWidth(), H = getHeight();
            final int DIM = W < H ? W : H, BOX_SIZE = (DIM - 2 * PADDING) / 8;
            final int OFFSET_X = (W - BOX_SIZE * 8) / 2;
            final int OFFSET_Y = (H - BOX_SIZE * 8) / 2;
            x = (x - OFFSET_X) / BOX_SIZE;
            y = (y - OFFSET_Y) / BOX_SIZE;
            if (this.selected.x == -1) {
                this.selected.x = x;
                this.selected.y = y;
            }
            else {
                boolean side;
                if (board.isWhiteMove()) side = Board.WHITE;
                else side = Board.BLACK;
                if (board.isValidMove(board.getCell(this.selected.y, this.selected.x), board.getCell(y, x))){
                    if (soloMode) view.createMoveButton(this.selected.y,this.selected.x,y,x,side,computer);
                    else view.createMoveButton(this.selected.y,this.selected.x,y,x,side);
                }
                board.doMove(board.getCell(this.selected.y, this.selected.x), board.getCell(y, x));
                repaint();
                this.selected.x = -1;
                this.selected.y = -1;


                if (soloMode) {
                    doAIMove();
                }

                if (board.isWin() != Board.CONTINUE) {
                    String victory;
                    if (board.isWin() == Board.W_WIN) victory = "Game Over! WHITE VICTORY";
                    else victory = "Game over! BLACK VICTORY";
                    new PostGameMenu(view,victory);
                }

            }
    }


    public void doAIMove(){
        if (BoardView.board.isWhiteMove() && computer.getSide() == Board.WHITE) {
            computer.doMove(board);
            view.createMoveButton(board.getLastMove().getSourceX(),board.getLastMove().getSourceY(),board.getLastMove().getDestinationX(),board.getLastMove().getDestinationY(),computer.getSide(),computer);
        }
        else if (!BoardView.board.isWhiteMove() && computer.getSide() == Board.BLACK) {
            computer.doMove(board);
            view.createMoveButton(board.getLastMove().getSourceX(),board.getLastMove().getSourceY(),board.getLastMove().getDestinationX(),board.getLastMove().getDestinationY(),computer.getSide(),computer);
        }
        repaint();
    }






}
