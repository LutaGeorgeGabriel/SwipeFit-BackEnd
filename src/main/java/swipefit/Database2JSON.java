package swipefit;

import com.google.gson.Gson;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by georgegabriel on 26/07/2017.
 */
public class Database2JSON {

    public static Connection connectDb() throws ClassNotFoundException,SQLException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/georgegabriel/Documents/licenta/SwipeFit-BackEnd/swipefit-database.db");
        return conn;
    }

    public static List<Product> getListOfFavorites() {
        try {
            connectDb();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM FAVORITES";

        try {Connection conn = null;
            // db parameters
            String url = "jdbc:sqlite:/Users/georgegabriel/Documents/licenta/SwipeFit-BackEnd/swipefit-database.db";
            // create a connection to the database

            conn = DriverManager.getConnection(url);
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                products.add(new Product(rs.getString("url"),
                        rs.getString("name"),
                        rs.getString("site"),
                        rs.getString("retailer"),
                        rs.getDouble("price"),
                        rs.getInt("id")));
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return products;
    }

    public static List<Product> getListOfProducts(){

        try {
            connectDb();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Product> products = new ArrayList<>();
        String sql = "SELECT id, url, name, site, retailer, price  FROM PRODUCTS";

        try {Connection conn = null;
            // db parameters
            String url = "jdbc:sqlite:/Users/georgegabriel/Documents/licenta/SwipeFit-BackEnd/swipefit-database.db";
            // create a connection to the database

            conn = DriverManager.getConnection(url);
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                products.add(new Product(rs.getString("url"),
                        rs.getString("name"),
                        rs.getString("site"),
                        rs.getString("retailer"),
                        rs.getDouble("price"),
                        rs.getInt("id")));
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return products;
    }

    public static String getJsonFromListOfProducts(List<Product> products) {
        Gson gson = new Gson();
        String jsonlistOfProducts = gson.toJson(products);
        return jsonlistOfProducts;
    }

    public static List<Product> getListOfRecommendedProducts() {
        List<Product> productList = new ArrayList<>();
        String[] products = MatrixManager.getRecommendedIDs();
        try {
            connectDb();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Connection connection = null;
            // db parameters
            String url = "jdbc:sqlite:/Users/georgegabriel/Documents/licenta/SwipeFit-BackEnd/swipefit-database.db";
            // create a connection to the database
        try {
            connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();

            for (int index = 0; index < products.length; index++) {
                String sql = "SELECT id, url, name, site, retailer, price  FROM PRODUCTS WHERE id=" + products[index];

                ResultSet rs = statement.executeQuery(sql);

                while (rs.next()) {
                    productList.add(new Product(rs.getString("url"),
                            rs.getString("name"),
                            rs.getString("site"),
                            rs.getString("retailer"),
                            rs.getDouble("price"),
                            rs.getInt("id")));
                }
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return productList;
    }

    public static String constructArrayOfProducts(String[] products) {
        StringBuilder result = new StringBuilder();
        result.append("(");
        for (int i = 0; i < products.length;i++) {
            result.append(products[i]);
            if(i != products.length - 1)
                result.append(",");
        }
        result.append(")");
        return result.toString();
    }

}
