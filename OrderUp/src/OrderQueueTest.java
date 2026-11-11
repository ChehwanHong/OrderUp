
import static org.junit.Assert.*;
import org.junit.Test;

public class OrderQueueTest {

    @Test
    public void testEnqueue() {
        OrderQueue queue = new OrderQueue();

        Order order = new Order(FoodType.MAIN, "Burger");
        queue.enqueue(order);

        assertEquals(1, queue.size());
        assertEquals(order, queue.peek());
    }

    @Test
    public void testDequeue() {
        OrderQueue queue = new OrderQueue();

        Order first = new Order(FoodType.MAIN, "Burger");
        Order second = new Order(FoodType.MAIN, "Pizza");

        queue.enqueue(first);
        queue.enqueue(second);

        assertEquals(first, queue.dequeue());
        assertEquals(1, queue.size());
        assertEquals(second, queue.peek());
    }

    @Test
    public void testFIFO() {
        OrderQueue queue = new OrderQueue();

        Order first = new Order(FoodType.MAIN, "Burger");
        Order second = new Order(FoodType.MAIN, "Pizza");
        Order third = new Order(FoodType.DESSERT, "Cake");

        queue.enqueue(first);
        queue.enqueue(second);
        queue.enqueue(third);

        assertSame(first, queue.dequeue());
        assertSame(second, queue.dequeue());
        assertSame(third, queue.dequeue());

        assertTrue(queue.isEmpty());
    }

    @Test
    public void testEmptyQueue() {
        OrderQueue queue = new OrderQueue();

        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
        assertNull(queue.peek());
        assertNull(queue.dequeue());
    }

    @Test
    public void testGetPendingOrders() {
        OrderQueue queue = new OrderQueue();

        Order first = new Order(FoodType.APPETIZER, "Soup");
        Order second = new Order(FoodType.MAIN, "Burger");

        queue.enqueue(first);
        queue.enqueue(second);

        assertEquals(2, queue.getPendingOrders().size());
        assertSame(first, queue.getPendingOrders().get(0));
        assertSame(second, queue.getPendingOrders().get(1));

        // Viewing orders should not remove them.
        assertEquals(2, queue.size());
    }

    @Test
    public void testEnqueueNull() {
        OrderQueue queue = new OrderQueue();

        try {
            queue.enqueue(null);
            fail("Expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            // Expected exception
        }

        assertTrue(queue.isEmpty());
    }

    @Test
    public void testQueueAfterRemovingLastOrder() {
        OrderQueue queue = new OrderQueue();

        Order first = new Order(FoodType.MAIN, "Burger");
        queue.enqueue(first);

        assertSame(first, queue.dequeue());
        assertTrue(queue.isEmpty());

        Order second = new Order(FoodType.DESSERT, "Cake");
        queue.enqueue(second);

        assertSame(second, queue.peek());
        assertEquals(1, queue.size());
    }
}
