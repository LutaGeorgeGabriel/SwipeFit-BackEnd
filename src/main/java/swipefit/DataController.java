package swipefit;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Created by georgegabriel on 22/07/2017.
 */

@RestController
public class DataController {

    private static double[] info = null;
    private ArrayList<String> json = new ArrayList<>();

    @RequestMapping(value = "/swipeFitProducts", method = RequestMethod.GET)
    public String fetchData() {
        //return new Data().getData("data.json");
        return Database2JSON.getJsonFromListOfProducts(Database2JSON.getListOfProducts());
    }

    // --------------- TEST data

    @RequestMapping(value = "/swipeFitProducts",method = RequestMethod.POST)
    public void fetchProductsInformation(@RequestBody double[] productsInformation) {
        info = productsInformation;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/test")
    public ArrayList<String> getData() {
        return json;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/test")
    public void dataFromAndroidTest(@RequestBody String json) {
        requestHandler(json);
    }

    public void requestHandler(String json) {
        this.json.add(json);
    }
}

