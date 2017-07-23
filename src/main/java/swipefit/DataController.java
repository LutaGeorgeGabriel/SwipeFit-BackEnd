package swipefit;

import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by georgegabriel on 22/07/2017.
 */

@RestController
public class DataController {
    @RequestMapping(value = "/swipeFitProducts", method = RequestMethod.GET)
    public String greeting(@RequestParam(value="product", defaultValue="clothing") String product) {
        try {
            return new Data().getData("data.json");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "Error occured";
    }

}

