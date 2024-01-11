import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 Name : James Bailey
 Student ID : 23673840
 */
/*
IF THE JDBC DRIVER IS NOT RECOGNISED BY INTELLIJ go to:
File > Project Structure > Libraries > '+' button for "new project library" > click 'Java' in the Dropdown menu
then navigate to this project file > src > and then click 'sqlite-jdbc-3.40.0.0.jar.

I have written code in Util.jar so your connection
link is automatically assigned to the connection variable.

 */
/**
 * The PasswordAuthentication class provides methods for hashing passwords and authenticating users.
 */
public class passwordAuthentication {

    /**
     * Initializes the connection to the SQLite database.
     *
     * @return A connection to the SQLite database.
     * @throws SQLException if a database access error occurs.
     */
    public static Connection sqlInit() throws SQLException {
        String userDir = System.getProperty("user.dir");
        System.out.println("Current user directory: " + userDir);

        String dbPath = userDir + "/src/foodstore.sqlite";
        String connectionURL = "jdbc:sqlite:" + dbPath;
        return DriverManager.getConnection(connectionURL);
    }


    /**
     * Hashes the provided password using MD5.
     *
     * @param plainTextPassword The password to be hashed.
     * @return The MD5 hash of the password.
     */

    public static String hashPassword(String plainTextPassword) {
        try {
            // Create a MessageDigest instance with the MD5 algorithm
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");

            // Get the byte array representation of the hashed password
            byte[] hashedBytes = messageDigest.digest(plainTextPassword.getBytes());

            // Convert the byte array to a hexadecimal string representation
            StringBuilder hashedPassword = new StringBuilder();
            for (byte b : hashedBytes) {
                // Ensure each byte is represented by two hexadecimal characters
                // and append it to the final hashed password
                hashedPassword.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }

            // Return the final hashed password as a string
            return hashedPassword.toString();
        } catch (NoSuchAlgorithmException e) {
            // Handle the case where MD5 algorithm is not available
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Authenticates a user by checking the provided username and password against the database.
     *
     * @param username The username entered by the user.
     * @param password The password entered by the user.
     * @return True if the authentication is successful, false otherwise.
     */
    public static boolean auth(String username, String password) {
        try (Connection dbconnection = sqlInit()) {
            String passSearch = "SELECT * FROM users WHERE Usernames = ? AND Passwords =?";

            password = password.trim();


            try (PreparedStatement preparedStatement = dbconnection.prepareStatement(passSearch)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Authentication failed
        return false;
    }
}