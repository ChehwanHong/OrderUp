
public class Order {

    private final FoodType foodType;
    private final String name;

    public Order(FoodType foodType, String name) {
        if (foodType == null) {
            throw new IllegalArgumentException(
                "Food type cannot be null.");
        }

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Order name cannot be empty.");
        }

        if (name.length() > 100) {
            throw new IllegalArgumentException(
                "Order name is too long.");
        }

        if (!name.matches("[a-zA-Z0-9 ]+")) {
            throw new IllegalArgumentException(
                "Order name contains invalid characters.");
        }

        this.foodType = foodType;
        this.name = name;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public String getName() {
        return name;
    }
}
