/**
 * Code by Vasilis Bougiamas and Eric Almonrode
 * This code needs json-simple-1.1.1.jar
 * Known objects to read from the JSON file:
 * ClassBase vars:
 *      className (String)
 *      attributes (ArrayList of attributes type)
 * Attributes vars:
 *      name (attributes String name)
 *      content (Arbitrary data type represented as a generic)
 * Relationship vars:
 *      name (String)
 *      fromClass; (String name of fromClass)
 *      toClass; (String name of toClass)
 *      List<Relationship> relationships (a static ArrayList of relationships)
 */

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LoadUML 
{

    /**
     *  Method to load UML data from a file
     *  @param fileName The name of the JSON file to load.
     *  @param classContainer An empty container of classes to be filled in from JSON file.
     *  @param relationshipContainer An empty container of relationships to be filled in from JSON file.
     */
    public void load(String fileName, ClassContainer classContainer, RelationshipContainer relationshipContainer) 
    {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(fileName)) 
        {
            Object obj = parser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            loadProcedure(jsonObject, classContainer, relationshipContainer);
            System.out.println("Loaded UML ");
        } 
        catch (IOException | ParseException e) 
        {
            e.printStackTrace();
        }
    }

    /**
     *  Iterates through the JSONObject and JSONArrays to put them into the containers
     * 
     *  Attributes are put into an ArrayList of attributes and added to their class
     * 
     *  Classes are put into the ClassContainer
     *  Relationships are put into the RelationshipContainer
     * 
     *  @param jsonObject This local object that holds the collective JSON data.
     *  @param classContainer An empty container of classes to be filled in from JSON file.
     *  @param relationshipContainer An empty container of relationships to be filled in from JSON file.
     */
    private void loadProcedure(JSONObject jsonObject, ClassContainer classContainer, RelationshipContainer relationshipContainer) 
    {
        JSONArray classesArray = (JSONArray) jsonObject.get("Classes");
        JSONArray relationshipsArray = (JSONArray) jsonObject.get("Relationships");
    
        //load relationships
        for (Object relationshipObj : relationshipsArray) 
        {
            JSONObject relationshipJson = (JSONObject) relationshipObj;
            String name = (String) relationshipJson.get("name");
            String fromClass = (String) relationshipJson.get("from class");
            String toClass = (String) relationshipJson.get("to class");
            Relationship relationship = new Relationship();
            relationship.setRelationship(name, fromClass, toClass);
            relationshipContainer.addRelationship(name, fromClass, toClass);
        }

        //load classes
        for (Object classObj : classesArray) 
        {
            JSONObject classJson = (JSONObject) classObj;
            String className = (String) classJson.get("name");
            ArrayList<attributes> classAttributes = new ArrayList<>();
            JSONArray attributesArray = (JSONArray) classJson.get("attributes");
            for (Object attributeObj : attributesArray) 
            {
                JSONObject attributeJson = (JSONObject) attributeObj;
                String attributeName = (String) attributeJson.get("name");
                String attributeContent = (String) attributeJson.get("content");
                attributes attribute = new attributes();
                attribute.setName(attributeName);
                attribute.setContent(attributeContent);
                classAttributes.add(attribute);
            }
            ClassBase classBase = new ClassBase(className);
            for (attributes att : classAttributes) 
            {
                classBase.addAttribute(att);
            }
            classContainer.addClass(classBase); // Add the class to the ClassContainer
        }
    }
}