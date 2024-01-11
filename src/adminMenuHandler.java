/*
 Name : James Bailey
 Student ID : 23673840
 */

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * The {@code AdminMenuHandler} class implements the {@code HttpHandler} interface
 * to handle HTTP requests related to the admin menu in a web application.
 */

public class adminMenuHandler implements HttpHandler {

    /**
     * Handles the HTTP exchange for the admin menu, generating an HTML response
     * with links to various admin functionalities.
     *
     * @param httpExchange The HTTP exchange object representing the client-server communication.
     * @throws IOException If an I/O error occurs while handling the request.
     */
    public void handle(HttpExchange httpExchange) throws IOException {
        httpExchange.sendResponseHeaders(200, 0);
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(httpExchange.getResponseBody()));

        out.write(
                "<!DOCTYPE html>" +
                        "<html lang=\"en\">" +
                        "<head>" +
                        "<meta charset=\"UTF-8\">" +
                        "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                        "<title>Admin Menu</title>" +
                        "<style>" +
                        "#cssDisplay {" +
                        "    background-color: #FFFFFF;" +
                        "    color: #000000;" +
                        "    border: 2px solid #6D6D6D;" +
                        "    padding: 20px;" +
                        "    margin: 20px auto; /* Center the element horizontally */" +
                        "    width: 80%; /* Make the box a long rectangle down the screen */" +
                        "    border-radius: 14px;" +
                        "    text-align: center;" +
                        "    box-shadow: 1px 3px 0px #6E6E6E;" +
                        "}" +
                        "#cssDisplay ul {" +
                        "    list-style-type: none; /* Remove bullets from the list items */" +
                        "    padding: 0; /* Remove default padding */" +
                        "}" +
                        "#cssDisplay ul li {" +
                        "    margin-bottom: 10px; /* Add margin between menu links */" +
                        "}" +
                        "</style>" +
                        "</head>" +
                        "<body>" +
                        "<div id=\"cssDisplay\">" +
                        "<h1>Admin Menu</h1>" +
                        "<ul>" +
                        "<li><a href=\"/addfoodproduct\">Add Product</a></li>" +
                        "<li><a href=\"/admindisplayProducts\">View Products</a></li>" +
                        "<li><a href=\"/addCustomerHandler\">Add Customer</a></li>" +
                        "<li><a href=\"/adminDisplayCustomers\">View Customers</a></li>" +
                        "</ul>" +
                        "</div>" +
                        "</body>" +
                        "</html>");

        out.close();
    }
}
