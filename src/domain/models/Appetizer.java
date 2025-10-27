package domain.models;

import domain.enums.MenuItemCategory;

public class Appetizer extends MenuItem {

    public Appetizer(String name, String description, double price) {
        super(name, description, price, MenuItemCategory.APPETIZER);
    }

    @Override
    public void prepare() {
        System.out.println("Preparing appetizer: " + name);
    }
}

