
import static org.junit.Assert.*;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class OrderControllerTest {

    private static class TestView extends OrderView {
        private final String[] inputs;
        private int index = 0;
        private final StringBuilder messages = new StringBuilder();

        public TestView(String... inputs) {
            this.inputs = inputs;
        }

        @Override
        public String readLine(String prompt) {
            if (index >= inputs.length) {
                return null;
            }
            return inputs[index++];
        }

        @Override
        public void showMessage(String message) {
            messages.append(message).append("\n");
        }

        public String getMessages() {
            return messages.toString();
        }
    }

    @Test
    public void testAddValidOrder() {
        OrderQueue queue = new OrderQueue();
        TestView view = new TestView();
        OrderController controller =
            new OrderController(queue, view);

        assertTrue(controller.addOrder("2", "Burger"));
        assertEquals(1, queue.size());
        assertEquals("Burger", queue.peek().getName());
    }

    @Test
    public void testInvalidFoodType() {
        OrderQueue queue = new OrderQueue();
        TestView view = new TestView();
        OrderController controller =
            new OrderController(queue, view);

        assertFalse(controller.addOrder("abc", "Burger"));
        assertFalse(controller.addOrder("99", "Burger"));
        assertFalse(controller.addOrder(null, "Burger"));
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testInvalidOrderName() {
        OrderQueue queue = new OrderQueue();
        TestView view = new TestView();
        OrderController controller =
            new OrderController(queue, view);

        assertFalse(controller.addOrder("2", ""));
        assertFalse(controller.addOrder("2", "   "));
        assertFalse(controller.addOrder("2", null));
        assertFalse(controller.addOrder("2", "Burger@"));
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testCancelInvalidNumber() {
        OrderQueue queue = new OrderQueue();
        TestView view = new TestView("4", "abc", "5");
        OrderController controller =
            new OrderController(queue, view);

        queue.enqueue(new Order(FoodType.MAIN, "Burger"));
        controller.run();

        assertEquals(1, queue.size());
        assertTrue(view.getMessages().contains("Invalid input"));
    }

    @Test
    public void testCancelOutOfRange() {
        OrderQueue queue = new OrderQueue();
        TestView view = new TestView("4", "99", "5");
        OrderController controller =
            new OrderController(queue, view);

        queue.enqueue(new Order(FoodType.MAIN, "Burger"));
        controller.run();

        assertEquals(1, queue.size());
        assertTrue(view.getMessages().contains("Invalid order number"));
    }

    @Test
    public void testCancelEmptyQueue() {
        OrderQueue queue = new OrderQueue();
        TestView view = new TestView("4", "5");
        OrderController controller =
            new OrderController(queue, view);

        controller.run();

        assertTrue(queue.isEmpty());
        assertTrue(view.getMessages().contains("no pending orders"));
    }

    @Test
    public void testInvalidMenuOption() {
        OrderQueue queue = new OrderQueue();
        TestView view = new TestView("abc", "5");
        OrderController controller =
            new OrderController(queue, view);

        controller.run();

        assertTrue(view.getMessages().contains("Invalid option"));
    }

    @Test
    public void testValidCancellationThroughController() {
        OrderQueue queue = new OrderQueue();
        TestView view = new TestView("4", "2", "5");
        OrderController controller =
            new OrderController(queue, view);

        Order first = new Order(FoodType.MAIN, "Burger");
        Order second = new Order(FoodType.MAIN, "Pizza");

        queue.enqueue(first);
        queue.enqueue(second);

        controller.run();

        assertEquals(1, queue.size());
        assertSame(first, queue.peek());
        assertTrue(view.getMessages().contains("Pizza"));
    }
}
