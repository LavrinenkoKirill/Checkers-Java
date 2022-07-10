package IO;
import java.io.*;
import Model.*;

public class BoardWriter extends FileWriter {
    public BoardWriter(File file) throws IOException {
        super(file);
    }

    public void write(Board board) throws IOException{
        for (int i = 0; i < board.getBoard_size(); i++){
            for (int j = 0; j < board.getBoard_size(); j++){
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
    }
}
