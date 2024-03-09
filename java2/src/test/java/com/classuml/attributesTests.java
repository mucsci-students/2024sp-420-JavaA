package com.classuml;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
public class attributesTests {
    @Test
    void isNameEmpty() {
        attributes attribute = new attributes();
        attribute.setName("");
        assertEquals("", attribute.getName(), "Name is not empty");
    }

    @Test
    void isNameNull() {
        attributes attribute = new attributes();
        assertEquals(null, attribute.getName(), "Name is not null");
    }

    @Test
    void isNameJohn() {
        attributes attribute = new attributes();
        attribute.setName("John");
        assertEquals("John", attribute.getName(), "Name is not \"John\"");
    }

    @Test
    void isTypeEmpty() {
        attributes attribute = new attributes();
        attribute.setType("");
        assertEquals("", attribute.getType(), "Type is not empty");
    }

    @Test
    void isTypeNull() {
        attributes attribute = new attributes();
        assertEquals(null, attribute.getType(), "Type is not null");
    }

    @Test
    void isTypeJohn() {
        attributes attribute = new attributes();
        attribute.setType("John");
        assertEquals("John", attribute.getType(), "Type is not \"John\"");
    }
}