package swipefit;

import java.io.IOException;
import java.util.*;

public class MatrixManager {

    public static boolean flag = true;
    private static ArrayList<String> previousMap = new ArrayList<>();

    public static void generateOtherUsersBehaviour(HashMap<Integer,String> map) {
        Process p;
        try {
            p = Runtime.getRuntime().exec("R CMD BATCH /Users/georgegabriel/Documents/licenta/SwipeFit-BackEnd/src/main/resources/usersData.R");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<String[]> users = null;

        try {
            users = FileTransformer.readMatrix("/Users/georgegabriel/Documents/licenta/SwipeFit-BackEnd/src/main/resources/otherUsersData.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }


        if(flag) {
            previousMap = new ArrayList<String>(Arrays.asList(getLikesAndDislikes(map)));
            String[] previous = new String[previousMap.size()];
            flag=false;
            System.out.println(previousMap.toString());
            users.add(previousMap.toArray(previous));
            FileTransformer.writeMatrix(users);
        } else {
            ArrayList<String> currentMap = new ArrayList<>(Arrays.asList(getLikesAndDislikes(map)));
            for(int i = 0; i < currentMap.size(); i++) {
                if(currentMap.get(i).equals("0.5"))
                    currentMap.set(i,previousMap.get(i));
            }
            String[] current = new String[currentMap.size()];
            System.out.println(currentMap.toString());
            users.add(currentMap.toArray(current));
            previousMap = currentMap;
            FileTransformer.writeMatrix(users);
        }
    }

    public static String[] getLikesAndDislikes(HashMap<Integer,String> map) {

        List<String> likesAndDislikesList = new ArrayList<>();

        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            likesAndDislikesList.add(pair.getValue().toString());
            it.remove(); // avoids a ConcurrentModificationException
        }

        String[] likesAndDislikesArray = likesAndDislikesList.toArray(new String[15]);
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

    public static void runRecommendationEngine() {
        Process p;
        try {
            // maybe call this from another Program ??
            p = Runtime.getRuntime().exec("R CMD BATCH /Users/georgegabriel/Documents/licenta/SwipeFit-BackEnd/src/main/resources/backend_engine.R");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
