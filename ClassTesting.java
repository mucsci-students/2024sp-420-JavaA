public class ClassTesting
{
    public static void main(String[] args)
    {
        ClassBase test = new ClassBase("testClass");
        ClassContainer classList = new ClassContainer();
        System.out.println(classList.addClass(test));
        System.out.println(test.getName());
        System.out.println(classList.removeClass(test));
    }
}