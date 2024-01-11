import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.io.*;

/*
 Name : James Bailey
 Student ID : 23673840
 */

/**
 * The {@code httpServer} class represents an HTTP server that listens on a specific port and handles various
 * HTTP requests related to the application's functionality. It utilises the following files:
 *  * {@link rootHandler}, {@link displayFoodProductsHandler}, {@link loginHandler},
 *  * {@link loginHandlerProcess}, {@link adminMenuHandler}, {@link adminDisplayProducts},
 *  * {@link customerDisplayHandler}, {@link AddFoodProductHandler}, {@link ProcessAddFoodProductHandler},
 *  * {@link deleteFoodProductProcessHandler}, {@link editFoodProductHandler}, {@link editFoodProductProcess},
 *  * {@link addCustomerHandler}, {@link addCustomerProcessHandler}, {@link deleteCustomerProcessHandler},
 *  * {@link editCustomerHandler}, {@link editCustomerProcessHandler}.
 */

public class httpServer {

    //port specification.
    private static final int PORT = 8080;

    /**
     * Starts the HTTP server and sets up contexts for handling different HTTP requests.
     *
     * @throws IOException If an I/O error occurs while starting the server.
     */
    public void startHttpServer() throws IOException {
        foodProductDAO foodProductDAO= new foodProductDAO();
        customerDAO customerDAO = new customerDAO();

        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
        passwordAuthentication passwordAuth = new passwordAuthentication();

       //rootmenu
        server.createContext("/", new rootHandler());

        //METHOD TO INTERACT WITH THE DB TO SUPPORT THE CUSTOMER
        server.createContext("/displayFoodProducts", new displayFoodProductsHandler());




        //Login pages
        server.createContext("/login", new loginHandler());
        server.createContext("/login-process", new loginHandlerProcess(passwordAuth));



        //admin Menu domains accessed post-login
        server.createContext("/adminmenu", new adminMenuHandler());

        //admin Displays for Product and Customers
        server.createContext("/admindisplayProducts", new adminDisplayProducts());
        server.createContext("/adminDisplayCustomers", new customerDisplayHandler());

        //CRUD ops for Food Products
        server.createContext("/addfoodproduct", new AddFoodProductHandler());
        server.createContext("/addfoodproduct-process", new ProcessAddFoodProductHandler());

        server.createContext("/deleteFoodProductProcessHandler" , new deleteFoodProductProcessHandler());


        server.createContext("/editfoodproductHandler" , new editFoodProductHandler(foodProductDAO));
        server.createContext("/editfoodproduct-process", new editFoodProductProcess());


        //add Customer Processes
        server.createContext("/addCustomerHandler" , new addCustomerHandler());
        server.createContext("/addCustomer-Process", new addCustomerProcessHandler());

        //delete Customer Processes
        server.createContext("/deleteCustomerProcessHandler" , new deleteCustomerProcessHandler());

        //edit Customer Processes
        server.createContext("/editCustomerHandler", new editCustomerHandler(customerDAO));
        server.createContext("/editCustomerProcessHandler", new editCustomerProcessHandler());












        server.setExecutor(null);
        server.start();
        System.out.println("Server is listening on port "+ PORT);


    }


}
