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
                    System.out.println("Please type what attribute you want to change.");
                    String atName = userInput.nextLine();
                    System.out.println("Please type 'Name' if you wish to update attribute name and 'Content' if you wish to update attribute content.");
                    String updating = userInput.nextLine();
                    System.out.println("Please type the class that holds this attribute.");
                    String claName = userInput.nextLine();
                    ClassBase tempClass2 = myClassContainer.getClassBase(claName);
                    System.out.println("Please type new updated information.");
                    String updated = userInput.nextLine();
                    tempClass2.updateAttribute(atName, updating, updated);
                    System.out.println(atName + " has had its " + updating + " updated to " + updated + ".");
                    break;
                case "delete attribute":
                    System.out.println("Please type what attribute you wish to delete.");
                    String delAtt = userInput.nextLine();
                    System.out.println("Please type which class holds this attribute.");
                    String delClass = userInput.nextLine();
                    ClassBase tempClass3 = myClassContainer.getClassBase(delClass);
                    tempClass3.deleteAttribute(delAtt);
                    System.out.println(delAtt + " attribute deleted.");
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