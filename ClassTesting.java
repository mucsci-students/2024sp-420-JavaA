import java.util.ArrayList;
//Written by: Cullen Kurtz
//Note to future self:
//Anything related to not inputting name is automatically handled by ClassContainer
//methods requiring a ClassBase object, which requires a name to be created.
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
        String removeReply = classList.removeClass(test);  
        if(removeReply.equals("Class was successfully removed!"))
            return true; 
        return false;
    }
    public static boolean removeTestNotExist(ClassBase test, ClassContainer classList)
    {
        String removeReply = classList.removeClass(test);
        if(removeReply.equals("No class matching that name was found."))
            return true;
        return false;
    }
    public static boolean removeTestRelationships()
    {
        return false;
    }
    public static boolean renameTest()
    {
        return false;
    }
    public static boolean renameNotExist()
    {
        return false;
    }
    public static boolean renameAlreadyExist()
    {
        return false;
    }
}