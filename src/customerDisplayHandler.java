import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

/*
 Name : James Bailey
 Student ID : 23673840
 */

/**
 * A simple HTTP handler to display a list of customers.
 */


public class customerDisplayHandler implements HttpHandler {

    /**
     * Handles HTTP requests to display a list of customers.
     *
     * @param he The HttpExchange object handling the HTTP requests and responses.
     * @throws IOException If an I/O error occurs while processing the request.
     */

    public void handle(HttpExchange he) throws IOException {
        he.sendResponseHeaders(200, 0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody()));

        customerDAO dao = new customerDAO();

        try {
            ArrayList<customerConstructor> customerArrayList = (ArrayList<customerConstructor>) dao.selectAllCustomers();

            out.write("<html>\n<head>\n<title>Customer List</title>\n" +
                    "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" " +
                    "integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">\n" +
                    "</head>\n<body>\n");

            // Write HTML content for the table headers
            out.write("<div class=\"container\">\n" +
                    "<h1>Shop Items List</h1>\n" +
                    "<table class=\"table\">\n" +
                    "<thead>\n" +
                    "  <tr>\n" +
                    "    <th>ID</th>\n" +
                    "    <th>Name</th>\n" +
                    "    <th>Business</th>\n" +
                    "    <th>Telephone</th>\n" +
                    "    <th>Address Line 1</th>\n" +
                    "    <th>Address Line 2</th>\n" +
                    "    <th>Address Line 3</th>\n" +
                    "    <th>Country</th>\n" +
                    "    <th>Postcode</th>\n" +
                    "  </tr>\n" +
                    "</thead>\n" +
                    "<tbody>");


            for (customerConstructor customer : customerArrayList) {
                out.write(
                        "  <tr>\n" +
                                "    <td>" + customer.getCustomerID() + "</td>\n" +
                                "    <td>" + customer.getCustomerName() + "</td>\n" +
                                "    <td>" + customer.getBusinessName() + "</td>\n" +
                                "    <td>" + customer.getTelephoneNumber() + "</td>\n" +
                                "    <td>" + customer.getAddressLine1() + "</td>\n" +
                                "    <td>" + customer.getAddressLine2() + "</td>\n" +
                                "    <td>" + customer.getAddressLine3() + "</td>\n" +
                                "    <td>" + customer.getCountry() + "</td>\n" +
                                "    <td>" + customer.getPostcode() + "</td>\n" +
                                "    <td><a href=\"/editCustomerHandler?id=" + customer.getCustomerID() + "\">Edit</a></td>\n" +
                                "    <td><a href=\"/deleteCustomerProcessHandler?id=" + customer.getCustomerID() + "\">Delete</a></td>\n" +
                                "  </tr>\n"
                );
            }

            out.write(
                    "</tbody>\n" +
                            "</table>\n" +
                            "<a href=\"/\">Back to Menu </a>\n" +
                            "</div>\n" +
                            "</body>\n" +
                            "</html>");

        } catch (Exception e) {
            // Handle exceptions if necessary
            e.printStackTrace();
        } finally {
            // Close the BufferedWriter and OutputStream
            out.close();
        }
    }
}