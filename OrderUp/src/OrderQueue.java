
import java.util.ArrayList;
import java.util.List;

public class OrderQueue {

    private OrderNode head;
    private OrderNode tail;
    private int size;

    public OrderQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    public void enqueue(Order order) {
        if (order == null) {
            throw new IllegalArgumentException(
                "Order cannot be null.");
        }

        OrderNode newNode = new OrderNode(order);

        if (isEmpty()) {
            head = newNode;
        }
        else {
            tail.setNext(newNode);
        }

        tail = newNode;
        size++;
    }

    public Order dequeue() {
        if (isEmpty()) {
            return null;
        }

        Order removed = head.getOrder();
        head = head.getNext();
        size--;

        if (isEmpty()) {
            tail = null;
        }

        return removed;
    }

    public Order peek() {
        if (isEmpty()) {
            return null;
        }

        return head.getOrder();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public List<Order> getPendingOrders() {
        List<Order> orders = new ArrayList<>();

        OrderNode current = head;

        while (current != null) {
            orders.add(current.getOrder());
            current = current.getNext();
        }

        return orders;
    }
}
