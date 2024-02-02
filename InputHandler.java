import java.util.Scanner;
public class InputHandler
{
    public static void main(String[] args)
    {
        System.out.println("Welcome. If you need help with commands, please type 'help', without the '' surrounding it.");
        while (true)
        {
            
        }

    }
    public void switchCaseHell()
    {
        Scanner input = new Scanner(System.in);
        while(true)
        {
            String userString = input.nextLine();
            switch(userString)
            {
                case "help":
                    break;
                case "add class":
                    System.out.println("Please type the name of the class you wish to add.");
                    break;
                case "remove class":
                    break;
                case "rename class":
                    break;
                case "add relationship":
                    break;
                case "remove relationship":
                    break;
                case "rename relationship":
                    break;
                case "add attribute":
                    break;
                case "edit attribute":
                    break;
                case "save":
                    break;
                case "load":
                    break;
                case "exit":
                    break;
                default:
                    System.out.println("Please enter a valid command, type 'help' without '' to see a list of available commands.");
            }
        }
        input.close();
    }
}