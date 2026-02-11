package sweetcupcakeshop.model;

/**
 * Manager class - demonstrates INHERITANCE from User
 * Managers have all cashier privileges PLUS user account management
 */
public class Manager extends User {

    /**
     * Constructor - calls parent constructor via super()
     * @param username unique username
     * @param password user password
     * @param fullName manager's full name
     */
    public Manager(String username, String password, String fullName) {
        super(username, password, fullName);
    }

    /**
     * POLYMORPHISM - overriding abstract method getRole()
     * @return role as MANAGER
     */
    @Override
    public String getRole() {
        return "MANAGER";
    }

    /**
     * POLYMORPHISM - overriding getDashboardTitle()
     */
    @Override
    public String getDashboardTitle() {
        return "Manager Dashboard - The Sweet Cupcake Shop";
    }

    /**
     * Manager-specific method to create a new cashier account
     * POLYMORPHISM - Manager can create User objects
     * @param username new cashier's username
     * @param password new cashier's password
     * @param fullName new cashier's full name
     * @return new Cashier object
     */
    public User createCashierAccount(String username, String password, String fullName) {
        return new Cashier(username, password, fullName);
    }

    /**
     * POLYMORPHISM - overriding toString()
     */
    @Override
    public String toString() {
        return "Manager: " + getFullName();
    }
}
