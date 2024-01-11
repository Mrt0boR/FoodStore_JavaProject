import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.Map;

/*
 Name : James Bailey
 Student ID : 23673840
 */

/**
 * {@code ProcessAddFoodProductHandler} is an HTTP request handler responsible for processing
 * and handling requests related to adding a food product to the system.
 *
 * It implements the {@code HttpHandler} interface to handle HTTP requests.
 */
public class ProcessAddFoodProductHandler implements HttpHandler {

    /**
     * Handles the incoming HTTP request for adding a food product.
     *
     * @param he The {@code HttpExchange} representing the HTTP request and response.
     * @throws IOException If an I/O error occurs while handling the request.
     */
    public void handle(HttpExchange he) throws IOException {
        // Print a debug message indicating that the process for adding a food product is called.
        System.out.println("Process Add Food Product Called");

        // Send a successful response code (200) to the client.
        he.sendResponseHeaders(200, 0);

        // Create a BufferedWriter to write the response to the client.
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody()));

        // Get parameters from the URL query string.
        Map<String, String> params = Util.requestStringToMap(he.getRequestURI().getQuery());

        // Print the parameters for debugging purposes.
        System.out.println(params);

        // Create an instance of the FoodProductDAO for database operations.
        foodProductDAO foodProductDAO = new foodProductDAO();

        // Print a debug message indicating the intention to retrieve data.
        System.out.println("About to get data");

        // Extract parameters from the request.
        int id = Integer.parseInt(params.get("id"));
        String sku = params.get("sku");
        String category = params.get("category");
        String description = params.get("description");
        int price = Integer.parseInt(params.get("price"));

        // Print a debug message indicating the intention to create a FoodProduct instance.
        System.out.println("About to create FoodProduct");

        // Create a FoodProduct instance using the extracted parameters.
        shopItemConstructor foodProduct = new shopItemConstructor(id, sku, category, description, price);

        // Print a debug message indicating the FoodProduct to be added.
        System.out.println("FoodProduct to add: " + foodProduct);

        try {
            // Attempt to add the FoodProduct to the database.
            foodProductDAO.addProduct(foodProduct);

            // Write HTML response indicating the successful addition of the food product.
            out.write(
                    "<html>" +
                            "<head> <title>Food Product Added</title> " +
                            "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                            "</head>" +
                            "<body>" +
                            "<h1>Food Product Added</h1>" +
                            "<table class=\"table\">" +
                            "<thead>" +
                            "  <tr>" +
                            "    <th>ID</th>" +
                            "    <th>SKU</th>" +
                            "    <th>Category</th>" +
                            "    <th>Description</th>" +
                            "    <th>Price</th>" +
                            "  </tr>" +
                            "</thead>" +
                            "<tbody>");
            out.write(
                    "  <tr>" +
                            "    <td>" + foodProduct.getID() + "</td>" +
                            "    <td>" + foodProduct.getSKU() + "</td>" +
                            "    <td>" + foodProduct.getCategory() + "</td>" +
                            "    <td>" + foodProduct.getDescription() + "</td>" +
                            "    <td>" + foodProduct.getPrice() + "</td>" +
                            "  </tr>");
            out.write(
                    "</tbody>" +
                            "</table>" +
                            "<a href=\"/\">Back to List</a>" +
                            "</body>" +
                            "</html>");
        } catch (SQLException se) {
            // Print the SQL exception message in case of an error.
            System.out.println(se.getMessage());
        } finally {
            // Close the BufferedWriter to complete the response.
            out.close();
        }
    }
}
