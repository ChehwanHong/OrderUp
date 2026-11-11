
public class ModelDriver {

    public static void main(String[] args) {

        OrderQueue queue = new OrderQueue();

        queue.enqueue(new Order(FoodType.MAIN, "Burger"));
        queue.enqueue(new Order(FoodType.MAIN, "Pizza"));
        queue.enqueue(new Order(FoodType.DESSERT, "Cake"));

        System.out.println("Pending orders:");

        for (Order order : queue.getPendingOrders()) {
            System.out.println(order.getName());
        }

        System.out.println("Cooking: "
            + queue.dequeue().getName());

        System.out.println("Next order: "
            + queue.peek().getName());

        System.out.println("Remaining orders: "
            + queue.size());

        OrderView view = new OrderView();
        OrderController controller = new OrderController(queue, view);
 
        controller.run();
    }
}
