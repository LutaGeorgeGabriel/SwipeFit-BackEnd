package swipefit;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import static swipefit.InputMatrixManager.generateOtherUsersBehaviour;

/**
 * Created by georgegabriel on 22/07/2017.
 */

@RestController
public class DataController {

    private static double[] info = null;
    private static HashMap<String,String> productsInformation = new HashMap<>();
    private static boolean INITIAL_DATA_FLAG = true;

    public static void setInitialDataFlag(boolean initialDataFlag) {
        INITIAL_DATA_FLAG = initialDataFlag;
    }

    public static HashMap<String, String> getProductsInformation() {
        return productsInformation;
    }



    // --------------- TEST data

    @RequestMapping(value = "/swipeFitProducts", method = RequestMethod.GET)
    public String fetchData() {
        //return new Data().getData("data.json");
        if(INITIAL_DATA_FLAG) {
            INITIAL_DATA_FLAG = false;
            return Database2JSON.getJsonFromListOfProducts(Database2JSON.getListOfProducts());
        }
        return Database2JSON.getJsonFromListOfProducts(Database2JSON.getListOfRecommendedProducts());
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

