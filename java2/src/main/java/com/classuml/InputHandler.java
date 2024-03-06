package com.classuml;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
public class InputHandler extends Application
{  
    /*@Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Hello World!");
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }*/

    public static void main(String[] args)
    {   
        //setup containers and print welcome message
        Model mainModel = new Model();
        ClassContainer myClassContainer = new ClassContainer();
        RelationshipContainer myRelationshipContainer = new RelationshipContainer();
        System.out.println("Welcome. If you need help with commands, please type 'help', without the '' surrounding it.");
        Scanner userInput = new Scanner(System.in);
        System.out.println("To start GUI, type gui (else, press enter): ");
        String guiResult = userInput.nextLine();
        if (guiResult.contains("gui")) {
            //launch(args);
            Application.launch(Controller.class, args);
            userInput.close();
            return;
        }
        while(true)
        {
            //Used for printing the "No valid command!" message.
            boolean valid = false;
            System.out.print("> ");
            //Splits userinput into strings split by a space.
            String userString = userInput.nextLine();
            String[] userTwo = userString.split(" ");
            if(userTwo.length > 0)
            {
            switch(userTwo[0])
            {
                case "help":
                    valid = true;

                    //Prints a large amount of stuff, which hopefully would tell the user how to use the program.
                    //Still needs to say what the valid types/edits are for stuff.
                    System.out.println("Here is a list of commands, please enter them without the '', or <>, replacing what's inside the <>:");
                    System.out.println("'add class <name> ' adds a class with given name.");
                    System.out.println("'remove class <name>', will remove a class with given name.");
                    System.out.println("'rename class <oldName> <newName>', will rename a class with oldName to newName.");
                    System.out.println("'add relationship <fromClass> <toClass>', will add a relationship between two classes.");
                    System.out.println("'add method <className> <methodName> <methodType>', adds a methodName of type methodType to className.");
                    System.out.println("'edit method <className> <methodName> <editType>', edits method according to editType in className and methodName.");
                    System.out.println("'remove method <className> <methodName>', removed method methodName from className.");
                    System.out.println("'add param <className> <methodName> <paramName> <paramType>', adds param of paramType to paramName in methodName in className.");
                    System.out.println("'remove param <className> <methodName> <paramName>', removes param with paramName from methodName in className.");
                    System.out.println("'clear params <className> <methodName>', clears all params from methodName in className.");
                    System.out.println("'add field <className> <fieldName> <fieldType>', adds a fieldName of fieldType to className.");
                    System.out.println("'edit field <className> <fieldName> <editType>', edits field according to editType in className and methodName.");
                    System.out.println("'remove field <className> <fieldName>', removed fieldName from className.");
                    System.out.println("'list one class <className>', will list all fields and methods of className.");
                    System.out.println("'list all classes', will list all classes and all of their fields and methods and types.");
                    System.out.println("'list one classes relationships <className>', will list all of the relationship the class belongs to.");
                    System.out.println("'save <fileName>', saves the class with file name fileName, if none is chosen it uses a default name.");
                    System.out.println("'load <fileName>', loads a file fileName.");
                    System.out.println("'exit', closes the program.");
                    System.out.println("'help', displays the help text.");
                    break;
                    case "save":
                    valid = true;
                    //takes in name of file without .json at the end and saves it to that file or creates it if it doesn't exist.
                    //currently no override warning

                    if(userTwo.length == 1){
                        mainModel.save();
                    }
                    else{
                        String fileName = userTwo[1];
                        int counter = 0;
                        for(String input : userTwo)
                        {
                            if(counter > 2)
                                fileName.concat(input);
                            else
                                counter++;
                        }
                        
                        mainModel.save(fileName);
                    }
                    break;
                case "load":
                    valid = true;
                    if(userTwo.length < 2)
                    {
                        tooSmall();
                        break;
                    }


                    //takes in name of file you wish to load without .json at the end
                    //stores data from the json into the containers passed to it
                    
                    String fileNameLoad = userTwo[1];
                    int counter = 0;
                    for(String input : userTwo)
                    {
                        if(counter > 2)
                            fileNameLoad.concat(input);
                        else
                            counter++;
                    }
                    mainModel.load(fileNameLoad);
                    break;
                case "exit":
                    valid = true;

                    //exits program prompting if you wish to save, exit without saving, or return to the editor
                    System.out.println("You are exiting NAME UML editor, type yes to save and exit, type no to exit without saving, anything else will return to the editor");
                    String saveState = userInput.nextLine();
                    int status;
                    if (saveState.equals("yes")){
                        status = 0;
                        System.out.println("enter a name for the save file, or type nothing for default (saveUML) name");
                        String fileNameExit = userInput.nextLine();
                        if(fileNameExit.length() == 0){
                            mainModel.save();
                        }
                        else{
                            
                            mainModel.save(fileNameExit);
                        }
                    }
                    else if (saveState.equals("no")){
                        status = 1;
                    }
                    else{
                        status = -1;
                    }
                    userInput.close();
                    ExitUML myExit = new ExitUML();
                    myExit.callExit(status);
                    break;
            }
        }
        if(userTwo.length > 1)
        {
            switch(userTwo[0] + " " + userTwo[1])
            {
                case "add class":
                    valid = true;
                    if(userTwo.length < 3)
                    {
                        tooSmall();
                        break;
                    }
                    if(userTwo.length > 3)
                    {
                        tooManyArgs();
                        break;
                    }
                    //Prints the return of addClass, so would say whether it works or not.
                    System.out.println(mainModel.addClass(userTwo[2]));
                    break;
                case "remove class":
                    valid = true;
                    if(userTwo.length < 3)
                    {
                        tooSmall();
                        break;
                    }
                    if(userTwo.length > 3)
                    {
                        tooManyArgs();
                        break;
                    }
                    System.out.println(mainModel.removeClass(userTwo[2]));
                    break; 
                case "rename class":
                valid = true;
                if(userTwo.length < 4)
                    {
                        tooSmall();
                        break;
                    }
                    if(userTwo.length > 4)
                    {
                        tooManyArgs();
                        break;
                    }
                    
                    //Prints the return of renameClass, so would say whether it works or not.
                    System.out.println(mainModel.renameClass(userTwo[2], userTwo[3]));
                    break;
                case "add relationship":
                    valid = true;
                if(userTwo.length < 5 )
                    {
                        tooSmall();
                        break;
                    }
                    if(userTwo.length > 5)
                    {
                        tooManyArgs();
                        break;
                    }
                     //asks for relname, and the two classes it belongs to and stores it in relContainer.
                     int retAdd = mainModel.addRelationship(userTwo[2], userTwo[3], userTwo[4]);
                     switch(retAdd)
                     {
                     case 1:
                        System.out.println("Class with name " + userTwo[2] + " does not exist.");
                        break;
                    case 2:
                        System.out.println("Class with name " + userTwo[3] + " does not exist.");
                        break;
                    case 3:
                        System.out.println(userTwo[2] + " -> " + userTwo[3] + " relationship added.");
                        break;
                    case 0:
                        System.out.println("Relationship with that source and destination already exists or relationship type is not one of the choices.");
                        break;
                    }
                     
                     break;
                case "remove relationship":
                    valid = true;
                if(userTwo.length < 4)
                {
                    tooSmall();
                    break;
                }
                if(userTwo.length > 4)
                {
                    tooManyArgs();
                    break;
                }
                    int retRemove = mainModel.removeRelationship(userTwo[2], userTwo[3]);
                    if(retRemove == 1)
                    {
                        System.out.println(userTwo[2] + " -> " + userTwo[3] + " relationship deleted.");
                    }
                    else
                        System.out.println("No such relationship with that source and destination");
                    break;
                case "add field":
                    valid = true;
                if(userTwo.length < 5)
                    {
                        tooSmall();
                        break;
                    }
                    if(userTwo.length > 5)
                    {
                        tooManyArgs();
                        break;
                    }
                    int success = mainModel.addField(userTwo[2], userTwo[3], userTwo[4]);
                    switch(success)
                    {
                        case 0:
                            System.out.println("A class of the given name does not exist.");
                            break;
                    
                        case 1:
                        
                            //The attribute was added successfully.
                            System.out.println(userTwo[3] + " field was added.");
                            break;
                        
                        case 2:
                        
                            System.out.println("A field with this name already exists in this class.");
                            break;
                    }   
                    break;
                case "edit field":
                    valid = true;
                    if(userTwo.length < 6)
                    {
                        tooSmall();
                        break;
                    }
                    if(userTwo.length > 6)
                    {
                        tooManyArgs();
                        break;
                    }
                    int retVal = 0;
                    if(userTwo[4].equalsIgnoreCase("Name"))
                    {
                        retVal = mainModel.renameField(userTwo[2], userTwo[3], userTwo[5]);
                    }
                    else
                    {
                        if(userTwo[4].equalsIgnoreCase("Type"))
                        {
                            retVal = mainModel.changeFieldType(userTwo[2], userTwo[3], userTwo[5]);
                        }
                        else
                        {
                            System.out.println("Please type a valid edit type.");
                        }
                    }
                switch(retVal)
                {
                case 0:
                    System.out.println("A class of the given name does not exist.");
                break;
                case 1:
                    System.out.println("A field with this name does not exist.");
                break;
                case 2:
                    System.out.println("A field with your new name already exists");
                break;
                case 3:
                    System.out.println("The fields " + userTwo[4] + " were changed.");
                break;
                }
                    break;
                case "remove field":
                    valid = true;
                    if(userTwo.length < 4)
                    {
                        tooSmall();
                        break;
                    }
                    if(userTwo.length > 4)
                    {
                        tooManyArgs();
                        break;
                    }
                    int remField = mainModel.removeField(userTwo[2], userTwo[3]);
                    switch(remField)
                    {
                        case 0:
                            System.out.println("A class of the given name does not exist.");
                            break;
                        case 1:
                            System.out.println("The field was deleted.");
                            break;
                        case 2:
                            System.out.println("A field with this name does not exist.");
                            break;
                    }

                    
                    break;
                
                case "add method":
                    valid = true;
                if(userTwo.length < 5)
                {
                    tooSmall();
                    break;
                }
                if(userTwo.length > 5)
                {
                    tooManyArgs();
                    break;
                }

                boolean check = mainModel.addMethod(userTwo[2], userTwo[3], userTwo[4]);
                if (check == false)
                {
                    System.out.println("A class of your given name doesn't exist.");
                }
                else
                {  
                    System.out.println("Method " + userTwo[3] + " of type " + userTwo[4] + " was successfully created in class " + userTwo[2]);
                }

                break;
                case "add param":
                valid = true;
                if(userTwo.length < 6)
                {
                    tooSmall();
                    break;
                }
                if(userTwo.length > 6)
                {
                    tooManyArgs();
                    break;
                }
                int test = mainModel.addParam(userTwo[2], userTwo[3], userTwo[4], userTwo[5]);
                switch(test)
                {
                    case 0:
                    System.out.println("Class of given name doesn't exist");
                    break;
                    case 1:
                    System.out.println("Method of given name doesn't exist!");
                    break;
                    case 2:
                    System.out.println("Param of given name already exists!");
                    break;
                    case 3:
                    System.out.println("Param " + userTwo[4] + " of type " + userTwo[5] + " was successfully added to method " + userTwo[3] + " in class " + userTwo[2]);
                    break;
                }
                break;
                case "remove param":
                valid = true;
                if(userTwo.length < 5)
                {
                    tooSmall();
                    break;
                }
                if(userTwo.length > 5)
                {
                    tooManyArgs();
                    break;
                }
                int testRemove = mainModel.removeParam(userTwo[2], userTwo[3], userTwo[4]);
                switch(testRemove)
                {
                    case 0:
                    System.out.println("Class of given name doesn't exist");
                    break;
                    case 1:
                    System.out.println("Method of given name doesn't exist!");
                    break;
                    case 2:
                    System.out.println("Param of given name doesn't exist!");
                    break;
                    case 3:
                    System.out.println("Param " + userTwo[4] + " was successfully removed from method " + userTwo[3] + " in class " + userTwo[2]);
                    break;
                }
                break;
                case "clear params":
                valid = true;
                if(userTwo.length < 4)
                {
                    tooSmall();
                    break;
                }
                if(userTwo.length > 4)
                {
                    tooManyArgs();
                    break;
                }
                int testClear = mainModel.clearParams(userTwo[2], userTwo[3]);
                switch(testClear)
                {
                    case 0:
                    System.out.println("Class of given name doesn't exist");
                    break;
                    case 1:
                    System.out.println("Method of given name doesn't exist!");
                    break;
                    case 2:
                    System.out.println("Params were cleared from method " + userTwo[3] + " in class " + userTwo[3]);
                    break;
                }
                break;
                case "remove method":
                valid = true;
                    if(userTwo.length < 4)
                    {
                        tooSmall();
                        break;
                    }
                    if(userTwo.length > 4)
                    {
                        tooManyArgs();
                        break;
                    }
                    int retValRem = mainModel.removeMethod(userTwo[2], userTwo[3]);
                    switch(retValRem)
                    {
                        case 0:
                            System.out.println("Class of given name doesn't exist!");
                        break;
                        case 1:
                            System.out.println("Method " + userTwo[5] + " was removed from class " + userTwo[4]);
                        break;
                        case 2:
                            System.out.println("Method of given name doesn't exist!");
                        break;
                    }
                break;
                case "edit method":
                valid = true;
                    if(userTwo.length < 6)
                    {
                        tooSmall();
                        break;
                    }
                    if(userTwo.length > 6)
                    {
                        tooManyArgs();
                        break;
                    }
                    int retValEdit = 0;
                    if(userTwo[2].equalsIgnoreCase("name"))
                    {
                        retValEdit = mainModel.renameMethod(userTwo[3], userTwo[4], userTwo[5]);

                    }
                    else
                    {
                        if(userTwo[2].equalsIgnoreCase("type"))
                        {
                            retValEdit = mainModel.changeMethodType(userTwo[3], userTwo[4], userTwo[5]);
                        }
                        else
                        {
                            System.out.println("Please type a valid edit type!");
                        }
                    }
                    switch(retValEdit)
                    {
                        case 0:
                            System.out.println("Class of given name doesn't exist!");
                        break;
                        case 1:
                            System.out.println("A method with the new name already exists.");
                        break;
                        case 2:
                            System.out.println("The method looking to be edited doesn't exist!");
                        break;
                        case 3:
                            System.out.println("The method was successfully changed.");
                        break;
                    }
                break;
            }
        }
            if(userTwo.length > 2)
            {
                switch(userTwo[0] + " " + userTwo[1] + " " + userTwo[2])
                {
                case "rename relationship type":
                    valid = true;
                    if(userTwo.length < 6)
                    {
                        tooSmall();
                        break;
                    }
                    if(userTwo.length > 6)
                    {
                        tooManyArgs();
                        break;
                    }
                    String relSource = userTwo[3];
                    String relDest = userTwo[4];
                    String relType = userTwo[5];
                    String tempType;
                    for (Relationship rel : myRelationshipContainer.getAllRelationships()){
                        if (rel.getSourceClass().equals(relSource) && rel.getDestClass().equals(relDest)){
                            tempType = rel.getType();
                            boolean temp = rel.setType(relType);
                            if (!temp){
                                System.out.println("Invalid relationship type");
                                break;
                            }
                            System.out.println("Relationship type of " + relSource + " -> " + relDest + " with type " + tempType + " changed to " + relType);
                        }
                    }
                    break;
                case "list one class":
                    valid = true;
                    if(userTwo.length < 4)
                    {
                        tooSmall();
                        break;
                    }
                    if(userTwo.length > 4)
                    {
                        tooManyArgs();
                        break;
                    }
                    //used to tell of there is a class with that name and if not gives an output
                    System.out.println(mainModel.listOneClass(userTwo[3]));
                    break;
                case "list all classes":
                    valid = true;
                    if(userTwo.length < 3)
                    {
                        tooSmall();
                        break;
                    }
                    if(userTwo.length > 3)
                    {
                        tooManyArgs();
                        break;
                    }
                        System.out.println(mainModel.listAllClasses());
                    
                    break;
                }
            }
                if(userTwo.length > 4)
                {
                switch(userTwo[0] + " " + userTwo[1] + " " + userTwo[2] + " " + userTwo[3])
                {
                case "list one classes relationships":
                    valid = true;
                    if(userTwo.length < 5)
                    {
                        tooSmall();
                        break;
                    }
                    if(userTwo.length > 5)
                    {
                        tooManyArgs();
                        break;
                    }
                    System.out.println(mainModel.listOneClassRelationship(userTwo[4]));
                    break;
                }
                }
                
                    //if a command without a case was entered print this
                    if(valid == false)
                    System.out.println("Please enter a valid command, type 'help' without '' to see a list of available commands.");
            }
        }
    //Informs the user that they used too few arguments for the given command.
    public static void tooSmall()
    {
        System.out.println("Too few arguments, type 'help' for help.");
    }
    //Informs the user that they used too many arguments for the given command.
    public static void tooManyArgs()
    {
        System.out.println("Too many arguments, type 'help' for help.");
    }
    @Override
    public void start(Stage arg0) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'start'");
    }
}   
