package com.classuml.Model;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Test;



public class ClassBaseTests {
private Object classBase = new ClassBase("testClass");
private TreeSet parameters = new TreeSet<>();
private TreeSet methods = new TreeSet<>();
private TreeSet fields = new TreeSet<>();
private TreeSet relationships = new TreeSet<>();

@Test
	public void Class() {
		ClassBase c = new ClassBase("test");
		assertEquals("test",c.getName());
		SortedSet<Parameter> p1 = new TreeSet<Parameter>();
		Method m = new Method("type","method",p1);
		SortedSet<Method> methods = new TreeSet<Method>();
		
		Field f = new Field("int","f1");
		SortedSet<Field> fields = new TreeSet<Field>();
		fields.add(f);
		Relationship r = new Relationship("rel","agg");
		SortedSet<Relationship> relationships = new TreeSet<Relationship>();
		relationships.add(r);
		
		ClassBase c1 = new ClassBase("test1",fields,methods,relationships, 0 , 0);
		assertTrue(c1.getFields().equals(fields));
		assertTrue(c1.getMethods().equals(methods));
		assertTrue(c1.getRelationships().equals(relationships));
		assertTrue(c1.getName().equals("test1"));
		
		Field f1 = new Field("int","f2");
		Relationship r1 = new Relationship("rel1","agg");
		Method m1 = new Method("type","method1",p1);
		
		fields.add(f1);
		relationships.add(r1);
		
		c1.setFields(fields);
		c1.setMethods(methods);
		c1.setName("test2");
		c1.setRelationships(relationships);
		
		assertTrue(c1.getFields().equals(fields));
		assertTrue(c1.getMethods().equals(methods));
		assertTrue(c1.getRelationships().equals(relationships));
		assertTrue(c1.getName().equals("test2"));
		
		assertTrue(c1.getField("f2").equals(f1));
		
		assertTrue(c1.getRelationship("rel1").equals(r1));
		
		assertEquals(false,c1.containsField("f4"));
		assertEquals(false,c1.containsMethod("f4",p1));
		assertEquals(false,c1.containsRelationship("test"));
		assertEquals(true,c1.containsField("f2"));
		
		assertEquals(true,c1.containsRelationship("rel1"));
		
		c1.addField("int","f3");
		assertEquals(true,c1.containsField("f3"));
		SortedSet<Parameter> p2 = new TreeSet<Parameter>();
		p2.add(new Parameter("int", "p1"));
		Method m3 = new Method("int","m3", p2);
		
		c1.addRelationship("rel3", "agg");
		assertEquals(true,c1.containsRelationship("rel3"));
		
		c1.deleteField("f3");
		
		c1.deleteRelationship("rel3");
		assertEquals(false,c1.containsField("f3"));
		assertEquals(false,c1.containsMethod(m3.getName(),m3.getParameters()));
		assertEquals(false,c1.containsRelationship("rel3"));
		
		ClassBase c2 = new ClassBase("test2",c1.getFields(),c1.getMethods(),c1.getRelationships(), 0, 0);;
		assertTrue(c1.equals(c2));

		ClassBase c3 = new ClassBase("test");
		c3.setName("updatedName");
		assertEquals("updatedName", c3.getName());
	    
		SortedSet<Field> fields1 = new TreeSet<>();
		Field f3 = new Field("String", "fieldName");
		fields1.add(f3);
		c3.setFields(fields1);
		assertEquals(fields1, c3.getFields());

		ClassBase c4 = new ClassBase("test");
		assertTrue(c4.getMethods().isEmpty());
		assertTrue(c4.getFields().isEmpty());
		assertTrue(c4.getRelationships().isEmpty());

		ClassBase c5 = new ClassBase("test");
		Method m2 = new Method("void");
		methods.add(m2);
		c5.setMethods(methods);
		assertEquals(1, c5.getMethods().size());
		assertTrue(c5.getMethods().contains(m2));
		

		ClassBase emptyClass = new ClassBase();
        	assertNotNull(emptyClass.getFields());
        	assertNotNull(emptyClass.getMethods());
        	assertNotNull(emptyClass.getRelationships());


		((ClassBase) classBase).setFields(Collections.emptySortedSet());
		((ClassBase) classBase).setMethods(Collections.emptySortedSet());
		((ClassBase) classBase).setRelationships(Collections.emptySortedSet());
		
		assertTrue(((ClassBase) classBase).getFields().isEmpty());
		assertTrue(((ClassBase) classBase).getMethods().isEmpty());
		assertTrue(((ClassBase) classBase).getRelationships().isEmpty());

		((ClassBase) classBase).addMethod(null, "method1", parameters);
		String classString = classBase.toString();
		assertNotNull(classString);
		assertFalse(classString.isEmpty());

		Relationship relationship = new Relationship("relType", "relName");
		relationships.add(relationship);
		((ClassBase) classBase).setRelationships(relationships);
		
		assertEquals(relationships, ((ClassBase) classBase).getRelationships());
		
		((ClassBase) classBase).deleteRelationship("relName");
		assertFalse(((ClassBase) classBase).containsRelationship("relName"));

		String guiString = ((ClassBase) classBase).toStringGUI();
		assertNotNull(guiString);
		
		assertNull(((ClassBase) classBase).getMethod("nonExistingMethod", parameters));


		int xLocation = 100;
		((ClassBase) classBase).setXLocation(xLocation);
		assertEquals(xLocation, ((ClassBase) classBase).getXLocation());
	
		String result = ((ClassBase) classBase).toString();
		assertNotNull(result);
		assertFalse(result.isEmpty());

		classBase = new ClassBase();
		assertTrue(((ClassBase) classBase).addMethod("exampleMethod"));
		
		SortedSet<Parameter> parameters = new TreeSet<>();
		assertNull(((ClassBase) classBase).getMethod("nonExistingMethod", parameters));

		classBase = new ClassBase();
		new Method(null);

		String returnType = "void"; 
		String methodName = "testMethod";
		SortedSet<Parameter> parameters1 = new TreeSet<>();
		parameters1.add(new Parameter("String", "param1"));
		parameters1.add(new Parameter("int", "param2"));
	
		assertTrue("Method should be added successfully", 
		((ClassBase) classBase).addMethod(returnType, methodName, parameters1));
		boolean result1 = ((ClassBase) classBase).deleteMethod(methodName, parameters1);
		assertTrue("Method should be deleted when it exists", result1);

        	assertThrows(IllegalArgumentException.class, () -> {
            		new ClassBase().Method(null);
        	});		
		
		ClassBase instance = new ClassBase();
		String expectedName = "validMethodName";
		instance.Method(expectedName);
		assertEquals("The name should be set when a valid name is passed", expectedName, instance.getName());
	    
		((ClassBase) classBase).setYLocation(100);
		int yLocation = ((ClassBase) classBase).getYLocation();

		// Assert - the Y location is as expected
		assertEquals("getYLocation should return the Y location that was set", 100, yLocation);
	    

		
	}
}
