package swipefit;

/**
 * Created by georgegabriel on 26/07/2017.
 */
public class Main {
    public static void main(String[] args) {
        /*try {
            connectDb();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        selectAll();*/
    }

    /*public static Connection connectDb() throws ClassNotFoundException,SQLException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/georgegabriel/Documents/licenta/SwipeFit-BackEnd/swipefit-database.db");
        return conn;
    }

    public static void selectAll(){
        String sql = "SELECT id, name FROM PRODUCTS";

        try {Connection conn = null;
            // db parameters
            String url = "jdbc:sqlite:/Users/georgegabriel/Documents/licenta/SwipeFit-BackEnd/swipefit-database.db";
            // create a connection to the database

            conn = DriverManager.getConnection(url);
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" +
                        rs.getString("name") + "\t");
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }*/

}
