package proyectobdii;

/**
 *
 * @author santiago
 */
class Column {

    private String name;
    private String type;

    public Column(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean equals(Column c) {
        if(!this.name.equals(c.getName())){
            Comparator.addDiff( new Diff("Different column name",this.name,c.getName()));
        
        }
        if(!this.type.equals(c.getType())){
            Comparator.addDiff( new Diff("Different column type",this.type,c.getType()));
        
        }
        return (this.name.equals(c.getName()) && this.type.equals(c.getType()));
        
    }

    @Override
    public String toString() {
        return "Column{" + "name=" + name + ", type=" + type + '}';
    }

}
