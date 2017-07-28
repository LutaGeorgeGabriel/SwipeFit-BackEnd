package swipefit;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
            // maybe call this from another Program ??
            p = Runtime.getRuntime().exec("R CMD BATCH /Users/georgegabriel/Documents/licenta/SwipeFit-BackEnd/src/main/resources/usersData.R");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<String[]> users = null;

        try {
            users = FileTransformer.readMatrix("/Users/georgegabriel/Documents/licenta/SwipeFit-BackEnd/src/main/resources/otherUsersData.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        users.add(getLikesAndDislikes());

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

    public static String[] getLikesAndDislikes() {

        List<String> likesAndDislikesList = new ArrayList<>();

        Iterator it = DataController.getProductsInformation().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            likesAndDislikesList.add(pair.getValue().toString());
            it.remove(); // avoids a ConcurrentModificationException
        }

        String[] likesAndDislikesArray = likesAndDislikesList.toArray(new String[10]);
        return likesAndDislikesArray;
    }

    public static String[] getRecommendedIDs() {
        List<String[]> users = null;
        try {
            users = FileTransformer.readMatrix("/Users/georgegabriel/Documents/licenta/SwipeFit-BackEnd/src/main/resources/ml_output.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users.get(users.size()-1);
    }





}
