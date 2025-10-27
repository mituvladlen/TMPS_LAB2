package domain.models;

import domain.enums.MenuItemCategory;

public abstract class MenuItem {
    protected String name;
    protected String description;
    protected double price;
    protected MenuItemCategory category;

    public MenuItem(String name, String description, double price, MenuItemCategory category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public MenuItemCategory getCategory() {
        return category;
    }

    public abstract void prepare();

    @Override
    public String toString() {
        return String.format("%s - %s ($%.2f)", name, description, price);
    }
}

