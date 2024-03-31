package com.classuml;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class ClassContainerTest {

    private ClassContainer classContainer = new ClassContainer();

    public void main() {
        classContainer = new ClassContainer();
    }

    @Test
    void testAddClass() {
        ClassBase newClass = new ClassBase("NewClass");
        assertEquals("Class NewClass was added.", classContainer.addClass(newClass));
        assertEquals(1, classContainer.getContainer().size());
    }

    @Test
    void testAddEmptyClass() {
        ClassBase newClass = new ClassBase("");
        assertEquals("Class name cannot be empty or invalid characters!", classContainer.addClass(newClass));
    }

    @Test
    void testAddBigEmptyClass() {
        ClassBase newClass = new ClassBase("               ");
        assertEquals("Class name cannot be empty or invalid characters!", classContainer.addClass(newClass));
    }

    @Test
    void testAddClassWithSpace() {
        ClassBase newClass = new ClassBase("bong bing");
        assertEquals("Class name cannot be empty or invalid characters!", classContainer.addClass(newClass));
    }

    @Test
    void testRemoveClass() {
        ClassBase newClass = new ClassBase("NewClass");
        classContainer.addClass(newClass);
        assertEquals("Class was successfully removed!", classContainer.removeClass("NewClass"));
        assertEquals(0, classContainer.getContainer().size());
    }

    @Test
    void testRenameClass() {
        ClassBase newClass = new ClassBase("NewClass");
        classContainer.addClass(newClass);
        assertEquals("Class NewClass was successfully renamed to NewRenamedClass.", classContainer.renameClass("NewClass", "NewRenamedClass"));
        assertEquals("NewRenamedClass", newClass.getName());
    }

    @Test
    void testGetClassBase() {
        ClassBase newClass = new ClassBase("NewClass");
        classContainer.addClass(newClass);
        assertEquals(newClass, classContainer.getClassBase("NewClass"));
    }

    @Test
    void testGetContainer() {
        ClassBase newClass1 = new ClassBase("NewClass1");
        ClassBase newClass2 = new ClassBase("NewClass2");
        classContainer.addClass(newClass1);
        classContainer.addClass(newClass2);
        ArrayList<ClassBase> classList = new ArrayList<>();
        classList.add(newClass1);
        classList.add(newClass2);
        assertEquals(classList, classContainer.getContainer());
    }
}