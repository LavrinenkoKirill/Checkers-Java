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
        if (this.state == Board.FREE) return true;
        else return false;
    }

    public boolean isBLACK(){
        if (this.state == Board.BLACK_PLAYER) return true;
        else return false;
    }

    public boolean isWHITE(){
        if (this.state == Board.WHITE_PLAYER) return true;
        else return false;
    }

    public void Str(){
        System.out.println(row);
        System.out.println(column);
    }

    public boolean isQUEEN(){
        if (this.queen == true) return true;
        else return false;
    }

    public static boolean isValidCell(Point testPoint) {

        if (testPoint == null) {
            return false;
        }

        final int x = testPoint.x, y = testPoint.y;
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return false;
        }

        if (x % 2 == y % 2) {
            return false;
        }
        return true;
    }
}
