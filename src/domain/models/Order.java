package domain.models;

import domain.enums.OrderType;
import patterns.singleton.RestaurantConfig;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private String customerName;
    private String phoneNumber;
    private String tableNumber;
    private List<MenuItem> items;
    private List<Meal> meals;
    private OrderType orderType;
    private String deliveryAddress;
    private Payment paymentMethod;
    private int orderNumber;
    private static int orderCounter = 1000;

    public Order(String customerName, String phoneNumber, String tableNumber,
                 List<MenuItem> items, List<Meal> meals, OrderType orderType,
                 String deliveryAddress, Payment paymentMethod) {
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.tableNumber = tableNumber;
        this.items = items != null ? new ArrayList<>(items) : new ArrayList<>();
        this.meals = meals != null ? new ArrayList<>(meals) : new ArrayList<>();
        this.orderType = orderType;
        this.deliveryAddress = deliveryAddress;
        this.paymentMethod = paymentMethod;
        this.orderNumber = ++orderCounter;
    }

    public double calculateSubtotal() {
        double subtotal = 0.0;
        for (MenuItem item : items) {
            subtotal += item.getPrice();
        }
        for (Meal meal : meals) {
            subtotal += meal.getTotalPrice();
        }
        return subtotal;
    }

    public double calculateTax() {
        RestaurantConfig config = RestaurantConfig.getInstance();
        return calculateSubtotal() * (config.getTaxRate() / 100);
    }

    public double calculateServiceFee() {
        if (orderType == OrderType.DELIVERY) {
            RestaurantConfig config = RestaurantConfig.getInstance();
            return calculateSubtotal() * (config.getServiceFeePercentage() / 100);
        }
        return 0.0;
    }

    public double calculateTotal() {
        return calculateSubtotal() + calculateTax() + calculateServiceFee();
    }

    public void processPayment() {
        if (paymentMethod != null) {
            paymentMethod.processPayment(calculateTotal());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        RestaurantConfig config = RestaurantConfig.getInstance();

        sb.append("\n╔════════════════════════════════════════╗\n");
        sb.append("║          ORDER RECEIPT                ║\n");
        sb.append("╚════════════════════════════════════════╝\n");
        sb.append("Restaurant: ").append(config.getRestaurantName()).append("\n");
        sb.append("Address: ").append(config.getAddress()).append("\n");
        sb.append("Phone: ").append(config.getPhoneNumber()).append("\n");
        sb.append("----------------------------------------\n");
        sb.append("Order #").append(orderNumber).append("\n");
        sb.append("Customer: ").append(customerName).append("\n");
        sb.append("Phone: ").append(phoneNumber).append("\n");
        if (tableNumber != null && !tableNumber.isEmpty()) {
            sb.append("Table: ").append(tableNumber).append("\n");
        }
        sb.append("Order Type: ").append(orderType).append("\n");
        if (deliveryAddress != null && !deliveryAddress.isEmpty()) {
            sb.append("Delivery Address: ").append(deliveryAddress).append("\n");
        }
        sb.append("----------------------------------------\n");

        if (!items.isEmpty()) {
            sb.append("Individual Items:\n");
            for (MenuItem item : items) {
                sb.append("  - ").append(item.getName())
                  .append(" ($").append(String.format("%.2f", item.getPrice())).append(")\n");
            }
        }

        if (!meals.isEmpty()) {
            sb.append("\nCombo Meals:\n");
            for (int i = 0; i < meals.size(); i++) {
                sb.append("  Meal #").append(i + 1).append(":\n");
                Meal meal = meals.get(i);
                if (meal.getMainItem() != null) {
                    sb.append("    Main: ").append(meal.getMainItem().getName()).append("\n");
                }
                if (meal.getSideItem() != null) {
                    sb.append("    Side: ").append(meal.getSideItem().getName()).append("\n");
                }
                if (meal.getBeverage() != null) {
                    sb.append("    Beverage: ").append(meal.getBeverage().getName()).append("\n");
                }
                if (meal.getDessert() != null) {
                    sb.append("    Dessert: ").append(meal.getDessert().getName()).append("\n");
                }
                if (meal.getMealSize() != null) {
                    sb.append("    Size: ").append(meal.getMealSize()).append("\n");
                }
                sb.append("    Meal Total: $").append(String.format("%.2f", meal.getTotalPrice())).append("\n");
            }
        }

        sb.append("----------------------------------------\n");
        sb.append("Subtotal: $").append(String.format("%.2f", calculateSubtotal())).append("\n");
        sb.append("Tax (").append(config.getTaxRate()).append("%): $")
          .append(String.format("%.2f", calculateTax())).append("\n");
        if (calculateServiceFee() > 0) {
            sb.append("Delivery Fee (").append(config.getServiceFeePercentage())
              .append("%): $").append(String.format("%.2f", calculateServiceFee())).append("\n");
        }
        sb.append("========================================\n");
        sb.append("TOTAL: $").append(String.format("%.2f", calculateTotal())).append("\n");
        sb.append("========================================\n");
        if (paymentMethod != null) {
            sb.append("Payment Method: ").append(paymentMethod.getPaymentType()).append("\n");
        }

        return sb.toString();
    }

    // Getters
    public String getCustomerName() { return customerName; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getTableNumber() { return tableNumber; }
    public List<MenuItem> getItems() { return new ArrayList<>(items); }
    public List<Meal> getMeals() { return new ArrayList<>(meals); }
    public OrderType getOrderType() { return orderType; }
    public String getDeliveryAddress() { return deliveryAddress; }
    public Payment getPaymentMethod() { return paymentMethod; }
    public int getOrderNumber() { return orderNumber; }
}

