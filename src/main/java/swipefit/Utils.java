package swipefit;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Utils {
    public static List<Product> loadFavorites(String json){
        try{
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            JSONArray array = new JSONArray(json);
            List<Product> productList = new ArrayList<>();
            for(int i=0;i<array.length();i++){
                Product product = gson.fromJson(array.getString(i), Product.class);
                productList.add(product);
            }
            return productList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static HashMap<Integer,String> requestHandler(String json) {
        Type type = new TypeToken<HashMap<Integer, String>>(){}.getType();
        Gson gson = new Gson();
        Data.setUserBehaviour(gson.fromJson(json,type));
        return gson.fromJson(json,type);
    }
}
