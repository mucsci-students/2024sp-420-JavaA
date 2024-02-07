public class Relationship {

    private String name;
    private String fromClass;
    private String toClass;

    public void setRelationship(String name, String fromClass, String toClass){

        //sets names of variables in class
        this.name = name;
        this.fromClass = fromClass;
        this.toClass = toClass;

    }

    public String getName(){

        //returns name of relationship
        return name;
    }
    public String getFromClass(){

        //returns the first class in the relationship
        return fromClass;
    }
    public String getToClass(){

        //returns the second class in the relationship
        return toClass;
    }

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