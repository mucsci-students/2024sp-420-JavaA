package java2.src.main.java.com.classuml;
import java.util.ArrayList;
//Written by: Cullen Kurtz
//Note to future self:
//Anything related to not inputting name is automatically handled by ClassContainer
//methods requiring a string.
public class ClassTesting
{
    public static void main(String[] args)
    {
        ClassBase test = new ClassBase("testClass");
        ClassContainer classList = new ClassContainer();
        System.out.println("true means it passed the test, false means it failed.");
        System.out.println("Basic add " + addTest(test, classList));
        System.out.println("Exists add " + addTestExists(test, classList));
        System.out.println("Basic remove " + removeTest(test, classList));
        System.out.println("Doesn't exist remove " + removeTestNotExist(test, classList));
        System.out.println("Has relationships remove " + removeTestRelationships());
        ClassBase testTwo = new ClassBase("testing");
        classList.addClass(testTwo);
        System.out.println("Basic rename " + renameTest(classList, "testing", "newName"));
        System.out.println("Rename where original doesn't exist " + renameNotExist(classList, "nothingExists", "newNameAgain"));
        classList.addClass(test);
        System.out.println("Rename where new name already exists " + renameAlreadyExist(classList, "newName", "testClass"));
    }
    public static boolean addTest(ClassBase test, ClassContainer classList)
    {
        String addReply = classList.addClass(test);
        if(addReply.equals("Class testClass was added."))
            return true;
        return false;  
    }
    public static boolean addTestExists(ClassBase test, ClassContainer classList)
    {
        String addReply = classList.addClass(test);
        if(addReply.equals("Class with that name already exists!"))
            return true;
        return false;
    }
    public static boolean removeTest(ClassBase test, ClassContainer classList)
    {
        String removeReply = classList.removeClass(test.getName());  
        if(removeReply.equals("Class was successfully removed!"))
            return true; 
        return false;
    }
    public static boolean removeTestNotExist(ClassBase test, ClassContainer classList)
    {
        String removeReply = classList.removeClass(test.getName());
        if(removeReply.equals("No class matching that name was found."))
            return true;
        return false;
    }
    public static boolean removeTestRelationships()
    {
        return false;
    }
    public static boolean renameTest(ClassContainer classList, String testClassName, String newName)
    {
        String renameReply = classList.renameClass(testClassName, newName);
        if(renameReply.equals("Class " + testClassName + " was successfully renamed to " + newName + "."))
            return true;
        return false;
    }
    public static boolean renameNotExist(ClassContainer classList, String noName, String newName)
    {
        String renameReply = classList.renameClass(noName, newName);
        if(renameReply.equals("You can't rename a class that doesn't exist!"))
            return true;
        return false;
    }
    public static boolean renameAlreadyExist(ClassContainer classList, String testClassName, String existName)
    {
        String renameReply = classList.renameClass(testClassName, existName);
        if(renameReply.equals("A class with your new name already exists!"))
            return true;
        return false;
    }
}