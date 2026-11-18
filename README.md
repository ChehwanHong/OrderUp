# OrderUp – Restaurant Order Management System

## Project Overview

OrderUp is a Java-based restaurant order management application that helps kitchen staff organize customer orders. Users can add orders, view pending orders, cook orders in FIFO order, and cancel pending orders.

The application uses a singly linked queue and follows the Model-View-Controller (MVC) design pattern. The Model manages order data, the View displays information, and the Controller handles user input.

## Deliverable 4 – Second Prototype + Tests

### 1. Current Implementation

**Model**
- `FoodType.java` – Defines APPETIZER, MAIN, and DESSERT.
- `Order.java` – Stores and validates order information.
- `OrderNode.java` – Represents a node in the linked queue.
- `OrderQueue.java` – Implements FIFO operations and pending order cancellation.

**View and Controller**
- `OrderView.java` – Displays menus, orders, and messages.
- `OrderController.java` – Validates user input and connects the View to the Model.

**Application and Tests**
- `Main.java` – Starts the application.
- `ModelDriver.java` – Demonstrates the Model independently.
- `OrderQueueTest.java` – Tests Queue operations and cancellation.
- `OrderTest.java` – Tests order validation.
- `OrderControllerTest.java` – Tests Controller behavior and invalid input handling.

### 2. How to Run

1. Clone or download the repository and import the OrderUp project into Eclipse.
2. Make sure JUnit 4 is available in the project's build path.
3. Right-click `Main.java` and select **Run As → Java Application**.
4. Use the console menu to add, view, cook, or cancel orders.

To run the Model independently, execute `ModelDriver.java` as a Java Application.

To run the tests, right-click each test file and select **Run As → JUnit Test**.

### 3. Testing

We ran 33 JUnit tests, and all 33 passed.

- `OrderQueueTest.java`: 17 tests
- `OrderTest.java`: 8 tests
- `OrderControllerTest.java`: 8 tests

The tests cover normal Queue operations, FIFO behavior, empty Queues, invalid orders, and cancellation at different positions.

We also tested interleaved enqueue and dequeue operations to verify that the FIFO order remains correct.

Our tests check invalid cancellation positions, null orders, empty names, overly long names, invalid characters, and invalid Controller input.

The Model tests run independently of the View and Controller.

We manually tested the console application and confirmed that adding, viewing, cooking, and canceling orders work correctly.

## 4. Reflection

### How Our Design Absorbed the Change

For Deliverable 4, we implemented the pending order cancellation feature that we planned in Deliverable 3.

We added `cancelOrder(int position)` to `OrderQueue.java`. This method removes a selected pending order while preserving the FIFO order of the remaining orders. It also updates the head, tail, and size when necessary.

The feature required changes to `OrderQueue.java`, `OrderController.java`, `OrderView.java`, and `OrderQueueTest.java`.

This matched our D3 prediction. We expected these classes and tests to change, while `FoodType.java`, `Order.java`, `OrderNode.java`, and `Main.java` would remain unchanged.

One important challenge was handling cancellation at different positions. Canceling the first, middle, and last orders required careful updates to the linked Queue.

### Model-View Separation

Our MVC structure helped us add the cancellation feature without mixing responsibilities.

The View displays pending orders and receives user input. The Controller validates the selected position and calls the Model. The Model removes the order and returns the result.

The View does not store a separate copy of the Queue or directly change the Model.

The View needed a new menu option, but it did not need to understand how the linked Queue works internally.

### Changes from Our D3 Plan

We followed our original plan to implement cancellation using the displayed order position.

Our D3 plan called the Controller method `cancelPendingOrder()`, but we implemented it as `handleCancelOrder()`. The functionality remained the same.

We also expanded testing beyond our original plan by adding separate tests for order validation and Controller behavior.

We did not need to redesign the existing Model or change the classes that we expected to remain unchanged.

### What Does Not Work Yet

The main MVP features are implemented and working.

We still need to complete final integration testing and review the remaining edge cases before the final submission.

Estimated cooking time and completed order history have not been implemented.

### Making Future Changes Easier

One design improvement would be separating input parsing and validation into reusable methods. This would make future Controller features easier to test and reduce repeated code.

For example, if we add a feature that allows users to edit an order, we could reuse the same input validation logic instead of writing it again.

Our MVC structure already makes future changes easier because the Model handles order logic independently of the console interface.

## 5. Next Steps

### Remaining Work for the Final Build

1. Review all existing JUnit tests and add any missing edge cases.
2. Complete final integration testing for all menu options.
3. Verify that invalid inputs do not crash the application.
4. Fix any issues discovered during testing.
5. Finalize documentation and prepare the demonstration.

### Stretch Goals

We are committing to keeping order cancellation as part of the final application.

We are dropping estimated cooking time and completed order history from the final build so we can focus on reliability and testing.

### Three-Minute Demo Plan

**0:00–0:30 — Introduction**

Explain the purpose of OrderUp and briefly introduce the MVC design.

**0:30–1:15 — Add and View Orders**

Add three orders and display the pending Queue.

**1:15–2:00 — Cancel an Order**

Cancel the second order and display the Queue again to show that the remaining orders stay in FIFO order.

**2:00–2:30 — Cook an Order**

Cook the next order and show that the oldest remaining order is removed first.

**2:30–3:00 — Bad Input and Tests**

Demonstrate an invalid cancellation position and show the JUnit test results.

### Team Responsibilities

**Chehwan**
- Maintain the Model classes.
- Implement and test order cancellation.
- Expand JUnit tests for Queue operations and invalid inputs.
- Assist with final integration testing.

**Diya Saraf**
- Maintain the View and Controller.
- Review cancellation input handling.
- Review Controller validation.
- Assist with final integration testing.

**Both Members**
- Complete final application testing.
- Review the README.
- Prepare the three-minute demonstration.

## 6. Attribution

| File | Contributor | Contribution |
|---|---|---|
| `FoodType.java` | Diya Saraf | Wrote food categories |
| `Order.java` | Chehwan | Wrote order data and validation |
| `OrderNode.java` | Chehwan | Wrote linked Queue nodes |
| `OrderQueue.java` | Chehwan | Wrote FIFO Queue and cancellation logic, GenAI-assisted |
| `OrderQueueTest.java` | Chehwan | Wrote Queue and cancellation tests, GenAI-assisted |
| `OrderTest.java` | Diya Saraf | Added order validation tests |
| `ModelDriver.java` | Chehwan | Wrote independent Model demonstration |
| `OrderView.java` | Diya Saraf | Original View implementation; cancellation menu update |
| `OrderController.java` | Diya Saraf | Original Controller implementation; cancellation handling update |
| `OrderControllerTest.java` | Diya Saraf | Added Controller validation and cancellation tests |
| `Main.java` | Diya Saraf | Connected Model, View, and Controller |

## 7. Generative AI Usage

We used generative AI to help us review code and suggest implementations for the order cancellation feature.

For Deliverable 4, AI helped handling invalid inputs, order validation, and Controller behavior. We reviewed the suggestion and implemented features.

We tested the application ourselves and checked that the implemented features matched the project requirements.
