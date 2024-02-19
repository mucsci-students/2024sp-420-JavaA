package com.classuml;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.io.*;

public class saveUML {

    /*
     * All of the JSONObjects and JSONArrays that
     * are used throughout the save process
     */
    private static JSONObject saved;
    private static JSONArray saveClasses;
    private static JSONObject saveClass;
    private static JSONArray saveRelationships;
    private static JSONObject saveRelationship;
    private static JSONArray saveAtts;
    private static JSONObject saveAtt;

    /*
     * Called to initiate the saving process
     * 
     * @Variables       myClassContainer - Contains all the classes in the UML editor
     *                  myRelationshipContainer - Contains all the relationships in the UML editor
     * 
     * @Preconditions   
     * @Postconditions  A save file is created with a Json object containing the contnent from the UML editor
     * 
     * @Returns         
     */
    public static void save(ClassContainer myClassContainer, RelationshipContainer myRelationshipContainer){
        saved = new JSONObject();
        saveProcedure(myClassContainer, myRelationshipContainer);

        //try to create the new file saveUML.json and save data to that file
        try{
            //will create a file named saveUML.json, if a file already exists, no error will occur
            File saveFile = new File("saveUML.json");
            saveFile.createNewFile();

            //writes the information to the file
            FileOutputStream writeToFile = new FileOutputStream("saveUML.json");
            writeToFile.write(saved.toJSONString().getBytes());
            writeToFile.close();
        }
        catch(IOException except){
            System.out.println("An error occured while creating this file");
            System.out.println(except.toString());
        }
        
        
        System.out.println(saved.toString());

    }

    /*
     * Called to initiate the saving process
     * 
     * @Variables       myClassContainer - Contains all the classes in the UML editor
     *                  myRelationshipContainer - Contains all the relationships in the UML editor
     *                  fileName - The name of the save file
     * 
     * @Preconditions   
     * @Postconditions  A save file is created with a Json object containing the contnent from the UML editor
     * 
     * @Returns         
     */
    public static void save(ClassContainer myClassContainer, RelationshipContainer myRelationshipContainer, String fileName){
        saved = new JSONObject();
        saveProcedure(myClassContainer, myRelationshipContainer);
        try{
            //will create a file named saveUML.json, if a file already exists, no error will occur
            File saveFile = new File(fileName +".json");
            saveFile.createNewFile();

            //writes the information to the file
            FileOutputStream writeToFile = new FileOutputStream(fileName + ".json");
            writeToFile.write(saved.toJSONString().getBytes());
            writeToFile.close();
        }
        catch(IOException except){
            System.out.println("An error occured while creating this file");
            System.out.println(except.toString());
        }
        
        
        System.out.println(saved.toString());
    }

    /*
     * Used by save to convert all the Classes, Attributes, and Relationships to a Json Object
     * 
     * @Variables       myClassContainer - Contains all the classes in the UML editor
     *                  myRelationshipContainer - Contains all the relationships in the UML editor
     * 
     * @Preconditions   
     * @Postconditions  Classes, Attrinutes, and Relationships are all in the Json Object
     * 
     * @Returns         
     */
    private static void saveProcedure(ClassContainer myClassContainer, RelationshipContainer myRelationshipContainer){
        saveClasses = new JSONArray();
        saveRelationships = new JSONArray();
        

        //loop for iterating through classes
        for (ClassBase classes: myClassContainer.getContainer()){
            saveClass = new JSONObject();
            saveAtts = new JSONArray();
            saveClass.put("name", classes.getName());

            //loop for iterating through a classes attributes
            
            for(attributes att: classes.getClassAttributes()){
                saveAtt = new JSONObject();
                saveAtt.put("name", att.getName());
                saveAtt.put("content", att.getContent());
                saveAtts.add(saveAtt);
            }
            saveClass.put("attributes", saveAtts);
            saveClasses.add(saveClass);
        }

        //loop for iterating through relationships
        for(Relationship relate: myRelationshipContainer.getAllRelationships()){
            saveRelationship = new JSONObject();
            saveRelationship.put("name", relate.getName());
            saveRelationship.put("from class", relate.getFromClass());
            saveRelationship.put("to class", relate.getToClass());
            saveRelationships.add(saveRelationship);
        }

        saved.put("Classes", saveClasses);
        saved.put("Relationships", saveRelationships);
    }
    
}