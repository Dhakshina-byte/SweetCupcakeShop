package sweetcupcakeshop.model;

/**
 * OrderItem class - represents an item in a customer order/bill
 * Demonstrates ENCAPSULATION and proper object-oriented design
 */
public class OrderItem {
    
    private Cupcake cupcake;
    private int quantity;
    
    /**
     * Constructor
     * @param cupcake the cupcake item
     * @param quantity number of items ordered
     */
    public OrderItem(Cupcake cupcake, int quantity) {
        this.cupcake = cupcake;
        this.quantity = quantity;
    }
    
    // Getters and Setters
    public Cupcake getCupcake() {
        return cupcake;
    }
    
    public void setCupcake(Cupcake cupcake) {
        this.cupcake = cupcake;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    /**
     * Calculate subtotal for this item
     * @return quantity * unit price
     */
    public double getSubtotal() {
        return cupcake.getPrice() * quantity;
    }
    
    @Override
    public String toString() {
        return cupcake.getName() + " x" + quantity + " = $" + 
               String.format("%.2f", getSubtotal());
    }
}
