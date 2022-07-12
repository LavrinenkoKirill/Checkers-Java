package Model;
import java.awt.Point;

public class Move {

    private Point source = new Point(-1,-1);
    private Point destination = new Point(-1,-1);

    public Move(){

    }

    public Move(Point src,Point dest){
        source.x = src.x;
        source.y = src.y;

        destination.x = dest.x;
        destination.y = dest.y;
    }

    public Point getSource(){
        return source;
    }

    public Point getDestination(){
        return destination;
    }

    public int getSourceX(){return source.x;}
    public int getSourceY(){return source.y;}

    public int getDestinationX(){return destination.x;}
    public int getDestinationY(){return destination.y;}
}
