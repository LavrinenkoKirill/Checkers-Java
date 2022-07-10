package Model;
import java.awt.Point;

public class Cell {
    public static final int FREE = 0;
    public static final int BLACK_PLAYER = 1;
    public static final int WHITE_PLAYER = 2;
    protected boolean colour;
    protected int state;
    protected int row;
    protected int column;
    protected boolean queen;

    protected Cell(){
        this.colour = false;
        this.state = FREE;
        this.queen = false;
    }

    public boolean isFREE(){
        if (this.state == FREE) return true;
        else return false;
    }

    public boolean isBLACK(){
        if (this.state == BLACK_PLAYER) return true;
        else return false;
    }

    public boolean isWHITE(){
        if (this.state == WHITE_PLAYER) return true;
        else return false;
    }

    public boolean isQUEEN(){
        if (this.queen == true) return true;
        else return false;
    }

    public static boolean isValidCell(Point testPoint) {

        if (testPoint == null) {
            return false;
        }

        // Check that it is on the board
        final int x = testPoint.x, y = testPoint.y;
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return false;
        }


        // Check that it is on a black tile
        if (x % 2 == y % 2) {
            return false;
        }
        return true;
    }
}
