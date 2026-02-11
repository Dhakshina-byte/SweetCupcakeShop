package sweetcupcakeshop.view;

import javax.swing.*;
import sweetcupcakeshop.model.Manager;

public class ManagerDashboard extends CashierDashboard {

    public ManagerDashboard(Manager manager) {
        super(manager);
        this.setTitle("Manager Dashboard - The Sweet Cupcake Shop");

        // Add the Manager-Specific Tab
        ManagerUsersPanel usersPanel = new ManagerUsersPanel(manager);
        this.tabbedPane.addTab("  Manage Users  ", usersPanel);
    }
}