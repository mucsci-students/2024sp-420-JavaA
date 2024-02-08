import java.util.Scanner;
public class InputHandler
{  

    public static void main(String[] args)
    {
        ClassContainer myClassContainer = new ClassContainer();
        RelationshipContainer myRelationshipContainer = new RelationshipContainer();
        attributes myAttributes = new attributes();
        System.out.println("Welcome. If you need help with commands, please type 'help', without the '' surrounding it.");
        while (true)
        {
        Scanner userInput = new Scanner(System.in);
        while(true)
        {
            System.out.print("> ");
            String userString = userInput.nextLine();
            switch(userString)
            {
                case "help":
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
                case "add class":
                    System.out.println("Please type the name of the class you wish to add.");
                    String className = userInput.nextLine();
                    ClassBase myClass = new ClassBase(className);
                    //Prints the return of addClass, so would say whether it works or not.
                    String add = myClassContainer.addClass(myClass);
                    System.out.println(add);
                    break;
                case "remove class":
                    System.out.println("Please type the name of the class you wish to delete.");
                    className = userInput.nextLine();
                    boolean hasRel = false;
                    boolean hasRel2 = false;
                    //Checks if the class has any relationships existing.
                    //If so, it informs the user and asks them to remove them before removing the class.
                    for (Relationship rel : myRelationshipContainer.getAllRelationships())
                    {
                        if(rel.getFromClass().equals(className))
                        {
                            hasRel = true;
                        }
                        else
                        {
                            if(rel.getToClass().equals(className))
                            {
                                hasRel = true;
                            }
                        }
                        if(hasRel == true)
                        {
                            System.out.println(rel.getName() + " is an existing relationship.");
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
                    System.out.println("Please type the name of the class you wish to rename.");
                    className = userInput.nextLine();
                    System.out.println("Please type the new name of the class.");
                    String newName = userInput.nextLine();
                    //Goes through every relationship, and if any used the class being renamed,
                    //It changes the name from the oldname to the newname.
                    for (Relationship rel : myRelationshipContainer.getAllRelationships())
                    {
                        if(rel.getFromClass().equals(className))
                        {
                            rel.setFromClass(newName);
                        }
                        else
                        {
                            if(rel.getToClass().equals(className))
                            {
                                rel.setToClass(newName);
                            }
                        }
                    }
                    //Prints the return of renameClass, so would say whether it works or not.
                    String rename = myClassContainer.renameClass(className, newName);
                    System.out.println(rename);
                    break;
                case "add relationship":
                    System.out.println("Please type the name of the relationship.");
                    String relName = userInput.nextLine();
                    System.out.println("Please type the name of the class it comes from.");
                    String relFrom = userInput.nextLine();
                    System.out.println("Please type the name of the class it goes to.");
                    String relTo = userInput.nextLine();
                    int test = 0;
                    if (myClassContainer.getClassBase(relFrom) == null){
                        test = 1;
                        if (myClassContainer.getClassBase(relTo) == null){
                            test = 2;
                        }
                    }
                    if (test == 1){
                        System.out.println("Class with name " + relFrom + " does not exist.");
                        break;
                    }
                    else if (test == 2){
                        System.out.println("Class with name " + relTo + " does not exist.");
                        break;
                    }
                    if (myRelationshipContainer.addRelationship(relName, relFrom, relTo) == true){
                        System.out.println(relName + " relationship added.");
                    }
                    else{
                        System.out.println("Relationship with name " + relName + " already exists.");
                    }
                    break;
                case "remove relationship":
                    System.out.println("Please type the name of the relationship you wish to remove.");
                    String relDelete = userInput.nextLine();
                    if (myRelationshipContainer.removeRelationship(relDelete) == true){
                        System.out.println(relDelete + " relationship deleted.");
                    }
                    else{
                        System.out.println("No such relationship with name " + relDelete);
                    }
                    break;
                case "add attribute":
                    //Prompts user for a class name.
                    System.out.println("Please type which class it will belong to.");
                    className = userInput.nextLine();
                    //Searches for that class from the container of classes.
                    ClassBase tempClass = myClassContainer.getClassBase(className);
                    //Variables that will be used if a class is found.
                    String attName;
                    String attContent;
                    //Checks whether a class of the given name exists or not.
                    if(tempClass != null){
                        //Asks for a name of the new attribute.
                        System.out.println("Please type attribute name.");
                        attName = userInput.nextLine();
                        myAttributes.setName(attName);
                        //Asks for the content of the new attribute.
                        System.out.println("Please type attribute content.");
                        attContent = userInput.nextLine();
                        myAttributes.setContent(attContent);
                        /* addAttribute will check if an attribute with the given name
                         * already exists or not.
                         */
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
                    //Asks user for a class name.
                    System.out.println("Please type which class the attribute belongs to.");
                    className = userInput.nextLine();

                    //Checks to see if that class exists or not.
                    tempClass = myClassContainer.getClassBase(className);
                    if(tempClass != null){
                        //Asks the user for a name for an existing attribute.
                        System.out.println("Please type the name of the attribute you wish to change.");
                        attName = userInput.nextLine();

                        //Checks to see whether that attribute exists or not.
                        attributes attCheck = tempClass.getAttribute(attName);
                        if(attCheck != null){
                            //Asks the user if they want to change the name or content.
                            System.out.println("Please type 'name' if you wish to edit the name or type 'content' if you wish to edit the content.");
                            String updateType = userInput.nextLine();

                            //Will be used to store the users desired content of the attribute.
                            String updatedContent;

                            /*
                             * If the user wanted to change the name, the if part will run.
                             * If the user wanted to change the content, the else part will run.
                             */
                            if(updateType.equalsIgnoreCase("Name")){
                                //Asks the user for a new name for the attribute.
                                System.out.println("Please type the new name.");
                                updatedContent = userInput.nextLine();

                                /*
                                 * Checks to see if an attribute with that name already exists,
                                 * If one does exist the error will be displayed and the case will break,
                                 * If one does not exist, then the case will continue as normal
                                 */
                                if(attCheck.getName().equalsIgnoreCase(updatedContent)){
                                    System.out.println("An attribute with this name already exists");
                                    break;
                                }
                            }
                            else{
                                //Asks the user for the desired updated content.
                                System.out.println("Please type the new content.");
                                updatedContent = userInput.nextLine();
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
                    //Asks user for a class name.
                    System.out.println("Please type the name of the class you wish to remove an attribute from.");
                    className = userInput.nextLine();
                    //Checks to see if the class exists.
                    tempClass = myClassContainer.getClassBase(className);
                    if(tempClass != null){
                        //Asks the user for an attribute name.
                        System.out.println("Please type the name of the attribute you wish to remove.");
                        attName = userInput.nextLine();

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
                case "list one class":
                    break;
                case "list all classes":
                    break;
                case "list one class relationship":
                    break;
                case "save":
                    break;
                case "load":
                    break;
                case "exit":
                    ExitUML myExit = new ExitUML();
                    myExit.callExit();
                    break;
                default:
                    System.out.println("Please enter a valid command, type 'help' without '' to see a list of available commands.");
                }
            }
        }
    }   
}