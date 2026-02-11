package sweetcupcakeshop.view;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import sweetcupcakeshop.model.*;
import sweetcupcakeshop.util.FileHandler;

public class BillingPanel extends JDialog {
    
    private User currentUser;
    private Transaction currentTransaction;
    
    // RED and BLUE
    private static final Color BTN_BLUE = new Color(0, 102, 204);
    private static final Color BTN_RED  = new Color(204, 0, 51);
    private static final Color WHITE = Color.WHITE;
    private static final Color PRIMARY = new Color(75, 36, 55);
    
    private JComboBox<String> cmbCategory;
    private JTable tableCupcakes;
    private DefaultTableModel cupcakeModel;
    private JTextField txtQuantity;
    private JButton btnAddToCart;
    private JTextField txtCustomerName;
    
    private JTable tableCart;
    private DefaultTableModel cartModel;
    private JButton btnRemoveItem;
    private JButton btnClearCart;
    
    private JLabel lblSubtotal;
    private JLabel lblTax;
    private JLabel lblDiscount;
    private JLabel lblTotal;
    private JTextField txtDiscount;
    private JButton btnApplyDiscount;
    private JComboBox<String> cmbPaymentMethod;
    private JButton btnProcessPayment;
    private JButton btnPrintReceipt;
    private JTextArea txtReceipt;
    
    public BillingPanel(JFrame parent, User user) {
        super(parent, "Point of Sale - Billing", true);
        this.currentUser = user;
        this.currentTransaction = new Transaction(user.getFullName(), "");
        initComponents();
        loadAllCupcakes();
        setLocationRelativeTo(parent);
    }
    
    private void initComponents() {
        setSize(1100, 750);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(250, 245, 248));
        
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(PRIMARY);
        topBar.setPreferredSize(new Dimension(1100, 60));
        topBar.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        
        JLabel lblTitle = new JLabel("🧁  Point of Sale System", SwingConstants.LEFT);
        lblTitle.setFont(new Font("Georgia", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);
        topBar.add(lblTitle, BorderLayout.WEST);
        
        JLabel lblCashier = new JLabel("Cashier: " + currentUser.getFullName(), SwingConstants.RIGHT);
        lblCashier.setFont(new Font("Arial", Font.PLAIN, 13));
        lblCashier.setForeground(Color.WHITE);
        topBar.add(lblCashier, BorderLayout.EAST);
        
        add(topBar, BorderLayout.NORTH);
        
        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 15, 0));
        mainPanel.setBackground(new Color(250, 245, 248));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        mainPanel.add(createProductPanel());
        mainPanel.add(createOrderPanel());
        
        add(mainPanel, BorderLayout.CENTER);
        add(createPaymentPanel(), BorderLayout.SOUTH);
    }
    
    private JPanel createProductPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 190, 205), 2),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        JLabel lblHeader = new JLabel("Product Catalog");
        lblHeader.setFont(new Font("Georgia", Font.BOLD, 16));
        panel.add(lblHeader, BorderLayout.NORTH);
        
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        filterPanel.setBackground(WHITE);
        
        filterPanel.add(new JLabel("Category:"));
        cmbCategory = new JComboBox<>(new String[]{"ALL", "CLASSIC", "SEASONAL", "CUSTOM", "GLUTEN_FREE", "BEVERAGE", "COMBO"});
        cmbCategory.setPreferredSize(new Dimension(150, 30));
        cmbCategory.addActionListener(e -> filterByCategory());
        filterPanel.add(cmbCategory);
        
        JButton btnRefresh = makeButton("Refresh", BTN_BLUE);
        btnRefresh.addActionListener(e -> loadAllCupcakes());
        filterPanel.add(btnRefresh);
        
        panel.add(filterPanel, BorderLayout.NORTH);
        
        String[] columns = {"ID", "Name", "Category", "Price"};
        cupcakeModel = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tableCupcakes = new JTable(cupcakeModel);
        tableCupcakes.getTableHeader().setBackground(new Color(95, 50, 75));
        tableCupcakes.getTableHeader().setForeground(WHITE);
        tableCupcakes.setRowHeight(28);
        
        JScrollPane scroll = new JScrollPane(tableCupcakes);
        panel.add(scroll, BorderLayout.CENTER);
        
        JPanel addPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        addPanel.setBackground(WHITE);
        
        addPanel.add(new JLabel("Quantity:"));
        txtQuantity = new JTextField("1", 5);
        addPanel.add(txtQuantity);
        
        btnAddToCart = makeButton("Add to Cart", BTN_BLUE);
        btnAddToCart.addActionListener(e -> addToCart());
        addPanel.add(btnAddToCart);
        
        panel.add(addPanel, BorderLayout.SOUTH);
        return panel;
    }
    
    private JPanel createOrderPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 190, 205), 2),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        JPanel headerPanel = new JPanel(new BorderLayout(10, 10));
        headerPanel.setBackground(WHITE);
        JLabel lblHeader = new JLabel("Current Order");
        lblHeader.setFont(new Font("Georgia", Font.BOLD, 16));
        headerPanel.add(lblHeader, BorderLayout.WEST);
        
        JPanel customerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        customerPanel.setBackground(WHITE);
        customerPanel.add(new JLabel("Customer:"));
        txtCustomerName = new JTextField("Walk-in", 15);
        customerPanel.add(txtCustomerName);
        headerPanel.add(customerPanel, BorderLayout.EAST);
        panel.add(headerPanel, BorderLayout.NORTH);
        
        String[] columns = {"Item", "Qty", "Price", "Subtotal"};
        cartModel = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tableCart = new JTable(cartModel);
        tableCart.getTableHeader().setBackground(new Color(95, 50, 75));
        tableCart.getTableHeader().setForeground(WHITE);
        tableCart.setRowHeight(28);
        
        JScrollPane scroll = new JScrollPane(tableCart);
        panel.add(scroll, BorderLayout.CENTER);
        
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        controlPanel.setBackground(WHITE);
        
        btnRemoveItem = makeButton("Remove Selected", BTN_RED);
        btnRemoveItem.addActionListener(e -> removeFromCart());
        controlPanel.add(btnRemoveItem);
        
        btnClearCart = makeButton("Clear All", BTN_RED);
        btnClearCart.addActionListener(e -> clearCart());
        controlPanel.add(btnClearCart);
        
        panel.add(controlPanel, BorderLayout.SOUTH);
        return panel;
    }
    
    private JPanel createPaymentPanel() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBackground(WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JPanel totalsPanel = new JPanel(new GridLayout(5, 2, 10, 8));
        totalsPanel.setBackground(WHITE);
        
        totalsPanel.add(new JLabel("Subtotal:"));
        lblSubtotal = new JLabel("$0.00");
        totalsPanel.add(lblSubtotal);
        
        totalsPanel.add(new JLabel("Tax (8%):"));
        lblTax = new JLabel("$0.00");
        totalsPanel.add(lblTax);
        
        totalsPanel.add(new JLabel("Discount:"));
        JPanel discountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        discountPanel.setBackground(WHITE);
        txtDiscount = new JTextField("0.00", 8);
        btnApplyDiscount = makeButton("Apply", BTN_BLUE);
        btnApplyDiscount.setPreferredSize(new Dimension(80, 25));
        btnApplyDiscount.addActionListener(e -> applyDiscount());
        discountPanel.add(txtDiscount);
        discountPanel.add(btnApplyDiscount);
        lblDiscount = new JLabel("$0.00");
        discountPanel.add(lblDiscount);
        totalsPanel.add(discountPanel);
        
        totalsPanel.add(new JSeparator());
        totalsPanel.add(new JSeparator());
        
        JLabel lblTotalTitle = new JLabel("TOTAL:");
        lblTotalTitle.setFont(new Font("Arial", Font.BOLD, 18));
        totalsPanel.add(lblTotalTitle);
        lblTotal = new JLabel("$0.00");
        lblTotal.setFont(new Font("Arial", Font.BOLD, 18));
        totalsPanel.add(lblTotal);
        
        panel.add(totalsPanel, BorderLayout.WEST);
        
        JPanel paymentPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        paymentPanel.setBackground(WHITE);
        
        paymentPanel.add(new JLabel("Payment:"));
        cmbPaymentMethod = new JComboBox<>(new String[]{"CASH", "CARD", "MOBILE PAY"});
        paymentPanel.add(cmbPaymentMethod);
        
        btnProcessPayment = makeButton("Process Payment", BTN_BLUE);
        btnProcessPayment.setPreferredSize(new Dimension(180, 45));
        btnProcessPayment.addActionListener(e -> processPayment());
        paymentPanel.add(btnProcessPayment);
        
        btnPrintReceipt = makeButton("Print Receipt", BTN_BLUE);
        btnPrintReceipt.setEnabled(false);
        btnPrintReceipt.setPreferredSize(new Dimension(150, 45));
        btnPrintReceipt.addActionListener(e -> printReceipt());
        paymentPanel.add(btnPrintReceipt);
        
        panel.add(paymentPanel, BorderLayout.EAST);
        return panel;
    }
    
    private JButton makeButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.BOLD, 12));
        btn.setFocusPainted(false);
        // Force Flat Style
        btn.setOpaque(true);
        btn.setBorderPainted(false);
        return btn;
    }
    
    // Logic Methods
    private void loadAllCupcakes() {
        cupcakeModel.setRowCount(0);
        List<Cupcake> cupcakes = FileHandler.getAllCupcakes();
        for (Cupcake c : cupcakes) {
            cupcakeModel.addRow(new Object[]{c.getId(), c.getName(), c.getCategory(), String.format("$%.2f", c.getPrice())});
        }
    }
    
    private void filterByCategory() {
        String selectedCategory = (String) cmbCategory.getSelectedItem();
        cupcakeModel.setRowCount(0);
        List<Cupcake> cupcakes = FileHandler.getAllCupcakes();
        for (Cupcake c : cupcakes) {
            if (selectedCategory.equals("ALL") || c.getCategory().equals(selectedCategory)) {
                cupcakeModel.addRow(new Object[]{c.getId(), c.getName(), c.getCategory(), String.format("$%.2f", c.getPrice())});
            }
        }
    }
    
    private void addToCart() {
        int selectedRow = tableCupcakes.getSelectedRow();
        if (selectedRow == -1) return;
        try {
            int quantity = Integer.parseInt(txtQuantity.getText().trim());
            String id = (String) cupcakeModel.getValueAt(selectedRow, 0);
            Cupcake c = FileHandler.getCupcakeById(id);
            currentTransaction.addItem(new OrderItem(c, quantity));
            updateCartDisplay();
        } catch(Exception e) {}
    }
    
    private void removeFromCart() {
        int row = tableCart.getSelectedRow();
        if (row != -1) {
            currentTransaction.removeItem(row);
            updateCartDisplay();
        }
    }
    
    private void clearCart() {
        currentTransaction = new Transaction(currentUser.getFullName(), txtCustomerName.getText());
        updateCartDisplay();
    }
    
    private void applyDiscount() {
        try {
            currentTransaction.applyDiscount(Double.parseDouble(txtDiscount.getText()));
            updateCartDisplay();
        } catch(Exception e) {}
    }
    
    private void updateCartDisplay() {
        cartModel.setRowCount(0);
        for(OrderItem i : currentTransaction.getItems()) {
            cartModel.addRow(new Object[]{i.getCupcake().getName(), i.getQuantity(), String.format("$%.2f", i.getCupcake().getPrice()), String.format("$%.2f", i.getSubtotal())});
        }
        lblSubtotal.setText(String.format("$%.2f", currentTransaction.getSubtotal()));
        lblTax.setText(String.format("$%.2f", currentTransaction.getTax()));
        lblDiscount.setText(String.format("$%.2f", currentTransaction.getDiscount()));
        lblTotal.setText(String.format("$%.2f", currentTransaction.getTotal()));
    }
    
    private void processPayment() {
        if(currentTransaction.getItems().isEmpty()) return;
        currentTransaction.setCustomerName(txtCustomerName.getText());
        currentTransaction.setPaymentMethod((String)cmbPaymentMethod.getSelectedItem());
        FileHandler.saveTransaction(currentTransaction);
        JOptionPane.showMessageDialog(this, "Paid: $" + String.format("%.2f", currentTransaction.getTotal()));
        btnPrintReceipt.setEnabled(true);
    }
    
    private void printReceipt() {
        JDialog d = new JDialog(this, "Receipt", true);
        d.setSize(400, 500);
        d.add(new JScrollPane(new JTextArea(currentTransaction.generateReceipt())));
        d.setVisible(true);
        currentTransaction = new Transaction(currentUser.getFullName(), "");
        updateCartDisplay();
        btnPrintReceipt.setEnabled(false);
    }
}