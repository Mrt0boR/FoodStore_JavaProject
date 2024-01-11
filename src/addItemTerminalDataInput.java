/*
 Name : James Bailey
 Student ID : 23673840
 */

import java.util.Scanner;

/**
 * The {@code addItemTerminalDataInput} class handles the addition of a new product through
 * terminal-based user input and interaction.
 */
public class addItemTerminalDataInput {
    manageFoodProductsMenu foodmenu = new manageFoodProductsMenu();


    /**
     * Takes user input from the terminal using the Scanner utility to add a new product and store it in the database.
     * this includes the assignment of a Primary Key ID
     */
    public void addItem() {
        // Create a Scanner object to read user input from the terminal
        Scanner scan = new Scanner(System.in);

        // Prompt the user to enter product details
        System.out.println("\nEnter Product ID:");
        int id = scan.nextInt();
        scan.nextLine();

        System.out.println("Enter SKU:");
        String sku = scan.nextLine();

        System.out.println("Enter Category:");
        String category = scan.nextLine();

        System.out.println("Enter Description:");
        String description = scan.nextLine();

        System.out.println("Enter Price:");
        int price = scan.nextInt();

        // Create a new shopItem with the user-input attributes
        shopItemConstructor newItem = new shopItemConstructor(id, sku, category, description, price);

        // Call the method in SQLDAO to add the new product to the database
        foodProductDAO dao = new foodProductDAO();
        try {
            boolean added = dao.addProduct(newItem);

            // Display success or failure message based on the result of adding the product
            if (added) {
                System.out.println("Product added successfully!");
                foodmenu.manageFoodProducts();

            } else {
                System.out.println("Failed to add the product.");
            }
        } catch (Exception e) {
            // Display an error message if an exception occurs during the process
            System.out.println("Error adding product: " + e.getMessage());
        } finally {
            // Close the Scanner to prevent resource leaks
            scan.close();
        }
    }
}
