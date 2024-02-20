package com.classuml;
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
    public boolean addRelationship(String sourceClass, String destClass){
        //loops through relationship container
        for (Relationship rel : relationships){ 
            if (rel.getSourceClass().equals(sourceClass) && rel.getDestClass().equals(destClass)){
                return false;
            }
        }

        //sets new relationship
        Relationship rel = new Relationship();
        rel.setRelationship(sourceClass, destClass);
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
    public boolean removeRelationship(String sourceClass, String destClass){
        //loops through relationship container searching for a name match
        for (Relationship rel : relationships){
            if (rel.getSourceClass().equals(sourceClass) && rel.getDestClass().equals(destClass)){
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
