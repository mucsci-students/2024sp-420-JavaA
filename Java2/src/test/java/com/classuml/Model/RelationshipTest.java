package com.classuml.Model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RelationshipTest {
  private com.classuml.Model.Relationship relationship;

  @Test
	public void Relationship() {
		Relationship r = new Relationship("test","composition");
		assertEquals("test",r.getDestination());
		assertEquals("composition",r.getType());
		r.setDestination("test1");
		r.setType("agg");
		assertEquals("test1",r.getDestination());
		assertEquals("agg",r.getType());
		assertEquals("test1 agg", r.toString());
		Relationship r1 = new Relationship("test1","agg");
		assertTrue(r.equals(r1));
		r1.setDestination("test2");
		assertFalse(r.equals(r1));

    relationship = new Relationship();
	}
}
