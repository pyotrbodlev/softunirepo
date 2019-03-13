package P05_Square;

import org.junit.Assert;
import org.junit.Test;

public class RectangleTests {
    @Test
    public void getSidesTest() {
        Rectangle rect = new Rectangle(5, 10);

        Assert.assertEquals(5, rect.getWidth());
        Assert.assertEquals(10, rect.getHeight());
    }

    @Test
    public void getAreaTest() {
        Rectangle rect = new Rectangle(5, 10);
        Assert.assertEquals(50, rect.getArea());
    }

    @Test
    public void testSidesSquare(){
        Rectangle square = new Square(5);
        Assert.assertEquals(5, square.getHeight());
        Assert.assertEquals(5, square.getWidth());
    }

    @Test
    public void getAreaTestSquare() {
        Rectangle rect = new Square(10);
        Assert.assertEquals(100, rect.getArea());
    }
}
