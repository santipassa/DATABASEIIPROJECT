package proyectobdii;

import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author santiago
 */
class Table {

    private String name;
    private List<Column> columnList;
    private List<Trigger> triggerList;
    private List<ForeignKey> foreignKeyList;
    private List<PrimaryKey> primaryKeyList;

    public Table(DBConnection c, String name) {
        this.name = name;
        this.columnList = new ArrayList<>();
        this.primaryKeyList = new ArrayList<>();
        this.foreignKeyList = new ArrayList<>();
        this.triggerList = new ArrayList<>();
        try {
            DatabaseMetaData md = c.getConnection().getMetaData();

            ResultSet rs = md.getColumns(null, null, this.name, null);
            // Get columns names and types
            while (rs.next()) {
                this.columnList.add(new Column(rs.getString("COLUMN_NAME"), rs.getString("TYPE_NAME")));
            }
            // Get primary keys 
            rs = md.getPrimaryKeys(null, c.getBd(), this.name);
            while (rs.next()) {
                this.primaryKeyList.add(new PrimaryKey(rs.getString("COLUMN_NAME")));
            }
            //Get foreign keys
            rs = md.getImportedKeys(null, c.getBd(), this.name);
            while (rs.next()) {

                String fk_name = rs.getString("FK_NAME");

                DBConnection dbc = new DBConnection(c.getHost(), "INFORMATION_SCHEMA", c.getUser(), c.getPwd());

                String sql = "SELECT COLUMN_NAME,REFERENCED_TABLE_NAME,REFERENCED_COLUMN_NAME FROM KEY_COLUMN_USAGE WHERE TABLE_SCHEMA=? AND TABLE_NAME=? and CONSTRAINT_NAME=?";
                PreparedStatement query = dbc.getConnection().prepareStatement(sql);
                query.setString(1, c.getBd());
                query.setString(2, this.name);
                query.setString(3, fk_name);
                ResultSet key_column_usage = query.executeQuery();

                if (key_column_usage.next()) {
                    String key = key_column_usage.getString("COLUMN_NAME");
                    String tableReferences = key_column_usage.getString("REFERENCED_TABLE_NAME");
                    String referencesKey = key_column_usage.getString("REFERENCED_COLUMN_NAME");
                    foreignKeyList.add(new ForeignKey(key, tableReferences, referencesKey));

                }
                dbc.closeConnection();

            }
            //get triggers

            DBConnection dbc = new DBConnection(c.getHost(), "INFORMATION_SCHEMA", c.getUser(), c.getPwd());

            String sql = "SELECT TRIGGER_NAME,EVENT_MANIPULATION,ACTION_ORIENTATION,ACTION_TIMING FROM TRIGGERS WHERE TRIGGER_SCHEMA=? AND EVENT_OBJECT_TABLE=?";
            
            PreparedStatement query = dbc.getConnection().prepareStatement(sql);
            query.setString(1, c.getBd());
            query.setString(2, this.name);
            if(this.name.equals("Articulos")){
                
                System.out.println(query.toString());
            
            }
            ResultSet triggers = query.executeQuery();
            
            while (triggers.next()) {
                String tName = triggers.getString("TRIGGER_NAME");
                String tEvent = triggers.getString("EVENT_MANIPULATION");
                String tActionOrient = triggers.getString("ACTION_ORIENTATION");
                String tActionTiming = triggers.getString("ACTION_TIMING");
                triggerList.add(new Trigger(tName,tEvent,this.name,tActionOrient,tActionTiming));

            }
            dbc.closeConnection();
        } catch (Exception e) {
                System.out.println(e.toString());
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Column> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<Column> columnList) {
        this.columnList = columnList;
    }

    public List<Trigger> getTriggerList() {
        return triggerList;
    }

    public void setTriggerList(List<Trigger> triggerList) {
        this.triggerList = triggerList;
    }

    public List<ForeignKey> getForeignKeyList() {
        return foreignKeyList;
    }

    public void setForeignKeyList(List<ForeignKey> foreignKeyList) {
        this.foreignKeyList = foreignKeyList;
    }

    public List<PrimaryKey> getPrimaryKeyList() {
        return primaryKeyList;
    }

    public void setPrimaryKeyList(List<PrimaryKey> primaryKeyList) {
        this.primaryKeyList = primaryKeyList;
    }

    public boolean equals(Table t) {
        boolean result;
        result = (this.name.equals(t.getName())) && (this.columnList.size() == t.getColumnList().size())
                && (this.foreignKeyList.size() == t.getForeignKeyList().size())
                && (this.primaryKeyList.size() == t.getPrimaryKeyList().size())
                && (this.triggerList.size() == t.getTriggerList().size());
        if (result) {
            if (this.columnList.size() == t.getColumnList().size()) {
                for (int i = 0; i < this.columnList.size(); i++) {
                    result = this.columnList.get(i).equals(t.getColumnList().get(i)) && result;
                }
            }

            if (this.foreignKeyList.size() == t.getForeignKeyList().size()) {
                for (int i = 0; i < this.columnList.size(); i++) {
                    result = this.foreignKeyList.get(i).equals(t.getForeignKeyList().get(i)) && result;
                }
            }

            if (this.primaryKeyList.size() == t.getPrimaryKeyList().size()) {
                for (int i = 0; i < this.columnList.size(); i++) {
                    result = this.primaryKeyList.get(i).equals(t.getPrimaryKeyList().get(i)) && result;
                }
            }

            if (this.triggerList.size() == t.getTriggerList().size()) {
                for (int i = 0; i < this.columnList.size(); i++) {
                    result = this.triggerList.get(i).equals(t.getTriggerList().get(i)) && result;
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        String column = "";
        String trigger = "";
        String primaryKey = "";
        String foreignKey = "";
        for (Column c : columnList) {
            column = column + " " + c.toString();
        }
        for (Trigger t : this.triggerList) {
            
            trigger = t.toString() + " " + trigger;
        }
        for (PrimaryKey p : this.primaryKeyList) {
            primaryKey = p.toString() + " " + primaryKey;
        }
        for (ForeignKey f : this.foreignKeyList) {
            foreignKey = f.toString() + " " + foreignKey;
        }
        return "Table{" + "name=" + name +" "+trigger + " " + primaryKey + " " + column + " " + foreignKey + '}';
    }

}
