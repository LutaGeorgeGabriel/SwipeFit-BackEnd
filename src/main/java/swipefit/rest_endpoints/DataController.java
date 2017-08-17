package swipefit.rest_endpoints;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import swipefit.database_transformer.Database2JSON;

import static swipefit.database_transformer.JSON2Database.deleteFavoriteItemFromDB;
import static swipefit.database_transformer.JSON2Database.pushFavorites2Database;
import static swipefit.machine_learning.MatrixManager.generateOtherUsersBehaviour;
import static swipefit.machine_learning.MatrixManager.runRecommendationEngine;
import static swipefit.utilities.Data.isInitialDataFlag;
import static swipefit.utilities.Data.setInitialDataFlag;
import static swipefit.utilities.Utils.loadFavorites;
import static swipefit.utilities.Utils.requestHandler;

@RestController
public class DataController {
    /**
     *          GET method requests
     * */

    @RequestMapping(method = RequestMethod.GET, value = "/getProducts")
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
    public void pushData(@RequestBody String json) {
        generateOtherUsersBehaviour(requestHandler(json));
        runRecommendationEngine();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/postFavorites")
    public void fetchFavorites(@RequestBody String json) {
        pushFavorites2Database(loadFavorites(json));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/deleteItem")
    public void deleteFavoriteItem(@RequestBody String json) {
        deleteFavoriteItemFromDB(json);
    }

}

