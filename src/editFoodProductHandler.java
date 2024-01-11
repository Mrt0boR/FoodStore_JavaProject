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
 * The {@code editFoodProductHandler} class represents an HTTP handler for displaying the form
 * to edit a specific food product. It retrieves the existing product information, populates the form,
 * and allows the user to update the product details. These details are then passed onto {@link editFoodProductProcess}
 */

public class editFoodProductHandler implements HttpHandler {
    private foodProductDAO dao;

    /**
     * Constructs an {@code editFoodProductHandler} with the specified DAO.
     *
     * @param dao The data access object for food products.
     */

    public editFoodProductHandler(foodProductDAO dao) {
        this.dao = dao;
    }

    /**
     * Handles the HTTP GET request to display the form for editing a food product.
     *
     * @param he The {@code HttpExchange} representing the HTTP request and response.
     * @throws IOException      If an I/O error occurs while handling the request or response.
     */

    @Override
    public void handle(HttpExchange he) throws IOException {
        Map<String, String> params = Util.requestStringToMap(he.getRequestURI().getQuery());

        int ID = Integer.parseInt(params.get("id"));

        System.out.println("Edit Food Product Handler Called");

        shopItemConstructor existingItem = null;
        try {
            existingItem = dao.productSearch(ID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(existingItem!= null) {
            he.sendResponseHeaders(200, 0);
            BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(he.getResponseBody()));
            out.write(
                    "<html>" +
                            "<head> <title>Edit Food Product</title> " +
                            "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                            "</head>" +
                            "<body>" +
                            "<div class=\"container\">" +
                            "<h1>Edit Food Product</h1>" +
                            "<form method=\"post\" action=\"/editfoodproduct-process\">" +
                            "<div class=\"form-group\"> " +
                            "<label for=\"id\">ID</label> " +
                            "<input type=\"text\" class=\"form-control\" name=\"id\" id=\"id\" value=\"" + existingItem.getID() + "\" readonly> " +

                            "<label for=\"sku\">SKU</label> " +
                            "<input type=\"text\" class=\"form-control\" name=\"sku\" id=\"sku\" value=\"" + existingItem.getSKU() + "\"> " +

                            "<label for=\"category\">Category</label> " +
                            "<input type=\"text\" class=\"form-control\" name=\"category\" id=\"category\" value=\"" + existingItem.getCategory() + "\"> " +

                            "<label for=\"description\">Description</label> " +
                            "<input type=\"text\" class=\"form-control\" name=\"description\" id=\"description\" value=\"" + existingItem.getDescription() + "\"> " +

                            "<label for=\"price\">Price</label> " +
                            "<input type=\"text\" class=\"form-control\" name=\"price\" id=\"price\" value=\"" + existingItem.getPrice() + "\"> " +

                            "<button type=\"submit\" class=\"btn btn-primary\">Update Product</button> " +
                            "</div>" +
                            "</form>" +
                            "<a href=\"/\">Back to List</a>" +
                            "</div>" +
                            "</body>" +
                            "</html>");

            out.close();
        }



    }
}