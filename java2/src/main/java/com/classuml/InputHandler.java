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
                    System.out.println("Here is a list of commands, please enter them without the '':");
                    System.out.println("'add class', will prompt you for a class name to add.");
                    System.out.println("'remove class', will prompt you for a class name to remove.");
                    System.out.println("'rename class', will prompt you for a class you wish to rename, and then the name you wish to rename it to. ");
                    System.out.println("'add relationship', will prompt you for the name of the relationship you wish to add, as well as the class it goes from/to.");
                    System.out.println("'remove relationship', asks you for the name of the relationship you wish to remove.");
                    System.out.println("'add attribute', asks you for the name of the attribute you wish to add, the content, and the class name.");
                    System.out.println("'edit attribute', asks you which class you want to edit an attribute from, which attribute you want to edit, how would you like to edit it, and the edit itself.");
                    System.out.println("'remove attribute', asks which class you would like to remove an attribute from, and which attribute you would like to remove. ");
                    System.out.println("'list one class', will prompt you for a class name and list all of its attributes and their content.");
                    System.out.println("'list all classes', will list all classes and all of their attributes and contents.");
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
                    String className = userTwo[2];
                    ClassBase myClass = new ClassBase(className);

                    //Prints the return of addClass, so would say whether it works or not.
                    String add = myClassContainer.addClass(myClass);
                    System.out.println(add);
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
                    className = userTwo[2];
                    boolean hasRel = false;
                    boolean hasRel2 = false;    

                    //Checks if the class has any relationships existing.
                    //If so, it informs the user and asks them to remove them before removing the class.
                    for (Relationship rel : myRelationshipContainer.getAllRelationships())
                    {
                        if(rel.getSourceClass().equals(className))
                        {
                            hasRel = true;
                        }
                        else
                        {
                            if(rel.getDestClass().equals(className))
                            {
                                hasRel = true;
                            }
                        }
                        if(hasRel == true)
                        {
                            System.out.println(rel.getSourceClass() + " -> " + rel.getDestClass() + " is an existing relationship.");
                            hasRel2 = true;
                            hasRel = false;
                        }
                    }
                    if(hasRel2 == true)
                    {
                        System.out.println("Please remove the existing relationship(s) before removing the class.");
                    }
                    else
                    {

                        //Prints the return of removeClass, so would say whether it works or not.
                        String removing = myClassContainer.removeClass(className);
                        System.out.println(removing);
                    }
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
                    className = userTwo[2];
                    String newName = userTwo[3];

                    //Goes through every relationship, and if any used the class being renamed,
                    //It changes the name from the oldname to the newname.
                    for (Relationship rel : myRelationshipContainer.getAllRelationships())
                    {
                        if(rel.getSourceClass().equals(className))
                        {
                            rel.setSourceClass(newName);
                        }
                        else
                        {
                            if(rel.getDestClass().equals(className))
                            {
                                rel.setDestClass(newName);
                            }
                        }
                    }

                    //Prints the return of renameClass, so would say whether it works or not.
                    String rename = myClassContainer.renameClass(className, newName);
                    System.out.println(rename);
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
                     String relSource = userTwo[2];
                     String relDest = userTwo[3];
                     String relType = userTwo[4];
                     int test = 0;
 
                     //used to check if the classes are in the container and prints a message if they are not
                     if (myClassContainer.getClassBase(relSource) == null){
                         test = 1;
                     }
                     if (myClassContainer.getClassBase(relDest) == null){
                         test = 2;
                     }
                     if (test == 1){
                         System.out.println("Class with name " + relSource + " does not exist.");
                         break;
                     }
                     if (test == 2){
                         System.out.println("Class with name " + relDest + " does not exist.");
                         break;
                     }
                     if (myRelationshipContainer.addRelationship(relSource, relDest, relType) == true){
                         System.out.println(relSource + " -> " + relDest + " relationship added.");
                     }
                     else{
                         System.out.println("Relationship with that source and destination already exists or relationship type is not one of the choices.");
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
                    //gets relname and removes it from the container
                    relSource = userTwo[2];
                    relDest = userTwo[3];
                    //removeRelationship returns a boolean so if it is true then it was deleted and if not then the relationship doesnt exist
                    if (myRelationshipContainer.removeRelationship(relSource, relDest) == true){
                        System.out.println(relSource + " -> " + relDest + " relationship deleted.");
                    }
                    else{
                        System.out.println("No such relationship with that source and destination");
                    }
                    break;
                case "add attribute":
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
                    className = userTwo[2];
                    ClassBase tempClass = myClassContainer.getClassBase(className);
                    String attName;
                    String attContent;

                    //Checks whether a class of the given name exists or not.
                    if(tempClass != null){
                        attributes myAttributes = new attributes();
                        attName = userTwo[3];
                        myAttributes.setName(attName);
                        attContent = userTwo[4];
                        myAttributes.setContent(attContent);
                        int success = tempClass.addAttribute(myAttributes);
                        if(success == 1){

                            //The attribute was added succesfully.
                            System.out.println(attName + " attribute was added.");
                        }
                        else{

                            //An attribute with this name is already in this class.
                            System.out.println("An attribute with this name already exists in this class.");
                        }
                    }
                    else{

                        //A class of the given name could not be found.
                        System.out.println("A class of the given name does not exist.");
                    }
                    
                    break;
                case "edit attribute":
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
                    className = userTwo[2];
                    tempClass = myClassContainer.getClassBase(className);

                    //checks to see if the class exists
                    if(tempClass != null){
                        attName = userTwo[3];

                        //Checks to see whether that attribute exists or not.
                        attributes attCheck = tempClass.getAttribute(attName);
                        if(attCheck != null){
                            String updateType = userTwo[4];
                            String updatedContent;
                            if(updateType.equalsIgnoreCase("Name")){

                                //Asks the user for a new name for the attribute.
                                updatedContent = userTwo[5];

                                //checks to see if an attribute with that name already exists
                                if(attCheck.getName().equalsIgnoreCase(updatedContent)){
                                    System.out.println("An attribute with this name already exists");
                                    break;
                                }
                            }

                            //asks for the new content
                            else{
                                updatedContent = userTwo[5];
                            }

                            //Updates the attribute and tells that to the user.
                            tempClass.updateAttribute(attCheck, updateType, updatedContent);
                            System.out.println("The attributes " + updateType + " was changed.");
                        }
                        else{

                            //An attribute could not be found with the given name
                            System.out.println("An attribute with this name does not exist.");
                        }
                    }
                    else{

                        //A class of the given name could not be found.
                        System.out.println("A class of the given name does not exist.");
                    }
                    break;
                case "remove attribute":
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
                    className = userTwo[2];

                    //Checks to see if the class exists.
                    tempClass = myClassContainer.getClassBase(className);
                    if(tempClass != null){
                        attName = userTwo[3];

                        //Checks to see if the attribute exists or not
                        attributes attCheck = tempClass.getAttribute(attName);
                        if(attCheck != null){

                            //An attribute with the name was found and deleted
                            tempClass.deleteAttribute(attCheck);
                            System.out.println("The attribute was deleted.");
                        }
                        else{
                            //An attribute could not be found with the given name

                            System.out.println("An attribute with this name does not exist.");
                        }
                    }
                    else{

                        //A class of the given name could not be found.
                        System.out.println("A class of the given name does not exist.");
                    }
                    break;
                }
            }
            if(userTwo.length > 2 && userTwo.length < 7)
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
                    boolean isIn = false;
                    boolean isCont = false;
                    String className = userTwo[3];

                    //loop through classContainer and print out the name, and attributes
                    for(ClassBase temp : myClassContainer.getContainer()){
                        if (temp.getName().equals(className)){
                            isIn = true;
                            System.out.println("Class Name: " + className);
                            for(attributes att : temp.getClassAttributes()){
                                System.out.println("Attribute Name: " + att.getName() + "\nContent: " + att.getContent());
                                isCont = true;
                            }
                        }
                    }
                    if(!isIn){
                        System.out.println("Class with that name does not exist.");
                    }
                    if(!isCont){
                        System.out.println("Class has no attributes.");
                    }
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

                    //useds to tell if there are any classes
                    isIn = false;
                    isCont = false;

                    //loop through classContainer and prints class name and attributes of every class
                    for(ClassBase temp : myClassContainer.getContainer()){
                        System.out.println("Class Name: " + temp.getName());
                        isIn = true;
                        for(attributes att : temp.getClassAttributes()){
                            System.out.println("Attribute Name: " + att.getName() + "\nContent: " + att.getContent());
                            isCont = true;
                        }
                    }
                    if (!isIn){
                        System.out.println("There are no classes.");
                    }
                    if(!isCont){
                        System.out.println("There are no attributes is any classes.");
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
                    String className = userTwo[4];

                    //used to tell if the class name exists and whether or not is has relationships
                    boolean isIn = false;
                    boolean isRel = false;

                    //loop through classContainer to check if the name exists
                    for(ClassBase cls : myClassContainer.getContainer()){
                        if(cls.getName().equals(className)){
                            isIn = true;
                        }
                    }

                    //loop thorugh relContainer and print name, fromClass, toClass
                    for(Relationship rel : myRelationshipContainer.getAllRelationships()){
                        if (rel.getSourceClass().equals(className) || rel.getDestClass().equals(className)){
                            System.out.println("Source Class: " + rel.getSourceClass() + "\nDestination Class: " + rel.getDestClass());
                            isRel = true;
                        }
                    }
                    if (!isIn){
                        System.out.println("Class with that name does not exist.");
                    }
                    if (!isRel){
                        System.out.println("There are no relationships connected to this class.");
                    }
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
