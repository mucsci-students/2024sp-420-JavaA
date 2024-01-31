import java.util.ArrayList;
public class ClassContainer
{
    private ArrayList<ClassBase> Classes;
    


    public ClassContainer()
    {

    }

    public String addClass(ClassBase newClass)
    {
        boolean exists = false;
        for(ClassBase classList : Classes)
        {
            if(classList.getName().equals(newClass.getName()))
                exists=true;
        }
        if(exists)
        return "Class with that name already exists!";
        Classes.add(newClass);
        return "Success!";
    }
}