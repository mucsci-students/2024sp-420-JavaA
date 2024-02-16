package java2.src.main.java.com.classuml;
public class ExitUML {
    private static void exitUML(int status){
        
        //if yes save and exit
        switch(status){
            case 0:

                //call save and exit
                System.out.println("Saving and exiting.");
                endProgramSave(status);
                break;

            case 1:

                //exit without save
                System.out.println("Saving without exiting.");
                endProgram(status);
                break;

            default:

                //anything else return to editor
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
    public void callExit(int status){ 
        //calls exit function
        exitUML(status);
    }
}
