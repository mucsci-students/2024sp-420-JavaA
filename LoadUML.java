/**
 * Code by Vasilis Bougiamas
 * This code needs json-simple-1.1.1.jar
 * Known objects to read from the JSON file:
 * ClassBase vars:
 *      className (String)
 *      attributes (ArrayList of attributes type)
 * Attributes vars:
 *      name (attributes String name)
 *      className (String name of class to which the attribute is associated with)
 *      content (Arbitrary data type represented as a generic)
 * Relationship vars:
 *      name (String)
 *      fromClass; (String name of fromClass)
 *      toClass; (String name of toClass)
 *      List<Relationship> relationships (a static ArrayList of relationships)
 */
import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class LoadUML 
{
    public void loadUML() 
    {
        JSONParser parser = new JSONParser();
        try 
        {
            Object obj = parser.parse(new FileReader("UML.json"));
            JSONObject jsonObject = (JSONObject) obj;
     
            // Load ClassBase
            JSONObject classBase = (JSONObject) jsonObject.get("ClassBase");
            String className = (String) classBase.get("className");

            JSONArray attributesArray = (JSONArray) classBase.get("attributes");
            ArrayList<attributes> classAttributes = new ArrayList<>();
            for (Object attributeObj : attributesArray) 
            {
                JSONObject attributeJson = (JSONObject) attributeObj;
                String attributeName = (String) attributeJson.get("name");
                // Parse content according to its data type
                // Assuming content is a String for now
                String attributeContent = (String) attributeJson.get("content");
                attributes attribute = new attributes(attributeName, attributeContent);
                classAttributes.add(attribute);
            }

            // Load Relationships
            JSONArray relationshipsArray = (JSONArray) jsonObject.get("Relationships");
            ArrayList<Relationship> relationships = new ArrayList<>();
            for (Object relationshipObj : relationshipsArray) 
            {
                JSONObject relationshipJson = (JSONObject) relationshipObj;
                String relationshipName = (String) relationshipJson.get("name");
                String fromClass = (String) relationshipJson.get("fromClass");
                String toClass = (String) relationshipJson.get("toClass");
                Relationship relationship = new Relationship(relationshipName, fromClass, toClass);
                relationships.add(relationship);
            }

            // Output loaded data
            System.out.println("Class: " + className);
            System.out.println("Attributes:");
            for (attributes attribute : classAttributes) 
            {
                System.out.println(attribute);
            }
            System.out.println("Relationships:");
            for (Relationship relationship : relationships) 
            {
                System.out.println(relationship);
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}     