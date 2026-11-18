# OrderUp – Restaurant Order Management System

## Project Overview

OrderUp is a Java-based restaurant order management application that helps kitchen staff organize and prepare customer orders. Users can add orders, view pending orders, cook orders in FIFO order, and cancel pending orders.

The application uses a singly linked queue and follows the Model-View-Controller (MVC) design pattern. The Model manages order data, the View displays information, and the Controller handles user input and communicates with the Model.

## Deliverable 4 – Second Prototype + Tests

### 1. Current Implementation

**Model**

- `FoodType.java` – Defines APPETIZER, MAIN, and DESSERT.
- `Order.java` – Stores food type and order name and validates order data.
- `OrderNode.java` – Represents a node in the singly linked queue.
- `OrderQueue.java` – Implements FIFO queue operations and allows pending orders to be canceled by position.

**View**

- `OrderView.java` – Displays the menu, pending orders, completed orders, and messages. It also reads user input.

**Controller**

- `OrderController.java` – Validates user input, communicates with the Model, and controls the application.

**Application and Testing**

- `Main.java` – Connects the Model, View, and Controller.
- `ModelDriver.java` – Demonstrates the Model independently.
- `OrderQueueTest.java` – Contains JUnit tests for queue operations and order cancellation.

### 2. How to Run

1. Clone or download the repository and import the OrderUp project into Eclipse.
2. Make sure JUnit 4 is available in the project's build path.
3. Open `Main.java` and select **Run As → Java Application**.
4. Use the console menu to add orders, view pending orders, cook orders, cancel orders, or exit.

To run the Model independently, open `ModelDriver.java` and select **Run As → Java Application**.

To run the tests, right-click `OrderQueueTest.java` and select **Run As → JUnit Test**.

### 3. Testing

We expanded our automated tests to cover normal behavior, invalid inputs, and edge cases in the Model.

Our JUnit tests check FIFO order, empty Queue operations, null orders, invalid cancellation positions, cancellation at different positions, and order validation.

We also tested interleaved enqueue and dequeue operations to verify that orders remain in the correct FIFO order.

The tests run independently of the View and Controller.

We manually tested the console application, including adding orders, viewing pending orders, cooking orders, canceling orders, and handling invalid menu selections.

All 13 current JUnit tests passed.

Our tests cover:

- Adding orders to the Queue.
- Removing orders in FIFO order.
- Viewing the next order without removing it.
- Handling an empty Queue.
- Rejecting null orders.
- Viewing pending orders without changing the Queue.
- Adding another order after the Queue becomes empty.
- Canceling the first, middle, and last pending orders.
- Handling invalid cancellation positions.
- Attempting cancellation when the Queue is empty.
- Preserving FIFO order after interleaved enqueue and dequeue operations.

We also manually tested the cancellation feature through the console. The View displays the pending orders with their positions, and the user can select an order to cancel.

The Controller handles invalid cancellation input without crashing the application.

## 4. Reflection

### How Our Design Absorbed the Change

For Deliverable 4, we implemented the order cancellation feature that we planned in Deliverable 3.

We added `cancelOrder(int position)` to `OrderQueue.java`. This method removes an order from a selected position while keeping the remaining orders in their original FIFO order. It also updates the head, tail, and size when necessary.

The feature required changes to these files:

- `OrderQueue.java` – Added the cancellation logic.
- `OrderQueueTest.java` – Added cancellation tests.
- `OrderController.java` – Added cancellation input handling.
- `OrderView.java` – Added the cancellation menu option.

This matched our Deliverable 3 prediction. We expected the Model, Controller, View, and tests to change, while `Order.java`, `OrderNode.java`, `FoodType.java`, and `Main.java` would remain unchanged.

One important detail was handling cancellation at different positions. Removing the first order was different from removing a middle or last order because the Queue's head and tail references needed to remain correct.

### Model-View Separation

Our design kept the Model separate from the View.

The View displays the menu and receives user input. The Controller validates the input and calls `cancelOrder()` in the Model. The Model removes the selected order and returns the result.

The View does not directly modify the Queue or store a separate copy of the pending orders.

The cancellation feature required a new menu option and Controller method, but it did not require the View to understand how the linked Queue works.

### Changes from Our D3 Plan

We followed our original plan to implement cancellation using the order's displayed position.

We originally planned a Controller method named `cancelPendingOrder()`, but the implemented method is named `handleCancelOrder()`. The behavior is the same.

We also expanded our JUnit tests to check cancellation at different positions, invalid positions, empty Queues, and FIFO behavior after interleaved operations.

We kept the existing MVC structure and did not need to redesign the application.

### What Does Not Work Yet

The main MVP features are implemented, including adding, viewing, cooking, and canceling orders.

We still need to expand automated testing for additional validation cases and confirm that all invalid inputs are handled correctly.

We also need to complete our final review of the application and documentation before the final submission.

### Making Future Changes Easier

One design improvement that would make future features easier is moving more order-related operations into reusable Model methods. For example, if we add completed order history, we could create a separate class to manage completed orders instead of adding that responsibility to the View or Controller.

We could also separate input parsing from the Controller's menu handling. This would make validation easier to test independently and reduce the number of changes needed when adding new menu options.

Our current MVC structure already helped us implement cancellation without changing the existing Order, OrderNode, FoodType, or Main classes. Keeping these responsibilities separate should make future features easier to implement and test.
## 5. Next Steps

### Remaining Work for the Final Build

1. Complete additional JUnit tests for edge cases and invalid inputs.
2. Review the Controller's input validation.
3. Test all features together through the console.
4. Fix any problems discovered during final testing.
5. Review the README and prepare the final demonstration.

### Stretch Goals

We are committing to completing and improving the order cancellation feature.

We are not committing to estimated cooking time or completed order history for the final build. We want to prioritize a reliable application with complete testing.

### Three-Minute Demo Plan

**0:00–0:30 — Introduction**

Explain the purpose of OrderUp and briefly introduce the MVC design.

**0:30–1:15 — Add and View Orders**

Add three orders and display the pending Queue.

**1:15–2:00 — Cancel an Order**

Cancel the second pending order and display the Queue again to show that the remaining orders keep their FIFO order.

**2:00–2:30 — Cook an Order**

Cook the next order and show that the oldest remaining order is removed first.

**2:30–3:00 — Bad Input and Testing**

Demonstrate an invalid cancellation position and briefly show the JUnit test results.

### Team Responsibilities

**Chehwan**

- Maintain the Model classes.
- Implement and test order cancellation in `OrderQueue.java`.
- Expand JUnit tests for Queue behavior and invalid inputs.
- Help with final integration testing.

**Diya Saraf**

- Maintain the View and Controller.
- Handle cancellation input and menu interaction.
- Review Controller validation.
- Help with final integration testing.

**Both Members**

- Review the final application.
- Prepare the demonstration.
- Complete and verify the README.

## 6. Attribution

| File | Contributor | Contribution |
|---|---|---|
| `FoodType.java` | Diya Saraf | Defined food categories |
| `Order.java` | Chehwan | Wrote order data and validation |
| `OrderNode.java` | Chehwan | Wrote linked Queue node implementation |
| `OrderQueue.java` | Chehwan | Wrote FIFO Queue operations and added cancellation logic |
| `OrderQueueTest.java` | Chehwan | Wrote and expanded JUnit tests, including cancellation tests |
| `ModelDriver.java` | Chehwan | Wrote independent Model demonstration |
| `OrderView.java` | Diya Saraf | Wrote console interface and added cancellation menu option |
| `OrderController.java` | Diya Saraf | Wrote input validation and Controller logic, including cancellation handling |
| `Main.java` | Diya Saraf | Connected Model, View, and Controller |

## 7. Generative AI Usage

We used generative AI to help us understand the project structure, review our code, and develop ideas for JUnit tests. For Deliverable 4, AI helped suggest how to implement order cancellation in the linked Queue and how to test different cancellation cases.

We reviewed the suggested code and made sure it matched our existing design. 
We ran the JUnit tests and manually tested the application to check that the implemented features worked correctly.
