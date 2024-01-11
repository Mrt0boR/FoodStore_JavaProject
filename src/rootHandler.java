import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;

/*
 Name : James Bailey
 Student ID : 23673840
 */

/**
 * The rootHandler class serves as an HTTP handler for the root URL ("/").
 * It generates an HTML page with a menu for navigating to different parts of the application.
 */
public class rootHandler implements HttpHandler {

    /**
     * Handles the HTTP request for the root URL and sends an HTML response.
     *
     * @param httpExchange The HTTP exchange representing the request and response.
     * @throws IOException if an I/O error occurs.
     */
    public void handle(HttpExchange httpExchange) throws IOException {
        // Send an HTTP 200 OK response
        httpExchange.sendResponseHeaders(200, 0);

        // Create a BufferedWriter for writing the HTML response
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(httpExchange.getResponseBody()));

        // HTML content for the menu page
        out.write(
                "<!DOCTYPE html>" +
                        "<html lang=\"en\">" +
                        "<head>" +
                        "<meta charset=\"UTF-8\">" +
                        "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                        "<title>Welcome to the Product DB Manager</title>" +
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
                        "<h1>Welcome to the Product DB Manager</h1>" +
                        "<ul>" +
                        "<li><a href=\"/displayFoodProducts\">Display All Products</a></li>" +
                        "<li><a href=\"/login\">Add / Delete / Edit Products</a></li>" +
                        "<li><a href=\"/login\">Add / Delete / Edit Customers</a></li>" +
                        "</ul>" +
                        "</div>" +
                        "</body>" +
                        "</html>");

        // Close the BufferedWriter
        out.close();
    }
}
