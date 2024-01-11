/*
 Name : James Bailey
 Student ID : 23673840
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * A utility class providing methods for converting InputStream to String.
 */
public class convertStreamToStringUtil {

    /**
     * Converts the provided InputStream to a String.
     *
     * @param inputStream The InputStream to be converted.
     * @return A String representation of the content in the InputStream.
     * @throws IOException If an I/O error occurs during the conversion.
     */
    public static String convertStreamToString(InputStream inputStream) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            // Using Java 8 Streams to efficiently collect lines and join them into a single String
            return bufferedReader.lines().collect(Collectors.joining(System.lineSeparator()));
        }
    }
}
