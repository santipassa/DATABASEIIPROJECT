package proyectobdii;

import java.util.Objects;

/**
 *
 * @author santiago
 */
class Trigger {

    private String name;
    private String event;
    private String tableName;
    private String orientation;
    private String timing;

    public Trigger(String name, String event, String tableName, String orientation, String timing) {
        this.name = name;
        this.event = event;
        this.tableName = tableName;
        this.orientation = orientation;
        this.timing = timing;
    }
    
   
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public String toString() {
        return "Trigger{" + "name=" + name + ", event=" + event + ", tableName=" + tableName + ", orientation=" + orientation + ", timing=" + timing + '}';
    }

    

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

  

    public boolean equals(Trigger t) {
       return this.name.equals(t.getName()) && this.event.equals(t.getEvent()) && 
               this.tableName.equals(t.getTableName()) && this.orientation.equals(t.getOrientation())
               && this.timing.equals(t.getTiming());
    }
    
    
  
    
    

}
