package swipefit;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
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

    public static void writeMatrix(List<String[]> data) {
        StringWriter writer = new StringWriter();

        List<Product> products;

        //List<String[]> data = toStringArray(emps);
        //TODO cauta in articol: http://www.journaldev.com/12014/opencsv-csvreader-csvwriter-example

        CSVWriter output = new CSVWriter(writer,' ',' ',"\n");

        output.writeAll(data);

        try {
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(writer);
    }
}
