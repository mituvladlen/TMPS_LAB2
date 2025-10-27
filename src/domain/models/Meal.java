package domain.models;

import domain.enums.MealSize;
import java.util.ArrayList;
import java.util.List;

public class Meal {
    private MenuItem mainItem;
    private MenuItem sideItem;
    private MenuItem beverage;
    private MenuItem dessert;
    private String specialInstructions;
    private MealSize mealSize;
    private List<String> components;

    public Meal(MenuItem mainItem, MenuItem sideItem, MenuItem beverage,
                MenuItem dessert, String specialInstructions, MealSize mealSize) {
        this.mainItem = mainItem;
        this.sideItem = sideItem;
        this.beverage = beverage;
        this.dessert = dessert;
        this.specialInstructions = specialInstructions;
        this.mealSize = mealSize;
        this.components = new ArrayList<>();
        buildComponentsList();
    }

    private void buildComponentsList() {
        if (mainItem != null) components.add(mainItem.getName());
        if (sideItem != null) components.add(sideItem.getName());
        if (beverage != null) components.add(beverage.getName());
        if (dessert != null) components.add(dessert.getName());
    }

    public double getTotalPrice() {
        double total = 0.0;
        if (mainItem != null) total += mainItem.getPrice();
        if (sideItem != null) total += sideItem.getPrice();
        if (beverage != null) total += beverage.getPrice();
        if (dessert != null) total += dessert.getPrice();

        // Apply size multiplier
        if (mealSize != null) {
            switch (mealSize) {
                case LARGE:
                    total *= 1.3;
                    break;
                case FAMILY_SIZE:
                    total *= 2.0;
                    break;
                default:
                    break;
            }
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== MEAL ===\n");
        if (mealSize != null) {
            sb.append("Size: ").append(mealSize).append("\n");
        }
        if (mainItem != null) {
            sb.append("Main: ").append(mainItem.getName()).append(" ($").append(String.format("%.2f", mainItem.getPrice())).append(")\n");
        }
        if (sideItem != null) {
            sb.append("Side: ").append(sideItem.getName()).append(" ($").append(String.format("%.2f", sideItem.getPrice())).append(")\n");
        }
        if (beverage != null) {
            sb.append("Beverage: ").append(beverage.getName()).append(" ($").append(String.format("%.2f", beverage.getPrice())).append(")\n");
        }
        if (dessert != null) {
            sb.append("Dessert: ").append(dessert.getName()).append(" ($").append(String.format("%.2f", dessert.getPrice())).append(")\n");
        }
        if (specialInstructions != null && !specialInstructions.isEmpty()) {
            sb.append("Special Instructions: ").append(specialInstructions).append("\n");
        }
        sb.append("Total Price: $").append(String.format("%.2f", getTotalPrice()));
        return sb.toString();
    }

    public MenuItem getMainItem() {
        return mainItem;
    }

    public MenuItem getSideItem() {
        return sideItem;
    }

    public MenuItem getBeverage() {
        return beverage;
    }

    public MenuItem getDessert() {
        return dessert;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public MealSize getMealSize() {
        return mealSize;
    }

    public List<String> getComponents() {
        return new ArrayList<>(components);
    }
}

