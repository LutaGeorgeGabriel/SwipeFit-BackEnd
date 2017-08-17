package swipefit.utilities;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by georgegabriel on 25/07/2017.
 */
public class FileTransformer {

    static int[][] matrix = new int[200][50];

    // reads the data from input.txt

    public static List<String[]> readMatrix(String file) throws IOException {
        CSVReader input = new CSVReader(
                new FileReader(file), ' ');
        // Read all rows at once

        List<String[]> allRows = input.readAll();

        return allRows;
    }

    public static void writeMatrix(List<String[]> users) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("/Users/georgegabriel/Documents/licenta/SwipeFit-BackEnd/src/main/resources/otherUsersData.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //using custom delimiter and quote character
        CSVWriter csvWriter = new CSVWriter(fileWriter, ' ', ' ', "\n");
        csvWriter.writeAll(users);
        try {
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
