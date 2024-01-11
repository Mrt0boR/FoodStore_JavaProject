import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/*

 Name : James Bailey
 Student ID : 23673840

IF THE JDBC DRIVER IS NOT RECOGNISED BY INTELLIJ go to:
File > Project Structure > Libraries > '+' button for "new project library" > click 'Java' in the Dropdown menu
then navigate to this project file > src > and then click 'sqlite-jdbc-3.40.0.0.jar.

I have written code in Util.jar so your connection
link is automatically assigned to the connection variable

 */

/**
 * The {@code foodProductDAO} class provides data access methods for interacting with the shopItems table in the database.
 * This class handles database connections, CRUD operations, and retrieval of food product information.
 */
public class foodProductDAO {

    /**
     * Initializes the SQL connection to the database.
     *
     * @return A {@code Connection} object representing the database connection.
     * @throws SQLException If a SQL exception occurs while establishing the connection.
     */

    private Connection sqlInit() throws SQLException {
        String userDir = System.getProperty("user.dir");
        System.out.println("Current user directory: " + userDir);

        String dbPath = userDir + "/src/foodstore.sqlite";
        String connectionURL = "jdbc:sqlite:" + dbPath;
        return DriverManager.getConnection(connectionURL);
    }


    /**
     * Retrieves a list of all food products from the database.
     *
     * @return A {@code List} of {@code shopItemConstructor} objects representing all food products.
     * The list is populated by executing a SQL SELECT query.
     */

    //display all food products
    public List<shopItemConstructor> selectallstatement() {

        /*
        initialise an array list so that the items can be
        logged into the system memory and outputted when the
        list is converted to a string and called upon in the Main method
        */


        List<shopItemConstructor> shopItemsArrayListConstructor = new ArrayList<>();

        //SQL statement for the selecallstatement method
        String select = "SELECT * FROM shopItems";

        //declaring objects to the Java.sql methods
        Connection dbconnection;
        PreparedStatement selectallstatement;
        ResultSet resultSet;


        try {
            //db connection object is = to the conenction establishment in the sqlInit method
            dbconnection = sqlInit();

            //preparedsattement object is linked to the prepaered select all sql statement.
            selectallstatement = dbconnection.prepareStatement(select);
            resultSet = selectallstatement.executeQuery();

            /*
            the while loop iterates through the result set creating strings associated with each item
            using the framework from the shopItemsconstructor class
             */

            while (resultSet.next()) {
                int ID = resultSet.getInt("ID");
                String SKU = resultSet.getString("SKU");
                String Category = resultSet.getString("Category");
                String Description = resultSet.getString("Description");
                int Price = resultSet.getInt("Price");

                shopItemConstructor selectallshopitems = new shopItemConstructor(ID, SKU, Category, Description, Price);

                shopItemsArrayListConstructor.add(selectallshopitems);
            }
            resultSet.close();
            selectallstatement.close();
            resultSet.close();

        } catch (SQLException e) {
            throw new RuntimeException("connection error", e);
        }
        return shopItemsArrayListConstructor;
    }

    /**
     * Searches for a specific product by its ID in the database.
     *
     * @param productID The ID of the product to search for.
     * @return A {@code shopItemConstructor} object representing the found product, or null if not found.
     * @throws SQLException If a SQL exception occurs during the search.
     */
    public shopItemConstructor productSearch(int productID) throws SQLException {
        shopItemConstructor shopItemConstructor = null;
        Connection dbconnection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String query = "SELECT * FROM shopItems WHERE ID =" + productID;

        try {
            dbconnection = sqlInit();
            statement = dbconnection.createStatement();
            System.out.println("DBQuery: " + query);
            // execute SQL query
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int ID = resultSet.getInt("ID");
                String SKU = resultSet.getString("SKU");
                String Category = resultSet.getString("Category");
                String Description = resultSet.getString("Description");
                int Price = resultSet.getInt("Price");

                shopItemConstructor = new shopItemConstructor(ID, SKU, Category, Description, Price);
                System.out.println(shopItemConstructor);

                return shopItemConstructor;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // Close resources in the reverse order of their creation
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace(); // Handle the exception appropriately
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace(); // Handle the exception appropriately
                }
            }
            if (dbconnection != null) {
                try {
                    dbconnection.close();
                } catch (SQLException e) {
                    e.printStackTrace(); // Handle the exception appropriately
                }
            }
        }

        return shopItemConstructor;
    }

    /**
     * Adds a new product to the database.
     *
     * @param item The {@code shopItemConstructor} object representing the product to be added.
     * @return True if the product is added successfully, false otherwise.
     * @throws SQLException If a SQL exception occurs during the database update.
     */

    public boolean addProduct(shopItemConstructor item) throws SQLException {

        Connection dbconnection = null;

        Statement statement = null;


        String insertStatement = "INSERT INTO shopItems (ID, SKU, Category, Description, Price) VALUES " +
                "(" + item.getID() + ", '" + item.getSKU() + "','" + item.getCategory() + "','" + item.getDescription() + "'," + item.getPrice() + ");";

        boolean ok = true;

        try {

            dbconnection = sqlInit();

            statement = dbconnection.createStatement();

            //execute insert statement
            statement.executeUpdate(insertStatement);


        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            if (statement != null)
                statement.close();
        }
        if (dbconnection != null) {
            dbconnection.close();
        }

        return ok;
    }

    /**
     * Checks if a product with a given ID exists in the database before updating.
     *
     * @param productID The ID of the product to check.
     * @return A {@code shopItemConstructor} object representing the existing product or null if not found.
     * @throws SQLException If a SQL exception occurs during the database query.
     */

    public shopItemConstructor productExistsForUpdate(int productID) throws SQLException {
        String idSearchStatement = "SELECT * FROM shopItems WHERE ID = " + productID;

        // declaring objects to the Java.sql methods
        Connection dbconnection = null;
        PreparedStatement idSearch = null;
        ResultSet resultSet = null;

        try {
            // sql to handle search
            dbconnection = sqlInit();

            idSearch = dbconnection.prepareStatement(idSearchStatement);

            resultSet = idSearch.executeQuery();

            while (resultSet.next()) {
                int ID = resultSet.getInt("ID");
                String SKU = resultSet.getString("SKU");
                String Category = resultSet.getString("Category");
                String Description = resultSet.getString("Description");
                int Price = resultSet.getInt("Price");

                shopItemConstructor shopItemConstructorResult = new shopItemConstructor(ID, SKU, Category, Description, Price);

                System.out.println("\nThe Shop Item Exists. Here is " + productID + "'s current status\n");
                System.out.println(shopItemConstructorResult);

                return shopItemConstructorResult;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // Close resources in the reverse order of their creation
            if (resultSet != null) resultSet.close();
            if (idSearch != null) idSearch.close();
            if (dbconnection != null) dbconnection.close();
        }

        return null;
    }

    /**
     * Updates an existing product in the database.
     *
     * @param updatedItem The {@code shopItemConstructor} object representing the updated product information.
     * @return True if the product is updated successfully, false otherwise.
     */

    public boolean updateProduct(shopItemConstructor updatedItem) {
        Connection dbConnection = null;
        Statement statement = null;

        String query = "UPDATE shopItems SET " +
                "SKU = '" + updatedItem.getSKU() + "'," +
                "Category = '" + updatedItem.getCategory() + "'," +
                "Description = '" + updatedItem.getDescription() + "'," +
                "Price = " + updatedItem.getPrice() +
                " WHERE ID = " + updatedItem.getID() + ";";

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
     * Deletes a product from the database based on its ID.
     *
     * @param productID The ID of the product to be deleted.
     * @return True if the product is deleted successfully, false otherwise.
     * @throws SQLException If a SQL exception occurs during the database update.
     */

    public boolean deleteProductByID(int productID) throws SQLException {
        Connection dbconnection = null;
        Statement statement = null;

        String deleteStatement = "DELETE FROM shopItems WHERE ID = " + productID;
        boolean ok = true;

        try {
            dbconnection = sqlInit();
            statement = dbconnection.createStatement();

            // Execute the delete statement
            statement.executeUpdate(deleteStatement);


        } catch (SQLException e) {
            System.out.println(e.getMessage());
            ok = false;
        } finally {
            // Close resources in the reverse order of their creation
            if (statement != null) statement.close();
            if (dbconnection != null) dbconnection.close();
        }

        return ok;
    }
}























