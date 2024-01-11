import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.List;

/*
 Name : James Bailey
 Student ID : 23673840
 */

/**
 * {@code manageCustomersMenu} extends the {@code mainMenu} class and provides a menu for managing customer-related operations.
 * This classes functionality is very similar to the manageFoodProductsMenu, yet it's context of use is, of course, different.
 */
public class manageCustomersMenu extends mainMenu {

    /**
     * Manages customers by presenting a menu with various options such as listing, searching, adding, updating, and deleting customers.
     *
     * @throws SQLException If a SQL exception occurs during database operations.
     * @throws IOException  If an I/O exception occurs.
     */
    public void manageCustomers() throws SQLException, IOException {
        Scanner scan = new Scanner(System.in);
        customerDAO dao = new customerDAO();
        String customerSelection;

        do {
            // Display customer menu options
            System.out.println("CUSTOMER MENU");
            System.out.println("--------------");
            System.out.println("[1] List all Customers");
            System.out.println("[2] Search by Customer ID");
            System.out.println("[3] Add a new Customer");
            System.out.println("[4] Update a Customer by ID");
            System.out.println("[5] Delete a Customer by ID");
            System.out.println("[6] Exit to Main Menu");

            // Take user input
            customerSelection = scan.nextLine();

            // 6-branch switch for customer menu
            switch (customerSelection) {
                case "1":
                    // List all customers
                    List<customerConstructor> listAllCustomers = dao.selectAllCustomers();
                    System.out.println(listAllCustomers);
                    System.out.println();
                    break;

                case "2":
                    // Search by customer ID
                    System.out.println("\nEnter Customer ID");
                    int customerID = scan.nextInt();
                    scan.nextLine(); // Consume the newline character
                    dao.searchCustomerById(customerID);
                    break;

                case "3":
                    // Add a new customer
                    addCustomerTerminalDataInput addCustomerInputs = new addCustomerTerminalDataInput();
                    addCustomerInputs.addCustomer();
                    break;

                case "4":
                    // Update a customer by ID
                    System.out.println("\nEnter Customer ID");
                    int updateCustomerID = scan.nextInt();
                    customerConstructor existingCustomer = dao.customerExistsForUpdate(updateCustomerID);

                    if (existingCustomer != null) {
                        // The customer exists, call the CustomerUpdater to get updated information
                        customerConstructor updatedCustomer = customerUpdater.updateCustomerFields(existingCustomer);

                        // Call the updateCustomer method
                        dao.updateCustomer(updatedCustomer);

                        System.out.println("Customer updated successfully.");
                    } else {
                        System.out.println("Customer with ID " + updateCustomerID + " does not exist.");
                    }
                    break;

                case "5":
                    // Delete a customer by ID
                    System.out.println("Deletion Selected");
                    System.out.println("Please Enter Customer ID:");
                    customerID = scan.nextInt();
                    dao.deleteCustomerByID(customerID);
                    break;

                case "6":
                    System.out.println("Returning to Main Menu...");
                    break;
            }

        } while (!customerSelection.equals("6"));

        // Call the menuOptions method from the parent class
        menuOptions();

        // Close the scanner when done
        scan.close();
    }
}




