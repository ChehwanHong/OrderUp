
public class OrderNode {

    private final Order order;
    private OrderNode next;

    public OrderNode(Order order) {
        this.order = order;
        this.next = null;
    }

    public Order getOrder() {
        return order;
    }

    public OrderNode getNext() {
        return next;
    }

    public void setNext(OrderNode next) {
        this.next = next;
    }
}
