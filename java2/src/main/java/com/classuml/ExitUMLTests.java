package java2.src.main.java.com.classuml;
import java.util.Scanner;
public class ExitUMLTests {
    
    public static void main(String args[]){
        System.out.println("Enter Test Number: ");
        Scanner obj = new Scanner(System.in);
        int testNo = obj.nextInt();
        switch(testNo){
        /*
         * Test 1
         * Test if program saves when requested and exits after
         */
        case 1:
            ExitUML Test1 = new ExitUML();
            Test1.callExit(0);
            break;
        //Test if JSON was updated properly
        //Test if exit was successful
        
        /*
         * Test 2
         * Test exit without saving
         */
        case 2:
            ExitUML Test2 = new ExitUML();
            Test2.callExit(1);
            break;
        //Test if it exited and JSON was not updated

        /*
         * Test 3
         * Test if program returns when exit is not selected
         */
        case 3:
            ExitUML Test3 = new ExitUML();
            Test3.callExit(-1);
            break;
        //Test if new updates can me made and program was not exited
        
        }
        obj.close();
    }
}
