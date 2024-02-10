import java.util.List;

public class Relationship {

    private String name;
    private String fromClass;
    private String toClass;

    /*
     * setRelationship - public setter for naming a relationship
     */
    public void setRelationship(String name, String fromClass, String toClass){

        //sets names of variables in class
        this.name = name;
        this.fromClass = fromClass;
        this.toClass = toClass;

    }

    /*
     * getName - public getter for relationship name
     */
    public String getName(){

        //returns name of relationship
        return name;
    }

    /*
     * getFromClass - public getter for fromClass
     */
    public String getFromClass(){

        //returns the first class in the relationship
        return fromClass;
    }

    /*
     * getToClass - public getter for toClass
     */
    public String getToClass(){

        //returns the second class in the relationship
        return toClass;
    }

    public void setFromClass(String newFromClass){
        this.fromClass = newFromClass;
    }

    public void setToClass(String newToClass){
        this.toClass = newToClass;
    }

    public Relationship getRelation(String name)
    {
        LoadUML load = new LoadUML();
        return load.getRelation(name);
    }

    public void delRelation(String name)
    {
        LoadUML load = new LoadUML();
        load.delRelation(name);
    }

    public List<Relationship> getAllRelationships()
    {
        LoadUML load = new LoadUML();
        return load.getAllRelationships();
    }

    /*
     * toString - override for toString to fit JSON format
     */
    @Override
    public String toString() 
    {
        return "Relationship{" +
            "name='" + name + '\'' +
            ", fromClass='" + fromClass + '\'' +
            ", toClass='" + toClass + '\'' +
            '}';
    }
}