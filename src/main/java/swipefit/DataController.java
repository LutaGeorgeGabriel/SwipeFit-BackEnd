package swipefit;

import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by georgegabriel on 22/07/2017.
 */

@RestController
public class DataController {

    private static double[] info = null;

    @RequestMapping(value = "/swipeFitProducts", method = RequestMethod.GET)
    public String fetchData() {
        try {
            return new Data().getData("data.json");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "Error occured";
    }

    @RequestMapping(value = "/swipeFitProducts",method = RequestMethod.POST)
    public void fetchProductsInformation(@RequestBody double[] productsInformation) {
        info = productsInformation;
    }
}

