package Tests;

import Model.Move;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class MoveTest {

    @Test
    public void getSourceTest() {
        Point first = new Point(1,1);
        Point second = new Point(2,2);
        Move mv = new Move(first,second);
        assertEquals(first,mv.getSource());
    }

    @Test
    public void getDestinationTest() {
        Point first = new Point(1,1);
        Point second = new Point(2,2);
        Move mv = new Move(first,second);
        assertEquals(second,mv.getDestination());
    }

    @Test
    public void getSourceXTest() {
        Point first = new Point(1,3);
        Point second = new Point(2,2);
        Move mv = new Move(first,second);
        assertEquals(1,mv.getSourceX());
    }

    @Test
    public void getSourceYTest() {
        Point first = new Point(1,3);
        Point second = new Point(2,2);
        Move mv = new Move(first,second);
        assertEquals(3,mv.getSourceY());
    }

    @Test
    public void getDestinationXTest() {
        Point first = new Point(1,3);
        Point second = new Point(2,10);
        Move mv = new Move(first,second);
        assertEquals(2,mv.getDestinationX());
    }

    @Test
    public void getDestinationYTest() {
        Point first = new Point(1,3);
        Point second = new Point(2,10);
        Move mv = new Move(first,second);
        assertEquals(10,mv.getDestinationY());
    }
}