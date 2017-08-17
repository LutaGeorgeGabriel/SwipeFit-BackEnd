package swipefit.database_transformer;

import swipefit.product.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by georgegabriel on 29/07/2017.
 */
public class JSON2Database {

    public static void pushFavorites2Database(List<Product> favorites) {
        try {
            connectDb();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        Statement statement = null;
        // db parameters
        String url = "jdbc:sqlite:/Users/georgegabriel/Documents/licenta/SwipeFit-BackEnd/swipefit-database.db";
        // create a connection to the database

        try {
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();


            String query = "SELECT id, url, name, site, retailer, price  FROM FAVORITES";
            ResultSet result = statement.executeQuery(query);
            List<Product> existingFavorites = new ArrayList<>();

            while (result.next()) {
                existingFavorites.add(new Product(result.getString("url"),
                        result.getString("name"),
                        result.getString("site"),
                        result.getString("retailer"),
                        result.getDouble("price"),
                        result.getInt("id")));
            }

            if(existingFavorites.isEmpty()) {
                for(Product favorite : favorites) {
                    String sql = "INSERT INTO FAVORITES (id, url, name, site, retailer, price) VALUES " +
                            "( " +
                            favorite.getID() + "," + "\"" +
                            favorite.getUrl() + "\"," + "\"" +
                            favorite.getName() + "\"," + "\"" +
                            favorite.getSite() + "\"," + "\"" +
                            favorite.getRetailer() + "\"," +
                            favorite.getPrice() + ");";
                    statement.executeUpdate(sql);
                }
            } else {
                for(Product favorite : favorites) {
                    if(existingFavorites.contains(favorite))
                        continue;
                    String sql = "INSERT INTO FAVORITES (id, url, name, site, retailer, price) VALUES " +
                            "( " +
                            favorite.getID() + "," + "\"" +
                            favorite.getUrl() + "\"," + "\"" +
                            favorite.getName() + "\"," + "\"" +
                            favorite.getSite() + "\"," + "\"" +
                            favorite.getRetailer() + "\"," +
                            favorite.getPrice() + ");";
                    statement.executeUpdate(sql);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteFavoriteItemFromDB(String id) {
        try {
            connectDb();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        Statement statement = null;
        // db parameters
        String url = "jdbc:sqlite:/Users/georgegabriel/Documents/licenta/SwipeFit-BackEnd/swipefit-database.db";
        // create a connection to the database
        String query = "DELETE FROM FAVORITES WHERE id=" + id;

        try {
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection connectDb() throws ClassNotFoundException,SQLException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/georgegabriel/Documents/licenta/SwipeFit-BackEnd/swipefit-database.db");
        return conn;
    }
}
