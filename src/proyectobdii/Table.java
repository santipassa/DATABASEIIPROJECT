
package proyectobdii;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        this.columnList = new ArrayList<>();
        this.primaryKeyList = new ArrayList<>();
        this.foreignKeyList = new ArrayList<>();
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
    //Hash code for tables
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.columnList);
        hash = 97 * hash + Objects.hashCode(this.triggerList);
        hash = 97 * hash + Objects.hashCode(this.foreignKeyList);
        hash = 97 * hash + Objects.hashCode(this.primaryKeyList);
        return hash;
    }
    //Equals for tables
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Table other = (Table) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.columnList, other.columnList)) {
            return false;
        }
        if (!Objects.equals(this.triggerList, other.triggerList)) {
            return false;
        }
        if (!Objects.equals(this.foreignKeyList, other.foreignKeyList)) {
            return false;
        }
        if (!Objects.equals(this.primaryKeyList, other.primaryKeyList)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
