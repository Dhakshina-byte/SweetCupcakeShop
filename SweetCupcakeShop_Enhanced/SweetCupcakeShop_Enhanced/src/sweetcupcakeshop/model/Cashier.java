package sweetcupcakeshop.model;

/**
 * Cashier class - demonstrates INHERITANCE from User
 * Cashiers can view, add, and search cupcakes
 */
public class Cashier extends User {

    /**
     * Constructor - calls parent constructor via super()
     * @param username unique username
     * @param password user password
     * @param fullName cashier's full name
     */
    public Cashier(String username, String password, String fullName) {
        super(username, password, fullName);
    }

    /**
     * POLYMORPHISM - overriding abstract method getRole()
     * @return role as CASHIER
     */
    @Override
    public String getRole() {
        return "CASHIER";
    }

    /**
     * POLYMORPHISM - overriding getDashboardTitle()
     */
    @Override
    public String getDashboardTitle() {
        return "Cashier Dashboard - The Sweet Cupcake Shop";
    }

    /**
     * POLYMORPHISM - overriding toString() further
     */
    @Override
    public String toString() {
        return "Cashier: " + getFullName();
    }
}
