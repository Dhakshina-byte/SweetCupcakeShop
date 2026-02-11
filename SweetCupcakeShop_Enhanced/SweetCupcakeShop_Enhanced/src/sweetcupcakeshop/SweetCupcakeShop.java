package sweetcupcakeshop;

import javax.swing.UIManager;
import sweetcupcakeshop.util.FileHandler;
import sweetcupcakeshop.view.LoginForm;

/**
 * SweetCupcakeShop - Main application entry point
 *
 * The Sweet Cupcake Shop - Staff Management System
 * Module: CSE4006 Object Oriented Programming
 *
 * OOP Concepts Applied:
 *   1. CLASS & OBJECTS   - User, Cashier, Manager, Cupcake, FileHandler
 *   2. ABSTRACTION       - Abstract User class with abstract methods
 *   3. ENCAPSULATION     - Private fields with getters/setters in all model classes
 *   4. INHERITANCE       - Cashier & Manager extend User; ManagerDashboard extends CashierDashboard
 *   5. POLYMORPHISM      - Method overriding (getRole, toString, getDashboardTitle, btnLogoutActionPerformed)
 *
 * Default Login Credentials:
 *   Manager  -> username: manager  | password: manager123
 *   Cashier  -> username: cashier  | password: cashier123
 */
public class SweetCupcakeShop {

    /**
     * Application entry point
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {

        // Set system look and feel for better visual appearance
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Fall back to default look and feel
        }

        // Initialize data files (creates defaults if they don't exist)
        FileHandler.initializeData();

        // Launch the Login Form on the Event Dispatch Thread
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }
}
