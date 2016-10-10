package proyectobdii;


/**
 *
 * @author santiago
 */
class PrimaryKey {
    private String key;
    
    public PrimaryKey(String key) {
        this.key=key;
    
    }


    public boolean equals(PrimaryKey k) {
       return this.key.equals(k.getKey());
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "PrimaryKey{" + "key=" + key + '}';
    }
    
    

}
