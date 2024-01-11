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
 * The {@code displayFoodProductsHandler} class represents an HTTP handler for displaying a list of food products.
 * It handles the HTTP request and retrieves the list of food products from the database rendering an HTML page and
 * displaying the retrieved products in a table with a link to a stylesheet.
 */
public class displayFoodProductsHandler implements HttpHandler {
    /**
     * Handles the HTTP request to display a list of food products.
     *
     * @param he The {@code HttpExchange} representing the HTTP request and response.
     * @throws IOException If an I/O error occurs while handling the request or response.
     */
    public void handle(HttpExchange he) throws IOException{
        he.sendResponseHeaders(200, 0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody()));
        foodProductDAO dao = new foodProductDAO();
        try {
            ArrayList<shopItemConstructor> shopItemsArrayListConstructor = (ArrayList<shopItemConstructor>) dao.selectallstatement();

            out.write(
                    "<html>\n" +
                            "<head><title>Shop Item List</title>\n" +
                            "   <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                            "</head>" +
                            "<body>" +
                            "<h1> Food Products in Stock</h1>"+
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


            /*Iterate through each stored food product and then returns the retrieved into the HTML content inserting them
            into table rows */
            for (shopItemConstructor si : shopItemsArrayListConstructor){

                out.write("<tr>\n");
                out.write("<td>" + si.getID() + "</td>\n");
                out.write("<td>" + si.getSKU() + "</td>\n");
                out.write("<td>" + si.getCategory() + "</td>\n");
                out.write("<td>" + si.getDescription() + "</td>\n");
                out.write("<td>" + si.getPrice() + "</td>\n");
                out.write("</tr>\n");
            }

            // Close HTML tags
            out.write("</table>\n</body>\n</html>");

            //exception handling
        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            out.close();
        }
    }
}



