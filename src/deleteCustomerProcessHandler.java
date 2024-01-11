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
 * This class serves as an HTTP handler for customer deletion requests. It manages the processing of HTTP requests,
 * extracting the customer ID from the URL parameters, utilizing a DAO class to delete the corresponding customer from the database,
 * and finally, generating an HTML page to confirm the successful deletion.
 */

public class deleteCustomerProcessHandler implements HttpHandler {

    /**
     * Handles HTTP requests to execute the deletion of a customer.
     *
     * @param he The {@code HttpExchange} representing the HTTP request and response.
     * @throws IOException If an I/O error occurs during the handling of the request or response.
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
        int CustomerID = Integer.parseInt(params.get("id"));

        // Assuming you have a DAO class for your items
        customerDAO dao = new customerDAO();
        System.out.println("loading page");
        try {
            // Get the item details before deletion from the Database
            customerConstructor deletedCustomer = dao.searchCustomerById(CustomerID);
            // Actually delete from the database
            dao.deleteCustomerByID(CustomerID);

            out.write(
                    "<html>" +
                            "<head>" +
                            "   <title>Customer Deleted</title>" +
                            "   <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                            "</head>" +
                            "<body>" +
                            "   <h1>Customer Deleted</h1>" +
                            "   <table class=\"table\">" +
                            "       <thead>" +
                            "           <tr>" +
                            "               <th>Customer ID</th>" +
                            "               <th>Customer Name</th>" +
                            "               <th>Business</th>" +
                            "               <th>Telephone</th>" +
                            "               <th>Address Line 1</th>" +
                            "               <th>Address Line 2</th>" +
                            "               <th>Address Line 3</th>" +
                            "               <th>Country</th>" +
                            "               <th>Postcode</th>" +
                            "           </tr>" +
                            "       </thead>" +
                            "       <tbody>" +
                            "           <tr>" +
                            "               <td>" + deletedCustomer.getCustomerID() + "</td>" +
                            "               <td>" + deletedCustomer.getCustomerName() + "</td>" +
                            "               <td>" + deletedCustomer.getBusinessName() + "</td>" +
                            "               <td>" + deletedCustomer.getTelephoneNumber() + "</td>" +
                            "               <td>" + deletedCustomer.getAddressLine1() + "</td>" +
                            "               <td>" + deletedCustomer.getAddressLine2() + "</td>" +
                            "               <td>" + deletedCustomer.getAddressLine3() + "</td>" +
                            "               <td>" + deletedCustomer.getCountry() + "</td>" +
                            "               <td>" + deletedCustomer.getPostcode() + "</td>" +
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
