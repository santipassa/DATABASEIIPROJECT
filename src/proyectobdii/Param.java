package proyectobdii;

/**
 *
 * @author santiago
 */
public class Param {

    private String name;
    private String type;
    private String mode;// can be input out or inout

    public Param(String name, String type, String mode) {
        this.name = name;
        this.type = type;
        this.mode = mode;
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

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public boolean equals(Param p) {
        return (this.name.equals(p.getName()) && this.type.equals(p.getType()) && this.mode.equals(p.getMode()));

    }

    @Override
    public String toString() {
        return "Param{" + "name=" + name + ", type=" + type + ", mode=" + mode + '}';
    }
    

}
