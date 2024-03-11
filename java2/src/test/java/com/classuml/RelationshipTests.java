package com.classuml;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class RelationshipTests {

    private Relationship relationship1;
    private Relationship relationship2;

    Relationship setUpRelationship1() {
        relationship1 = new Relationship();
        relationship1.setRelationship("OldSource1", "OldDest1", "Aggregation");
        return relationship1;
    }

    Relationship setUpRelationship2() {
        relationship1 = new Relationship();
        relationship1.setRelationship("OldSource2", "OldDest2", "Inheritance");
        return relationship1;
    }

    @Test
    void areRelSourceAndDestClassesInitializedCorrectly() {
        this.relationship1 = setUpRelationship1();
        this.relationship2 = setUpRelationship2();
        assertEquals("OldSource1", this.relationship1.getSourceClass(), 
            "Relationship 1's source class is not initialized \"OldSource1\"");
        assertEquals("OldSource2", this.relationship2.getSourceClass(), 
            "Relationship 2's source class is not initialized \"OldSource2\"");
        assertEquals("OldDest1", this.relationship1.getDestClass(), 
            "Relationship 1's destination class is not initialized \"OldDest1\"");
        assertEquals("OldDest2", this.relationship2.getDestClass(), 
            "Relationship 2's destination class is not initialized \"OldDest2\"");
    }

    @Test
    void areRelSourceAndDestClassesModifiedCorrectly() {
        this.relationship1 = setUpRelationship1();
        this.relationship2 = setUpRelationship2();
        assertEquals("OldSource1", this.relationship1.getSourceClass(), 
            "Relationship 1's source class is not initialized \"OldSource1\"");
        assertEquals("OldSource2", this.relationship2.getSourceClass(), 
            "Relationship 2's source class is not initialized \"OldSource2\"");
        assertEquals("OldDest1", this.relationship1.getDestClass(), 
            "Relationship 1's destination class is not initialized \"OldDest1\"");
        assertEquals("OldDest2", this.relationship2.getDestClass(), 
            "Relationship 2's destination class is not initialized \"OldDest2\"");
        this.relationship1.setSourceClass("NewSource1");
        this.relationship2.setSourceClass("NewSource2");
        this.relationship1.setDestClass("NewDest1");
        this.relationship2.setDestClass("NewDest2");
        assertEquals("NewSource1", this.relationship1.getSourceClass(), 
            "Relationship 1's source class is not modified to \"NewSource1\"");
        assertEquals("NewSource2", this.relationship2.getSourceClass(), 
            "Relationship 2's source class is not modified to \"NewSource2\"");
        assertEquals("NewDest1", this.relationship1.getDestClass(), 
            "Relationship 1's destination class is not modified to \"NewDest1\"");
        assertEquals("NewDest2", this.relationship2.getDestClass(), 
            "Relationship 2's destination class is not modified to \"NewDest2\"");
    }

    @Test
    void areRelTestsModifiedCorrectly() {
        this.relationship1 = setUpRelationship1();
        this.relationship2 = setUpRelationship2();
        assertEquals("Aggregation", this.relationship1.getType(), 
            "Relationship 1's relation type is not initialized (case-insensitive) \"Aggregation\"");
        assertEquals("Inheritance", this.relationship2.getType(), 
            "Relationship 2's relation type is not initialized (case-insensitive) \"Inheritance\"");
        this.relationship1.setType("Composition");
        this.relationship2.setType("Realization");
        assertEquals("Composition", this.relationship1.getType(), 
            "Relationship 1's relation type is not modified to (case-insensitive) \"Composition\"");
        assertEquals("Realization", this.relationship2.getType(), 
            "Relationship 2's relation type is not modified to (case-insensitive) \"Realization\"");
    }
}