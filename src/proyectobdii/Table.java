
package proyectobdii;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author santiago
 */
class Table {
    Database database;
    String name;
    List<Column> columnList;
    List<Trigger> triggerList;
    List<ForeignKey> foreignKeyList;
    List<PrimaryKey> primaryKeyList;
    

    public Table(Database d,String name) {
        this.name = name;
        this.database=d;
        this.columnList = new ArrayList<Column>();
        this.primaryKeyList = new ArrayList<PrimaryKey>();
        this.foreignKeyList = new ArrayList<ForeignKey>();
        try{
            DatabaseMetaData md = this.database.getConection().getMetaData();
            ResultSet rs = md.getColumns(null, null,this.name , null);
            // Get the columns names and types
            while (rs.next()) {
                this.columnList.add(new Column(rs.getString("COLUMN_NAME"),rs.getString("TYPE_NAME")));
            }
            // Get the primary keys 
            rs = md.getPrimaryKeys(null, null, this.name);
             while (rs.next()) {
                this.primaryKeyList.add(new PrimaryKey(rs.getString("COLUMN_NAME")));
            }
             //Get the foreign keys
             rs = md.getImportedKeys(null, null, this.name);
             while (rs.next()) {
                this.foreignKeyList.add(new ForeignKey(rs.getString("FK_NAME")));
            }
            
        }catch(Exception e){
        
        
        }  
        
    }
    
    
    
    
}
