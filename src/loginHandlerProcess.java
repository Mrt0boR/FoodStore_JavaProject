import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

/*
 Name : James Bailey
 Student ID : 23673840
 */

/*

                << LOGIN DETAILS ARE STORED IN THE DATABASE>>
                <<PUT IN "username" and "password" to Log in>>
 <<NB the "password" string is stored as its MD5 hash in my DB under the "users" table>>


 */

/**
 * The LoginHandlerProcess class handles the authentication process for a login page in an HTTP server.
 */
public class loginHandlerProcess implements HttpHandler {

    private final passwordAuthentication passwordAuthentication;

    /**
     * Constructor for LoginHandlerProcess.
     *
     * @param passwordAuthentication The PasswordAuthentication instance for handling user authentication.
     */
    public loginHandlerProcess(passwordAuthentication passwordAuthentication) {
        this.passwordAuthentication = passwordAuthentication;
    }

    /**
     * Handles the HTTP request for user login.
     *
     * @param httpExchange The HTTP exchange representing the request and response.
     * @throws IOException if an I/O error occurs.
     */
    public void handle(HttpExchange httpExchange) throws IOException {
        String requestMethod = httpExchange.getRequestMethod();

        if (requestMethod.equalsIgnoreCase("POST")) {
            // Reading the request body to get form data
            String requestBody = convertStreamToStringUtil.convertStreamToString(httpExchange.getRequestBody());
            String[] params = requestBody.split("&");

            String username = "";
            String hashedPassword = "";

            // Extracting username and password from form data
            for (String param : params) {
                String[] keyValue = param.split("=");
                if (keyValue.length == 2) {
                    String key = keyValue[0];
                    String value = keyValue[1];

                    if (key.equalsIgnoreCase("username")) {
                        username = value;
                    } else if (key.equalsIgnoreCase("password")) {
                        // Hash the entered password before checking
                        hashedPassword = passwordAuthentication.hashPassword(value);
                    }
                }
            }

            // Authenticating the user
            boolean isAuthenticated = passwordAuthentication.auth(username, hashedPassword);


            // Handling the response based on authentication result
            if (isAuthenticated) {
                httpExchange.getResponseHeaders().set("Location", "/adminmenu");
                httpExchange.sendResponseHeaders(302, -1); // 302 Found (redirect)
            } else {
                sendFailedLoginResponse(httpExchange);
            }
        } else {
            // Handling other HTTP methods
            httpExchange.sendResponseHeaders(405, 0); // Method Not Allowed
            httpExchange.getResponseBody().close();
        }
    }

    /**
     * Sends a response for a failed login attempt.
     *
     * @param httpExchange The HTTP exchange representing the response.
     * @throws IOException if an I/O error occurs.
     */
    private void sendFailedLoginResponse(HttpExchange httpExchange) throws IOException {
        httpExchange.sendResponseHeaders(200, 0);
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(httpExchange.getResponseBody()));

        // HTML response for failed login
        out.write("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Login Result</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <h2>Invalid username or password. Please try again.</h2>\n" +
                "</body>\n" +
                "</html>");

        out.close();
    }
}
