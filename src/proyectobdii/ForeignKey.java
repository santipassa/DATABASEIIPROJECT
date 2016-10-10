package proyectobdii;

/**
 *
 * @author santiago
 */
class ForeignKey {

    private String key;
    private String tableReferences;
    private String referencesKey;

    public ForeignKey(String key, String tableReferences, String referencesKey) {
        this.key = key;
        this.tableReferences = tableReferences;
        this.referencesKey = referencesKey;
    }

    public boolean equals(ForeignKey k) {
        return this.key.equals(k.getKey()) && this.tableReferences.equals(k.getTableReferences()) && this.referencesKey.equals(k.getReferencesKey());
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTableReferences() {
        return tableReferences;
    }

    public void setTableReferences(String tableReferences) {
        this.tableReferences = tableReferences;
    }

    public String getReferencesKey() {
        return referencesKey;
    }

    public void setReferencesKey(String referencesKey) {
        this.referencesKey = referencesKey;
    }

    @Override
    public String toString() {
        return "ForeignKey{" + "key=" + key + ", tableReferences=" + tableReferences + ", referencesKey=" + referencesKey + '}';
    }

}
