import java.util.Scanner;

public class ExitUML {
    
    private void exitUML(){
        //if program is not saved call this
        endProgramSave(0);
        //if program is saved call this
        endProgram(0);

    }
    private static void endProgram(int status){
        System.exit(status);
    }
    private static void endProgramSave(int status){
        //create popup once front end is available
        System.out.println("Your UML has not been saved would you like to save and exit, exit without saving, or return to UML?");

        //replace scanner with values from popups this is a place holder to show my idea
        Scanner obj = new Scanner(System.in);

        //reads from scanner
        String mySave = obj.nextLine();
        obj.close();

        //if yes save and exit
        if(mySave.equals("yes")){
            //save here
            System.exit(status);
        }
        //exit without save
        else if(mySave.equals("no")){
            System.exit(status);
        }
        else{
            //return to work
        }
    }

    //public get exit for other files returns status of exit
    public void getExit(){ 
        //calls exit function
        exitUML();
    }
}
