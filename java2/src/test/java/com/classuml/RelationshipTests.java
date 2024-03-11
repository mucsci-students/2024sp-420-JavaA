package com.classuml;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RelationshipTests {

    @Test
    public void testSetType() {
        Relationship relationship = new Relationship();
        assertTrue(relationship.setType("aggregation"));
        assertEquals("aggregation", relationship.getType().toLowerCase());
        assertTrue(relationship.setType("composition"));
        assertEquals("composition", relationship.getType().toLowerCase());
        assertTrue(relationship.setType("inheritence"));
        assertEquals("inheritence", relationship.getType().toLowerCase());
        assertTrue(relationship.setType("realization"));
        assertEquals("realization", relationship.getType().toLowerCase());
    }

    @Test
    public void testGetType() {
        Relationship relationship = new Relationship();
        relationship.setType("aggregation");
        assertEquals("Aggregation", relationship.getType());
        relationship.setType("composition");
        assertEquals("Composition", relationship.getType());
        relationship.setType("inheritence");
        assertEquals("Inheritence", relationship.getType());
        relationship.setType("realization");
        assertEquals("Realization", relationship.getType());
    }

    @Test
    public void testSetRelationship() {
        Relationship relationship = new Relationship();
        relationship.setRelationship("sourceClass", "destClass", "aggregation");
        assertEquals("sourceClass", relationship.getSourceClass());
        assertEquals("destClass", relationship.getDestClass());
        assertEquals("aggregation", relationship.getType().toLowerCase());
    }

    @Test
    public void testGetSourceClass() {
        Relationship relationship = new Relationship();
        relationship.setRelationship("sourceClass", "destClass", "aggregation");
        assertEquals("sourceClass", relationship.getSourceClass());
    }

    @Test
    public void testGetDestClass() {
        Relationship relationship = new Relationship();
        relationship.setRelationship("sourceClass", "destClass", "aggregation");
        assertEquals("destClass", relationship.getDestClass());
    }

}