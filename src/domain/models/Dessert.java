package domain.models;

import domain.enums.MenuItemCategory;

public class Dessert extends MenuItem {

    public Dessert(String name, String description, double price) {
        super(name, description, price, MenuItemCategory.DESSERT);
    }

    @Override
    public void prepare() {
        System.out.println("Preparing dessert: " + name);
    }
}

