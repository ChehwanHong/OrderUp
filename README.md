# OrderUp – Restaurant Order Management System

## Project Overview

OrderUp is a Java-based restaurant order management application that helps kitchen staff organize and prepare customer orders. Users can add orders, view pending orders, and cook orders in the order they were received.

The application uses a singly linked queue that follows the First-In, First-Out (FIFO) principle. We use the Model-View-Controller (MVC) design pattern to separate order management logic from user interaction.

## Deliverable 3 – First Prototype

### 1. Current Implementation

Our first prototype includes the following classes:

**Model**
- `FoodType.java` – Defines APPETIZER, MAIN, and DESSERT.
- `Order.java` – Stores food type and order name and validates order data.
- `OrderNode.java` – Represents a node in the singly linked queue.
- `OrderQueue.java` – Implements FIFO queue operations.

**View**
- `OrderView.java` – Displays menus, pending orders, completed orders, and messages. It also reads console input.

**Controller**
- `OrderController.java` – Validates user input, communicates with the model, and controls the application's flow.

**Application and Testing**
- `Main.java` – Connects the Model, View, and Controller and starts the program.
- `ModelDriver.java` – Demonstrates the model independently of the user interface.
- `OrderQueueTest.java` – Contains JUnit tests for queue operations.

### 2. How to Run

1. Clone or download the repository and import the `OrderUp` Java project into Eclipse.
2. Make sure JUnit 4 is available in the project's build path.
3. Open `Main.java` and select **Run As → Java Application**.
4. Use the console menu to add orders, view pending orders, cook orders, or exit.

To run the model independently, execute `ModelDriver.java` as a Java Application.

To run the unit tests, right-click `OrderQueueTest.java` and select **Run As → JUnit Test**.

### 3. Testing

We tested the queue model independently using JUnit and the ModelDriver.

The model tests passed, and we also successfully ran the console application using the Main class.

We manually verified the following application behaviors:

- Adding food orders.
- Displaying pending orders.
- Cooking orders in FIFO order.
- Displaying a message when the queue is empty.
- Handling invalid menu selections.
- Rejecting invalid food types and allowing the user to try again.
- Exiting the application normally.

The controller was improved so that an invalid food type is rejected immediately, before asking for an order name.

## 4. Reflection

### Changes from Deliverable 2

We followed our original plan to implement a singly linked queue using head and tail references. The queue includes `enqueue()`, `dequeue()`, `peek()`, `isEmpty()`, `size()`, and `getPendingOrders()`.

We implemented `ModelDriver.java` to demonstrate that our Model can run independently without the View or Controller. This allowed us to check the queue's behavior before integrating the entire application.

We also implemented `OrderQueueTest.java` using JUnit to verify the core queue operations. These tests helped us check FIFO behavior and identify potential problems with adding and removing orders.

During integration testing, we discovered that the program asked for an order name even when the user entered an invalid food type. To fix this issue, we modified `OrderController.handleAddOrder()` to validate the food type before requesting the order name.

### Model Independence

Our Model runs independently of the View and Controller. `OrderQueue.java` manages the orders without reading console input or displaying messages.

We confirmed this by running `ModelDriver.java` and the JUnit tests separately from the complete application.

### Problems Solved and Remaining Questions

One challenge was making sure the queue correctly updates its head, tail, and size when adding or removing orders. We addressed this through the linked queue implementation and testing.

Another challenge was handling invalid user input. We improved the Controller to reject invalid food types immediately instead of asking for unnecessary information.

The main MVP features are implemented and the application runs successfully. However, we still need to expand automated testing for additional invalid inputs and edge cases.

For Deliverable 4, we will also need to determine how to identify a specific pending order for cancellation while preserving the FIFO order of the remaining orders.

### 5. Next Steps for Deliverable 4

Remaining Work

1. Expand Queue JUnit tests (Chehwan): Add tests for empty queues, null orders, and boundary cases.

2. Add Controller validation tests (Diya): Test invalid food types, empty order names, and other invalid inputs.

3. Implement order cancellation in the Model (Chehwan): Add cancelOrder(int position) to OrderQueue.java and test cancellation at different positions.

4. Implement cancellation in the Controller and View (Diya): Add cancelPendingOrder() to OrderController.java and update the menu and input prompts in OrderView.java.

5. Final integration testing (Both): Test all MVP features, invalid inputs, and order cancellation.

6. Final documentation review (Both): Update the README and verify that the implementation matches the project requirements.

#### New Feature: Cancel a Pending Order

For Deliverable 4, we plan to implement an order cancellation feature. This will allow kitchen staff to cancel a pending order while preserving the FIFO order of all remaining orders.

**Planned implementation**

- `OrderQueue.java`: Add `cancelOrder(int position)` to remove a pending order from the linked queue. The method will update the head, tail, and size correctly.
- `OrderController.java`: Add `cancelPendingOrder()` to validate cancellation requests and call the queue.
- `OrderView.java`: Add a menu option and prompts for selecting an order to cancel.
- `OrderQueueTest.java`: Add JUnit tests for cancellation.
- `Main.java`: No changes expected because it only initializes the application.
- `FoodType.java`: No changes expected.
- `Order.java`: No changes expected if cancellation uses the displayed queue position.
- `OrderNode.java`: No changes expected because its existing links can be reused.

**Planned cancellation tests**

- Cancel the first order.
- Cancel an order in the middle.
- Cancel the last order.
- Cancel the only order.
- Attempt cancellation when the queue is empty.
- Reject invalid positions.
- Verify that the remaining orders preserve their original FIFO order.

#### Stretch Goals

We will prioritize order cancellation for Deliverable 4.

Estimated cooking time and completed order history are not currently committed features. We may consider them if the cancellation feature and all required functionality are completed.

Team Responsibilities

Chehwan: Maintain the Model, implement queue cancellation, expand JUnit tests, and assist with integration testing.

Diya Saraf: Maintain the View and Controller, implement cancellation input handling, add Controller tests, and assist with integration testing.

Both team members will review the completed application and documentation.

### 6. Attribution

| File | Contributor | Contribution |
|---|---|---|
| `FoodType.java` | Diya Saraf | Defined food categories |
| `Order.java` | Chehwan | Implemented order data and validation |
| `OrderNode.java` | Chehwan | Implemented singly linked nodes |
| `OrderQueue.java` | Chehwan | Implemented FIFO queue operations |
| `OrderQueueTest.java` | Chehwan | Implemented JUnit tests |
| `ModelDriver.java` | Chehwan | Implemented independent model demonstration |
| `OrderView.java` | Diya Saraf | Implemented console input and output |
| `OrderController.java` | Diya Saraf | Implemented controller logic; improved invalid food type handling |
| `Main.java` | Diya Saraf | Connected Model, View, and Controller |

### 7. Generative AI Usage

We used generative AI to help us understand the class structure and how the different classes work together. It also helped us troubleshoot errors during development and understand how to fix them. After implementing our code, we used AI to review our work and check whether the classes, methods, and overall program structure matched the project requirements. We reviewed the suggestions and tested the program ourselves to make sure everything worked correctly.
