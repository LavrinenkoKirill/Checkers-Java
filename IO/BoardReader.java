package IO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import Model.*;
import java.awt.Point;

public class BoardReader extends FileReader {
    public BoardReader(File file) throws IOException {
        super(file);
    }

    public Board load() throws IOException{
        try {
            int row = 0;
            int column = 0;
            String str = new String();
            Scanner scan = new Scanner(this);
            Board board = new Board();
            while (scan.hasNextLine()) {
                str = scan.nextLine();
                if (column == 8) {
                    row++;
                    column = 0;
                }
                if (str.lastIndexOf("white") != -1) {
                    if (str.lastIndexOf("0") != -1) {
                        board.setCell(row, column, 2, false);
                    }
                    else {
                        board.setCell(row, column, 2, true);
                    }
                }
                else if (str.lastIndexOf("black") != -1) {
                    if (str.lastIndexOf("0") != -1) {
                        board.setCell(row, column, 1, false);
                    } else {
                        board.setCell(row, column, 1, true);
                    }
                }
                else if (str.lastIndexOf("free") != -1) {
                    board.setCell(row, column, 0, false);
                }
                else {
                    Exception e = new Exception("Incorrect file");
                    throw e;
                }
                if (row == 7 && column == 7) break;
                column++;
            }
            str = scan.nextLine();
            if (str.lastIndexOf("white_move") != -1) {
                board.setMove(false);
            }
            else if (str.lastIndexOf("black_move") != -1) {
                board.setMove(true);
            }
            else {
                Exception e = new Exception("Incorrect file");
                throw e;
            }
            if (scan.hasNextInt()){
                board.setWhiteCounter(scan.nextInt());
            }
            else {
                Exception e = new Exception("Incorrect file");
                throw e;
            }
            if (scan.hasNextInt()){
                board.setBlackCounter(scan.nextInt());
            }
            else {
                Exception e = new Exception("Incorrect file");
                throw e;
            }
            scan.skip("\n");
            if (scan.hasNextLine()){
                str = scan.nextLine();
                System.out.println(str);
                if (str.lastIndexOf("Match History") != -1) {
                    Point source = new Point(-1,-1);
                    Point destination=  new Point(-1,-1);
                    boolean flag = true;
                    while (scan.hasNextInt()){
                        source.x = scan.nextInt();
                        if (scan.hasNextInt()) source.y = scan.nextInt();
                        else flag = false;
                        if (scan.hasNextInt()) destination.x = scan.nextInt();
                        else flag = false;
                        if (scan.hasNextInt()) destination.y = scan.nextInt();
                        else flag = false;

                        if (flag == true){
                            System.out.println(source.x);
                            System.out.println(source.y);

                            System.out.println(destination.x);
                            System.out.println(destination.y);
                            Move mv = new Move(source,destination);
                            board.addMove(mv);
                        }
                        else {
                            Exception e = new Exception("Ne chitaet");
                            throw e;
                        }
                    }
                }
                else {
                    Exception e = new Exception("OH my God");
                    throw e;
                }
            }

            return board;
        }
        catch(Exception e){
            e.getMessage();
            return null;
        }
    }
}

