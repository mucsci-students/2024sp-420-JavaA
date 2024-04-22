package com.classuml.Model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ParameterTest {
  private com.classuml.Model.Parameter parameter;
  private com.classuml.Model.Parameter params3;

  @Test
	public void Parameter() {
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

    parameter = new Parameter("string", "paramName");
    assertNotNull("Parameter should be created", parameter);
    assertEquals("Parameter name should be 'paramName'", "paramName", parameter.getName());
    assertEquals("Parameter type should be 'string'", "string", parameter.getType());
    parameter.setName("newName");
    assertEquals("Parameter name should be changed to 'newName'", "newName", parameter.getName());
    parameter.setType("int");
    assertEquals("Parameter type should be changed to 'int'", "int", parameter.getType());

    Parameter differentParameter = new Parameter("int", "paramName");
    assertFalse("Parameter should not be equal to another parameter with a different type", parameter.equals(differentParameter));        
    Parameter lessParameter = new Parameter("string", "lessParam");
    assertTrue("compareTo should return a positive number when this name is more", parameter.compareTo(lessParameter) > 0);
            
    parameter.setType("int");
    assertEquals("Parameter type should now be 'int'", "int", parameter.getType());
    Parameter param1 = new Parameter("string", "paramName");
    Parameter param2 = new Parameter("string", "paramName");
    assertTrue("Parameters with the same name should be equal", param1.equals(param2));
            
    Parameter param3 = new Parameter("string", "paramName");
    Parameter param4 = new Parameter("string", "otherName");
    assertFalse("Parameters with different names should not be equal", param3.equals(param4));
    Parameter parameter = new Parameter();
    assertNull("Name should be null by default", parameter.getName());
    assertNull("Type should be null by default", parameter.getType());
	}
}
