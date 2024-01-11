import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.io.File;


/*
 Name : James Bailey
 Student ID : 23673840
 */
/**
 *
 * The Util class provides utility methods for handling HTTP requests and database file paths.
 */
public class Util {

    /**
     * Converts a URL-encoded request string into a HashMap.
     *
     * @param request The URL-encoded request string.
     * @return A HashMap containing key-value pairs from the request.
     */
    public static HashMap<String, String> requestStringToMap(String request) {
        HashMap<String, String> map = new HashMap<String, String>();
        String[] pairs = request.split("&");

        for (int i = 0; i < pairs.length; i++) {
            String pair = pairs[i];

            try {
                String[] kv = pair.split("=");
                String key = kv[0];
                key = URLDecoder.decode(key, "UTF-8");

                String value = kv[1];
                value = URLDecoder.decode(value, "UTF-8");

                map.put(key, value);
            } catch (UnsupportedEncodingException e) {
                // Print an error message to standard error if decoding fails
                System.err.println("Error decoding request parameter: " + e.getMessage());
            }
        }
        return map;
    }

    /**
     * Prints the absolute file path of the database file for establishing a JDBC connection.
     * The database file is assumed to be named "foodstore.sqlite".
     */
    public void dbFilePathMessage() {
        String databaseFileName = "foodstore.sqlite";
        String currentWorkingDirectory = System.getProperty("user.dir");
        String absoluteFilePath = "jdbc:sqlite:"+currentWorkingDirectory+"/foodstore.sqlite";


        // Print a message with the absolute file path for establishing a JDBC connection
        System.out.println("\n To Correctly Establish a JDBC Connection on Your System: \n\n " +
                "Absolute File Path of the Database File: " + absoluteFilePath + "\n" + "\n"+
                "Please make sure the database is connected via the above filepath to the foodstore.sqlite file on YOUR MACHINE");
    }
}

