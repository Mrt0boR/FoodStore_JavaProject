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
 * The {@code loginHandler} class implements the {@code HttpHandler} interface to handle HTTP requests
 * related to the login page. It provides a simple HTML form for user authentication.
 */

/*

                << LOGIN DETAILS ARE STORED IN THE DATABASE>>
                <<PUT IN "username" and "password" to Log in>>
    <<NB the "password" string is stored as its MD5 hash in my DB under the "users" table>>


 */

public class loginHandler implements HttpHandler{
    /**
     * Handles the HTTP request for the /login page. This simply displays a form similar to that of the {@link AddFoodProductHandler}
     * or {@link addCustomerHandler} page. The values of this form are then passed to the {@link loginHandler}
     *
     * @param he The {@code HttpExchange} representing the HTTP request and response.
     * @throws IOException If an I/O error occurs while handling the request.
     */

    public void handle(HttpExchange he) throws IOException{
        //successful response is HTTP Code 200, whilst 500 would be an internal server-side error.
        he.sendResponseHeaders(200,0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody() ));


        //html content
        out.write(

                "<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                        "    <title>Login Page</title>\n" +
                        "    <style>\n" +
                        "        body {\n" +
                        "            font-family: Arial, sans-serif;\n" +
                        "            margin: 20px;\n" +
                        "        }\n" +
                        "        label {\n" +
                        "            display: block;\n" +
                        "            margin-bottom: 8px;\n" +
                        "        }\n" +
                        "        input {\n" +
                        "            width: 100%;\n" +
                        "            padding: 8px;\n" +
                        "            margin-bottom: 16px;\n" +
                        "            box-sizing: border-box;\n" +
                        "        }\n" +
                        "        button {\n" +
                        "            padding: 10px;\n" +
                        "            background-color: #4CAF50;\n" +
                        "            color: white;\n" +
                        "            border: none;\n" +
                        "            cursor: pointer;\n" +
                        "        }\n" +
                        "    </style>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "    <h2>Login</h2>\n" +
                        "    <form method=\"POST\" action=\"/login-process\">\n" +
                        "        <label for=\"username\">Username:</label>\n" +
                        "        <input type=\"text\" id=\"username\" name=\"username\" required>\n" +
                        "        <label for=\"password\">Password:</label>\n" +
                        "        <input type=\"password\" id=\"password\" name=\"password\" required>\n" +
                        "        <button type=\"submit\">Login</button>\n" +
                        "    </form>\n" +
                        "</body>\n"

        );



        out.close();



    }


}