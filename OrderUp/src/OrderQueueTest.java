
import static org.junit.Assert.*;
import org.junit.Test;

public class OrderQueueTest
{

    @Test
    public void testEnqueue()
    {
        OrderQueue queue = new OrderQueue();

        Order order = new Order(FoodType.MAIN, "Burger");
        queue.enqueue(order);

        assertEquals(1, queue.size());
        assertEquals(order, queue.peek());
    }


    @Test
    public void testDequeue()
    {
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
    public void testFIFO()
    {
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
    public void testEmptyQueue()
    {
        OrderQueue queue = new OrderQueue();

        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
        assertNull(queue.peek());
        assertNull(queue.dequeue());
    }


    @Test
    public void testGetPendingOrders()
    {
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
    public void testEnqueueNull()
    {
        OrderQueue queue = new OrderQueue();

        try
        {
            queue.enqueue(null);
            fail("Expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e)
        {
            // Expected exception
        }

        assertTrue(queue.isEmpty());
    }


    @Test
    public void testQueueAfterRemovingLastOrder()
    {
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


    @Test
    public void testCancelFirstOrder()
    {
        OrderQueue queue = new OrderQueue();

        Order first = new Order(FoodType.MAIN, "Burger");
        Order second = new Order(FoodType.MAIN, "Pizza");

        queue.enqueue(first);
        queue.enqueue(second);

        assertSame(first, queue.cancelOrder(1));
        assertEquals(1, queue.size());
        assertSame(second, queue.peek());
    }


    @Test
    public void testCancelMiddleOrder()
    {
        OrderQueue queue = new OrderQueue();

        Order first = new Order(FoodType.MAIN, "Burger");
        Order second = new Order(FoodType.MAIN, "Pizza");
        Order third = new Order(FoodType.DESSERT, "Cake");

        queue.enqueue(first);
        queue.enqueue(second);
        queue.enqueue(third);

        assertSame(second, queue.cancelOrder(2));
        assertEquals(2, queue.size());
        assertSame(first, queue.dequeue());
        assertSame(third, queue.dequeue());
        assertTrue(queue.isEmpty());
    }


    @Test
    public void testCancelLastOrder()
    {
        OrderQueue queue = new OrderQueue();

        Order first = new Order(FoodType.MAIN, "Burger");
        Order second = new Order(FoodType.MAIN, "Pizza");
        Order third = new Order(FoodType.DESSERT, "Cake");

        queue.enqueue(first);
        queue.enqueue(second);
        queue.enqueue(third);

        assertSame(third, queue.cancelOrder(3));
        assertEquals(2, queue.size());

        Order fourth = new Order(FoodType.APPETIZER, "Soup");
        queue.enqueue(fourth);

        assertSame(first, queue.dequeue());
        assertSame(second, queue.dequeue());
        assertSame(fourth, queue.dequeue());
        assertTrue(queue.isEmpty());
    }


    @Test
    public void testCancelInvalidPosition()
    {
        OrderQueue queue = new OrderQueue();

        Order order = new Order(FoodType.MAIN, "Burger");
        queue.enqueue(order);

        assertNull(queue.cancelOrder(0));
        assertNull(queue.cancelOrder(-1));
        assertNull(queue.cancelOrder(5));

        assertEquals(1, queue.size());
        assertSame(order, queue.peek());
    }


    @Test
    public void testCancelEmptyQueue()
    {
        OrderQueue queue = new OrderQueue();

        assertNull(queue.cancelOrder(1));
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }


    @Test
    public void testInterleavedEnqueueDequeue()
    {
        OrderQueue queue = new OrderQueue();

        Order first = new Order(FoodType.MAIN, "Burger");
        Order second = new Order(FoodType.MAIN, "Pizza");
        Order third = new Order(FoodType.DESSERT, "Cake");
        Order fourth = new Order(FoodType.APPETIZER, "Soup");

        queue.enqueue(first);
        queue.enqueue(second);

        assertSame(first, queue.dequeue());

        queue.enqueue(third);
        assertSame(second, queue.dequeue());

        queue.enqueue(fourth);

        assertSame(third, queue.dequeue());
        assertSame(fourth, queue.dequeue());
        assertTrue(queue.isEmpty());
    }


    @Test
    public void testCancelOnlyOrder()
    {
        OrderQueue queue = new OrderQueue();
        Order order = new Order(FoodType.MAIN, "Burger");

        queue.enqueue(order);

        assertSame(order, queue.cancelOrder(1));
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
        assertNull(queue.peek());

        Order next = new Order(FoodType.MAIN, "Pizza");
        queue.enqueue(next);
        assertSame(next, queue.dequeue());
    }


    @Test
    public void testEmptyPendingOrders()
    {
        OrderQueue queue = new OrderQueue();

        assertTrue(queue.getPendingOrders().isEmpty());
        assertEquals(0, queue.size());
    }


    @Test
    public void testCancelPreservesPendingOrderList()
    {
        OrderQueue queue = new OrderQueue();

        Order first = new Order(FoodType.MAIN, "Burger");
        Order second = new Order(FoodType.MAIN, "Pizza");
        Order third = new Order(FoodType.DESSERT, "Cake");

        queue.enqueue(first);
        queue.enqueue(second);
        queue.enqueue(third);

        queue.cancelOrder(2);

        assertEquals(2, queue.getPendingOrders().size());
        assertSame(first, queue.getPendingOrders().get(0));
        assertSame(third, queue.getPendingOrders().get(1));
    }


    @Test
    public void testInvalidCancelDoesNotChangeQueue()
    {
        OrderQueue queue = new OrderQueue();

        Order first = new Order(FoodType.MAIN, "Burger");
        Order second = new Order(FoodType.MAIN, "Pizza");

        queue.enqueue(first);
        queue.enqueue(second);

        assertNull(queue.cancelOrder(-5));
        assertNull(queue.cancelOrder(0));
        assertNull(queue.cancelOrder(100));

        assertEquals(2, queue.size());
        assertSame(first, queue.dequeue());
        assertSame(second, queue.dequeue());
    }

}
