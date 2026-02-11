package sweetcupcakeshop.util;

import java.io.*;
import java.util.*;
import sweetcupcakeshop.model.*;

/**
 * FileHandler - Handles all file I/O operations for the Sweet Cupcake Shop system
 * Saves and retrieves data for Users and Cupcakes
 */
public class FileHandler {

    private static final String DATA_DIR = "data" + File.separator;
    private static final String USERS_FILE = DATA_DIR + "users.txt";
    private static final String CUPCAKES_FILE = DATA_DIR + "cupcakes.txt";
    private static final String TRANSACTIONS_FILE = DATA_DIR + "transactions.txt";

    /**
     * Initializes the data directory and default data files if they don't exist
     */
    public static void initializeData() {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File usersFile = new File(USERS_FILE);
        if (!usersFile.exists()) {
            createDefaultUsers();
        }

        File cupcakesFile = new File(CUPCAKES_FILE);
        if (!cupcakesFile.exists()) {
            createDefaultCupcakes();
        }
    }

    /**
     * Creates default admin/user accounts on first run
     */
    private static void createDefaultUsers() {
        List<User> users = new ArrayList<>();
        users.add(new Manager("manager", "manager123", "Admin Manager"));
        users.add(new Cashier("cashier", "cashier123", "Jane Cashier"));
        saveUsers(users);
    }

    /**
     * Creates default cupcake inventory on first run
     */
    private static void createDefaultCupcakes() {
        List<Cupcake> cupcakes = new ArrayList<>();
        cupcakes.add(new Cupcake("C001", "Classic Vanilla", "Vanilla", "CLASSIC", 3.50, "Moist vanilla cupcake with vanilla buttercream"));
        cupcakes.add(new Cupcake("C002", "Rich Chocolate", "Chocolate", "CLASSIC", 3.75, "Decadent chocolate cupcake with chocolate ganache"));
        cupcakes.add(new Cupcake("C003", "Red Velvet Delight", "Red Velvet", "CLASSIC", 4.00, "Classic red velvet with cream cheese frosting"));
        cupcakes.add(new Cupcake("C004", "Strawberry Bliss", "Strawberry", "CLASSIC", 3.50, "Fresh strawberry cupcake with strawberry cream"));
        cupcakes.add(new Cupcake("C005", "Pumpkin Spice", "Pumpkin", "SEASONAL", 4.25, "Seasonal pumpkin spice with cinnamon frosting"));
        cupcakes.add(new Cupcake("C006", "Winter Snowflake", "Peppermint", "SEASONAL", 4.50, "Holiday peppermint cupcake with white frosting"));
        cupcakes.add(new Cupcake("C007", "Wedding Elegance", "Almond", "CUSTOM", 6.00, "Custom wedding cupcake - contact for personalization"));
        cupcakes.add(new Cupcake("C008", "Birthday Burst", "Funfetti", "CUSTOM", 5.50, "Colorful birthday cupcake with sprinkles"));
        cupcakes.add(new Cupcake("C009", "GF Vanilla Dream", "Vanilla", "GLUTEN_FREE", 4.75, "Gluten-free vanilla cupcake - same great taste"));
        cupcakes.add(new Cupcake("C010", "GF Chocolate Joy", "Chocolate", "GLUTEN_FREE", 5.00, "Gluten-free rich chocolate cupcake"));
        cupcakes.add(new Cupcake("C011", "Hot Cocoa", "Chocolate", "BEVERAGE", 3.00, "Rich hot chocolate drink"));
        cupcakes.add(new Cupcake("C012", "Iced Vanilla Latte", "Vanilla", "BEVERAGE", 3.50, "Iced vanilla latte"));
        cupcakes.add(new Cupcake("C013", "Cupcake & Coffee Duo", "Mixed", "COMBO", 6.50, "Any cupcake plus a hot or cold coffee"));
        cupcakes.add(new Cupcake("C014", "Sweet Box Set", "Mixed", "COMBO", 12.00, "Box of 4 cupcakes plus 2 beverages"));
        saveCupcakes(cupcakes);
    }

    // ============ USER FILE OPERATIONS ============

    /**
     * Saves list of users to file
     * @param users list of User objects to save
     */
    public static void saveUsers(List<User> users) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(USERS_FILE))) {
            for (User u : users) {
                writer.println(u.toFileString());
            }
        } catch (IOException e) {
            System.err.println("Error saving users: " + e.getMessage());
        }
    }

    /**
     * Loads all users from file
     * @return list of User objects
     */
    public static List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        File file = new File(USERS_FILE);
        if (!file.exists()) return users;

        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split(",", 4);
                if (parts.length >= 4) {
                    String username = parts[0];
                    String password = parts[1];
                    String fullName = parts[2];
                    String role = parts[3];
                    if ("MANAGER".equalsIgnoreCase(role)) {
                        users.add(new Manager(username, password, fullName));
                    } else {
                        users.add(new Cashier(username, password, fullName));
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading users: " + e.getMessage());
        }
        return users;
    }

    /**
     * Authenticates a user with username and password
     * @param username entered username
     * @param password entered password
     * @return User object if authenticated, null otherwise
     */
    public static User authenticate(String username, String password) {
        List<User> users = loadUsers();
        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }

    /**
     * Adds a new user account to the system
     * @param newUser User to add
     * @return true if added successfully, false if username already exists
     */
    public static boolean addUser(User newUser) {
        List<User> users = loadUsers();
        for (User u : users) {
            if (u.getUsername().equalsIgnoreCase(newUser.getUsername())) {
                return false; // Username already taken
            }
        }
        users.add(newUser);
        saveUsers(users);
        return true;
    }

    /**
     * Returns all users as a 2D array for JTable display
     * @return 2D array of user data
     */
    public static Object[][] getUsersTableData() {
        List<User> users = loadUsers();
        Object[][] data = new Object[users.size()][3];
        for (int i = 0; i < users.size(); i++) {
            data[i][0] = users.get(i).getUsername();
            data[i][1] = users.get(i).getFullName();
            data[i][2] = users.get(i).getRole();
        }
        return data;
    }

    // ============ CUPCAKE FILE OPERATIONS ============

    /**
     * Saves list of cupcakes to file
     * @param cupcakes list of Cupcake objects to save
     */
    public static void saveCupcakes(List<Cupcake> cupcakes) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CUPCAKES_FILE))) {
            for (Cupcake c : cupcakes) {
                writer.println(c.toFileString());
            }
        } catch (IOException e) {
            System.err.println("Error saving cupcakes: " + e.getMessage());
        }
    }

    /**
     * Loads all cupcakes from file
     * @return list of Cupcake objects
     */
    public static List<Cupcake> loadCupcakes() {
        List<Cupcake> cupcakes = new ArrayList<>();
        File file = new File(CUPCAKES_FILE);
        if (!file.exists()) return cupcakes;

        try (BufferedReader reader = new BufferedReader(new FileReader(CUPCAKES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split("\\|", 6);
                if (parts.length >= 6) {
                    try {
                        Cupcake c = new Cupcake(
                            parts[0], parts[1], parts[2], parts[3],
                            Double.parseDouble(parts[4]), parts[5]
                        );
                        cupcakes.add(c);
                    } catch (NumberFormatException e) {
                        System.err.println("Skipping invalid cupcake record: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading cupcakes: " + e.getMessage());
        }
        return cupcakes;
    }

    /**
     * Adds a new cupcake to the inventory
     * @param cupcake Cupcake to add
     */
    public static void addCupcake(Cupcake cupcake) {
        List<Cupcake> cupcakes = loadCupcakes();
        cupcakes.add(cupcake);
        saveCupcakes(cupcakes);
    }

    /**
     * Searches cupcakes by category
     * @param category category to search (or "ALL" for all)
     * @return filtered list of cupcakes
     */
    public static List<Cupcake> searchByCategory(String category) {
        List<Cupcake> all = loadCupcakes();
        if ("ALL".equalsIgnoreCase(category)) return all;

        List<Cupcake> result = new ArrayList<>();
        for (Cupcake c : all) {
            if (c.getCategory().equalsIgnoreCase(category)) {
                result.add(c);
            }
        }
        return result;
    }

    /**
     * Returns cupcakes as a 2D array for JTable display
     * @param category filter category ("ALL" for all)
     * @return 2D array of cupcake data
     */
    public static Object[][] getCupcakesTableData(String category) {
        List<Cupcake> cupcakes = searchByCategory(category);
        Object[][] data = new Object[cupcakes.size()][6];
        for (int i = 0; i < cupcakes.size(); i++) {
            Cupcake c = cupcakes.get(i);
            data[i][0] = c.getId();
            data[i][1] = c.getName();
            data[i][2] = c.getFlavor();
            data[i][3] = c.getCategory();
            data[i][4] = String.format("$%.2f", c.getPrice());
            data[i][5] = c.getDescription();
        }
        return data;
    }

    /**
     * Generates a new unique cupcake ID
     * @return new ID string like C015
     */
    public static String generateCupcakeId() {
        List<Cupcake> cupcakes = loadCupcakes();
        int maxNum = 0;
        for (Cupcake c : cupcakes) {
            try {
                int num = Integer.parseInt(c.getId().substring(1));
                if (num > maxNum) maxNum = num;
            } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                // Skip malformed IDs
            }
        }
        return "C" + String.format("%03d", maxNum + 1);
    }
    
    /**
     * Gets all cupcakes (alias for loadCupcakes for convenience)
     * @return list of all cupcakes
     */
    public static List<Cupcake> getAllCupcakes() {
        return loadCupcakes();
    }
    
    /**
     * Gets a cupcake by its ID
     * @param id the cupcake ID to search for
     * @return Cupcake object if found, null otherwise
     */
    public static Cupcake getCupcakeById(String id) {
        List<Cupcake> cupcakes = loadCupcakes();
        for (Cupcake c : cupcakes) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }
    
    // ============ TRANSACTION FILE OPERATIONS ============
    
    /**
     * Saves a transaction to file
     * @param transaction Transaction to save
     */
    public static void saveTransaction(Transaction transaction) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(TRANSACTIONS_FILE, true))) {
            writer.println(transaction.toFileString());
        } catch (IOException e) {
            System.err.println("Error saving transaction: " + e.getMessage());
        }
    }
    
    /**
     * Loads all transactions from file
     * @return list of Transaction objects (note: this is a simplified version)
     */
    public static List<String> loadTransactionSummaries() {
        List<String> transactions = new ArrayList<>();
        File file = new File(TRANSACTIONS_FILE);
        if (!file.exists()) return transactions;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(TRANSACTIONS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    transactions.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading transactions: " + e.getMessage());
        }
        return transactions;
    }
}
