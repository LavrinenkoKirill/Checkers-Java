package Tests;

import Model.Move;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class MoveTest {

    @Test
    void getSourceTest() {
        Point first = new Point(1,1);
        Point second = new Point(2,2);
        Move mv = new Move(first,second);
        assertEquals(first,mv.getSource());
    }

    @Test
    void getDestinationTest() {
        Point first = new Point(1,1);
        Point second = new Point(2,2);
        Move mv = new Move(first,second);
        assertEquals(second,mv.getDestination());
    }

    @Test
    void getSourceXTest() {
        Point first = new Point(1,3);
        Point second = new Point(2,2);
        Move mv = new Move(first,second);
        assertEquals(1,mv.getSourceX());
    }

    @Test
    void getSourceYTest() {
        Point first = new Point(1,3);
        Point second = new Point(2,2);
        Move mv = new Move(first,second);
        assertEquals(3,mv.getSourceY());
    }

    @Test
    void getDestinationXTest() {
        Point first = new Point(1,3);
        Point second = new Point(2,10);
        Move mv = new Move(first,second);
        assertEquals(2,mv.getDestinationX());
    }

    @Test
    void getDestinationYTest() {
        Point first = new Point(1,3);
        Point second = new Point(2,10);
        Move mv = new Move(first,second);
        assertEquals(10,mv.getDestinationY());
    }
}