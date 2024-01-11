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
 * The {@code deleteFoodProductProcessHandler} class serves as an HTTP handler for managing the deletion of a food product.
 * It processes the HTTP request, retrieves the item ID to be deleted from the URL parameters, deletes the corresponding
 * item from the database using a DAO class, and generates an HTML page confirming the successful deletion.
 */

public class deleteFoodProductProcessHandler implements HttpHandler {

    /**
     * Handles the HTTP request to execute the deletion of a food product.
     *
     * @param he The {@code HttpExchange} representing the HTTP request and response.
     * @throws IOException If an I/O error occurs while handling the request or response.
     */
    public void handle(HttpExchange he) throws IOException {
        System.out.println("Process Delete Handler Called");

        he.sendResponseHeaders(200, 0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody()));

        // Get parameter from URL
        Map<String, String> params = Util.requestStringToMap(he.getRequestURI().getQuery());

        // Print the parameters for debugging
        System.out.println(params);

        // Get the ID number
        int ID = Integer.parseInt(params.get("id"));

        // Assuming you have a DAO class for your items
        foodProductDAO dao = new foodProductDAO();
        System.out.println("loading page");
        try {
            // Get the item details before deletion from the Database
            shopItemConstructor deletedItem = dao.productSearch(ID);
            // Actually delete from the database
            dao.deleteProductByID(ID);

            out.write(
                    "<html>" +
                            "<head>" +
                            "   <title>Food Product Deleted</title>" +
                            "   <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                            "</head>" +
                            "<body>" +
                            "   <h1>Food Product Deleted</h1>" +
                            "   <table class=\"table\">" +
                            "       <thead>" +
                            "           <tr>" +
                            "               <th>ID</th>" +
                            "               <th>SKU</th>" +
                            "               <th>Category</th>" +
                            "               <th>Description</th>" +
                            "               <th>Price</th>" +
                            "           </tr>" +
                            "       </thead>" +
                            "       <tbody>" +
                            "           <tr>" +
                            "               <td>" + deletedItem.getID() + "</td>" +
                            "               <td>" + deletedItem.getSKU() + "</td>" +
                            "               <td>" + deletedItem.getCategory() + "</td>" +
                            "               <td>" + deletedItem.getDescription() + "</td>" +
                            "               <td>" + deletedItem.getPrice() + "</td>" +
                            "           </tr>" +
                            "       </tbody>" +
                            "   </table>" +
                            "   <a href=\"/\">Back to List</a>" +
                            "</body>" +
                            "</html>");

            // Close the BufferedWriter and OutputStream
            out.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
