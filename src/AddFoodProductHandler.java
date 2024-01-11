/*
 Name : James Bailey
 Student ID : 23673840
 */

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * The {@code AddFoodProductHandler} class implements the {@code HttpHandler} interface
 * to handle HTTP requests for adding a new food product.
 */
public class AddFoodProductHandler implements HttpHandler {

    /**
     * Handles the HTTP exchange for adding a new food product, displaying an HTML form for user input.
     *
     * @param he The HTTP exchange object representing the client-server communication.
     * @throws IOException If an I/O error occurs while handling the request.
     */
    public void handle(HttpExchange he) throws IOException {
        System.out.println("Add Food Product Handler Called");
        he.sendResponseHeaders(200, 0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody()));

        out.write(
                "<html>" +
                        "<head> <title>Add Food Product</title> " +
                        "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                        "</head>" +
                        "<body>" +
                        "<div class=\"container\">" +
                        "<h1>Add Food Product</h1>" +
                        "<form method=\"get\" action=\"/addfoodproduct-process\">" +
                        "<div class=\"form-group\"> " +
                        "<label for=\"id\">ID</label> " +
                        "<input type=\"text\" class=\"form-control\" name=\"id\" id=\"id\"> " +

                        "<label for=\"sku\">SKU</label> " +
                        "<input type=\"text\" class=\"form-control\" name=\"sku\" id=\"sku\"> " +

                        "<label for=\"category\">Category</label> " +
                        "<input type=\"text\" class=\"form-control\" name=\"category\" id=\"category\"> " +

                        "<label for=\"description\">Description</label> " +
                        "<input type=\"text\" class=\"form-control\" name=\"description\" id=\"description\"> " +

                        "<label for=\"price\">Price</label> " +
                        "<input type=\"text\" class=\"form-control\" name=\"price\" id=\"price\"> " +

                        "<button type=\"submit\" class=\"btn btn-primary\">Add Product</button> " +
                        "</div>" +
                        "</form>" +
                        "<a href=\"/\">Back to List</a>" +
                        "</div>" +
                        "</body>" +
                        "</html>");
        out.close();
    }
}