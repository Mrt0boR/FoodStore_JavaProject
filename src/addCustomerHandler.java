import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
/*
 Name : James Bailey
 Student ID : 23673840
 */

/**
 * HTTP handler for serving a web page allowing users to input customer details through a form.
 * The form data is then submitted to the "/addCustomer-Process" for processing as seen in the HTML code in the class.
 */
public class addCustomerHandler implements HttpHandler {

    /**
     * Handles the HTTP request to display a form for adding a new customer.
     * Responds with an HTML page containing a form for entering customer details.
     * The form data is submitted to the "/addCustomer-Process" endpoint for processing.
     *
     * @param he The HttpExchange object representing the HTTP request and response.
     * @throws IOException If an I/O error occurs while handling the request.
     */
    public void handle(HttpExchange he) throws IOException {
        System.out.println("Add Customer Handler Called");
        he.sendResponseHeaders(200, 0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody()));

        out.write(
                "<html>" +
                        "<head>" +
                        "  <title>Add Customer</title>" +
                        "  <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                        "</head>" +
                        "<body>" +
                        "  <div class=\"container\">" +
                        "    <h1>Add Customer</h1>" +
                        "    <form method=\"get\" action=\"/addCustomer-Process\">" +
                        "      <div class=\"form-group\"> " +
                        "        <label for=\"id\">ID</label> " +
                        "        <input type=\"text\" class=\"form-control\" name=\"id\" id=\"id\"> " +

                        "        <label for=\"name\">Name</label> " +
                        "        <input type=\"text\" class=\"form-control\" name=\"name\" id=\"name\"> " +

                        "        <label for=\"business\">Business Name</label> " +
                        "        <input type=\"text\" class=\"form-control\" name=\"business\" id=\"business\"> " +

                        "        <label for=\"telephone\">Telephone Number</label> " +
                        "        <input type=\"text\" class=\"form-control\" name=\"telephone\" id=\"telephone\"> " +

                        "        <label for=\"address1\">Address Line 1</label> " +
                        "        <input type=\"text\" class=\"form-control\" name=\"address1\" id=\"address1\"> " +

                        "        <label for=\"address2\">Address Line 2</label> " +
                        "        <input type=\"text\" class=\"form-control\" name=\"address2\" id=\"address2\"> " +

                        "        <label for=\"address3\">Address Line 3</label> " +
                        "        <input type=\"text\" class=\"form-control\" name=\"address3\" id=\"address3\"> " +

                        "        <label for=\"country\">Country</label> " +
                        "        <input type=\"text\" class=\"form-control\" name=\"country\" id=\"country\"> " +

                        "        <label for=\"postcode\">Postcode</label> " +
                        "        <input type=\"text\" class=\"form-control\" name=\"postcode\" id=\"postcode\"> " +

                        "        <button type=\"submit\" class=\"btn btn-primary\">Add Customer</button> " +
                        "      </div>" +
                        "    </form>" +
                        "    <a href=\"/\">Back to List</a>" +
                        "  </div>" +
                        "</body>" +
                        "</html>");
        out.close();
    }
}