package proyectobdii;


import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 *
 * @author santiago
 */
public class ProyectoBDII {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException, FileNotFoundException {
        Database rc = new Database();
        rc.connect("localhost", "RioCuartoCamping", "root", "root");
        
        
        System.out.println("tables: "+rc.getTableCount());
        PrintWriter out = new PrintWriter("/home/santiago/Escritorio/database.txt");
        out.print(rc.toString());
        out.close();
    }
}
