
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
    List<ForeignKeys> foreignKeyList;

    public Table(Database d,String name) {
        this.name = name;
        this.database=d;
        this.columnList = new ArrayList<Column>();
        try{
            DatabaseMetaData md = this.database.getConection().getMetaData();
            ResultSet rs = md.getColumns(null, null,this.name , null);
            while (rs.next()) {
                this.columnList.add(new Column(rs.getString(4),rs.getString(6)));
            }
        }catch(Exception e){
        
        
        }  
        
    }
    
    
    
    
}
