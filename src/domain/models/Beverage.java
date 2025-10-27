package domain.models;

import domain.enums.MenuItemCategory;

public class Beverage extends MenuItem {

    public Beverage(String name, String description, double price) {
        super(name, description, price, MenuItemCategory.BEVERAGE);
    }

    @Override
    public void prepare() {
        System.out.println("Preparing beverage: " + name);
    }
}

