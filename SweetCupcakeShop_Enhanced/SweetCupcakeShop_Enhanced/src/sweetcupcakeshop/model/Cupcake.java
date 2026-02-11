package sweetcupcakeshop.model;

/**
 * Cupcake class - demonstrates ENCAPSULATION with private fields and getters/setters
 * Represents a cupcake item in The Sweet Cupcake Shop
 */
public class Cupcake {

    // Categories constant
    public static final String[] CATEGORIES = {
        "CLASSIC", "SEASONAL", "CUSTOM", "GLUTEN_FREE", "BEVERAGE", "COMBO"
    };

    // Private fields - ENCAPSULATION
    private String id;
    private String name;
    private String flavor;
    private String category;
    private double price;
    private String description;

    /**
     * Constructor for Cupcake
     */
    public Cupcake(String id, String name, String flavor,
                   String category, double price, String description) {
        this.id = id;
        this.name = name;
        this.flavor = flavor;
        this.category = category;
        this.price = price;
        this.description = description;
    }

    // ======= Getters and Setters - ENCAPSULATION =======

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * POLYMORPHISM - overriding toString()
     */
    @Override
    public String toString() {
        return "[" + id + "] " + name + " - " + flavor +
               " (" + category + ") - $" + String.format("%.2f", price);
    }

    /**
     * Converts cupcake data to CSV format for file storage
     * @return CSV string
     */
    public String toFileString() {
        return id + "|" + name + "|" + flavor + "|" +
               category + "|" + price + "|" + description;
    }
}
