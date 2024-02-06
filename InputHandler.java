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
        Scanner input = new Scanner(System.in);
        Scanner userInput = new Scanner(System.in);
        while(true)
        {
            String userString = input.nextLine();
            switch(userString)
            {
                case "help":
                    System.out.println("Help denied :)");
                    break;
                case "add class":
                    System.out.println("Please type the name of the class you wish to add.");
                    String className = userInput.nextLine();
                    ClassBase myClass = new ClassBase(className);
                    myClassContainer.addClass(myClass);
                    break;
                case "remove class":
                    System.out.println("Please type the name of the class you wish to delete.");
                    className = userInput.nextLine();
                    myClassContainer.removeClass(className);
                    break;
                case "rename class":
                    System.out.println("Please type the name of the class you wish to rename and the new name of the class.");
                    className = userInput.nextLine();
                    String newName = userInput.nextLine();
                    myClassContainer.renameClass(className, newName);
                    break;
                case "add relationship":
                    System.out.println("Please type the name of the relationship.");
                    String relName = userInput.nextLine();
                    System.out.println("Please type the name of the class it comes from.");
                    String relFrom = userInput.nextLine();
                    System.out.println("Please type the name of the class it goes to.");
                    String relTo = userInput.nextLine();
                    myRelationship.setRelationship(relName, relFrom, relTo);
                    break;
                //case "remove relationship":           I dont think this was one of the reqs for relationship but I will check and add it if it was
                //    break;
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
                    break;
                case "edit attribute":
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