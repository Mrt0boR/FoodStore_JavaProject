import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.List;

/*
 Name : James Bailey
 Student ID : 23673840
 */

/**
 * {@code manageFoodProductsMenu} extends the {@code mainMenu} class and provides a menu for managing food products.
 * This is a child of the parent Menu class to introduct the functionality of returning to the Main Menu without
 * instantiating a new menu object.
 */
public class manageFoodProductsMenu extends mainMenu{

    /**
     * Manages food products by presenting a menu with various options such as listing, searching, adding, updating, and deleting products.
     *
     * @throws SQLException If a SQL exception occurs during database operations.
     * @throws IOException  If an I/O (Input/Output) exception occurs.
     */
    public void manageFoodProducts() throws SQLException, IOException {
        Scanner scan = new Scanner(System.in);
        String selection;


        do {

            System.out.println("FOOD STORE");
            System.out.println("-----------");

            System.out.println("[1] List all Products");

            System.out.println("[2] Search by Product ID");

            System.out.println("[3] Add a new Product");

            System.out.println("[4] Update a Product by ID");

            System.out.println("[5] Delete a Product by ID");

            System.out.println("[6] Return to Main Menu");

            selection = scan.nextLine();

            //initialise DAO for interface with each switch statement
            foodProductDAO dao = new foodProductDAO();
            // 5 branch switch
            switch (selection) {
                case "1":
                    List<shopItemConstructor> selectallitems = dao.selectallstatement();
                    System.out.println(selectallitems);
                    System.out.println();
                    break;

                case "2":

                    System.out.println("\nEnter Product ID");
                    int productID = scan.nextInt();
                    dao.productSearch(productID);


                    break;

                case "3":
                    addItemTerminalDataInput addItemData = new addItemTerminalDataInput();
                    addItemData.addItem();
                    break;

                case "4":
                    System.out.println("\nEnter Product ID");
                    int updateproductID = scan.nextInt();
                    shopItemConstructor existingProduct = dao.productExistsForUpdate(updateproductID);

                    if (existingProduct != null) {

                        System.out.println("Enter updated SKU:");
                        String updatedSKU = scan.next();
                        System.out.println("Enter updated Category:");
                        String updatedCategory = scan.next();
                        System.out.println("Enter updated Description:");
                        String updatedDescription = scan.next();
                        System.out.println("Enter updated Price:");
                        int updatedPrice = scan.nextInt();

                        // Create a new shopItem object with updated information
                        shopItemConstructor updatedItem = new shopItemConstructor(existingProduct.getID(), updatedSKU, updatedCategory, updatedDescription, updatedPrice);

                        // Call the updateProduct method from the DAO instantiated atop the switch statement.
                        dao.updateProduct(updatedItem);

                        System.out.println("Product updated successfully.");
                    } else {
                        System.out.println("Product with ID " + updateproductID + " does not exist.");
                    }

                    break;

                case "5":
                    System.out.println("\nEnter Product ID to delete:");
                    int deleteProductID = scan.nextInt();
                    boolean deleteResult = dao.deleteProductByID(deleteProductID);

                    if (deleteResult) {
                        System.out.println("Product with ID " + deleteProductID + " deleted successfully.");
                    } else {
                        System.out.println("Failed to delete product with ID " + deleteProductID + ".");
                    }
                    break;

            }

        } while (!selection.equals("6"));

        menuOptions();

        // Close the scanner when done
        scan.close();
    }
}