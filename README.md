# 🧁 The Sweet Cupcake Shop Management System

A robust, Java-based Point of Sale (POS) and Staff Management application designed for a bakery environment. This application uses **Java Swing** for the GUI and demonstrates core Object-Oriented Programming (OOP) principles like Inheritance, Polymorphism, and Encapsulation.

## 🚀 Features

### 🔐 Role-Based Access Control
* **Secure Login:** Authentication system verifying usernames and passwords.
* **Role Separation:** Distinct dashboards for **Managers** and **Cashiers** with specific permissions.

### 👤 Cashier Features
* **Product Catalog:** View all available cupcakes with details (Name, Flavor, Price, Category).
* **Search & Filter:** Filter products by categories like Classic, Seasonal, Gluten-Free, and Beverage.
* **Billing System:**
    * Add multiple items to a shopping cart with quantity checks.
    * Real-time calculation of **Subtotal**, **Tax (8%)**, and **Total**.
    * Apply custom discounts.
    * Process payments via **Cash**, **Card**, or **Mobile Pay**.
    * **Receipt Generation:** Generates a detailed text-based receipt for every transaction.

### 👔 Manager Features
* **Inherited Access:** Managers possess all Cashier capabilities (Inventory view, Billing, Search).
* **Staff Management:** Exclusive ability to create new **Cashier Accounts** with secure password validation.
* **User Oversight:** View a list of all existing system users and their roles.

## 🛠️ Technology Stack
* **Language:** Java (JDK 8+)
* **GUI Framework:** Java Swing / AWT (Custom styling with Flat UI elements)
* **Architecture:** MVC Pattern (Model-View-Controller separation)
* **Data Persistence:** File-based storage (Text/CSV files via `FileHandler`) for Users, Inventory, and Transactions.

## 📂 Key Components
* **`LoginForm`**: Entry point handling authentication and routing to appropriate dashboards.
* **`CashierDashboard`**: The main interface for daily operations and inventory viewing.
* **`ManagerDashboard`**: Extends `CashierDashboard` to add administrative tabs.
* **`BillingPanel`**: A comprehensive dialog for processing customer orders.

## ⚙️ Setup & Installation

1.  **Clone the Repository**
    ```bash
    git clone [https://github.com/yourusername/sweet-cupcake-shop.git](https://github.com/yourusername/sweet-cupcake-shop.git)
    ```
2.  **Open in IDE**
    * Open Apache NetBeans (or any Java IDE).
    * Import the project folder.
3.  **Build and Run**
    * Right-click the project -> `Clean and Build`.
    * Run `SweetCupcakeShop.java` (or `LoginForm.java` to start the app).

## 🔑 Default Credentials
Use these credentials to test the different roles upon first launch:

| Role | Username | Password |
| :--- | :--- | :--- |
| **Manager** | `manager` | `manager123` |
| **Cashier** | `cashier` | `cashier123` |

*(Credentials sourced from application footer)*

## 📸 Usage Guide
1.  **Login:** Enter valid credentials to access your specific dashboard.
2.  **Manager Dashboard:** Navigate to the "Manage Users" tab to add new staff members.
3.  **Billing:** Click "Open Billing System" to handle customer orders.
    * Select a product category or browse the list.
    * Select a cupcake, enter quantity, and click "Add to Cart".
    * Apply discounts if applicable.
    * Click "Process Payment" to finalize the order and enable the "Print Receipt" button.

---
*Developed by Dhakshina*
