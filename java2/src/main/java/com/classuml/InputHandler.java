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
    @Override
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
    }

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
            launch(args);
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
                    System.out.println("Here is a list of commands, please enter them without the '', or <>, replacing what's inside the <>:");
                    System.out.println("'add class <name> ' adds a class with given name");
                    System.out.println("'remove class <name>', will remove a class with given name");
                    System.out.println("'rename class <oldName> <newName>', will rename a class with oldName to newName");
                    System.out.println("'add relationship <fromClass> <toClass>', will add a relationship between two classes.");
                    System.out.println("'remove relationship <fromClass> <toClass>', will remove a relationship between two classes.");
                    //Got to replace attribute stuff with method/field specific ones.
                    System.out.println("'add attribute', asks you for the name of the attribute you wish to add, the type, and the class name.");
                    System.out.println("'edit attribute', asks you which class you want to edit an attribute from, which attribute you want to edit, how would you like to edit it, and the edit itself.");
                    System.out.println("'remove attribute', asks which class you would like to remove an attribute from, and which attribute you would like to remove. ");
                    System.out.println("'list one class', will prompt you for a class name and list all of its attributes and their type.");
                    System.out.println("'list all classes', will list all classes and all of their attributes and type.");
                    System.out.println("'list one class relationship', will prompt you for a class name and list all of the relationships it belongs to.");
                    System.out.println("'save', saves the class.");
                    System.out.println("'load', asks you for a file to load from, then loads said file.");
                    System.out.println("'exit', closes the program.");
                    System.out.println("'help', displays the help text.");
                    break;
                    case "save":
                    valid = true;
                    //takes in name of file without .json at the end and saves it to that file or creates it if it doesn't exist.
                    //currently no override warning

                    if(userTwo.length == 1){
                        saveUML.save(myClassContainer, myRelationshipContainer);
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
                        
                        saveUML.save(myClassContainer, myRelationshipContainer, fileName);
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
                    LoadUML load = new LoadUML();
                    String fileNameLoad = userTwo[1];
                    int counter = 0;
                    for(String input : userTwo)
                    {
                        if(counter > 2)
                            fileNameLoad.concat(input);
                        else
                            counter++;
                    }
                    for (ClassBase cls : myClassContainer.getContainer()){
                        cls.getClassAttributes().clear();
                    }
                    myClassContainer.getContainer().clear();
                    myRelationshipContainer.getAllRelationships().clear();
                    load.load(fileNameLoad + ".json",myClassContainer,myRelationshipContainer);
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
                            saveUML.save(myClassContainer, myRelationshipContainer);
                        }
                        else{
                            
                            saveUML.save(myClassContainer, myRelationshipContainer, fileNameExit);
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
                            System.out.println(userTwo[3] + " attribute was added.");
                            break;
                        
                        case 2:
                        
                            System.out.println("An attribute with this name already exists in this class.");
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
                    System.out.println("An attribute with this name does not exist.");
                break;
                case 2:
                    System.out.println("An attribute with your new name already exists");
                break;
                case 3:
                    System.out.println("The attributes " + userTwo[4] + " was changed.");
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
                            System.out.println("The attribute was deleted.");
                            break;
                        case 2:
                            System.out.println("An attribute with this name does not exist.");
                            break;
                    }

                    
                    break;
                
                case "add method":
                    valid = true;
                if(userTwo.length < 7)
                {
                    tooSmall();
                    break;
                }
                if(userTwo.length % 2 == 0)
                {
                    System.out.println("Please enter an equal amount of parameter names and types.");
                    break;
                }
                String paramNames = "";
                String paramTypes = "";
                for(int i = 5; i < userTwo.length; i++)
                {
                    if(i%2==1)
                    {
                        paramNames.concat(" ");
                        paramNames.concat(userTwo[i]);
                    }
                    else
                    {
                        paramNames.concat(" ");
                        paramNames.concat(userTwo[i]);
                    }
                }
                boolean check = mainModel.addMethod(userTwo[2], userTwo[3], userTwo[4], paramNames, paramTypes);
                if (check == false)
                {
                    System.out.println("A class of your given name doesn't exist.");
                }
                else
                {  
                    System.out.println("Method " + userTwo[3] + " of type " + userTwo[4] + " was successfully created in class " + userTwo[2]);
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

                    
                    break;
                }
            }
                if(userTwo.length > 4)
                {
                switch(userTwo[0] + " " + userTwo[1] + " " + userTwo[2] + " " + userTwo[3])
                {
                case "list one class relationship":
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
}   
