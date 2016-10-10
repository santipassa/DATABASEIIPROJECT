package proyectobdii;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author santiago
 */
public class Database {

    private String name;
    private DBConnection connection;
    private List<Table> tableList;
    private List<Procedure> procedureList;

    public Database() {
    }

    public boolean connect(String host, String bd, String user, String pwd) {
        this.name = bd;
        try {
            
            tableList = new ArrayList<>();
            procedureList = new ArrayList<>();
            this.connection = new DBConnection(host,bd,user,pwd);
            
            DatabaseMetaData md = this.connection.getConnection().getMetaData();
            
            ResultSet rs = md.getTables(null, bd, null, null);
            while (rs.next()) {
                Table t = new Table(this.connection, rs.getString("TABLE_NAME"));
                tableList.add(t);
            }
            rs = md.getProcedures(null, this.name, null);
            while (rs.next()) {
                procedureList.add(new Procedure(connection, rs.getString("PROCEDURE_NAME")));
            }
            return true;

        } catch (Exception e) {
            
            return false;
        }

    }

    @Override
    public String toString() {
        String table ="";
        String procedure="";
        for (Table t : this.tableList) {
            table=t.toString()+" "+table;
            
        }
        for (Procedure p : this.procedureList) {
            procedure=p.toString()+" "+procedure;
        }
        return "Database{" + "name=" + name +" "+table+" "+procedure+ '}';
    }
    
    public int getTableCount(){
        return this.tableList.size();
    }
    
    public int getProcedureCount(){
        return this.procedureList.size();
    }
    

}
