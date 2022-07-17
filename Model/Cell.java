package Model;
import java.awt.Point;

public class Cell {
    protected boolean colour;
    protected int state;
    protected int row;
    protected int column;
    protected boolean queen;

    protected Cell(){
        this.colour = false;
        this.state = Board.FREE;
        this.queen = false;
    }

    public boolean isFREE(){
        return this.state == Board.FREE;
    }

    public boolean isBLACK(){
        return this.state == Board.BLACK_PLAYER;
    }

    public boolean isWHITE(){
        return this.state == Board.WHITE_PLAYER;
    }

    public boolean isQUEEN(){
        return this.queen;
    }

    public static boolean isValidCell(Point testPoint) {

        if (testPoint == null) {
            return false;
        }

        final int x = testPoint.x, y = testPoint.y;
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return false;
        }

        return x % 2 != y % 2;
    }
}
