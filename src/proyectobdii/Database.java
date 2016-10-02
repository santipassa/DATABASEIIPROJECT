
package proyectobdii;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author santiago
 */
public class Database {

    String name;
    Connection conection;
    List<Table> tableList;
    List<Procedure> procedureList;

    public Database(String host, String bd, String user, String pwd) {
        this.name = bd;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            //conexion = DriverManager.getConnection("jdbc:mysql://"+host+":3306/"+bd,user,pwd);
            conection = DriverManager.getConnection("jdbc:mysql://localhost:3306/RioCuartoCamping", "root", "root");
            tableList = new ArrayList<Table>();
            procedureList = new ArrayList<Procedure>();
            DatabaseMetaData md = conection.getMetaData();
            ResultSet rs = md.getTables(null, null, "%", null);
            while (rs.next()) {
                tableList.add(new Table(this,rs.getString(3)));
            }
            rs = md.getProcedures(null, this.name, null);
            while (rs.next()) {
                procedureList.add(new Procedure(rs.getString(3)));
            }
            
            
        } catch (Exception e) {

        }
    }

    public Connection getConection() {
        return conection;
    }

    public void setConection(Connection conection) {
        this.conection = conection;
    }

}