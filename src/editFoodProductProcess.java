import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.*;
import java.util.Map;

/*
 Name : James Bailey
 Student ID : 23673840
 */
/**
 * The {@code editFoodProductProcess} class represents an HTTP handler responsible for processing
 * the HTTP POST request to edit a food product. It extracts the edited product information from the request found in
 * {@link editFoodProductHandler} and updates the corresponding database entry,
 * and responds with the edited product details in HTML format.
 */

public class editFoodProductProcess implements HttpHandler {
    /**
     * Handles the HTTP POST request for editing a food product.
     *
     * @param he The {@code HttpExchange} representing the HTTP request and response.
     * @throws IOException      If an I/O error occurs while handling the request or response.
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
            System.out.println("Process Edit Food Product Called");


            // Get parameters from URL
            Map<String, String> params = Util.requestStringToMap(requestData.toString());

            // Print the parameters for debugging
            System.out.println(params);


            System.out.println("About to get data");

            // Extract parameters
            int id = Integer.parseInt(params.get("id"));
            String sku = params.get("sku");
            String category = params.get("category");
            String description = params.get("description");
            int price = Integer.parseInt(params.get("price"));

            shopItemConstructor editItem = new shopItemConstructor(id, sku, category, description, price);

            foodProductDAO foodProductDAO = new foodProductDAO();

            foodProductDAO.updateProduct(editItem);

            boolean isEdited = foodProductDAO.updateProduct(editItem);

            if (isEdited) {
                he.sendResponseHeaders(200, 0);
                OutputStream os = he.getResponseBody();
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(os));

                out.write(
                        "<html>" +
                                "<head> <title>Food Product Edited</title> " +
                                "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                                "</head>" +
                                "<body>" +
                                "<h1>Food Product Edited</h1>" +
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

                                "    <td>" + editItem.getSKU() + "</td>" +
                                "    <td>" + editItem.getCategory() + "</td>" +
                                "    <td>" + editItem.getDescription() + "</td>" +
                                "    <td>" + editItem.getPrice() + "</td>" +
                                "  </tr>");
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



