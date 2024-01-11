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
 * Handles the HTTP request to add a customer.
 */

public class addCustomerProcessHandler implements HttpHandler {
    public void handle(HttpExchange he) throws IOException {
        System.out.println("Process Add Customer Called");
        he.sendResponseHeaders(200, 0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody()));

        // Get parameters from URL
        Map<String, String> params = Util.requestStringToMap(he.getRequestURI().getQuery());

        // Print the parameters for debugging
        System.out.println(params);

        customerDAO customerDAO = new customerDAO();

        System.out.println("About to get data");

        // Extract parameters

        int customerId = Integer.parseInt(params.get("id"));
        String customerName = params.get("name");
        String businessName = params.get("business");
        String telephoneNumber = params.get("telephone");
        String addressLine1 = params.get("address1");
        String addressLine2 = params.get("address2");
        String addressLine3 = params.get("address3");
        String country = params.get("country");
        String postcode = params.get("postcode");

        System.out.println("About to create FoodProduct"); // Debugging message
        customerConstructor customer = new customerConstructor(customerId, customerName,businessName,telephoneNumber,addressLine1,addressLine2,addressLine3, country, postcode);
        System.out.println("Customer to add: " + customer);

        try {
            customerDAO.addCustomer(customer);
            out.write(
                    "<html>" +
                            "<head> <title>Customer Added</title> " +
                            "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                            "</head>" +
                            "<body>" +
                            "<h1>Customer Added</h1>" +
                            "<table class=\"table\">" +
                            "<thead>" +
                            "  <tr>" +
                            "    <th>Customer ID</th>" +
                            "    <th>Customer Name</th>" +
                            "    <th>Business Name</th>" +
                            "    <th>Telephone Number</th>" +
                            "    <th>Address Line 1</th>" +
                            "    <th>Address Line 2</th>" +
                            "    <th>Address Line 3</th>" +
                            "    <th>Country</th>" +
                            "    <th>Postcode</th>" +
                            "  </tr>" +
                            "</thead>" +
                            "<tbody>");
            out.write(
                    "  <tr>" +
                            "    <td>" + customer.getCustomerID() + "</td>" +
                            "    <td>" + customer.getCustomerName() + "</td>" +
                            "    <td>" + customer.getBusinessName() + "</td>" +
                            "    <td>" + customer.getTelephoneNumber() + "</td>" +
                            "    <td>" + customer.getAddressLine1() + "</td>" +
                            "    <td>" + customer.getAddressLine2() + "</td>" +
                            "    <td>" + customer.getAddressLine3() + "</td>" +
                            "    <td>" + customer.getCountry() + "</td>" +
                            "    <td>" + customer.getPostcode() + "</td>" +
                            "  </tr>");


            out.write(
                    "</tbody>" +
                            "</table>" +
                            "<a href=\"/\">Back to List</a>" +
                            "</body>" +
                            "</html>");
        }catch(SQLException se){
            System.out.println(se.getMessage());
        }
        out.close();

    }
}