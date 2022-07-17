package IO;
import java.io.*;
import Model.*;

public class BoardWriter extends FileWriter {
    public BoardWriter(File file) throws IOException {
        super(file);
    }

    public void write(Board board) throws IOException{
        for (int i = 0; i < board.getBoardSize(); i++){
            for (int j = 0; j < board.getBoardSize(); j++){
                if (board.getCell(i,j).isWHITE()) write("white");
                else if (board.getCell(i,j).isBLACK()) write("black");
                else write("free");

                write("-");
                if (board.getCell(i,j).isQUEEN()) write("1");
                else write("0");
                write('\n');
            }
        }
        if(board.isWhiteMove()) write("white_move");
        else write("black_move");
        write('\n');
        int wc = board.getWhiteCounter();
        write(String.valueOf(wc));
        write('\n');
        int bc = board.getBlackCounter();
        write(String.valueOf(bc));
        write('\n');

        if (!board.isHistoryEmpty()) {
            write("Match History");
            write('\n');
            for (int i = 0; i < board.HistorySize(); i++){
                int src_row = board.getHistoryMove(i).getSourceX();
                write(String.valueOf(src_row));
                write(" ");
                int src_column = board.getHistoryMove(i).getSourceY();
                write(String.valueOf(src_column));
                write(" ");
                int dest_row = board.getHistoryMove(i).getDestinationX();
                write(String.valueOf(dest_row));
                write(" ");
                int dest_column = board.getHistoryMove(i).getDestinationY();
                write(String.valueOf(dest_column));
                write('\n');

            }
        }

    }
}
