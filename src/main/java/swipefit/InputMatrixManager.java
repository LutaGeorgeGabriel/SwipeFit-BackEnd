package swipefit;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by georgegabriel on 26/07/2017.
 */
public class InputMatrixManager {

    /*public static void main(String[] args) {
        generateOtherUsersBehaviour();
    }*/

    public static void generateOtherUsersBehaviour() {
        Process p;
        try {
§            // maybe call this from another Program ??
            p = Runtime.getRuntime().exec("R CMD BATCH /Users/georgegabriel/Documents/licenta/SwipeFit-BackEnd/src/main/resources/usersData.R");
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*try {
            Thread.sleep(200);
            p = Runtime.getRuntime().exec("R CMD BATCH /Users/georgegabriel/Documents/licenta/SwipeFit-BackEnd/src/main/resources/usersData.R");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        List<String[]> users = null;

        try {
            users = FileTransformer.readMatrix("/Users/georgegabriel/Documents/licenta/SwipeFit-BackEnd/src/main/resources/otherUsersData.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        users.add(DataController.getLikesAndDislikes());

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("/Users/georgegabriel/Documents/licenta/SwipeFit-BackEnd/src/main/resources/otherUsersData.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //using custom delimiter and quote character
        CSVWriter csvWriter = new CSVWriter(fileWriter,' ', ' ', "\n");
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
