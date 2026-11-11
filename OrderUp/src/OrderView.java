import java.util.List;
import java.util.Scanner;

/**
 * View: displays menus, orders, and messages, and reads raw
 * text from the console. It holds no queue and applies no
 * validation rules.
 */
public class OrderView {

    private final Scanner input;

    public OrderView() {
        input = new Scanner(System.in);
    }

    public void showMenu() {
        System.out.println();
        System.out.println("=== Order Up ===");
        System.out.println("1. Add an order");
        System.out.println("2. View pending orders");
        System.out.println("3. Cook next order");
        System.out.println("4. Exit");
    }

    public void showFoodTypes() {
        FoodType[] types = FoodType.values();

        for (int i = 0; i < types.length; i++) {
            System.out.println((i + 1) + ". " + label(types[i]));
        }
    }

    public void showPendingOrders(List<Order> orders) {
        if (orders.isEmpty()) {
            System.out.println("No pending orders.");
            return;
        }

        System.out.println("Pending orders (oldest first):");

        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            System.out.println((i + 1) + ". "
                + label(order.getFoodType())
                + " - " + order.getName());
        }
    }

    public void showCookedOrder(Order order) {
        System.out.println("Cooked: " + order.getName()
            + " (" + label(order.getFoodType()) + ")");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints the prompt and returns the next line typed, or null
     * if the input has ended.
     */
    public String readLine(String prompt) {
        System.out.print(prompt);

        if (input.hasNextLine()) {
            return input.nextLine();
        }

        return null;
    }

    private String label(FoodType type) {
        String text = type.name();
        return text.charAt(0) + text.substring(1).toLowerCase();
    }
}
