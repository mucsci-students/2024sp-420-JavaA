package com.classuml;

//import org.junit.Before;
//import org.junit.Test;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.List;

public class ClassBaseTest {

    private ClassBase classBase = new ClassBase("TestClass");

    public void main() {
        classBase = new ClassBase("TestClass");
    }

    @Test
    void testGetName() {
        assertEquals("TestClass", classBase.getName());
    }

    @Test
    void testSetName() {
        classBase.setName("NewTestClass");
        assertEquals("NewTestClass", classBase.getName());
    }

    @Test
    void testSetEquals() {
        ClassBase input = new ClassBase("InputClass");
        classBase.setEquals(input);
        assertEquals("TestClass", input.getName());
        assertEquals(classBase.getClassAttributes(), input.getClassAttributes());
    }

    @Test
    void testAddAttribute() {
        attributes att = new attributes();
        att.setName("testAtt");
        att.setType("int");
        classBase.addAttribute(att);
        assertEquals(1, classBase.getClassAttributes().size());
        attributes att2 = new attributes();
        att.setName("testAtt2");
        att.setType("int2");
        classBase.addAttribute(att2);
        assertNotEquals(1, classBase.getClassAttributes().size());
    }

    @Test
    void testAddMethod() {
        methods meth = new methods("testMeth", "void");
        classBase.addMethod(meth);
        assertEquals(1, classBase.getClassMethods().size());
        methods meth2 = new methods("testMeth2", "void");
        classBase.addMethod(meth2);
        assertNotEquals(1, classBase.getClassMethods().size());
    }

    @Test
    void testUpdateAttribute() {
        attributes att = new attributes();
        att.setName("testAtt");
        att.setType("int");
        classBase.addAttribute(att);
        classBase.updateAttribute(att, "Name", "NewTestAtt");
        assertEquals("NewTestAtt", att.getName());
        classBase.updateAttribute(att, "Type", "double");
        assertEquals("double", att.getType());
    }

    @Test
    void testUpdateMethod() {
        methods meth = new methods("testMeth", "void");
        classBase.addMethod(meth);
        classBase.updateMethod(meth, "NewTestMeth","Name");
        assertEquals("NewTestMeth", meth.getName());
        classBase.updateMethod(meth, "String","Type");
        assertEquals("String", meth.getType());
    }

    @Test
    void testDeleteAttribute() {
        attributes att = new attributes();
        att.setName("testAtt");
        att.setType("int");
        classBase.addAttribute(att);
        classBase.deleteAttribute(att);
        assertEquals(0, classBase.getClassAttributes().size());
    }

    @Test
    void testDeleteMethod() {
        methods meth = new methods("testMeth", "void");
        classBase.addMethod(meth);
        classBase.deleteMethod(meth);
        assertEquals(0, classBase.getClassMethods().size());
    }

    @Test
    void testGetAttribute() {
        attributes att = new attributes();
        att.setName("testAtt");
        att.setType("int");
        classBase.addAttribute(att);
        assertEquals(att, classBase.getAttribute("testAtt"));
    }

    @Test
    void testGetClassAttributes() {
        attributes att = new attributes();
        att.setName("testAtt");
        att.setType("int");
        classBase.addAttribute(att);
        List<attributes> attList = new ArrayList<>();
        attList.add(att);
        assertEquals(attList, classBase.getClassAttributes());
    }

    @Test
    void testGetClassMethods() {
        methods meth = new methods("testMeth", "void");
        classBase.addMethod(meth);
        ArrayList<methods> methList = new ArrayList<>();
        methList.add(meth);
        assertEquals(methList, classBase.getClassMethods());
    }
}