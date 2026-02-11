package sweetcupcakeshop.view;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import sweetcupcakeshop.model.*;
import sweetcupcakeshop.util.FileHandler;

public class CashierDashboard extends javax.swing.JFrame {

    protected User currentUser;

    // Colors & Styling
    protected static final Color PRIMARY    = new Color(75, 36, 55);
    protected static final Color BG         = new Color(250, 245, 248);
    protected static final Color WHITE      = Color.WHITE;
    protected static final Color TABLE_HDR  = new Color(95, 50, 75);
    
    // RED and BLUE BUTTONS
    protected static final Color BTN_BLUE   = new Color(0, 102, 204);
    protected static final Color BTN_RED    = new Color(204, 0, 51);

    protected static final String[] CUPCAKE_COLS = {"ID", "Name", "Flavor", "Category", "Price", "Description"};
    protected static final String[] USER_COLS    = {"Username", "Full Name", "Role"};

    // Components
    protected JPanel topBar;
    protected JLabel lblWelcome;
    protected JButton btnLogout;
    protected JTabbedPane tabbedPane;
    protected JTable tableAllCupcakes;
    protected JScrollPane scrollAll;
    protected JButton btnRefresh;
    protected JTextField txtCupName, txtCupFlavor, txtCupPrice, txtCupDesc;
    protected JComboBox<String> cmbCategory;
    protected JButton btnAddCupcake, btnClearForm;
    protected JLabel lblAddStatus;
    protected JComboBox<String> cmbSearchCategory;
    protected JTable tableSearch;
    protected JScrollPane scrollSearch;
    protected JButton btnSearch;

    public CashierDashboard(User user) {
        this.currentUser = user;
        initComponents();
        loadAllCupcakes();
        setLocationRelativeTo(null);
    }
    
    protected CashierDashboard() {}

    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    protected void initComponents() {

        setTitle(currentUser.getDashboardTitle());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 680);
        setMinimumSize(new Dimension(820, 600));
        getContentPane().setBackground(BG);
        getContentPane().setLayout(new BorderLayout());

        // ===== TOP BAR =====
        topBar = new JPanel(new BorderLayout());
        topBar.setBackground(PRIMARY);
        topBar.setPreferredSize(new Dimension(900, 65));
        topBar.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        // Left
        JPanel leftTop = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        leftTop.setOpaque(false);
        JLabel lblIcon = new JLabel("\uD83E\uDDC1");
        lblIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 28));
        lblIcon.setForeground(WHITE);
        leftTop.add(lblIcon);
        JLabel lblTitle = new JLabel("The Sweet Cupcake Shop");
        lblTitle.setFont(new Font("Georgia", Font.BOLD, 18));
        lblTitle.setForeground(WHITE);
        leftTop.add(lblTitle);
        topBar.add(leftTop, BorderLayout.WEST);

        // Center
        lblWelcome = new JLabel("Welcome, " + currentUser.getFullName() + "  |  " + currentUser.getRole(),
                                 SwingConstants.CENTER);
        lblWelcome.setFont(new Font("Arial", Font.PLAIN, 13));
        lblWelcome.setForeground(new Color(255, 210, 220));
        topBar.add(lblWelcome, BorderLayout.CENTER);

        // Right (LOGOUT - RED)
        JPanel rightTop = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 12));
        rightTop.setOpaque(false);
        btnLogout = makeButton("Logout", BTN_RED, WHITE);
        btnLogout.setPreferredSize(new Dimension(90, 36));
        btnLogout.addActionListener(e -> btnLogoutActionPerformed());
        rightTop.add(btnLogout);
        topBar.add(rightTop, BorderLayout.EAST);

        getContentPane().add(topBar, BorderLayout.NORTH);

        // ===== TABBED PANE =====
        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setFont(new Font("Arial", Font.BOLD, 13));
        tabbedPane.setBackground(BG);
        tabbedPane.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        // ---- TAB 1: VIEW (REFRESH - BLUE) ----
        JPanel panelView = new JPanel(new BorderLayout(10, 10));
        panelView.setBackground(BG);
        panelView.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel viewHeader = new JPanel(new BorderLayout());
        viewHeader.setOpaque(false);
        JLabel lblViewTitle = makeHeaderLabel("\uD83C\uDF70  All Cupcakes");
        viewHeader.add(lblViewTitle, BorderLayout.WEST);
        
        btnRefresh = makeButton("Refresh", BTN_BLUE, WHITE);
        btnRefresh.addActionListener(e -> loadAllCupcakes());
        viewHeader.add(btnRefresh, BorderLayout.EAST);
        panelView.add(viewHeader, BorderLayout.NORTH);

        tableAllCupcakes = createStyledTable(CUPCAKE_COLS);
        scrollAll = new JScrollPane(tableAllCupcakes);
        styleScrollPane(scrollAll);
        panelView.add(scrollAll, BorderLayout.CENTER);

        tabbedPane.addTab("  View Cupcakes  ", panelView);

        // ---- TAB 2: ADD (ADD - BLUE, CLEAR - RED) ----
        JPanel panelAdd = new JPanel(null);
        panelAdd.setBackground(BG);

        JLabel lblAddTitle = makeHeaderLabel("\u2795  Add New Cupcake");
        lblAddTitle.setBounds(20, 20, 400, 35);
        panelAdd.add(lblAddTitle);

        JPanel formCard = new JPanel(null);
        formCard.setBackground(WHITE);
        formCard.setBorder(BorderFactory.createLineBorder(new Color(220, 190, 205), 1));
        formCard.setBounds(20, 70, 840, 440);
        panelAdd.add(formCard);

        addFormLabel(formCard, "Cupcake Name *", 30, 30);
        txtCupName = addFormField(formCard, 30, 55, 360);
        addFormLabel(formCard, "Flavor *", 430, 30);
        txtCupFlavor = addFormField(formCard, 430, 55, 360);
        addFormLabel(formCard, "Category *", 30, 115);
        cmbCategory = new JComboBox<>(new String[]{"CLASSIC","SEASONAL","CUSTOM","GLUTEN_FREE","BEVERAGE","COMBO"});
        styleCombo(cmbCategory);
        cmbCategory.setBounds(30, 138, 360, 38);
        formCard.add(cmbCategory);
        addFormLabel(formCard, "Price (USD) *", 430, 115);
        txtCupPrice = addFormField(formCard, 430, 138, 360);
        addFormLabel(formCard, "Description", 30, 198);
        txtCupDesc = new JTextField();
        txtCupDesc.setFont(new Font("Arial", Font.PLAIN, 13));
        txtCupDesc.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 170, 185), 1, true),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        txtCupDesc.setBounds(30, 222, 760, 38);
        formCard.add(txtCupDesc);

        lblAddStatus = new JLabel(" ");
        lblAddStatus.setFont(new Font("Arial", Font.BOLD, 12));
        lblAddStatus.setHorizontalAlignment(SwingConstants.CENTER);
        lblAddStatus.setBounds(0, 278, 840, 24);
        formCard.add(lblAddStatus);

        // Buttons
        btnAddCupcake = makeButton("Add Cupcake", BTN_BLUE, WHITE);
        btnAddCupcake.setFont(new Font("Arial", Font.BOLD, 14));
        btnAddCupcake.setBounds(30, 315, 200, 45);
        btnAddCupcake.addActionListener(e -> btnAddCupcakeActionPerformed());
        formCard.add(btnAddCupcake);

        btnClearForm = makeButton("Clear Form", BTN_RED, WHITE);
        btnClearForm.setFont(new Font("Arial", Font.BOLD, 14));
        btnClearForm.setBounds(245, 315, 160, 45);
        btnClearForm.addActionListener(e -> btnClearFormActionPerformed());
        formCard.add(btnClearForm);

        tabbedPane.addTab("  Add Cupcake  ", panelAdd);

        // ---- TAB 3: SEARCH (SEARCH - BLUE) ----
        JPanel panelSearch = new JPanel(new BorderLayout(10, 10));
        panelSearch.setBackground(BG);
        panelSearch.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel searchTop = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 5));
        searchTop.setOpaque(false);
        JLabel lblSearchTitle = makeHeaderLabel("\uD83D\uDD0D  Search by Category:");
        searchTop.add(lblSearchTitle);

        cmbSearchCategory = new JComboBox<>(new String[]{
            "ALL","CLASSIC","SEASONAL","CUSTOM","GLUTEN_FREE","BEVERAGE","COMBO"
        });
        styleCombo(cmbSearchCategory);
        cmbSearchCategory.setPreferredSize(new Dimension(180, 36));
        searchTop.add(cmbSearchCategory);

        btnSearch = makeButton("Search", BTN_BLUE, WHITE);
        btnSearch.setPreferredSize(new Dimension(100, 36));
        btnSearch.addActionListener(e -> btnSearchActionPerformed());
        searchTop.add(btnSearch);

        panelSearch.add(searchTop, BorderLayout.NORTH);

        tableSearch = createStyledTable(CUPCAKE_COLS);
        scrollSearch = new JScrollPane(tableSearch);
        styleScrollPane(scrollSearch);
        panelSearch.add(scrollSearch, BorderLayout.CENTER);

        tabbedPane.addTab("  Search Cupcakes  ", panelSearch);
        
        // ---- TAB 4: BILLING (OPEN - BLUE) ----
        JPanel panelBilling = new JPanel(new BorderLayout(10, 10));
        panelBilling.setBackground(BG);
        panelBilling.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        JPanel billingContent = new JPanel(new BorderLayout(20, 20));
        billingContent.setBackground(BG);
        billingContent.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(WHITE);
        infoPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 190, 205), 2),
            BorderFactory.createEmptyBorder(30, 30, 30, 30)
        ));
        
        JLabel lblInfo2 = new JLabel("Billing System");
        lblInfo2.setFont(new Font("Arial", Font.BOLD, 18));
        lblInfo2.setForeground(PRIMARY);
        lblInfo2.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoPanel.add(lblInfo2);
        infoPanel.add(Box.createVerticalStrut(20));
        
        JButton btnOpenBilling = makeButton("Open Billing System", BTN_BLUE, WHITE);
        btnOpenBilling.setFont(new Font("Arial", Font.BOLD, 16));
        btnOpenBilling.setPreferredSize(new Dimension(250, 50));
        btnOpenBilling.setMaximumSize(new Dimension(250, 50));
        btnOpenBilling.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnOpenBilling.addActionListener(e -> openBillingSystem());
        infoPanel.add(btnOpenBilling);
        
        billingContent.add(infoPanel, BorderLayout.CENTER);
        panelBilling.add(billingContent, BorderLayout.CENTER);
        
        tabbedPane.addTab("  💰 Billing  ", panelBilling);

        getContentPane().add(tabbedPane, BorderLayout.CENTER);
    }
    // </editor-fold>

    protected void btnLogoutActionPerformed() {
        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to logout?", "Confirm Logout",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            new LoginForm().setVisible(true);
            this.dispose();
        }
    }

    protected void loadAllCupcakes() {
        Object[][] data = FileHandler.getCupcakesTableData("ALL");
        updateTable(tableAllCupcakes, data, CUPCAKE_COLS);
    }

    protected void btnAddCupcakeActionPerformed() {
        try {
            String name  = txtCupName.getText().trim();
            String flavor = txtCupFlavor.getText().trim();
            String priceStr = txtCupPrice.getText().trim();
            String category = (String) cmbCategory.getSelectedItem();
            String desc  = txtCupDesc.getText().trim();

            if (name.isEmpty() || flavor.isEmpty() || priceStr.isEmpty()) {
                showStatus(lblAddStatus, "Please fill in all required fields (*)", false);
                return;
            }

            double price = Double.parseDouble(priceStr);
            if (price <= 0) throw new NumberFormatException();

            String newId = FileHandler.generateCupcakeId();
            Cupcake cupcake = new Cupcake(newId, name, flavor, category, price, desc.isEmpty() ? "-" : desc);
            FileHandler.addCupcake(cupcake);

            showStatus(lblAddStatus, "Cupcake Added! (ID: " + newId + ")", true);
            btnClearFormActionPerformed();
            loadAllCupcakes();
        } catch (NumberFormatException ex) {
            showStatus(lblAddStatus, "Invalid Price", false);
        }
    }

    protected void btnClearFormActionPerformed() {
        txtCupName.setText("");
        txtCupFlavor.setText("");
        txtCupPrice.setText("");
        txtCupDesc.setText("");
        cmbCategory.setSelectedIndex(0);
        lblAddStatus.setText(" ");
    }

    protected void btnSearchActionPerformed() {
        String category = (String) cmbSearchCategory.getSelectedItem();
        Object[][] data = FileHandler.getCupcakesTableData(category);
        updateTable(tableSearch, data, CUPCAKE_COLS);
    }
    
    protected void openBillingSystem() {
        BillingPanel billingDialog = new BillingPanel(this, currentUser);
        billingDialog.setVisible(true);
    }

    // Helpers
    protected JTable createStyledTable(String[] columns) {
        DefaultTableModel model = new DefaultTableModel(new Object[][]{}, columns) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        JTable table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.setRowHeight(32);
        table.setSelectionBackground(new Color(245, 180, 205));
        JTableHeader header = table.getTableHeader();
        header.setBackground(TABLE_HDR);
        header.setForeground(WHITE);
        return table;
    }

    protected void updateTable(JTable table, Object[][] data, String[] cols) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (Object[] row : data) model.addRow(row);
    }

    protected void styleScrollPane(JScrollPane sp) {
        sp.setBorder(BorderFactory.createLineBorder(new Color(220, 190, 205), 1));
        sp.getViewport().setBackground(WHITE);
    }

    protected JLabel makeHeaderLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Georgia", Font.BOLD, 16));
        lbl.setForeground(PRIMARY);
        return lbl;
    }

    protected JButton makeButton(String text, Color bg, Color fg) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 12));
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFocusPainted(false);
        // FORCE FLAT STYLE FOR COLOR
        btn.setOpaque(true);
        btn.setBorderPainted(false);
        return btn;
    }

    protected void addFormLabel(JPanel p, String text, int x, int y) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Arial", Font.BOLD, 12));
        lbl.setBounds(x, y, 300, 22);
        p.add(lbl);
    }

    protected JTextField addFormField(JPanel p, int x, int y, int w) {
        JTextField f = new JTextField();
        f.setBounds(x, y, w, 38);
        p.add(f);
        return f;
    }

    protected void styleCombo(JComboBox<?> combo) {
        combo.setBackground(WHITE);
    }

    protected void showStatus(JLabel lbl, String msg, boolean success) {
        lbl.setText(msg);
        lbl.setForeground(success ? new Color(0, 150, 80) : new Color(200, 50, 70));
    }
}