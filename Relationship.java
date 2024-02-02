import java.util.ArrayList;
import java.util.List;

public class Relationship {
    private String name;
    private String firstClass;
    private String secondClass;
    private static List<Relationship> relationships = new ArrayList<>();
    
    public Relationship(String name, String firstClass, String secondClass) {

        //Variables to store name of relationship and the names of the two classes it belongs to
        this.name = name;
        this.firstClass = firstClass;
        this.secondClass = secondClass;

        //adds this relationship to the arraylist of relationships
        relationships.add(this);
    }
    private void deleteRelation(Relationship relationship){
        
        //removes the given relationship from the arraylist
        relationships.remove(relationship);
    }

    public void setRelation(String name, String firstClass, String secondClass){

        //sets names of variables in class
        this.name = name;
        this.firstClass = firstClass;
        this.secondClass = secondClass;
    }
    public void delRelation(String relationship){

        //finds if there is a relationship in relationships with the given name and calls deleteRelation if there is
        for (Relationship rel : relationships){
            if (rel.getName().equals(relationship)){
                deleteRelation(rel);
            }
        }
    }
    public String getName(){
        
        //returns name of relationship
        return name;
    }
    public String getFirstClass(){

        //returns the first class in the relationship
        return firstClass;
    }
    public String getSecondClass(){

        //returns the second class in the relationship
        return secondClass;
    }
    public List<Relationship> getAllRelationships() {

        //returns list of all relationship
        return relationships;
    }
}