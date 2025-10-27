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
- Public `getInstance()` method provides global access
- Thread-safe implementation using double-checked locking

**Code example from RestaurantConfig.java:**

```java
public class RestaurantConfig {
    private static RestaurantConfig instance;
    
    // Private constructor prevents instantiation from other classes
    private RestaurantConfig() {
        this.restaurantName = "TMPS Pizza";
        this.address = "123 Main Street, Downtown";
        this.phoneNumber = "+1-555-0123";
        this.taxRate = 8.5;
        this.serviceFeePercentage = 10.0;
        this.capacity = 50;
        this.currency = "USD";
    }

    // Thread-safe method to get the single instance
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
    
    // Getters for configuration data
    public double getTaxRate() {
        return taxRate;
    }
    
    public double getServiceFeePercentage() {
        return serviceFeePercentage;
    }
}
```

**Usage:** The Singleton is used throughout the application, especially in the `Order` class for calculating taxes and service fees based on the restaurant's configuration.

---

### Factory Method

The **Factory Method Pattern** defines an interface for creating objects but lets subclasses decide which class to instantiate. This project uses two factory classes: `MenuItemFactory` for creating menu items and `PaymentFactory` for creating payment methods.

**Key characteristics:**
- Encapsulates object creation logic
- Returns abstract types (MenuItem, Payment interface)
- Client code doesn't need to know concrete classes
- Easy to extend with new product types

**Code example from MenuItemFactory.java:**

```java
public class MenuItemFactory {
    
    // Factory Method that creates menu items based on category and name
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
                throw new IllegalArgumentException("Unknown menu item category: " + category);
        }
    }

    private static MenuItem createPizza(String itemName) {
        switch (itemName.toLowerCase()) {
            case "margherita pizza":
            case "margherita":
                return new Pizza("Margherita Pizza", 
                    "Tomato sauce, mozzarella, fresh basil", 12.99);
            case "pepperoni pizza":
            case "pepperoni":
                return new Pizza("Pepperoni Pizza", 
                    "Tomato sauce, mozzarella, pepperoni", 14.99);
            case "quattro formaggi pizza":
            case "quattro formaggi":
                return new Pizza("Quattro Formaggi Pizza", 
                    "Tomato sauce, mozzarella, gorgonzola, parmesan, ricotta", 15.99);
            case "capricciosa pizza":
            case "capricciosa":
                return new Pizza("Capricciosa Pizza", 
                    "Tomato sauce, mozzarella, ham, black olives", 16.99);
            default:
                throw new IllegalArgumentException("Unknown pizza: " + itemName);
        }
    }
}
```

**Code example from PaymentFactory.java:**

```java
public class PaymentFactory {
    
    // Factory Method to create appropriate payment based on payment type
    public static Payment createPayment(PaymentType paymentType, Object... params) {
        switch (paymentType) {
            case CASH:
                if (params.length > 0 && params[0] instanceof Double) {
                    return new CashPayment((Double) params[0]);
                }
                throw new IllegalArgumentException("Cash payment requires amount given");

            case CREDIT_CARD:
                if (params.length >= 4) {
                    return new CreditCardPayment(
                        (String) params[0],  // card number
                        (String) params[1],  // card holder name
                        (String) params[2],  // expiry date
                        (String) params[3]   // cvv
                    );
                }
                throw new IllegalArgumentException("Credit card payment requires: cardNumber, cardHolderName, expiryDate, cvv");

            case MOBILE_PAYMENT:
                if (params.length >= 2) {
                    return new MobilePayment(
                        (String) params[0],  // phone number
                        (String) params[1]   // provider
                    );
                }
                throw new IllegalArgumentException("Mobile payment requires: phoneNumber, provider");

            default:
                throw new IllegalArgumentException("Unknown payment type: " + paymentType);
        }
    }
}
```

**Usage:** The factories are used in `Main.java` to create menu items and payment methods without exposing the creation logic to the client code.

---

### Builder

The **Builder Pattern** separates the construction of a complex object from its representation, allowing the same construction process to create different representations. This project uses `MealBuilder` for creating custom meals and `OrderBuilder` for creating orders.

**Key characteristics:**
- Constructs complex objects step by step
- Provides fluent interface (method chaining)
- Handles optional parameters elegantly
- Validates data before building the final object
- Makes code more readable and maintainable

**Code example from MealBuilder.java:**

```java
public class MealBuilder {
    private MenuItem mainItem;
    private MenuItem sideItem;
    private MenuItem beverage;
    private MenuItem dessert;
    private String specialInstructions;
    private MealSize mealSize;

    public MealBuilder() {
        this.mealSize = MealSize.REGULAR; // default size
    }

    // Builder methods return 'this' to enable method chaining
    public MealBuilder setMainItem(MenuItem mainItem) {
        this.mainItem = mainItem;
        return this;
    }

    public MealBuilder setSideItem(MenuItem sideItem) {
        this.sideItem = sideItem;
        return this;
    }

    public MealBuilder setBeverage(MenuItem beverage) {
        this.beverage = beverage;
        return this;
    }

    public MealBuilder setDessert(MenuItem dessert) {
        this.dessert = dessert;
        return this;
    }

    public MealBuilder setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
        return this;
    }

    public MealBuilder setMealSize(MealSize mealSize) {
        this.mealSize = mealSize;
        return this;
    }

    // Validates and builds the final Meal object
    public Meal build() {
        if (mainItem == null) {
            throw new IllegalStateException("Cannot build meal without a main item");
        }
        return new Meal(mainItem, sideItem, beverage, dessert, specialInstructions, mealSize);
    }
}
```

**Code example from OrderBuilder.java:**

```java
public class OrderBuilder {
    private String customerName;
    private String phoneNumber;
    private String tableNumber;
    private List<MenuItem> items;
    private List<Meal> meals;
    private OrderType orderType;
    private String deliveryAddress;
    private Payment paymentMethod;

    public OrderBuilder() {
        this.items = new ArrayList<>();
        this.meals = new ArrayList<>();
        this.orderType = OrderType.DINE_IN; // default
    }

    // Builder methods with method chaining
    public OrderBuilder setCustomerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public OrderBuilder setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public OrderBuilder addItem(MenuItem item) {
        this.items.add(item);
        return this;
    }

    public OrderBuilder addMeal(Meal meal) {
        this.meals.add(meal);
        return this;
    }

    public OrderBuilder setOrderType(OrderType orderType) {
        this.orderType = orderType;
        return this;
    }

    public OrderBuilder setPaymentMethod(Payment paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    // Validates and builds the final Order object
    public Order build() {
        if (customerName == null || customerName.isEmpty()) {
            throw new IllegalStateException("Customer name is required");
        }
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            throw new IllegalStateException("Phone number is required");
        }
        if (items.isEmpty() && meals.isEmpty()) {
            throw new IllegalStateException("Order must have at least one item or meal");
        }
        return new Order(customerName, phoneNumber, tableNumber, items, meals, 
                        orderType, deliveryAddress, paymentMethod);
    }
}
```

**Usage:** The builders are extensively used in `Main.java` to create customized meals and complete orders with readable, maintainable code.

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

