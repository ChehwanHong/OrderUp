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

### 4. Reflection

#### Changes from Deliverable 2

We followed our original class design and implemented a singly linked queue using head and tail references. The queue provides `enqueue()`, `dequeue()`, `peek()`, `isEmpty()`, `size()`, and `getPendingOrders()`.

We added `ModelDriver.java` to demonstrate the model without the View or Controller. We also implemented JUnit tests to verify the queue's behavior.

During integration testing, we noticed that entering an invalid food type still prompted the user for an order name. We modified `OrderController.handleAddOrder()` to validate the food type immediately. This improved input handling without changing the model.

#### Model Independence

Our Model works independently of the View and Controller. The queue manages orders without reading console input or displaying messages.

The Controller communicates with the Model, while the View handles user interaction. This separation makes the application easier to test, maintain, and extend.

#### Problems Solved and Remaining Questions

One challenge was maintaining the correct head and tail references when adding and removing orders. We addressed this through our linked queue implementation and testing.

Another issue was validating user input at the correct time. We improved the Controller so invalid food types are rejected before the user enters an order name.

The main MVP features are implemented and the console application runs successfully.

We still need to expand our tests for additional edge cases and ensure the application behaves correctly with all invalid input scenarios.

### 5. Next Steps for Deliverable 4

#### Remaining Work

1. Expand JUnit tests for empty queues, null orders, and queue boundary cases.
2. Add automated tests for Controller validation and application behavior.
3. Improve error messages and input handling where necessary.
4. Implement an additional nontrivial feature.
5. Perform final integration testing and review the application.

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

#### Team Responsibilities

**Chehwan**
- Maintain and improve the Model classes.
- Implement queue cancellation logic.
- Expand JUnit tests.
- Assist with integration testing.

**Diya Saraf**
- Maintain and improve the View and Controller.
- Implement cancellation menu interactions and validation.
- Assist with integration testing.

Both members will review the completed application and update documentation.

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

### 7. Generative AI Usage

We used generative AI to help us understand the class structure and how the different classes work together. It also helped us troubleshoot errors during development and understand how to fix them. After implementing our code, we used AI to review our work and check whether the classes, methods, and overall program structure matched the project requirements. We reviewed the suggestions and tested the program ourselves to make sure everything worked correctly.
