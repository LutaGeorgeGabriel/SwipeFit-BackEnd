package swipefit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by georgegabriel on 23/07/2017.
 */

public class DatabaseEmulator {
    private static List<Product> products = new ArrayList<>();

    public static List<Product> getProducts() {
        return products;
    }

    public static void setProducts(List<Product> products) {
        DatabaseEmulator.products = products;
    }
}
