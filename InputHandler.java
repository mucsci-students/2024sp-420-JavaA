import java.util.Scanner;
public class InputHandler
{  
    ClassContainer myClassContainer = new ClassContainer();
    Relationship myRelationship = new Relationship();
    attributes myAttributes = new attributes();

    public void main(String[] args)
    {
        System.out.println("Welcome. If you need help with commands, please type 'help', without the '' surrounding it.");
        while (true)
        {
        Scanner userInput = new Scanner(System.in);
        while(true)
        {
            String userString = userInput.nextLine();
            switch(userString)
            {
                case "help":
                    System.out.println("Here is a list of commands, please enter them without the '':");
                    System.out.println("'add class', will prompt you for a class name to add.");
                    System.out.println("'remove class', will prompt you for a class name to remove.");
                    System.out.println("'rename class', will prompt you for a class you wish to rename, and then the name you wish to rename it to. ");
                    System.out.println("'add relationship', will prompt you for the name of the relationship you wish to add, as well as the class it goes from/to.");
                    System.out.println("'remove relationship', asks you for the name of the relationship you wish to remove.");
                    System.out.println("'add attribute', asks you for the name of the attribute you wish to add, the content, and the class name.");
                    System.out.println("'edit attribute', asks you which class you want to edit an attribute from, which attribute you want to edit, how would you like to edit it, and the edit itself.");
                    System.out.println("'remove attribute', asks which class you would like to remove an attribute from, and which attribute you would like to remove. ");
                    System.out.println("'save', saves the class.");
                    System.out.println("'load', asks you for a file to load from, then loads said file.");
                    System.out.println("'exit', closes the program.");
                    System.out.println("'help', loads this document.");
                    break;
                case "add class":
                    System.out.println("Please type the name of the class you wish to add.");
                    String className = userInput.nextLine();
                    ClassBase myClass = new ClassBase(className);
                    myClassContainer.addClass(myClass);
                    System.out.println(className +" class added.");
                    break;
                case "remove class":
                    System.out.println("Please type the name of the class you wish to delete.");
                    className = userInput.nextLine();
                    myClassContainer.removeClass(className);
                    System.out.println(className + " class removed.");
                    break;
                case "rename class":
                    System.out.println("Please type the name of the class you wish to rename and the new name of the class.");
                    className = userInput.nextLine();
                    String newName = userInput.nextLine();
                    myClassContainer.renameClass(className, newName);
                    System.out.println(className + "renamed to " + newName +".");
                    break;
                case "add relationship":
                    System.out.println("Please type the name of the relationship.");
                    String relName = userInput.nextLine();
                    System.out.println("Please type the name of the class it comes from.");
                    String relFrom = userInput.nextLine();
                    System.out.println("Please type the name of the class it goes to.");
                    String relTo = userInput.nextLine();
                    myRelationship.setRelationship(relName, relFrom, relTo);
                    System.out.println(relName + " relationship added.");
                    break;
                case "remove relationship":
                    System.out.println("Please type the name of the relationship you wish to remove.");
                    String relDelete = userInput.nextLine();
                    myRelationship.delRelation(relDelete);
                    System.out.println(relDelete + " relationship deleted.");
                    break;
                case "add attribute":
                    System.out.println("Please type attribute name.");
                    String attName = userInput.nextLine();
                    myAttributes.setName(attName);
                    System.out.println("Please type attribute content.");
                    String attContent = userInput.nextLine();
                    System.out.println("Please type class name.");
                    String className2 = userInput.nextLine();
                    myAttributes.setContent(attContent);
                    ClassBase tempClass = myClassContainer.getClassBase(className2);
                    tempClass.addAttribute(myAttributes);
                    System.out.println(attName + " attribute added with " + attContent + " as its content.");
                    break;
                case "edit attribute":
                    System.out.println("From which class.");
                    className = userInput.nextLine();
                    System.out.println("Which attribute would you like to change.");
                    attName = userInput.nextLine();
                    System.out.println("What edit would you like to make, name or content of an attribute.");
                    String updateType = userInput.nextLine();
                    System.out.println("Type the new change.");
                    String updatedContent = userInput.nextLine();
                    tempClass = myClassContainer.getClassBase(className);
                    tempClass.updateAttribute(tempClass.getAttribute(attName), updateType, updatedContent);
                    break;
                case "remove attribute":
                    System.out.println("From which class.");
                    className = userInput.nextLine();
                    System.out.println("Which attribute would you like to remove.");
                    attName = userInput.nextLine();
                    tempClass = myClassContainer.getClassBase(className);
                    tempClass.deleteAttribute(tempClass.getAttribute(attName));
                    break;
                case "save":
                    break;
                case "load":
                    break;
                case "exit":
                    ExitUML myExit = new ExitUML();
                    userInput.close();
                    myExit.callExit();
                    break;
                default:
                    System.out.println("Please enter a valid command, type 'help' without '' to see a list of available commands.");
                }
            }
        }
    }   
}