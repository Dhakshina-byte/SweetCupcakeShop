# Sweet Cupcake Shop - Point of Sale System

## 🎉 What's New - Enhanced Features

### ✅ Billing System for Both Cashiers and Managers
Both Cashiers and Managers can now process customer transactions using the new **Point of Sale (POS)** system!

### ✅ Product Categories Expanded
The system now supports:
- **CLASSIC** - Traditional cupcake flavors
- **SEASONAL** - Special seasonal items  
- **CUSTOM** - Custom orders for special occasions
- **GLUTEN_FREE** - Gluten-free options
- **BEVERAGE** - Drinks and beverages
- **COMBO** - Dessert combo deals

### ✅ Complete Transaction Management
- Add multiple items to cart
- Apply discounts
- Process payments (Cash, Card, Mobile Pay)
- Generate professional receipts
- Track transaction history

---

## 🐛 Fixing the NetBeans Form Error

### The Problem
You're seeing this error because NetBeans GUI Builder expects special comment markers in the Java source files. The error message states:

> "The form seems to be corrupted. The GUI builder is not able to find the sections with the generated code. The special comments that denote the start and the end of these sections were removed or modified."

### The Solution

**Option 1: Continue without GUI Builder (Recommended)**
The application works perfectly fine! The forms are already properly coded. You just won't be able to edit them visually in NetBeans GUI Builder. You can:
- Run the application normally
- Edit code manually in the text editor
- All functionality works as expected

**Option 2: Regenerate Forms (Advanced)**
If you need to use the GUI Builder:

1. **Backup your project first!**

2. **For each .java file that has a .form file:**
   - The `// <editor-fold>` and `// </editor-fold>` markers are present
   - NetBeans may need the exact format it expects
   
3. **Alternative:** Use the provided forms as-is and edit manually

4. **Best Practice:** Modern Java development often skips GUI builders in favor of manual code, which gives you more control

---

## 🚀 How to Run the Application

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- NetBeans IDE (or any Java IDE)
- Or compile from command line

### Running from NetBeans
1. Open the project in NetBeans
2. Right-click on the project → "Run"
3. The login window will appear

### Running from Command Line
```bash
# Navigate to the project directory
cd SweetCupcakeShop

# Compile
javac -d bin src/sweetcupcakeshop/**/*.java

# Run
java -cp bin sweetcupcakeshop.view.LoginForm
```

### Default Login Credentials
- **Manager Account:**
  - Username: `manager`
  - Password: `manager123`
  
- **Cashier Account:**
  - Username: `cashier`
  - Password: `cashier123`

---

## 📖 Using the Billing System

### For Cashiers and Managers

1. **Login** with your credentials

2. **Navigate to the Billing Tab**
   - Click on the "💰 Billing" tab
   - Click "Open Billing System"

3. **Select Products**
   - Filter by category (ALL, CLASSIC, SEASONAL, etc.)
   - Select a product from the table
   - Enter quantity
   - Click "Add to Cart"

4. **Manage Cart**
   - View all items in the current order
   - Remove items if needed
   - Clear entire cart if necessary

5. **Enter Customer Information**
   - Enter customer name (or leave as "Walk-in")

6. **Apply Discount (Optional)**
   - Enter discount amount
   - Click "Apply"

7. **Process Payment**
   - Select payment method (Cash, Card, or Mobile Pay)
   - Click "Process Payment"
   - Transaction is saved automatically

8. **Print Receipt**
   - After payment, click "Print Receipt"
   - View/print the formatted receipt
   - Start a new transaction

---

## 🗂️ Project Structure

```
SweetCupcakeShop/
├── src/
│   └── sweetcupcakeshop/
│       ├── model/
│       │   ├── User.java          (Abstract base class)
│       │   ├── Manager.java       (Extends User)
│       │   ├── Cashier.java       (Extends User)
│       │   ├── Cupcake.java       (Product model)
│       │   ├── OrderItem.java     (NEW - Item in cart)
│       │   └── Transaction.java   (NEW - Complete transaction)
│       ├── view/
│       │   ├── LoginForm.java     (Login screen)
│       │   ├── CashierDashboard.java  (Cashier interface)
│       │   ├── ManagerDashboard.java  (Manager interface)
│       │   └── BillingPanel.java  (NEW - POS System)
│       ├── util/
│       │   └── FileHandler.java   (Data persistence)
│       └── SweetCupcakeShop.java  (Main class)
├── data/
│   ├── users.txt              (User accounts)
│   ├── cupcakes.txt           (Product inventory)
│   └── transactions.txt       (NEW - Transaction history)
└── nbproject/
```

---

## 🎨 OOP Concepts Demonstrated

### Encapsulation
- Private fields with public getters/setters
- Data hiding in model classes
- Protected access for inheritance

### Inheritance
- `User` → `Manager` and `Cashier`
- `CashierDashboard` → `ManagerDashboard`
- Code reuse and extension

### Polymorphism
- `User` reference can hold `Manager` or `Cashier`
- Method overriding (toString, etc.)
- Interface-based design

### Abstraction
- Abstract concepts (User, Transaction)
- Clean separation of concerns
- Model-View architecture

---

## 💾 Data Persistence

All data is stored in plain text files in the `data/` directory:

### users.txt
Format: `username,password,fullName,role`
```
manager,manager123,Admin Manager,MANAGER
cashier,cashier123,Jane Cashier,CASHIER
```

### cupcakes.txt
Format: `id|name|flavor|category|price|description`
```
C001|Classic Vanilla|Vanilla|CLASSIC|3.50|Moist vanilla cupcake
C011|Hot Cocoa|Chocolate|BEVERAGE|3.00|Rich hot chocolate drink
C013|Cupcake & Coffee Duo|Mixed|COMBO|6.50|Any cupcake plus coffee
```

### transactions.txt (NEW)
Format: `txnId|cashier|customer|timestamp|payment|subtotal|tax|discount|total`
```
TXN1001|Jane Cashier|John Doe|2024-02-10 14:30:00|CASH|15.00|1.20|0.00|16.20
```

---

## 🆕 New Features in Detail

### Transaction Class
- Automatic transaction ID generation
- Calculates subtotal, tax (8%), and total
- Supports discount application
- Generates formatted receipts
- Multiple payment methods

### OrderItem Class
- Links products to quantities
- Calculates subtotals
- Clean object composition

### BillingPanel
- Full-featured POS interface
- Product catalog with filtering
- Shopping cart management
- Payment processing
- Receipt generation
- Transaction tracking

### Enhanced FileHandler
- Transaction persistence
- Get cupcake by ID
- Transaction history retrieval

---

## 🔧 Troubleshooting

### "Form corrupted" error
- **Solution:** This is a GUI Builder warning. The application runs fine! Just click "OK" and edit code manually if needed.

### Data files not created
- **Solution:** Run the application once. The `data/` directory and files are created automatically on first run.

### Login fails
- **Solution:** Use default credentials: `manager/manager123` or `cashier/cashier123`

### Billing tab not showing
- **Solution:** Make sure you compiled all new files (OrderItem, Transaction, BillingPanel)

---

## 📝 Sample Usage Scenarios

### Scenario 1: Simple Sale
1. Login as cashier
2. Open Billing System
3. Add 2x Classic Vanilla cupcakes
4. Add 1x Hot Cocoa
5. Process payment with Cash
6. Print receipt

### Scenario 2: Custom Order with Discount
1. Login as cashier/manager  
2. Open Billing System
3. Add 4x Wedding Elegance cupcakes
4. Add 2x Iced Vanilla Latte
5. Enter customer name: "Smith Wedding"
6. Apply $5.00 discount
7. Select Card payment
8. Process and print receipt

### Scenario 3: Combo Deal
1. Open Billing System
2. Add "Cupcake & Coffee Duo" combo
3. Add extra items if needed
4. Process transaction

---

## 👨‍💻 Development Notes

### Adding New Products
1. Login as Manager or Cashier
2. Go to "Add Cupcake" tab
3. Fill in product details
4. Select appropriate category
5. Click "Add Cupcake"

### Creating New User Accounts
1. Login as Manager
2. Go to "Manage Users" tab
3. Fill in user details
4. Click "Create User"

### Viewing Transactions
- All transactions are saved to `data/transactions.txt`
- Can be viewed/analyzed later for reports

---

## 🎯 Future Enhancements (Ideas)

- [ ] Inventory management (stock tracking)
- [ ] Sales reports and analytics
- [ ] Customer loyalty program
- [ ] Receipt email functionality
- [ ] Database integration (SQL)
- [ ] Barcode scanning support
- [ ] Multi-store support

---

## 📄 License

This project is for educational purposes, demonstrating Object-Oriented Programming concepts in Java.

---

## 🙏 Acknowledgments

Developed to demonstrate:
- Clean OOP design in Java
- Swing GUI development
- File-based data persistence
- Practical business application
- Point of Sale system implementation

---

**Enjoy using The Sweet Cupcake Shop POS System! 🧁**
