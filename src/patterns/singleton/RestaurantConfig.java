package patterns.singleton;

/**
 * SINGLETON PATTERN
 *
 * Purpose: Ensures only one instance of RestaurantConfig exists throughout the application.
 * This class manages global restaurant settings like name, address, tax rate, etc.
 *
 * Key Features:
 * - Private constructor to prevent direct instantiation
 * - Static instance variable to hold the single instance
 * - Public static method to access the instance
 * - Thread-safe implementation
 */
public class RestaurantConfig {
    // Single instance of the class
    private static RestaurantConfig instance;

    // Restaurant configuration data
    private String restaurantName;
    private String address;
    private String phoneNumber;
    private String businessHours;
    private double taxRate; // percentage
    private double serviceFeePercentage; // for delivery
    private int capacity;
    private String currency;

    // Private constructor prevents instantiation from other classes
    private RestaurantConfig() {
        // Initialize with default configuration
        this.restaurantName = "TMPS Pizza";
        this.address = "123 Main Street, Downtown";
        this.phoneNumber = "+1-555-0123";
        this.businessHours = "Mon-Fri: 11AM-10PM, Sat-Sun: 12PM-11PM";
        this.taxRate = 8.5;
        this.serviceFeePercentage = 10.0;
        this.capacity = 50;
        this.currency = "USD";
    }

    /**
     * Thread-safe method to get the single instance of RestaurantConfig
     * Uses synchronized block for thread safety
     */
    public static RestaurantConfig getInstance() {
        if (instance == null) {
            synchronized (RestaurantConfig.class) {
                if (instance == null) {
                    instance = new RestaurantConfig();
                    System.out.println("✓ Restaurant Configuration initialized (Singleton Pattern)");
                }
            }
        }
        return instance;
    }

    // Getters and Setters
    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBusinessHours() {
        return businessHours;
    }

    public void setBusinessHours(String businessHours) {
        this.businessHours = businessHours;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public double getServiceFeePercentage() {
        return serviceFeePercentage;
    }

    public void setServiceFeePercentage(double serviceFeePercentage) {
        this.serviceFeePercentage = serviceFeePercentage;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Restaurant Configuration:\n" +
               "  Name: " + restaurantName + "\n" +
               "  Address: " + address + "\n" +
               "  Phone: " + phoneNumber + "\n" +
               "  Hours: " + businessHours + "\n" +
               "  Tax Rate: " + taxRate + "%\n" +
               "  Delivery Fee: " + serviceFeePercentage + "%\n" +
               "  Capacity: " + capacity + " tables\n" +
               "  Currency: " + currency;
    }
}

