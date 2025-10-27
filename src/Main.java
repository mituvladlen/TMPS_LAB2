import domain.models.*;
import domain.enums.*;
import patterns.singleton.RestaurantConfig;
import patterns.factory.MenuItemFactory;
import patterns.factory.PaymentFactory;
import patterns.builder.MealBuilder;
import patterns.builder.OrderBuilder;

/**
 * TMPS Laboratory Work #2 - Creational Design Patterns
 *
 * Domain: Restaurant Management System
 *
 * Implemented Patterns:
 * 1. SINGLETON - RestaurantConfig (single instance of restaurant configuration)
 * 2. FACTORY METHOD - MenuItemFactory & PaymentFactory (creates different types of objects)
 * 3. BUILDER - MealBuilder & OrderBuilder (constructs complex objects step by step)
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║   TMPS Lab #2 - Creational Design Patterns Demo        ║");
        System.out.println("║   Restaurant Management System: TMPS Pizza              ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝\n");

        // ========================================
        // PATTERN 1: SINGLETON
        // ========================================
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("   PATTERN 1: SINGLETON");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");

        // Get restaurant configuration (Singleton)
        RestaurantConfig config = RestaurantConfig.getInstance();
        System.out.println(config);

        // Verify it's the same instance
        RestaurantConfig config2 = RestaurantConfig.getInstance();
        System.out.println("\nVerifying Singleton: config == config2? " + (config == config2));

        // ========================================
        // PATTERN 2: FACTORY METHOD
        // ========================================
        System.out.println("\n\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("   PATTERN 2: FACTORY METHOD");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");

        // Display menu
        MenuItemFactory.displayMenu();

        // Create menu items using Factory
        System.out.println("Creating menu items using Factory Pattern:\n");
        MenuItem bruschetta = MenuItemFactory.createMenuItem(MenuItemCategory.APPETIZER, "Bruschetta");
        System.out.println("✓ Created: " + bruschetta);

        MenuItem margherita = MenuItemFactory.createMenuItem(MenuItemCategory.MAIN_COURSE, "Margherita");
        System.out.println("✓ Created: " + margherita);

        MenuItem tiramisu = MenuItemFactory.createMenuItem(MenuItemCategory.DESSERT, "Tiramisu");
        System.out.println("✓ Created: " + tiramisu);

        MenuItem cappuccino = MenuItemFactory.createMenuItem(MenuItemCategory.BEVERAGE, "Cappuccino");
        System.out.println("✓ Created: " + cappuccino);

        // Create payment methods using Factory
        System.out.println("\nCreating payment methods using Factory Pattern:\n");
        Payment cashPayment = PaymentFactory.createCashPayment(100.0);
        System.out.println("✓ Created: " + cashPayment.getPaymentDetails());

        Payment creditCard = PaymentFactory.createCreditCardPayment(
            "1234567890123456", "John Doe", "12/26", "123");
        System.out.println("✓ Created: " + creditCard.getPaymentDetails());

        // ========================================
        // PATTERN 3: BUILDER
        // ========================================
        System.out.println("\n\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("   PATTERN 3: BUILDER");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");

        // Create menu items for meals
        MenuItem pepperoniPizza = MenuItemFactory.createMenuItem(MenuItemCategory.MAIN_COURSE, "Pepperoni");
        MenuItem caesarSalad = MenuItemFactory.createMenuItem(MenuItemCategory.APPETIZER, "Caesar Salad");
        MenuItem coke = MenuItemFactory.createMenuItem(MenuItemCategory.BEVERAGE, "Coca-Cola");
        MenuItem lavaCake = MenuItemFactory.createMenuItem(MenuItemCategory.DESSERT, "Chocolate Lava Cake");

        // Build a meal using Builder Pattern
        System.out.println("Building Meal #1 - Lunch Special:");
        Meal lunchMeal = new MealBuilder()
            .setMainItem(margherita)
            .setSideItem(caesarSalad)
            .setBeverage(cappuccino)
            .setMealSize(MealSize.REGULAR)
            .setSpecialInstructions("Extra basil on pizza")
            .build();
        System.out.println(lunchMeal);

        // Build another meal
        System.out.println("\n\nBuilding Meal #2 - Family Feast:");
        Meal familyMeal = new MealBuilder()
            .setMainItem(pepperoniPizza)
            .setSideItem(bruschetta)
            .setBeverage(coke)
            .setDessert(lavaCake)
            .setMealSize(MealSize.FAMILY_SIZE)
            .setSpecialInstructions("Please cut pizza in 8 slices")
            .build();
        System.out.println(familyMeal);

        // ========================================
        // COMPLETE ORDER EXAMPLE
        // ========================================
        System.out.println("\n\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("   COMPLETE ORDER DEMONSTRATION");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");

        // Create additional items
        MenuItem quattroFormaggi = MenuItemFactory.createMenuItem(MenuItemCategory.MAIN_COURSE, "Quattro Formaggi");
        MenuItem espresso = MenuItemFactory.createMenuItem(MenuItemCategory.BEVERAGE, "Espresso");
        MenuItem pannaCotta = MenuItemFactory.createMenuItem(MenuItemCategory.DESSERT, "Panna Cotta");

        // Build Order #1 - Dine-in
        System.out.println("Building Order #1 - Dine-in:");
        Payment payment1 = PaymentFactory.createCreditCardPayment(
            "4532123456789012", "Alice Smith", "11/27", "456");

        Order order1 = new OrderBuilder()
            .setCustomerName("Alice Smith")
            .setPhoneNumber("+1-555-1234")
            .setTableNumber("12")
            .setOrderType(OrderType.DINE_IN)
            .addMeal(lunchMeal)
            .addItem(espresso)
            .setPaymentMethod(payment1)
            .build();

        System.out.println(order1);
        System.out.println("\nProcessing payment for Order #1:");
        order1.processPayment();

        // Build Order #2 - Delivery
        System.out.println("\n\n" + "=".repeat(50));
        System.out.println("Building Order #2 - Delivery:");
        Payment payment2 = PaymentFactory.createMobilePayment("+1-555-9876", "Apple Pay");

        Order order2 = new OrderBuilder()
            .setCustomerName("Bob Johnson")
            .setPhoneNumber("+1-555-9876")
            .setOrderType(OrderType.DELIVERY)
            .setDeliveryAddress("456 Oak Avenue, Apt 5B")
            .addItem(quattroFormaggi)
            .addItem(bruschetta)
            .addItem(pannaCotta)
            .addItem(coke)
            .setPaymentMethod(payment2)
            .build();

        System.out.println(order2);
        System.out.println("\nProcessing payment for Order #2:");
        order2.processPayment();

        // Build Order #3 - Takeout with Family Meal
        System.out.println("\n\n" + "=".repeat(50));
        System.out.println("Building Order #3 - Takeout:");
        Payment payment3 = PaymentFactory.createCashPayment(100.0);

        Order order3 = new OrderBuilder()
            .setCustomerName("Carol Davis")
            .setPhoneNumber("+1-555-5555")
            .setOrderType(OrderType.TAKEOUT)
            .addMeal(familyMeal)
            .addItem(tiramisu)
            .setPaymentMethod(payment3)
            .build();

        System.out.println(order3);
        System.out.println("\nProcessing payment for Order #3:");
        order3.processPayment();

        // ========================================
        // END OF DEMONSTRATION
        // ========================================
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("         Thank you for using TMPS Pizza!");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }
}

