/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/

import java.util.HashMap;
import java.util.Scanner;

// Product class
class Product {
    private String name;
    private String barcode;
    private int quantity;
    private double price;

    public Product(String name, String barcode, int quantity, double price) {
        this.name = name;
        this.barcode = barcode;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() { return name; }
    public String getBarcode() { return barcode; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

// Inventory management class
class InventoryManager {
    private HashMap<String, Product> inventory = new HashMap<>();

    public void addProduct(Product product) {
        inventory.put(product.getBarcode(), product);
    }

    public Product getProductByBarcode(String barcode) {
        return inventory.get(barcode);
    }

    public void updateQuantity(String barcode, int change) {
        Product product = inventory.get(barcode);
        if (product != null) {
            int newQty = product.getQuantity() + change;
            if (newQty >= 0) {
                product.setQuantity(newQty);
            } else {
                System.out.println("Insufficient stock to remove that quantity.");
            }
        }
    }

    public void displayInventory() {
        System.out.println("\nCurrent Inventory:");
        System.out.println("Barcode\t\tName\t\tQty\tPrice");
        System.out.println("-------------------------------------------------");
        for (Product p : inventory.values()) {
            System.out.printf("%s\t%s\t\t%d\t%.2f\n",
                p.getBarcode(), p.getName(), p.getQuantity(), p.getPrice());
        }
        System.out.println();
    }
}

// Main class
public class InventorySystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InventoryManager manager = new InventoryManager();

        // Sample products
        manager.addProduct(new Product("Mouse", "111111", 15, 25.50));
        manager.addProduct(new Product("Keyboard", "222222", 10, 45.00));
        manager.addProduct(new Product("Monitor", "333333", 5, 150.00));

        while (true) {
            System.out.println("\n==== Inventory System ====");
            System.out.println("1. Scan Product (Sell)");
            System.out.println("2. Restock Product");
            System.out.println("3. View Inventory");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1: // Sell
                    System.out.print("Scan Barcode: ");
                    String barcode = scanner.nextLine();
                    Product p = manager.getProductByBarcode(barcode);
                    if (p != null) {
                        System.out.println("Product: " + p.getName());
                        System.out.print("Enter quantity to sell: ");
                        int qty = Integer.parseInt(scanner.nextLine());
                        manager.updateQuantity(barcode, -qty);
                     } else {
                        System.out.println("Product not found!");
                    }
                    break;

                case 2: // Restock
                    System.out.print("Enter Barcode: ");
                    String restockBarcode = scanner.nextLine();
                    Product restockProduct = manager.getProductByBarcode(restockBarcode);
                    if (restockProduct != null) {
                        System.out.print("Enter quantity to add: ");
                        int restockQty = Integer.parseInt(scanner.nextLine());
                        manager.updateQuantity(restockBarcode, restockQty);
                    } else {
                        System.out.println("Product not found!");
                    }
                    break;

                case 3: // Display inventory
                    manager.displayInventory();
                    break;

                case 4: // Exit
                    System.out.println("Exiting system...");
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
