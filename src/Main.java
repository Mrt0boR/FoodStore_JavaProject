import java.io.IOException;
import java.sql.SQLException;


/*

    Name : James Bailey
    Student ID : 23673840


                << LOGIN DETAILS ARE STORED IN THE DATABASE>>
                <<PUT IN "username" and "password" to Log in>>
<<NB the "password" string is stored as its MD5 hash in my DB under the "users" table>>

IF THE JDBC DRIVER IS NOT RECOGNISED BY INTELLIJ go to:
File > Project Structure > Libraries > '+' button for "new project library" > click 'Java' in the Dropdown menu
then navigate to this project file > src > and then click 'sqlite-jdbc-3.40.0.0.jar. I have written code so your connection
link is automatically assigned to the connection variable.

 */

/**
 * The {@code Main} class serves as the entry point for the application, providing functionality to interact with
 * a terminal menu and start an HTTP server for managing the database.
 */
public class Main {
    /**
     * The port number for the HTTP server. Such that the URL will load when option 3 in the menu is pressed.
     * In this case the URL in your browser would be " http://localhost:8080/ " to get to the rootHandler page.
     */
    static private final int PORT = 8080;

    /**
     * The main method that is executed when the application is run.
     *
     * @param args Command-line arguments (not used in this application).
     * @throws SQLException If a SQL exception occurs during database operations.
     * @throws IOException  If an I/O exception occurs.
     */
    public static void main(String[] args) throws SQLException, IOException {
        // Display a message related to the database file path using the Util class
        Util util = new Util();
        util.dbFilePathMessage();

        // Terminal functionality
        mainMenu menu = new mainMenu();
        menu.menuOptions();  // User interacts with the menu

    }
}