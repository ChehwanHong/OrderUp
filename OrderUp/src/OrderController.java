/**
 * Controller: takes raw input from the view, validates it, calls the model, and
 * tells the view what to show. It stores no order data itself.
 */
public class OrderController
{

    public static final int MAX_NAME_LENGTH = 100;

    private final OrderQueue queue;
    private final OrderView view;

    public OrderController(OrderQueue queue, OrderView view)
    {
        this.queue = queue;
        this.view = view;
    }


    /**
     * Runs the console menu until the user exits or the input ends.
     */
    public void run()
    {
        boolean running = true;

        while (running)
        {
            view.showMenu();
            String choice = view.readLine("Choose an option: ");

            if (choice == null)
            {
                running = false;
            }
            else
            {
                switch (choice.trim())
                {
                    case "1":
                        handleAddOrder();
                        break;
                    case "2":
                        showPendingOrders();
                        break;
                    case "3":
                        cookNextOrder();
                        break;
                    case "4":
                        running = false;
                        break;
                    default:
                        view.showMessage(
                            "Invalid option. Enter 1, 2, 3, or 4.");
                        break;
                }
            }
        }

        view.showMessage("Goodbye!");
    }


    /**
     * Asks for a food type and name until an order is added or the user cancels
     * with 0 at the food type prompt.
     */

    private void handleAddOrder()
    {
        boolean added = false;

        while (!added)
        {
            view.showFoodTypes();
            String type = view.readLine("Food type (0 to cancel): ");

            if (type == null || type.trim().equals("0"))
            {
                view.showMessage("Order cancelled.");
                return;
            }

            if (parseFoodType(type) == null)
            {
                view.showMessage("Invalid food type. Please enter 1, 2, or 3.");
                continue;
            }

            String name = view.readLine("Order name: ");

            if (name == null)
            {
                view.showMessage("Order cancelled.");
                return;
            }

            added = addOrder(type, name);
        }
    }


    /**
     * Validates the raw input, creates an Order, and adds it to the queue.
     * Shows an error and returns false if the input is invalid, leaving the
     * queue unchanged.
     */
    public boolean addOrder(String foodType, String name)
    {
        FoodType type = parseFoodType(foodType);

        if (type == null)
        {
            view.showMessage(
                "Invalid food type. Enter a number from 1 to "
                    + FoodType.values().length + ".");
            return false;
        }

        String error = checkName(name);

        if (error != null)
        {
            view.showMessage(error);
            return false;
        }

        try
        {
            Order order = new Order(type, name.trim());
            queue.enqueue(order);
            view.showMessage("Order added: " + order.getName());
            return true;
        }
        catch (IllegalArgumentException e)
        {
            // Backstop: the model rejected something the checks missed.
            view.showMessage("Order rejected: " + e.getMessage());
            return false;
        }
    }


    /**
     * Removes the oldest order and tells the view which one was completed, or
     * shows a message if nothing is waiting.
     */
    public void cookNextOrder()
    {
        Order cooked = queue.dequeue();

        if (cooked == null)
        {
            view.showMessage("There are no pending orders to cook.");
        }
        else
        {
            view.showCookedOrder(cooked);
        }
    }


    /**
     * Gets the pending orders from the model and asks the view to display them.
     */
    public void showPendingOrders()
    {
        view.showPendingOrders(queue.getPendingOrders());
    }


    private FoodType parseFoodType(String input)
    {
        if (input == null)
        {
            return null;
        }

        try
        {
            int choice = Integer.parseInt(input.trim());
            FoodType[] types = FoodType.values();

            if (choice >= 1 && choice <= types.length)
            {
                return types[choice - 1];
            }

            return null;
        }
        catch (NumberFormatException e)
        {
            return null;
        }
    }


    private String checkName(String name)
    {
        if (name == null || name.trim().isEmpty())
        {
            return "Order name cannot be empty.";
        }

        String trimmed = name.trim();

        if (trimmed.length() > MAX_NAME_LENGTH)
        {
            return "Order name is too long (maximum " + MAX_NAME_LENGTH
                + " characters).";
        }

        return null;
    }
}
