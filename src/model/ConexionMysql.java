
package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexionMysql {
    
 private static final String base = "FP_206_Java_con_MYSQL";
    private static final String user = "root";
    private static final String password = "UOC12345";
    private static final String url = "jdbc:mysql://localhost:3306/" + base;
    private static Connection con = null;

    public static Connection getConexion() {
        Connection con = null;
        try {

            con = DriverManager.getConnection(url,user,password);
        } catch(SQLException e) {
            System.err.println(e);
        } 
        return con;
    }
}
