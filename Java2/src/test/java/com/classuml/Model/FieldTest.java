package com.classuml.Model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FieldTest {
        	@Test
	public void Field() {
		Field f = new Field("string","test");
		assertEquals("test",f.getName());
		assertEquals("string",f.getType());
		f.setName("test1");
		f.setType("int");
		assertEquals("test1",f.getName());
		assertEquals("int",f.getType());
		assertEquals("int test1", f.toString());
		Field f1 = new Field("int","test1");
		assertTrue(f.equals(f1));
		f1.setName("test2");
		assertFalse(f.equals(f1));

    Field field = new Field();
    assertNotNull("The Field instance should not be null", field);
	}
}
