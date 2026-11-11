
public class Main {

    public static void main(String[] args) {
        OrderQueue queue = new OrderQueue();
        OrderView view = new OrderView();

        OrderController controller =
            new OrderController(queue, view);

        controller.run();
    }
}
