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
    private String host;
    private String bd;
    private String user;
    private String pwd;

    public Database(String host, String bd, String user, String pwd) {
        this.host = host;
        this.bd = bd;
        this.user = user;
        this.pwd = pwd;
    }
    

    public boolean connect() {
        this.name = this.bd;
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

    public String getName() {
        return name;
    }

    public DBConnection getConnection() {
        return connection;
    }

    public void setConnection(DBConnection connection) {
        this.connection = connection;
    }

    public List<Table> getTableList() {
        return tableList;
    }

    public void setTableList(List<Table> tableList) {
        this.tableList = tableList;
    }

    public List<Procedure> getProcedureList() {
        return procedureList;
    }

    public void setProcedureList(List<Procedure> procedureList) {
        this.procedureList = procedureList;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getBd() {
        return bd;
    }

    public void setBd(String bd) {
        this.bd = bd;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    
    public boolean equals(Database d) {
        boolean result;
        result = (this.name.equals(d.getName())) && (this.tableList.size() == d.getTableList().size())
                && (this.procedureList.size() == d.getProcedureList().size());
       if(!this.name.equals(d.getName())){
           Comparator.addDiff( new Diff("Different database names",this.name,d.getName()));
       
       }
       if(this.tableList.size() != d.getTableList().size()){
           Comparator.addDiff( new Diff("Different number of tables",this.tableList.size(),d.getTableList().size()));
       
       }
        if(this.procedureList.size() != d.getProcedureList().size()){
           Comparator.addDiff( new Diff("Different number of procedures",this.procedureList.size(),d.getProcedureList().size()));
       
       }
    
            if (this.tableList.size() == d.getTableList().size()) {
                for (int i = 0; i < this.tableList.size(); i++) {
                    if(!this.tableList.get(i).equals(d.getTableList().get(i))){
                        Comparator.addDiff( new Diff("Different tables",this.tableList.get(i),d.getTableList().get(i)));
                        result = false && result;
                    }
                }
            }

            if (this.procedureList.size() == d.getProcedureList().size()) {
                for (int i = 0; i < this.procedureList.size(); i++) {
                     if(!this.procedureList.get(i).equals(d.getProcedureList().get(i))){
                        Comparator.addDiff( new Diff("Different Procedures",this.procedureList.get(i),d.getProcedureList().get(i)));
                        result = false && result;
                    }
                }
            }

            
        
        return result;
    }
    

}
