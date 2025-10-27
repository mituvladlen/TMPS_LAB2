package patterns.builder;

import domain.models.Order;
import domain.models.MenuItem;
import domain.models.Meal;
import domain.models.Payment;
import domain.enums.OrderType;
import java.util.ArrayList;
import java.util.List;

/**
 * BUILDER PATTERN
 *
 * Purpose: Constructs complex Order objects step by step.
 * Orders can have many optional fields (delivery address, table number, multiple items, etc.)
 *
 * Benefits:
 * - Handles complex object construction elegantly
 * - Makes code more readable
 * - Supports fluent interface
 * - Validates data before building the final object
 */
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

    /**
     * Builder methods with method chaining
     */

    public OrderBuilder setCustomerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public OrderBuilder setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public OrderBuilder setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
        return this;
    }

    public OrderBuilder addItem(MenuItem item) {
        this.items.add(item);
        return this;
    }

    public OrderBuilder addItems(List<MenuItem> items) {
        this.items.addAll(items);
        return this;
    }

    public OrderBuilder addMeal(Meal meal) {
        this.meals.add(meal);
        return this;
    }

    public OrderBuilder addMeals(List<Meal> meals) {
        this.meals.addAll(meals);
        return this;
    }

    public OrderBuilder setOrderType(OrderType orderType) {
        this.orderType = orderType;
        return this;
    }

    public OrderBuilder setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
        return this;
    }

    public OrderBuilder setPaymentMethod(Payment paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    /**
     * Validates and builds the final Order object
     */
    public Order build() {
        // Validation
        if (customerName == null || customerName.isEmpty()) {
            throw new IllegalStateException("Customer name is required");
        }
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            throw new IllegalStateException("Phone number is required");
        }
        if (items.isEmpty() && meals.isEmpty()) {
            throw new IllegalStateException("Order must have at least one item or meal");
        }
        if (orderType == OrderType.DELIVERY && (deliveryAddress == null || deliveryAddress.isEmpty())) {
            throw new IllegalStateException("Delivery address is required for delivery orders");
        }
        if (orderType == OrderType.DINE_IN && (tableNumber == null || tableNumber.isEmpty())) {
            throw new IllegalStateException("Table number is required for dine-in orders");
        }

        System.out.println("✓ Building order with Builder Pattern...");
        return new Order(customerName, phoneNumber, tableNumber, items, meals,
                        orderType, deliveryAddress, paymentMethod);
    }

    /**
     * Reset builder to create a new order
     */
    public OrderBuilder reset() {
        this.customerName = null;
        this.phoneNumber = null;
        this.tableNumber = null;
        this.items = new ArrayList<>();
        this.meals = new ArrayList<>();
        this.orderType = OrderType.DINE_IN;
        this.deliveryAddress = null;
        this.paymentMethod = null;
        return this;
    }

    // Pre-configured order templates
    public static OrderBuilder dineInOrder(String customerName, String phoneNumber, String tableNumber) {
        return new OrderBuilder()
            .setCustomerName(customerName)
            .setPhoneNumber(phoneNumber)
            .setTableNumber(tableNumber)
            .setOrderType(OrderType.DINE_IN);
    }

    public static OrderBuilder takeoutOrder(String customerName, String phoneNumber) {
        return new OrderBuilder()
            .setCustomerName(customerName)
            .setPhoneNumber(phoneNumber)
            .setOrderType(OrderType.TAKEOUT);
    }

    public static OrderBuilder deliveryOrder(String customerName, String phoneNumber, String deliveryAddress) {
        return new OrderBuilder()
            .setCustomerName(customerName)
            .setPhoneNumber(phoneNumber)
            .setDeliveryAddress(deliveryAddress)
            .setOrderType(OrderType.DELIVERY);
    }
}

