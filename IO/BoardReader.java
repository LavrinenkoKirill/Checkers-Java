package IO;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import Model.*;


public class BoardReader extends FileReader {
    public BoardReader(File file) throws IOException {
        super(file);
    }

    public Board load() throws IOException{
        String str = new String();
        Scanner scan = new Scanner(this);
        Board board = new Board();
        while (scan.hasNextLine()) {
            str = scan.nextLine();
            if (str.lastIndexOf("white") != -1){
                if (str.lastIndexOf("0") != -1){

                }
            }
        }

    }
}
