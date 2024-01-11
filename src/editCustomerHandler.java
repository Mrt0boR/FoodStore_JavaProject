import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
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
 * The {@code editCustomerHandler} class represents an HTTP handler for rendering the form to edit customer information.
 * It handles the HTTP GET request, retrieves the existing customer information from the database, and renders the HTML
 * form with pre-filled values for editing. The form submission is directed to the {@code editCustomerProcessHandler}.
 */

public class editCustomerHandler implements HttpHandler {
    private customerDAO dao;

    /**
     * Constructs an {@code editCustomerHandler} with the specified {@code customerDAO}.
     *
     * @param dao The {@code customerDAO} used for interacting with the database.
     */

    public editCustomerHandler(customerDAO dao) {
        this.dao = dao;
    }

    /**
     * Handles the HTTP GET request to render the form for editing customer information.
     *
     * @param he The {@code HttpExchange} representing the HTTP request and response.
     * @throws IOException If an I/O error occurs while handling the request or response.
     */

    @Override
    public void handle(HttpExchange he) throws IOException {
        Map<String, String> params = Util.requestStringToMap(he.getRequestURI().getQuery());

        int ID = Integer.parseInt(params.get("id"));

        System.out.println("Edit Customer Handler Called");

        customerConstructor existingCustomer = null;
        try {
            existingCustomer = dao.searchCustomerById(ID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(existingCustomer != null) {
            he.sendResponseHeaders(200, 0);
            BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(he.getResponseBody()));
            out.write(
                    "<html>" +
                            "<head>" +
                            "   <title>Edit Customer</title>" +
                            "   <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                            "</head>" +
                            "<body>" +
                            "   <div class=\"container\">" +
                            "       <h1>Edit Customer</h1>" +
                            "       <form method=\"post\" action=\"/editCustomerProcessHandler\">" +
                            "           <div class=\"form-group\">" +
                            "               <label for=\"id\">Customer ID</label>" +
                            "               <input type=\"text\" class=\"form-control\" name=\"id\" id=\"id\" value=\"" + existingCustomer.getCustomerID() + "\" readonly>" +

                            "               <label for=\"customerName\">Customer Name</label>" +
                            "               <input type=\"text\" class=\"form-control\" name=\"customerName\" id=\"customerName\" value=\"" + existingCustomer.getCustomerName() + "\">" +

                            "               <label for=\"businessName\">Business Name</label>" +
                            "               <input type=\"text\" class=\"form-control\" name=\"businessName\" id=\"businessName\" value=\"" + existingCustomer.getBusinessName() + "\">" +

                            "               <label for=\"telephoneNumber\">Telephone Number</label>" +
                            "               <input type=\"text\" class=\"form-control\" name=\"telephoneNumber\" id=\"telephoneNumber\" value=\"" + existingCustomer.getTelephoneNumber() + "\">" +

                            "               <label for=\"addressLine1\">Address Line 1</label>" +
                            "               <input type=\"text\" class=\"form-control\" name=\"addressLine1\" id=\"addressLine1\" value=\"" + existingCustomer.getAddressLine1() + "\">" +

                            "               <label for=\"addressLine2\">Address Line 2</label>" +
                            "               <input type=\"text\" class=\"form-control\" name=\"addressLine2\" id=\"addressLine2\" value=\"" + existingCustomer.getAddressLine2() + "\">" +

                            "               <label for=\"addressLine3\">Address Line 3</label>" +
                            "               <input type=\"text\" class=\"form-control\" name=\"addressLine3\" id=\"addressLine3\" value=\"" + existingCustomer.getAddressLine3() + "\">" +

                            "               <label for=\"country\">Country</label>" +
                            "               <input type=\"text\" class=\"form-control\" name=\"country\" id=\"country\" value=\"" + existingCustomer.getCountry() + "\">" +

                            "               <label for=\"postcode\">Postcode</label>" +
                            "               <input type=\"text\" class=\"form-control\" name=\"postcode\" id=\"postcode\" value=\"" + existingCustomer.getPostcode() + "\">" +
                            "           </div>" +
                            "           <button type=\"submit\" class=\"btn btn-primary\">Save Changes</button>" +
                            "       </form>" +
                            "       <a href=\"/\">Back to List</a>" +
                            "   </div>" +
                            "</body>" +
                            "</html>"
            );

            out.close();
        }



    }
}