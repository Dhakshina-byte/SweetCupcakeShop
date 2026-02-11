package sweetcupcakeshop.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Transaction class - represents a complete customer transaction/bill
 * Demonstrates ENCAPSULATION and object composition
 */
public class Transaction {
    
    private static int transactionCounter = 1000;
    
    private String transactionId;
    private String cashierName;
    private LocalDateTime timestamp;
    private List<OrderItem> items;
    private double subtotal;
    private double tax;
    private double discount;
    private double total;
    private String paymentMethod;
    private String customerName;
    
    /**
     * Constructor for new transaction
     */
    public Transaction(String cashierName, String customerName) {
        this.transactionId = "TXN" + (++transactionCounter);
        this.cashierName = cashierName;
        this.customerName = customerName != null && !customerName.trim().isEmpty() 
                            ? customerName : "Walk-in Customer";
        this.timestamp = LocalDateTime.now();
        this.items = new ArrayList<>();
        this.discount = 0.0;
        this.paymentMethod = "CASH";
    }
    
    /**
     * Add an item to the transaction
     */
    public void addItem(OrderItem item) {
        items.add(item);
        calculateTotals();
    }
    
    /**
     * Remove an item from the transaction
     */
    public void removeItem(int index) {
        if (index >= 0 && index < items.size()) {
            items.remove(index);
            calculateTotals();
        }
    }
    
    /**
     * Calculate subtotal, tax, and total
     */
    private void calculateTotals() {
        subtotal = 0.0;
        for (OrderItem item : items) {
            subtotal += item.getSubtotal();
        }
        tax = subtotal * 0.08; // 8% tax
        total = subtotal + tax - discount;
    }
    
    /**
     * Apply discount to transaction
     */
    public void applyDiscount(double discountAmount) {
        this.discount = discountAmount;
        calculateTotals();
    }
    
    // Getters and Setters
    public String getTransactionId() {
        return transactionId;
    }
    
    public String getCashierName() {
        return cashierName;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public String getFormattedTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return timestamp.format(formatter);
    }
    
    public List<OrderItem> getItems() {
        return items;
    }
    
    public double getSubtotal() {
        return subtotal;
    }
    
    public double getTax() {
        return tax;
    }
    
    public double getDiscount() {
        return discount;
    }
    
    public double getTotal() {
        return total;
    }
    
    public String getPaymentMethod() {
        return paymentMethod;
    }
    
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    public String getCustomerName() {
        return customerName;
    }
    
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    /**
     * Generate receipt text
     */
    public String generateReceipt() {
        StringBuilder receipt = new StringBuilder();
        receipt.append("=====================================\n");
        receipt.append("   THE SWEET CUPCAKE SHOP\n");
        receipt.append("      Transaction Receipt\n");
        receipt.append("=====================================\n\n");
        receipt.append("Transaction ID: ").append(transactionId).append("\n");
        receipt.append("Date & Time: ").append(getFormattedTimestamp()).append("\n");
        receipt.append("Cashier: ").append(cashierName).append("\n");
        receipt.append("Customer: ").append(customerName).append("\n");
        receipt.append("-------------------------------------\n\n");
        receipt.append("ITEMS:\n");
        
        for (OrderItem item : items) {
            receipt.append(String.format("%-25s x%-3d $%7.2f\n",
                item.getCupcake().getName(),
                item.getQuantity(),
                item.getSubtotal()));
        }
        
        receipt.append("\n-------------------------------------\n");
        receipt.append(String.format("Subtotal:        $%10.2f\n", subtotal));
        receipt.append(String.format("Tax (8%%):        $%10.2f\n", tax));
        if (discount > 0) {
            receipt.append(String.format("Discount:       -$%10.2f\n", discount));
        }
        receipt.append("-------------------------------------\n");
        receipt.append(String.format("TOTAL:           $%10.2f\n", total));
        receipt.append("-------------------------------------\n");
        receipt.append("Payment Method: ").append(paymentMethod).append("\n\n");
        receipt.append("    Thank you for your purchase!\n");
        receipt.append("       Visit us again soon!\n");
        receipt.append("=====================================\n");
        
        return receipt.toString();
    }
    
    /**
     * Convert to file storage format
     */
    public String toFileString() {
        StringBuilder sb = new StringBuilder();
        sb.append(transactionId).append("|");
        sb.append(cashierName).append("|");
        sb.append(customerName).append("|");
        sb.append(getFormattedTimestamp()).append("|");
        sb.append(paymentMethod).append("|");
        sb.append(String.format("%.2f", subtotal)).append("|");
        sb.append(String.format("%.2f", tax)).append("|");
        sb.append(String.format("%.2f", discount)).append("|");
        sb.append(String.format("%.2f", total));
        return sb.toString();
    }
}
