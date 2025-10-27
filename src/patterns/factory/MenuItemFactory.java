package patterns.factory;

import domain.models.*;
import domain.enums.MenuItemCategory;

/**
 * FACTORY METHOD PATTERN
 *
 * Purpose: Creates different types of menu items without exposing creation logic to the client.
 * The factory encapsulates the instantiation process and returns the appropriate MenuItem subclass.
 *
 * Benefits:
 * - Centralizes object creation logic
 * - Makes it easy to add new menu items
 * - Client code doesn't need to know about concrete classes
 * - Promotes loose coupling
 */
public class MenuItemFactory {

    /**
     * Creates a menu item based on category and name
     * This is the Factory Method that decides which concrete class to instantiate
     */
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

    // Factory methods for creating specific appetizers
    private static MenuItem createAppetizer(String itemName) {
        switch (itemName.toLowerCase()) {
            case "bruschetta":
                return new Appetizer("Bruschetta",
                    "Toasted bread with tomatoes, garlic, basil", 8.99);
            case "mozzarella sticks":
                return new Appetizer("Mozzarella Sticks",
                    "Fried mozzarella with marinara sauce", 9.99);
            case "caesar salad":
                return new Appetizer("Caesar Salad",
                    "Romaine lettuce, parmesan, croutons, Caesar dressing", 10.99);
            default:
                throw new IllegalArgumentException("Unknown appetizer: " + itemName);
        }
    }

    // Factory methods for creating specific pizzas
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
            case "four cheese":
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

    // Factory methods for creating specific desserts
    private static MenuItem createDessert(String itemName) {
        switch (itemName.toLowerCase()) {
            case "tiramisu":
                return new Dessert("Tiramisu",
                    "Classic Italian coffee-flavored dessert", 7.99);
            case "panna cotta":
                return new Dessert("Panna Cotta",
                    "Italian cream dessert with berry sauce", 6.99);
            case "chocolate lava cake":
            case "lava cake":
                return new Dessert("Chocolate Lava Cake",
                    "Warm chocolate cake with vanilla ice cream", 8.99);
            default:
                throw new IllegalArgumentException("Unknown dessert: " + itemName);
        }
    }

    // Factory methods for creating specific beverages
    private static MenuItem createBeverage(String itemName) {
        switch (itemName.toLowerCase()) {
            case "espresso":
                return new Beverage("Espresso",
                    "Strong Italian coffee", 3.99);
            case "cappuccino":
                return new Beverage("Cappuccino",
                    "Espresso with steamed milk and foam", 4.99);
            case "coca-cola":
            case "coke":
                return new Beverage("Coca-Cola",
                    "Soft drink", 2.99);
            case "sprite":
                return new Beverage("Sprite",
                    "Soft drink", 2.99);
            case "mineral water":
            case "water":
                return new Beverage("Mineral Water",
                    "Still or sparkling", 1.99);
            default:
                throw new IllegalArgumentException("Unknown beverage: " + itemName);
        }
    }

    // Convenience method to display available menu
    public static void displayMenu() {
        System.out.println("\n========== TMPS PIZZA MENU ==========\n");

        System.out.println("APPETIZERS:");
        System.out.println("  • Bruschetta - $8.99");
        System.out.println("  • Mozzarella Sticks - $9.99");
        System.out.println("  • Caesar Salad - $10.99");

        System.out.println("\nMAIN COURSES (Pizzas):");
        System.out.println("  • Margherita Pizza - $12.99");
        System.out.println("  • Pepperoni Pizza - $14.99");
        System.out.println("  • Quattro Formaggi Pizza - $15.99");
        System.out.println("  • Capricciosa Pizza - $16.99");

        System.out.println("\nDESSERTS:");
        System.out.println("  • Tiramisu - $7.99");
        System.out.println("  • Panna Cotta - $6.99");
        System.out.println("  • Chocolate Lava Cake - $8.99");

        System.out.println("\nBEVERAGES:");
        System.out.println("  • Espresso - $3.99");
        System.out.println("  • Cappuccino - $4.99");
        System.out.println("  • Coca-Cola - $2.99");
        System.out.println("  • Sprite - $2.99");
        System.out.println("  • Mineral Water - $1.99");

        System.out.println("\n======================================\n");
    }
}

