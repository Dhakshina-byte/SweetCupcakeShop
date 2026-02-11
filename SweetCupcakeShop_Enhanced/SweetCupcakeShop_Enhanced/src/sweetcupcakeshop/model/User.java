package sweetcupcakeshop.model;

/**
 * Abstract User class - demonstrates ABSTRACTION and ENCAPSULATION
 * Base class for all user types in the Sweet Cupcake Shop system
 */
public abstract class User {

    // Private fields - ENCAPSULATION
    private String username;
    private String password;
    private String fullName;

    /**
     * Constructor for User
     * @param username unique username
     * @param password user password
     * @param fullName user's full name
     */
    public User(String username, String password, String fullName) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
    }

    // Abstract method - ABSTRACTION (subclasses must implement)
    public abstract String getRole();

    // Abstract method for role-specific display
    public abstract String getDashboardTitle();

    // Getters and Setters - ENCAPSULATION
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * POLYMORPHISM - overriding toString()
     */
    @Override
    public String toString() {
        return fullName + " [" + getRole() + "]";
    }

    /**
     * Converts user data to CSV format for file storage
     */
    public String toFileString() {
        return username + "," + password + "," + fullName + "," + getRole();
    }
}
