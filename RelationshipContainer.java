import java.util.ArrayList;
import java.util.List;

public class RelationshipContainer {
    private static List<Relationship> relationships = new ArrayList<>();
    
    public void addRelationship(String relName, String fromClass, String toClass){
        Relationship rel = new Relationship();
        rel.setRelationship(relName, fromClass, toClass);
        relationships.add(rel);
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
            else{
                System.out.println("No Relationship with that name");
                return null;
            }
        }
        Relationship relTemp = new Relationship();
        return relTemp;

    }
    public List<Relationship> getAllRelationships() {

        //returns list of all relationship
        return relationships;
    }
}
