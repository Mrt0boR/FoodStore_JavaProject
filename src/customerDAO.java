import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
 Name : James Bailey
 Student ID : 23673840
 */

/*

IF THE JDBC DRIVER IS NOT RECOGNISED BY INTELLIJ go to:
File > Project Structure > Libraries > '+' button for "new project library" > click 'Java' in the Dropdown menu
then navigate to this project file > src > and then click 'sqlite-jdbc-3.40.0.0.jar.

I have written code in Util.jar so your connection
link is automatically assigned to the connection variable.

 */
/**
 * A Data Access Object (DAO) responsible for managing customer data in the database.
 */
public class customerDAO {

    /**
     * Establishes and returns a connection to the SQLite database.
     *
     * @return A connection to the SQLite database.
     * @throws SQLException If there's an error accessing the database.
     */

    private Connection sqlInit() throws SQLException {
        String userDir = System.getProperty("user.dir");
        System.out.println("Current user directory: " + userDir);
        String dbPath = userDir + "/src/foodstore.sqlite";
        String connectionURL = "jdbc:sqlite:" + dbPath;
        return DriverManager.getConnection(connectionURL);
    }

    /**
     * Retrieves a list of all customers from the database.
     *
     * @return A list of customerConstructor objects representing the customers in the database.
     */

    public List<customerConstructor> selectAllCustomers() {
        List<customerConstructor> customersList = new ArrayList<>();

        String selectQuery = "SELECT * FROM customers";

        try (Connection dbConnection = sqlInit();
             PreparedStatement selectAllStatement = dbConnection.prepareStatement(selectQuery);
             ResultSet resultSet = selectAllStatement.executeQuery()) {

            while (resultSet.next()) {
                int customerID = resultSet.getInt("ID");
                String customerName = resultSet.getString("Customer Name");
                String businessName = resultSet.getString("Business Name");
                String telephoneNumber = resultSet.getString("Telephone Number");
                String addressLine1 = resultSet.getString("Address Line 1");
                String addressLine2 = resultSet.getString("Address Line 2");
                String addressLine3 = resultSet.getString("Address Line 3");
                String country = resultSet.getString("Country");
                String postcode = resultSet.getString("Post Code");

                customerConstructor customer = new customerConstructor(
                        customerID, customerName, businessName, telephoneNumber,
                        addressLine1, addressLine2, addressLine3, country, postcode);

                customersList.add(customer);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving customers.", e);
        }

        return customersList;
    }

    /**
     * Searches for a customer by their ID in the database.
     *
     * @param customerId The ID of the customer to search for.
     * @return A customerConstructor object representing the found customer, or null if not found.
     * @throws SQLException If there's an error accessing the database.
     */

    public customerConstructor searchCustomerById(int customerId) throws SQLException {
        String idSearchStatement = "SELECT * FROM customers WHERE ID = " + customerId;
        customerConstructor customer = null;

        try (Connection dbconnection = sqlInit();
             Statement statement = dbconnection.createStatement();
             ResultSet resultSet = statement.executeQuery(idSearchStatement)) {

            while (resultSet.next()) {
                int ID = resultSet.getInt("ID");
                String customerName = resultSet.getString("Customer Name");
                String businessName = resultSet.getString("Business Name");
                String telephoneNumber = resultSet.getString("Telephone Number");
                String addressLine1 = resultSet.getString("Address Line 1");
                String addressLine2 = resultSet.getString("Address Line 2");
                String addressLine3 = resultSet.getString("Address Line 3");
                String country = resultSet.getString("Country");
                String postcode = resultSet.getString("Post Code");

                customer = new customerConstructor(
                        ID, customerName, businessName, telephoneNumber,
                        addressLine1, addressLine2, addressLine3, country, postcode);

                System.out.println(customer);
                return customer;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    /**
     * Adds a new customer to the database.
     *
     * @param customer The customerConstructor object representing the new customer.
     * @return true if the customer is successfully added, false otherwise.
     * @throws SQLException If there's an error accessing the database.
     */

    public boolean addCustomer(customerConstructor customer) throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        String insertStatement = "INSERT INTO customers (ID, [Customer Name], [Business Name], [Telephone Number], " +
                "[Address Line 1], [Address Line 2], [Address Line 3], Country, [Post Code]) VALUES " +
                "(" + customer.getCustomerID() + ", '" + customer.getCustomerName() + "','" +
                customer.getBusinessName() + "','" + customer.getTelephoneNumber() + "','" +
                customer.getAddressLine1() + "','" + customer.getAddressLine2() + "','" +
                customer.getAddressLine3() + "','" + customer.getCountry() + "','" +
                customer.getPostcode() + "');";
        boolean ok = true;
        try {
            dbConnection = sqlInit();

            statement = dbConnection.createStatement();

            statement.executeUpdate(insertStatement);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            if (statement != null)
                statement.close();
            if (dbConnection != null)
                dbConnection.close();
        }
        return ok;
    }

    /**
     * Checks if a customer with the given ID exists in the database and retrieves their information for updating.
     *
     * @param customerID The ID of the customer to check for existence.
     * @return A customerConstructor object representing the existing customer, or null if not found.
     * @throws SQLException If there's an error accessing the database.
     */
    public customerConstructor customerExistsForUpdate(int customerID) throws SQLException {
        String idSearchStatement = "SELECT * FROM customers WHERE ID = " + customerID;

        Connection dbconnection = null;
        PreparedStatement idSearch = null;
        ResultSet resultSet = null;

        try {
            dbconnection = sqlInit();

            idSearch = dbconnection.prepareStatement(idSearchStatement);

            resultSet = idSearch.executeQuery();

            while (resultSet.next()) {
                int ID = resultSet.getInt("ID");
                String customerName = resultSet.getString("Customer Name");
                String businessName = resultSet.getString("Business Name");
                String telephoneNumber = resultSet.getString("Telephone Number");
                String addressLine1 = resultSet.getString("Address Line 1");
                String addressLine2 = resultSet.getString("Address Line 2");
                String addressLine3 = resultSet.getString("Address Line 3");
                String country = resultSet.getString("Country");
                String postcode = resultSet.getString("Post Code");

                customerConstructor customerResult = new customerConstructor(
                        ID, customerName, businessName, telephoneNumber,
                        addressLine1, addressLine2, addressLine3, country, postcode);

                System.out.println("\nThe Customer Exists. Here is " + customerID + "'s current status\n");
                System.out.println(customerResult);

                return customerResult;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (resultSet != null) resultSet.close();
            if (idSearch != null) idSearch.close();
            if (dbconnection != null) dbconnection.close();
        }

        return null;
    }

    /**
     * Updates an existing customer's information in the database.
     *
     * @param updatedCustomer The customerConstructor object representing the updated customer information.
     * @return true if the update is successful, false otherwise.
     */

    public boolean updateCustomer(customerConstructor updatedCustomer) {
        Connection dbConnection = null;
        Statement statement = null;

        String query = "UPDATE customers SET " +
                "\"Customer Name\" = '" + updatedCustomer.getCustomerName() + "'," +
                "\"Business Name\" = '" + updatedCustomer.getBusinessName() + "'," +
                "\"Telephone Number\" = '" + updatedCustomer.getTelephoneNumber() + "'," +
                "\"Address Line 1\" = '" + updatedCustomer.getAddressLine1() + "'," +
                "\"Address Line 2\" = '" + updatedCustomer.getAddressLine2() + "'," +
                "\"Address Line 3\" = '" + updatedCustomer.getAddressLine3() + "'," +
                "\"Country\" = '" + updatedCustomer.getCountry() + "'," +
                "\"Post Code\" = '" + updatedCustomer.getPostcode() + "'" +
                " WHERE ID = " + updatedCustomer.getCustomerID() + ";";

        try {
            dbConnection = sqlInit();
            statement = dbConnection.createStatement();
            System.out.println(query);
            // execute SQL update
            statement.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (dbConnection != null) {
                    dbConnection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    /**
     * Deletes a customer from the database by their ID.
     *
     * @param customerID The ID of the customer to be deleted.
     * @return true if the deletion is successful, false otherwise.
     * @throws SQLException If there's an error accessing the database.
     */


    public boolean deleteCustomerByID(int customerID) throws SQLException {
        Connection dbconnection = null;
        Statement statement = null;


        String deleteStatement = "DELETE FROM customers WHERE ID = " + customerID;
        boolean ok = true;

        try {
            dbconnection = sqlInit();
            statement = dbconnection.createStatement();
            statement.executeUpdate(deleteStatement);

            System.out.println("Customer with ID " + customerID + " deleted successfully.");

        } catch (SQLException e) {
            System.out.println("Error deleting customer: " + e.getMessage());
            ok = false;
        } finally {
            if (statement != null) statement.close();
            if (dbconnection != null) dbconnection.close();
        }

        return ok;
    }
}
