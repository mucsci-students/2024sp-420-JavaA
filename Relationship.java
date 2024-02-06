import java.util.ArrayList;
import java.util.List;

public class Relationship {
    private String name;
    private String fromClass;
    private String toClass;
    private static List<Relationship> relationships = new ArrayList<>();
    
    public void setRelationship(String name, String fromClass, String toClass) {

        //Variables to store name of relationship and the names of the two classes it belongs to
        this.name = name;
        this.fromClass = fromClass;
        this.toClass = toClass;

        //adds this relationship to the arraylist of relationships
        relationships.add(this);
    }
    private void deleteRelation(Relationship relationship){
        
        //removes the given relationship from the arraylist
        relationships.remove(relationship);
    }

    public void delRelation(String relationship){

        //finds if there is a relationship in relationships with the given name and calls deleteRelation if there is
        deleteRelation(getRelation(relationship));
    }
    public Relationship getRelation(String relName){
        for (Relationship rel : relationships){
            if (rel.getName().equals(relName)){
                return rel;
            }
        }
        System.out.println(relName + " does not exist");
        Relationship relTemp = new Relationship();
        relTemp.setRelationship(relName, "thisisnotarealclass", "thisisalsonotarealclass");
        return relTemp;
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
    public List<Relationship> getAllRelationships() {

        //returns list of all relationship
        return relationships;
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