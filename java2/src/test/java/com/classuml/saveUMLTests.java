package com.classuml;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class saveUMLTests {
    void isSavedClassContainerEmptyStringWithoutFilename() {
        ClassContainer cc = new ClassContainer();
        ClassBase cb = new ClassBase("");
        RelationshipContainer rc = new RelationshipContainer();
        cc.addClass(cb);
        assertEquals(cb, cc.getClassBase(""), "Initialized class is not the same returned");
        assertEquals("", cc.getClassBase("").getName(), "Class is not an empty string");
        saveUML.save(cc, rc);
        LoadUML luml = new LoadUML();
        luml.load("saveUML.json", cc, rc);
        assertEquals(cb, cc.getClassBase(""), "Loaded class is not the same saved");
        assertEquals("", cc.getClassBase("").getName(), "Loaded class is not an empty string");
    }

    void isSavedClassContainerEmptyStringWithFilename() {
        ClassContainer cc = new ClassContainer();
        ClassBase cb = new ClassBase("");
        RelationshipContainer rc = new RelationshipContainer();
        cc.addClass(cb);
        assertEquals(cb, cc.getClassBase(""), "Initialized class is not the same returned");
        assertEquals("", cc.getClassBase("").getName(), "Class is not an empty string");
        saveUML.save(cc, rc, "ExampleSave.json");
        LoadUML luml = new LoadUML();
        luml.load("ExampleSave.json", cc, rc);
        assertEquals(cb, cc.getClassBase(""), "Loaded class is not the same saved");
        assertEquals("", cc.getClassBase("").getName(), "Loaded class is not an empty string");
    }

    void doesSavedClassContainerHoldClassJohnWithNoAttributes() {
        ClassContainer cc = new ClassContainer();
        ClassBase cb = new ClassBase("John");
        RelationshipContainer rc = new RelationshipContainer();
        cc.addClass(cb);
        assertEquals(cb, cc.getClassBase("John"), "Initialized class is not the same returned");
        assertEquals("John", cc.getClassBase("John").getName(), "Class is not named \"John\"");
        saveUML.save(cc, rc);
        LoadUML luml = new LoadUML();
        luml.load("saveUML.json", cc, rc);
        assertEquals(cb, cc.getClassBase("John"), "Loaded class is not the same saved");
        assertEquals("John", cc.getClassBase("John").getName(), "Loaded class is not named \"John\"");
    }

    void doesSavedClassContainerHoldClassJohnWithAttributeSmith() {
        ClassContainer cc = new ClassContainer();
        ClassBase cb = new ClassBase("John");
        attributes attribute = new attributes();
        attribute.setName("Smith");
        cb.addAttribute(attribute);
        RelationshipContainer rc = new RelationshipContainer();
        cc.addClass(cb);
        assertEquals(cb, cc.getClassBase("John"), "Initialized class is not the same returned");
        assertEquals("John", cc.getClassBase("John").getName(), "Class is not named \"John\"");
        assertEquals("Smith", cb.getAttribute("Smith"), "Attribute in class \"John\" is not named \"Smith\"");
        saveUML.save(cc, rc);
        LoadUML luml = new LoadUML();
        luml.load("saveUML.json", cc, rc);
        assertEquals(cb, cc.getClassBase("John"), "Loaded class is not the same saved");
        assertEquals("John", cc.getClassBase("John").getName(), "Loaded class is not named \"John\"");
        assertEquals("Smith", cb.getAttribute("Smith").getName(), "Loaded attribute in class \"John\" is not named \"Smith\"");
    }
}