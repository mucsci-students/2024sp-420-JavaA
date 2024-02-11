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
     * the method to call that initiates saving, it takes
     * the class and relationship containers to send them off
     * into the save procedure
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
     * the method to call that initiates saving, it takes
     * the class and relationship containers to send them off
     * into the save procedure, it also takes a name for the save file
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
     * iterates through the containers to put them into a JSONObject
     * 
     * Attributes are put into a JSONArray and added to their class
     * 
     * Classes are put into a JSONArray
     * Relationships are put into a JSONArray
     * 
     * Classes and Relationships are put into the saved JSONObject
     * that will be put into a file for storing
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