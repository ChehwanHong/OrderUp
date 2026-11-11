# OrderUp — Restaurant Order Management System

## Project Overview

OrderUp is a Java-based restaurant order management application designed to help kitchen staff organize customer orders. The application uses a singly linked queue to process orders in First-In, First-Out (FIFO) order.

## How to Run

1. Import the OrderUp project into Eclipse.
2. Make sure JUnit 4 is added to the project's build path.
3. Run `ModelDriver.java` as a Java Application to test the model independently.
4. Run `OrderQueueTest.java` as a JUnit Test to verify the queue functionality.
5. Once the View and Controller are integrated, run `Main.java` to start the full application.

## Reflection

### Changes from Deliverable 2

We implemented the model using the planned classes: `FoodType`, `Order`, `OrderNode`, and `OrderQueue`. The queue uses linked nodes instead of an array, allowing orders to be added and removed without shifting elements.

The model provides `enqueue()`, `dequeue()`, `peek()`, `isEmpty()`, `size()`, and `getPendingOrders()`. We also created `ModelDriver` to demonstrate the queue independently and `OrderQueueTest` to test its behavior.

### Model Independence

The model does not depend on the View or Controller. `OrderQueue` manages orders without requiring user input or console output. This separation allows the model to be tested independently.

### Problems and Remaining Work

One important design consideration was maintaining FIFO order when adding and removing orders. We used head and tail references to manage the linked queue.

The remaining work includes integrating the View and Controller, testing the complete application, and confirming that invalid user input is handled correctly.

## Next Steps

### Remaining MVP Work

1. Complete the console-based View and Controller.
2. Connect the View and Controller to the queue model.
3. Test adding, displaying, and cooking orders.
4. Verify that invalid input does not crash the program.
5. Run the complete application and fix integration issues.

### New Feature for Deliverable 4: Order Cancellation

We plan to implement order cancellation, allowing kitchen staff to remove a pending order without changing the relative order of the remaining orders.

Planned changes:

- `OrderQueue`: Add a method such as `cancelOrder(...)` to locate and remove a pending order.
- `OrderController`: Add a cancellation action and handle invalid cancellation requests.
- `OrderView`: Add a cancellation menu option and display feedback.
- `OrderNode`: Reuse its existing next-node references.
- `FoodType`: No changes expected.

We will test cancellation of the first, middle, and last orders, as well as cancellation requests for nonexistent orders.

### Stretch Goals

Order cancellation is our planned next feature. Estimated cooking time and completed-order history remain optional stretch goals and will be reconsidered after the MVP is stable.

## Attribution

| File | Contributor | Work |
|---|---|---|
| `FoodType.java` | Chehwan | Defined food categories |
| `Order.java` | Chehwan | Implemented order data and validation |
| `OrderNode.java` | Chehwan | Implemented linked-node structure |
| `OrderQueue.java` | Chehwan | Implemented FIFO queue operations |
| `OrderQueueTest.java` | Chehwan | Created JUnit tests |
| `ModelDriver.java` | Chehwan | Created independent model demonstration |
| `OrderView.java` | Diya Saraf (planned) | Console interface |
| `OrderController.java` | Diya Saraf (planned) | Application flow and input handling |
| `Main.java` | Diya Saraf (planned) | Application entry point |

The attribution table will be updated to reflect actual contributions before submission.

## Generative AI Usage

We used generative AI to help plan the class structure, understand linked queue implementation, and develop initial code and testing examples. We reviewed and tested the generated suggestions in Eclipse. We also used AI assistance to troubleshoot JUnit setup and GitHub integration. The final implementation and attribution will be reviewed by both team members before submission.
