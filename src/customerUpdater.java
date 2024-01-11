import java.util.Scanner;

/*
 Name : James Bailey
 Student ID : 23673840
 */

/**
 * The class responsible for updating customer information interactively through the terminal.
 */
public class customerUpdater {
    /**
     * Updates the fields of an existing customer interactively.
     *
     * @param existingCustomer The existing customer with current information.
     * @return A new customerConstructor object with updated information.
     */

    public static customerConstructor updateCustomerFields(customerConstructor existingCustomer) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter updated Customer Name (press Enter to keep the same):");
        String updatedCustomerName = scan.nextLine();
        if (updatedCustomerName.isEmpty()) {
            updatedCustomerName = existingCustomer.getCustomerName();
        }

        System.out.println("Enter updated Business Name (press Enter to keep the same):");
        String updatedBusinessName = scan.nextLine();
        if (updatedBusinessName.isEmpty()) {
            updatedBusinessName = existingCustomer.getBusinessName();
        }

        System.out.println("Enter updated Telephone Number (press Enter to keep the same):");
        String updatedTelephoneNumber = scan.nextLine();
        if (updatedTelephoneNumber.isEmpty()) {
            updatedTelephoneNumber = String.valueOf(existingCustomer.getTelephoneNumber());
        }

        System.out.println("Enter updated Address Line 1 (press Enter to keep the same):");
        String updatedAddressLine1 = scan.nextLine();
        if (updatedAddressLine1.isEmpty()) {
            updatedAddressLine1 = existingCustomer.getAddressLine1();
        }

        System.out.println("Enter updated Address Line 2 (press Enter to keep the same):");
        String updatedAddressLine2 = scan.nextLine();
        if (updatedAddressLine2.isEmpty()) {
            updatedAddressLine2 = existingCustomer.getAddressLine2();
        }

        System.out.println("Enter updated Address Line 3 (press Enter to keep the same):");
        String updatedAddressLine3 = scan.nextLine();
        if (updatedAddressLine3.isEmpty()) {
            updatedAddressLine3 = existingCustomer.getAddressLine3();
        }

        System.out.println("Enter updated Country (press Enter to keep the same):");
        String updatedCountry = scan.nextLine();
        if (updatedCountry.isEmpty()) {
            updatedCountry = existingCustomer.getCountry();
        }

        System.out.println("Enter updated Postcode (press Enter to keep the same):");
        String updatedPostcode = scan.nextLine();
        if (updatedPostcode.isEmpty()) {
            updatedPostcode = existingCustomer.getPostcode();
        }

        // Create and return a new customerConstructor object with updated information
        return new customerConstructor(
                existingCustomer.getCustomerID(), updatedCustomerName, updatedBusinessName,
                updatedTelephoneNumber, updatedAddressLine1, updatedAddressLine2,
                updatedAddressLine3, updatedCountry, updatedPostcode);
    }
}
