package com.classuml;
import javax.json.*;
import java.util.*;
import java.io.*;
import java.nio.file.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.*;

public class saveUML {

    /*
     * All of the JSONObjects and JSONArrays that
     * are used throughout the save process
     */
    private static Map<String,String> map;
    private static JsonBuilderFactory jsonBuilder;
    private static JsonObject saveData;
    private static JsonArrayBuilder cArray;
    private static JsonArrayBuilder rArray;
    private static JsonArrayBuilder fArray;
    private static JsonArrayBuilder mArray;
    private static JsonArrayBuilder pArray;

    

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
        saveProcedure(myClassContainer, myRelationshipContainer);

        //try to create the new file saveUML.json and save data to that file
        try{
            //will create a file named saveUML.json, if a file already exists, no error will occur
            File saveFile = new File("saveUML.json");
            saveFile.createNewFile();

            //writes the information to the file
            FileWriter writeToFile = new FileWriter("saveUML.json");
            writeToFile.write(saveData.toString());
            writeToFile.close();
            schemaValidator(Paths.get("").toAbsolutePath().toString() + "\\saveUML.json"); 
        }
        catch(IOException except){
            System.out.println("An error occured while creating this file");
        }
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
        saveProcedure(myClassContainer, myRelationshipContainer);

        try{
            //will create a file named saveUML.json, if a file already exists, no error will occur
            File saveFile = new File(fileName +".json");
            saveFile.createNewFile();

            //writes the information to the file
            FileWriter writeToFile = new FileWriter(fileName + ".json");
            writeToFile.write(saveData.toString());
            writeToFile.close();
            schemaValidator(Paths.get("").toAbsolutePath().toString() + "\\" + fileName + ".json");
            
        }
        catch(IOException except){
            System.out.println("An error occured while creating this file");
        }
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
        jsonBuilder = Json.createBuilderFactory(map);
        cArray = jsonBuilder.createArrayBuilder();
        rArray = jsonBuilder.createArrayBuilder();

        //loop for iterating through classes, and there fields and methods
        for (ClassBase classes: myClassContainer.getContainer()){
            fArray = jsonBuilder.createArrayBuilder();
            mArray = jsonBuilder.createArrayBuilder();

            for(attributes fields: classes.getClassAttributes()){
                fArray.add(jsonBuilder.createObjectBuilder()
                    .add("Name", fields.getName())
                    .add("Type", fields.getType()));
            }

            for(methods meth: classes.getClassMethods()){

                pArray = jsonBuilder.createArrayBuilder();
                for(attributes params: meth.getParams()){
                    pArray.add(jsonBuilder.createObjectBuilder()
                    .add("Name", params.getName())
                    .add("Type", params.getType()));
                }

                mArray.add(jsonBuilder.createObjectBuilder()
                    .add("Name", meth.getName())
                    .add("Return type", meth.getType())
                    .add("Paramaters", pArray));
            }

            cArray.add(jsonBuilder.createObjectBuilder()
            .add("Name", classes.getName())
            .add("Fields", fArray)
            .add("Methods", mArray));
        }

        //loop for iterating through relationships
        for(Relationship relate: myRelationshipContainer.getAllRelationships()){
            rArray.add(
                jsonBuilder.createObjectBuilder()
                .add("Source Class", relate.getSourceClass())
                .add("Destination Class", relate.getDestClass())
                .add("Type", relate.getType()));
        }

        saveData = jsonBuilder.createObjectBuilder().add("Classes", cArray).add("Relationships", rArray).build();
    }
    






/*
 * Validates that the saveData Json format is correct with the schema, if it doesn't it will display what the problem is
 * 
 *  @Variables   saveFileName, gives the name of the file used for saving
 * 
 *  @Preconditions
 *  @Postconditions
 * 
 *  @Returns
 */

public static void schemaValidator(String saveFileName){

    ObjectMapper objectMapper = new ObjectMapper();

    JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7);

    try(  
            InputStream jsonStream = new FileInputStream(saveFileName); 
            InputStream schemaStream = new FileInputStream(Paths.get("").toAbsolutePath().toString() + "\\src\\main\\java\\com\\classuml\\SaveSchema.Json");   
        ){
            JsonNode json = objectMapper.readTree(jsonStream);
            JsonSchema schema = schemaFactory.getSchema(schemaStream);

            Set<ValidationMessage> validationResult = schema.validate(json);
            if (validationResult.isEmpty()){

            }
            else{
                validationResult.forEach(vm -> System.out.println(vm.getMessage()));
            }
        }
        catch(IOException except){
            System.out.println("The Schema did not like your save file, please contact the manufacturer");
        }
         
    }
    


}