package swipefit;


import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by georgegabriel on 22/07/2017.
 */

public class Data {
    public String getData(String filePath) throws IOException, ParseException {

        StringBuilder result = new StringBuilder("");

        //Get file from resources folder
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(filePath).getFile());

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result.append(line).append("\n");
            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    // this is how we call the R script in the app - as a process

    /*public class Main {
        public static void main(String[] args) throws IOException {
            Process p;
            p = Runtime.getRuntime().exec("R CMD BATCH /Users/georgegabriel/Documents/js/testing/src/main/resources/test.R");
        }
    }*/
}