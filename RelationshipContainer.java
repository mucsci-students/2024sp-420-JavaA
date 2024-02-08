import java.util.ArrayList;
import java.util.List;

public class RelationshipContainer {
    private static List<Relationship> relationships = new ArrayList<>();
    
    /*
     * addRelationship - returns boolean based on whether or not a relationship was created
     * if there is already a relationship with that name then it will not add a duplicate
     * and returns false. If there was no match then it will call the setter for relationship
     * and add a new relationship to the container
     */
    public boolean addRelationship(String relName, String fromClass, String toClass){
        //loops through relationship container
        for (Relationship rel : relationships){ 
            if (rel.getName().equals(relName)){
                return false;
            }
        }

        //sets new relationship
        Relationship rel = new Relationship(relName, fromClass, toClass);
        relationships.add(rel);
        return true;
    }
    /*
     * deleteRelation - calls remove on relationship container from a given relationship
     * done so that users can call remove relationship and not have a relationship object
     * to call remove on relationships
     */
    private void deleteRelation(Relationship relationship){
        //removes the given relationship from the arraylist
        relationships.remove(relationship);
    }

    /*
     * Remove Relationship - returns a boolean value based on
     * whether or not there was a match found for the given relationship name
     * if there was it returns true if not it returns false
     */
    public boolean removeRelationship(String relationship){
        //loops through relationship container searching for a name match
        for (Relationship rel : relationships){
            if (rel.getName().equals(relationship)){
                deleteRelation(rel);
                return true;
            }
        }
        
        return false;
    }

    /*
     * getAllRelationships - getter for relationship container
     */
    public List<Relationship> getAllRelationships() {
        return relationships;
    }
}
