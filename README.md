# Creational Design Patterns

**Author:** Vladlen Mitu, FAF-232

---

## Objectives

1. Study and understand the Creational Design Patterns.
2. Choose a domain, define its main classes/models/entities and choose the appropriate instantiation mechanisms.
3. Use some creational design patterns for object instantiation in a sample project.

---

## Used Design Patterns

This project implements three creational design patterns in a Restaurant Management System domain:

1. **Singleton Pattern** - `RestaurantConfig`
2. **Factory Method Pattern** - `MenuItemFactory` and `PaymentFactory`
3. **Builder Pattern** - `MealBuilder` and `OrderBuilder`

---

## Implementation

### Singleton

The **Singleton Pattern** ensures that a class has only one instance and provides a global point of access to it. In this project, the `RestaurantConfig` class uses the Singleton pattern to manage restaurant configuration settings.

**Key characteristics:**
- Private constructor prevents direct instantiation
- Static instance variable holds the single instance
- Thread-safe implementation using double-checked locking

**Code example from RestaurantConfig.java:**

```java
public static RestaurantConfig getInstance() {
    if (instance == null) {
        synchronized (RestaurantConfig.class) {
            if (instance == null) {
                instance = new RestaurantConfig();
            }
        }
    }
    return instance;
}
```

**Usage:** The Singleton is used throughout the application to access configuration settings like tax rate and service fees.

---

### Factory Method

The **Factory Method Pattern** defines an interface for creating objects but lets subclasses decide which class to instantiate. This project uses two factory classes: `MenuItemFactory` for creating menu items and `PaymentFactory` for creating payment methods.

**Key characteristics:**
- Encapsulates object creation logic
- Returns abstract types (MenuItem, Payment interface)
- Easy to extend with new product types

**Code example from MenuItemFactory.java:**

```java
public static MenuItem createMenuItem(MenuItemCategory category, String itemName) {
    switch (category) {
        case APPETIZER:
            return createAppetizer(itemName);
        case MAIN_COURSE:
            return createPizza(itemName);
        case DESSERT:
            return createDessert(itemName);
        case BEVERAGE:
            return createBeverage(itemName);
        default:
            throw new IllegalArgumentException("Unknown category: " + category);
    }
}
```

**Usage:** The factories create menu items and payment methods without exposing instantiation logic to client code.

---

### Builder

The **Builder Pattern** separates the construction of a complex object from its representation, allowing the same construction process to create different representations. This project uses `MealBuilder` for creating custom meals and `OrderBuilder` for creating orders.

**Key characteristics:**
- Constructs complex objects step by step
- Provides fluent interface (method chaining)
- Validates data before building the final object

**Code example from OrderBuilder.java:**

```java
public OrderBuilder addItem(MenuItem item) {
    this.items.add(item);
    return this;
}

public Order build() {
    if (customerName == null || customerName.isEmpty()) {
        throw new IllegalStateException("Customer name is required");
    }
    if (items.isEmpty() && meals.isEmpty()) {
        throw new IllegalStateException("Order must have at least one item");
    }
    return new Order(customerName, phoneNumber, tableNumber, items, 
                    meals, orderType, deliveryAddress, paymentMethod);
}
```

**Usage:** The builders create customized meals and complete orders with readable, maintainable code through method chaining.

---

## Results

The implementation successfully demonstrates all three creational design patterns working together in a restaurant management system:

- The **Singleton pattern** provides centralized configuration management
- The **Factory Method pattern** simplifies object creation for menu items and payments
- The **Builder pattern** enables flexible construction of complex meals and orders

The program creates multiple orders (dine-in, delivery, and takeout) with different meals, menu items, and payment methods, calculating totals with taxes and fees, and processing payments accordingly.

_(Screenshots of program execution will be added here)_

---

## Conclusion

This laboratory work successfully demonstrates the practical application of three creational design patterns in a real-world scenario. The Singleton pattern ensures consistent configuration across the application, the Factory Method pattern provides a flexible way to create different types of objects without coupling the client code to specific classes, and the Builder pattern makes the construction of complex objects intuitive and maintainable.

The implementation follows object-oriented design principles such as separation of concerns, single responsibility, and loose coupling. These patterns not only solve specific design problems but also make the codebase more extensible, testable, and easier to maintain. The restaurant management system demonstrates how design patterns can be combined effectively to create a robust and scalable application architecture.

