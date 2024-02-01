//Written by: Cullen Kurtz
//Note to future self:
//Write tests for forgetting name, not giving name for remove, not giving for rename, etc.
public class ClassTesting
{
    public static void main(String[] args)
    {
        System.out.println("true means it passed the test, false means it failed.");
        System.out.println("Basic add " + addTest());
        System.out.println("No name add " + addTestNoName());
        System.out.println("Exists add " + addTestExists());
        System.out.println("Basic remove " + removeTest());
        System.out.println("No name remove " + removeTestNoName());
        System.out.println("Doesn't exist remove " + removeTestNotExist());
        System.out.println("Has relationships remove " + removeTestRelationships());
        ClassBase test = new ClassBase("testClass");
        ClassContainer classList = new ClassContainer();
        System.out.println(classList.addClass(test));
        System.out.println(test.getName());
        System.out.println(classList.removeClass(test));
    }
    public static boolean addTest()
    {
        return false;  
    }
    public static boolean addTestNoName()
    {
        return false;
    }
    public static boolean addTestExists()
    {
        return false;
    }
    public static boolean removeTest()
    {
        return false;
    }
    public static boolean removeTestNoName()
    {
        return false;
    }
    public static boolean removeTestNotExist()
    {
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