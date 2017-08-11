package swipefit;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

import static swipefit.Data.isInitialDataFlag;
import static swipefit.Data.setInitialDataFlag;
import static swipefit.InputMatrixManager.generateOtherUsersBehaviour;
import static swipefit.InputMatrixManager.runRecommendationEngine;
import static swipefit.JSON2Database.pushFavorites2Database;
import static swipefit.Utils.loadFavorites;
import static swipefit.Utils.requestHandler;

@RestController
public class DataController {


    /**
     *          GET method requests
     * */

    @RequestMapping(value = "/getProducts", method = RequestMethod.GET)
    public String fetchData() {
        if(isInitialDataFlag()) {
            setInitialDataFlag(false);
            return Database2JSON.getJsonFromListOfProducts(Database2JSON.getListOfProducts());
        }
        return Database2JSON.getJsonFromListOfProducts(Database2JSON.getListOfRecommendedProducts());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getFavorites")
    public String fetchFavorites() {
        return Database2JSON.getJsonFromListOfProducts(Database2JSON.getListOfFavorites());
    }

    /**
     *          POST method requests
     * */

    @RequestMapping(method = RequestMethod.POST, value = "/postBehaviour")
    public void dataFromAndroidTest(@RequestBody String json) {
        HashMap<Integer,String> map = requestHandler(json);
        generateOtherUsersBehaviour(map);
        runRecommendationEngine();
    }


    @RequestMapping(method = RequestMethod.POST, value = "/postFavorites")
    public void fetchFavorites(@RequestBody String json) {
        pushFavorites2Database(loadFavorites(json));
    }

}

