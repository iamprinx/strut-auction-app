package connection;

import java.sql.DriverManager;
import java.sql.Connection;

/**
 * This brings database into the application. Helps to connect application to 
 * the database.
 * @author i-am-prinx
 */
public class ConnectionFactory {
    
    private static String URL = "";
    private static String USER = "root";
    
    
    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(URL, USER, "");
        } catch (Exception e) {
            System.out.println("Connection Error: \n" + e );
        }
        return null;
    }
    
    
    
}
