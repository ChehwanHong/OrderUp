
import static org.junit.Assert.*;
import org.junit.Test;

public class OrderTest {

    @Test
    public void testValidOrder() {
        Order order = new Order(FoodType.MAIN, "Burger");

        assertEquals(FoodType.MAIN, order.getFoodType());
        assertEquals("Burger", order.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullFoodType() {
        new Order(null, "Burger");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullName() {
        new Order(FoodType.MAIN, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyName() {
        new Order(FoodType.MAIN, "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWhitespaceName() {
        new Order(FoodType.MAIN, "   ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTooLongName() {
        String name = "";

        for (int i = 0; i < 101; i++) {
            name += "A";
        }

        new Order(FoodType.MAIN, name);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidCharacters() {
        new Order(FoodType.MAIN, "Burger@");
    }

    @Test
    public void testMaximumValidNameLength() {
        String name = "";

        for (int i = 0; i < 100; i++) {
            name += "A";
        }

        Order order = new Order(FoodType.MAIN, name);
        assertEquals(100, order.getName().length());
    }
}
