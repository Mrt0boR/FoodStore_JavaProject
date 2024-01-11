import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.*;
import java.util.Map;

/*
 Name : James Bailey
 Student ID : 23673840
 */

/**
 * The {@code editCustomerProcessHandler} class represents an HTTP handler for processing the form
 * to edit a specific customer. It handles the HTTP POST request, retrieves and validates the form data,
 * updates the customer information in the database, and provides a response indicating the success or failure
 * of the operation.
 */

public class editCustomerProcessHandler implements HttpHandler {

    /**
     * Handles the HTTP POST request to process the form for editing a customer.
     *
     * @param he The {@code HttpExchange} representing the HTTP request and response.
     * @throws IOException If an I/O error occurs while handling the request or response.
     */

    @Override
    public void handle(HttpExchange he) throws IOException {

        if (he.getRequestMethod().equalsIgnoreCase("POST")) {
            InputStream requestBody = he.getRequestBody();
            BufferedReader reader = new BufferedReader(new InputStreamReader(requestBody));
            StringBuilder requestData = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                requestData.append(line);
            }
            System.out.println("Process Edit Customer Called");


            // Get parameters from URL
            Map<String, String> params = Util.requestStringToMap(requestData.toString());

            // Print the parameters for debugging
            System.out.println(params);


            System.out.println("About to get data");

            // Extract parameters
            int customerId = Integer.parseInt(params.get("id"));
            String customerName = params.get("customerName");
            String businessName = params.get("businessName");
            String telephoneNumber = params.get("telephoneNumber");
            String addressLine1 = params.get("addressLine1");
            String addressLine2 = params.get("addressLine2");
            String addressLine3 = params.get("addressLine3");
            String country = params.get("country");
            String postcode = params.get("postcode");

            customerConstructor editCustomer= new customerConstructor(customerId,customerName, businessName, telephoneNumber,
                    addressLine1, addressLine2, addressLine3, country, postcode);

            customerDAO CustomerDAO = new customerDAO();

            CustomerDAO.updateCustomer(editCustomer);

            boolean isEdited = CustomerDAO.updateCustomer(editCustomer);

            if (isEdited) {
                he.sendResponseHeaders(200, 0);
                OutputStream os = he.getResponseBody();
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(os));

                out.write(
                        "<html>" +
                                "<head> <title>Customer Edited</title> " +
                                "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                                "</head>" +
                                "<body>" +
                                "<h1>Customer Edited</h1>" +
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
                                "    <td>" + editCustomer.getCustomerID() + "</td>" +
                                "    <td>" + editCustomer.getCustomerName() + "</td>" +
                                "    <td>" + editCustomer.getBusinessName() + "</td>" +
                                "    <td>" + editCustomer.getTelephoneNumber() + "</td>" +
                                "    <td>" + editCustomer.getAddressLine1() + "</td>" +
                                "    <td>" + editCustomer.getAddressLine2() + "</td>" +
                                "    <td>" + editCustomer.getAddressLine3() + "</td>" +
                                "    <td>" + editCustomer.getCountry() + "</td>" +
                                "    <td>" + editCustomer.getPostcode() + "</td>" +
                                "  </tr>"
                );
                out.write(
                        "</tbody>" +
                                "</table>" +
                                "<a href=\"/\">Back to List</a>" +
                                "</body>" +
                                "</html>");


                out.close();
            } else {
                he.sendResponseHeaders(500, 0);

            }
        } else {
            he.sendResponseHeaders(500, 0);
        }
        he.close();
    }
}
