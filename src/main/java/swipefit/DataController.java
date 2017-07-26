package swipefit;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.*;

import static swipefit.InputMatrixManager.generateOtherUsersBehaviour;

/**
 * Created by georgegabriel on 22/07/2017.
 */

@RestController
public class DataController {

    private static double[] info = null;
    private static HashMap<String,String> productsInformation = new HashMap<>();

    public static String[] getLikesAndDislikes() {

        List<String> likesAndDislikesList = new ArrayList<>();

        Iterator it = productsInformation.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            likesAndDislikesList.add(pair.getValue().toString());
            it.remove(); // avoids a ConcurrentModificationException
        }

        String[] likesAndDislikesArray = likesAndDislikesList.toArray(new String[10]);
        return likesAndDislikesArray;
    }

    // --------------- TEST data

    @RequestMapping(value = "/swipeFitProducts", method = RequestMethod.GET)
    public String fetchData() {
        //return new Data().getData("data.json");
        return Database2JSON.getJsonFromListOfProducts(Database2JSON.getListOfProducts());
    }

    @RequestMapping(value = "/swipeFitProducts",method = RequestMethod.POST)
    public void fetchProductsInformation(@RequestBody double[] productsInformation) {
        info = productsInformation;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/test")
    public HashMap<String,String> getData() {
        return productsInformation;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/test")
    public void dataFromAndroidTest(@RequestBody String json) {
        requestHandler(json);
        generateOtherUsersBehaviour();
    }

    public void requestHandler(String json) {
        Type type = new TypeToken<HashMap<String, String>>(){}.getType();
        Gson gson = new Gson();
        Map<String, String> myMap = gson.fromJson(json, type);
        productsInformation = gson.fromJson(json,type);
    }
}

