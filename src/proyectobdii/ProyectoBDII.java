package proyectobdii;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author santiago
 */
public class ProyectoBDII {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException, FileNotFoundException, UnsupportedEncodingException, IOException {
       Properties cfgFile = new Properties();
       InputStream input = null;
       // args1 path del archivo de salida
       //args0 path del archivo de las bases
       input = new FileInputStream(args[0]);
               
       cfgFile.load(input);
       
       Database db1 = new Database(cfgFile.getProperty("host_1"),cfgFile.getProperty("schema_1"),cfgFile.getProperty("user_1"),cfgFile.getProperty("pass_1"));
       Database db2 = new Database(cfgFile.getProperty("host_2"),cfgFile.getProperty("schema_2"),cfgFile.getProperty("user_2"),cfgFile.getProperty("pass_2"));
       db1.connect();
       db2.connect();
       db1.equals(db2);
       PrintWriter writer = new PrintWriter(args[1], "UTF-8");
       for(Diff d :Comparator.getDifferences()){
           writer.println(d.toString());
       }
       writer.close();
        
    }
}
