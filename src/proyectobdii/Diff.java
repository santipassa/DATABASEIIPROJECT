
package proyectobdii;

/**
 *
 * @author santiago
 */
public class Diff<T,E> {
    private String name;
    private T fst;
    private E snd;

    public Diff(String name,T fst, E snd) {
        this.fst = fst;
        this.snd = snd;
        this.name=name;
    }

    public T getFst() {
        return fst;
    }

    public void setFst(T fst) {
        this.fst = fst;
    }

    public E getSnd() {
        return snd;
    }

    public void setSnd(E snd) {
        this.snd = snd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String toString(){
        return this.name+" "+this.fst.toString()+" "+this.snd.toString();
    
    }
    
    
    
    
}
