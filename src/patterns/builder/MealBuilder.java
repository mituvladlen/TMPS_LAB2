package patterns.builder;

import domain.models.Meal;
import domain.models.MenuItem;
import domain.enums.MealSize;

/**
 * BUILDER PATTERN
 *
 * Purpose: Constructs complex Meal objects step by step.
 * Allows creating meals with different combinations of items without having
 * a constructor with too many parameters.
 *
 * Benefits:
 * - Makes code more readable and maintainable
 * - Allows creating objects with different configurations
 * - Separates construction from representation
 * - Supports fluent interface (method chaining)
 */
public class MealBuilder {
    private MenuItem mainItem;
    private MenuItem sideItem;
    private MenuItem beverage;
    private MenuItem dessert;
    private String specialInstructions;
    private MealSize mealSize;

    public MealBuilder() {
        // Start with empty meal
        this.mealSize = MealSize.REGULAR; // default size
    }

    /**
     * Builder methods return 'this' to enable method chaining
     */

    public MealBuilder setMainItem(MenuItem mainItem) {
        this.mainItem = mainItem;
        return this;
    }

    public MealBuilder setSideItem(MenuItem sideItem) {
        this.sideItem = sideItem;
        return this;
    }

    public MealBuilder setBeverage(MenuItem beverage) {
        this.beverage = beverage;
        return this;
    }

    public MealBuilder setDessert(MenuItem dessert) {
        this.dessert = dessert;
        return this;
    }

    public MealBuilder setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
        return this;
    }

    public MealBuilder setMealSize(MealSize mealSize) {
        this.mealSize = mealSize;
        return this;
    }

    /**
     * Validates and builds the final Meal object
     */
    public Meal build() {
        // Validation: At least main item is required
        if (mainItem == null) {
            throw new IllegalStateException("Cannot build meal without a main item");
        }

        System.out.println("✓ Building meal with Builder Pattern...");
        return new Meal(mainItem, sideItem, beverage, dessert, specialInstructions, mealSize);
    }

    /**
     * Reset builder to create a new meal
     */
    public MealBuilder reset() {
        this.mainItem = null;
        this.sideItem = null;
        this.beverage = null;
        this.dessert = null;
        this.specialInstructions = null;
        this.mealSize = MealSize.REGULAR;
        return this;
    }

    // Pre-configured meal templates
    public static MealBuilder lunchSpecial() {
        return new MealBuilder()
            .setMealSize(MealSize.REGULAR)
            .setSpecialInstructions("Lunch Special");
    }

    public static MealBuilder familyFeast() {
        return new MealBuilder()
            .setMealSize(MealSize.FAMILY_SIZE)
            .setSpecialInstructions("Family Feast");
    }

    public static MealBuilder dateNight() {
        return new MealBuilder()
            .setMealSize(MealSize.LARGE)
            .setSpecialInstructions("Date Night Special");
    }
}

