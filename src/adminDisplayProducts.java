/*
 Name : James Bailey
 Student ID : 23673840
 */

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The {@code AdminMenuHandler} class implements the {@code HttpHandler} interface
 * to handle HTTP requests related to the admin menu in a web application.
 */

public class adminDisplayProducts implements HttpHandler {

    /**
     * Handles the HTTP exchange for the admin menu, generating an HTML response
     * with links to various admin functionalities.
     *
     * @param he (httpExchange) The HTTP exchange object representing the client-server communication.
     * @throws IOException If an I/O error occurs while handling the request.
     */

    public void handle(HttpExchange he) throws IOException {
        he.sendResponseHeaders(200, 0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody()));

        foodProductDAO dao = new foodProductDAO();

        try {
            ArrayList<shopItemConstructor> shopItemsArrayListConstructor = (ArrayList<shopItemConstructor>) dao.selectallstatement();

            out.write("<html>\n<head>\n<title>Shop Item List</title>\n" +
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
                    "    <th>SKU</th>\n" +
                    "    <th>Category</th>\n" +
                    "    <th>Description</th>\n" +
                    "    <th>Price</th>\n" +
                    "    <th>Action</th>\n" +
                    "  </tr>\n" +
                    "</thead>\n" +
                    "<tbody>");

            for (shopItemConstructor si : shopItemsArrayListConstructor) {
                out.write(
                        "  <tr>\n" +
                                "    <td>" + si.getID() + "</td>\n" +
                                "    <td>" + si.getSKU() + "</td>\n" +
                                "    <td>" + si.getCategory() + "</td>\n" +
                                "    <td>" + si.getDescription() + "</td>\n" +
                                "    <td>" + si.getPrice() + "</td>\n" +
                                "    <td>\n" +
                                "        <a href=\"/editfoodproductHandler?id="+si.getID()+ "\">Edit</a> |\n" +
                                "        <a href=\"/deleteFoodProductProcessHandler?id=" + si.getID() + "\">Delete</a>\n" +
                                "    </td>\n" +
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

