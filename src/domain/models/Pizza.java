package domain.models;

import domain.enums.MenuItemCategory;

public class Pizza extends MenuItem {

    public Pizza(String name, String description, double price) {
        super(name, description, price, MenuItemCategory.MAIN_COURSE);
    }

    @Override
    public void prepare() {
        System.out.println("Preparing pizza: " + name);
        System.out.println("  - Rolling out dough...");
        System.out.println("  - Adding toppings...");
        System.out.println("  - Baking in wood-fired oven...");
    }
}

