import org.json.simple.*;

public class saveUML {

    /*
     * All of the JSONObjects and JSONArrays that
     * are used throughout the save process
     */
    private static JSONObject saveFile;
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
        saveFile = new JSONObject();
        saveProcedure(myClassContainer, myRelationshipContainer);
        System.out.println( saveFile.toString());

    }

    /*
     * the method to call that initiates saving, it takes
     * the class and relationship containers to send them off
     * into the save procedure, it also takes a name for the save file
     */
    public static void save(ClassContainer myClassContainer, RelationshipContainer myRelationshipContainer, String fileName){
        saveFile = new JSONObject();
        saveProcedure(myClassContainer, myRelationshipContainer);
        saveFile.toString();
    }

    /*
     * iterates through the containers to put them into a JSONObject
     * 
     * Attributes are put into a JSONArray and added to their class
     * 
     * Classes are put into a JSONArray
     * Relationships are put into a JSONArray
     * 
     * Classes and Relationships are put into the saveFile JSONObject
     * that will be put into a file for storing
     */
    private static void saveProcedure(ClassContainer myClassContainer, RelationshipContainer myRelationshipContainer){
        saveClasses = new JSONArray();
        saveRelationships = new JSONArray();
        saveAtts = new JSONArray();

        //loop for iterating through classes
        for (ClassBase classes: myClassContainer.getContainer()){
            saveClass = new JSONObject();
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

        saveFile.put("Classes", saveClasses);
        saveFile.put("Relationships", saveRelationships);
    }
    
}