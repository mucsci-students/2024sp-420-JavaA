import java.util.Scanner;

public class ExitUML {
    private static void exitUML(int status){
        //create popup once front end is available
        System.out.println("You are exiting NAME UML editor, type yes to save and exit, type no to exit without saving, anything else will return to the editor");

        //replace scanner with values from popups this is a place holder to show my idea
        Scanner obj = new Scanner(System.in);

        //reads from scanner
        String mySave = obj.nextLine();

        //if yes save and exit
        switch(mySave){
            case "yes":

                //call save and exit
                System.out.println("Saving and exiting.");
                endProgramSave(status);
                break;

            case "no":

                //exit without save
                System.out.println("Saving without exiting.");
                endProgram(status);
                break;

            default:

                //nothing return to editor
                System.out.println("Returning to editor.");
                break;
        }
    }
    

    //exit without saving
    private static void endProgram(int status){
        System.out.println("You have exited without saving.");
        System.exit(status);
    }

    //save and exit
    private static void endProgramSave(int status){
        //save
        System.out.println("You have saved.");
        System.exit(status);
    }

    //public call to exit for other files returns status of exit
    public void callExit(){ 
        //calls exit function
        exitUML(0);
    }
}
