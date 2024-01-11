import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

/*
 Name : James Bailey
 Student ID : 23673840
 */

/**
 * The {@code mainMenu} class provides a text-based menu system for users to interact with different features of the application.
 * It serves as the central hub for navigating through the various functionalities, including managing food products, customers,
 * and interacting with the database via a web server. This is the first thing the user will see in the console on startup.
 *
 */
public class mainMenu {

    /**
     * Displays the main menu options and allows users to navigate through different functionalities of the application.
     *
     * @throws SQLException If a SQL exception occurs during database operations.
     * @throws IOException  If an I/O exception occurs.
     */
    public void menuOptions() throws SQLException, IOException {
        Scanner scan = new Scanner(System.in);
        String selection;

        do {
            // Display main menu options
            System.out.println("MAIN MENU");
            System.out.println("-----------");
            System.out.println("[1] Manage Food Products");
            System.out.println("[2] Manage Customers");
            System.out.println("[3] Manage DB via a Webserver");
            System.out.println("[4] Exit");

            // Take user input
            selection = scan.nextLine();

            // 3-branch switch for main menu
            switch (selection) {
                case "1":
                    // Navigate to the food products management menu
                    manageFoodProductsMenu foodProductsMenu = new manageFoodProductsMenu();
                    foodProductsMenu.manageFoodProducts();
                    break;

                case "2":
                    // Navigate to the customers management menu
                    manageCustomersMenu customersMenu = new manageCustomersMenu();
                    customersMenu.manageCustomers();
                    break;

                case "3":
                    // Start the web server for managing the database
                    try {
                        httpServer httpServer = new httpServer();
                        httpServer.startHttpServer();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;

                case "4":
                    // Exit the program
                    System.out.println("Exiting the Program");
            }

            System.out.println();

        } while (!selection.equals("4"));

        // Close the scanner when done
        scan.close();
    }
}