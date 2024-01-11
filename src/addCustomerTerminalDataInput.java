/*
 Name : James Bailey
 Student ID : 23673840
 */

import java.util.Scanner;

/**
 * This class handles the input of customer data through the terminal and adds a new customer to the database.It
 * is the customer version of the {@link addCustomerTerminalDataInput}
 */

public class addCustomerTerminalDataInput {

    /**
     * This method collects user input for customer details and adds a new customer to the database.
     */

    public void addCustomer() {
        Scanner scan = new Scanner(System.in);

        System.out.println("\nEnter Customer ID:");
        int customerID = scan.nextInt();
        scan.nextLine();

        System.out.println("Enter Customer Name:");
        String customerName = scan.nextLine();

        System.out.println("Enter Business Name:");
        String businessName = scan.nextLine();

        System.out.println("Enter Telephone Number:");
        String telephoneNumber = scan.nextLine();

        System.out.println("Enter Address Line 1:");
        String addressLine1 = scan.nextLine();

        System.out.println("Enter Address Line 2:");
        String addressLine2 = scan.nextLine();

        System.out.println("Enter Address Line 3:");
        String addressLine3 = scan.nextLine();

        System.out.println("Enter Country:");
        String country = scan.nextLine();

        System.out.println("Enter Postcode:");
        String postcode = scan.nextLine();

        // Create a new customer with the user-input attributes
        customerConstructor newCustomer = new customerConstructor(
                customerID, customerName, businessName, telephoneNumber,
                addressLine1, addressLine2, addressLine3, country, postcode);

        // Call the method in customerDAO to add the new customer to the database
        customerDAO dao = new customerDAO();
        try {
            boolean added = dao.addCustomer(newCustomer);

            if (added) {
                System.out.println("Customer added successfully!");
            } else {
                System.out.println("Failed to add the customer.");
            }
        } catch (Exception e) {
            System.out.println("Error adding customer: " + e.getMessage());
        }
    }
}
