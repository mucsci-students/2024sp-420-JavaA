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
package com.classuml;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javafx.scene.control.Alert;

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
    public void load(String fileName, ClassContainer classContainer, RelationshipContainer relationshipContainer, String cligui) 
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
            if (cligui.equals("gui")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Load File");
                alert.setContentText("An error occurred while loading the file. Please make sure the file is valid.");
                alert.showAndWait();
            }
            System.out.println("This is an invalid file name.");
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
            String sourceClass = (String) relationshipJson.get("Source Class");
            String destClass = (String) relationshipJson.get("Destination Class");
            String relType = (String) relationshipJson.get("Type");
            relationshipContainer.addRelationship(sourceClass, destClass, relType);
        }

        //load classes
        for (Object classObj : classesArray) 
        {
            JSONObject classJson = (JSONObject) classObj;
            ArrayList<attributes> classAttributes = new ArrayList<>();
            ArrayList<methods> classMethods = new ArrayList<>();
            String className = (String) classJson.get("Name");
            JSONArray attributesArray = (JSONArray) classJson.get("Fields");
            JSONArray methodsArray = (JSONArray) classJson.get("Methods");
            for (Object attributeObj : attributesArray) 
            {
                JSONObject attributeJson = (JSONObject) attributeObj;
                String attributeName = (String) attributeJson.get("Name");
                String attributeType = (String) attributeJson.get("Type");
                attributes attribute = new attributes();
                attribute.setName(attributeName);
                attribute.setType(attributeType);
                classAttributes.add(attribute);
            }
            for (Object methodObj : methodsArray){
                JSONObject methodJson = (JSONObject) methodObj;
                String methodName = (String) methodJson.get("Name");
                String methodType = (String) methodJson.get("Return type");
                JSONArray paramsArray = (JSONArray) methodJson.get("Paramaters");
                methods newMethod = new methods(methodName, methodType);
                for (Object paramObj : paramsArray){
                    JSONObject paramJson = (JSONObject) paramObj;
                    String paramName = (String) paramJson.get("Name");
                    String paramType = (String) paramJson.get("Type");
                    newMethod.addParam(paramName, paramType);
                }
                classMethods.add(newMethod);
            }
            ClassBase classBase = new ClassBase(className);
            for (attributes att : classAttributes) 
            {
                classBase.addAttribute(att);
            }
            for (methods meth : classMethods){
                classBase.addMethod(meth);
            }
            classContainer.addClass(classBase); // Add the class to the ClassContainer
        }
    }
}