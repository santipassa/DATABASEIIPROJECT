
package proyectobdii;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author santiago
 */
public class ProyectoBDII {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
       try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            //conexion = DriverManager.getConnection("jdbc:mysql://"+host+":3306/"+bd,user,pwd);
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/RioCuartoCamping", "root", "root");
           
            DatabaseMetaData md = connection.getMetaData();
            
            ResultSet rs = md.getImportedKeys(null, null, "Articulos_vendidos");
            while (rs.next()) {
                System.out.println(rs.getString(12));
            }
        } catch (Exception e) {

        }
    }  
}
