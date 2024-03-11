package com.classuml;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

public class methodsTest {

    @Test
    public void testGetParams() {
        methods method = new methods("testMethod", "void");
        ArrayList<attributes> params = new ArrayList<>();
        attributes param1 = new attributes();
        param1.setName("param1");
        param1.setType("int");
        attributes param2 = new attributes();
        param2.setName("param2");
        param2.setType("String");
        params.add(param1);
        params.add(param2);
        method.changeAllParam(params);
        assertEquals(params, method.getParams());
    }

    @Test
    public void testAddParam() {
        methods method = new methods("testMethod", "void");
        method.addParam("param1", "int");
        method.addParam("param2", "String");
        ArrayList<attributes> params = method.getParams();
        assertEquals(2, params.size());
        assertEquals("param1", params.get(0).getName());
        assertEquals("int", params.get(0).getType());
        assertEquals("param2", params.get(1).getName());
        assertEquals("String", params.get(1).getType());
    }

    @Test
    public void testRemoveParam() {
        methods method = new methods("testMethod", "void");
        method.addParam("param1", "int");
        method.addParam("param2", "String");
        assertTrue(method.removeParam("param1"));
        assertEquals(1, method.getParams().size());
        assertFalse(method.removeParam("param1"));
        assertEquals(1, method.getParams().size());
    }

    @Test
    public void testRemoveAllParam() {
        methods method = new methods("testMethod", "void");
        method.addParam("param1", "int");
        method.addParam("param2", "String");
        method.removeAllParam();
        assertEquals(0, method.getParams().size());
    }

    @Test
    public void testChangeParam() {
        methods method = new methods("testMethod", "void");
        method.addParam("param1", "int");
        method.addParam("param2", "String");
        attributes newParam = new attributes();
        newParam.setName("newParam");
        newParam.setType("double");
        assertTrue(method.changeParam(newParam, "param1"));
        assertEquals(2, method.getParams().size());
        assertEquals("newParam", method.getParams().get(0).getName());
        assertEquals("double", method.getParams().get(0).getType());
        assertEquals("param2", method.getParams().get(1).getName());
        assertEquals("String", method.getParams().get(1).getType());
        assertFalse(method.changeParam(newParam, "param3"));
        assertEquals(2, method.getParams().size());
    }

    @Test
    public void testChangeAllParam() {
        methods method = new methods("testMethod", "void");
        method.addParam("param1", "int");
        method.addParam("param2", "String");
        ArrayList<attributes> newParams = new ArrayList<>();
        attributes param1 = new attributes();
        param1.setName("newParam1");
        param1.setType("double");
        attributes param2 = new attributes();
        param2.setName("newParam2");
        param2.setType("float");
        newParams.add(param1);
        newParams.add(param2);
        method.changeAllParam(newParams);
        assertEquals(2, method.getParams().size());
        assertEquals("newParam1", method.getParams().get(0).getName());
        assertEquals("double", method.getParams().get(0).getType());
        assertEquals("newParam2", method.getParams().get(1).getName());
        assertEquals("float", method.getParams().get(1).getType());
    }

    @Test
    public void testSetName() {
        methods method = new methods("testMethod", "void");
        method.setName("newTestMethod");
        assertEquals("newTestMethod", method.getName());
    }

    @Test
    public void testGetName() {
        methods method = new methods("testMethod", "void");
        assertEquals("testMethod", method.getName());
    }

    @Test
    public void testSetType() {
        methods method = new methods("testMethod", "void");
        method.setType("int");
        assertEquals("int", method.getType());
    }

    @Test
    public void testGetType() {
        methods method = new methods("testMethod", "void");
        assertEquals("void", method.getType());
    }

}