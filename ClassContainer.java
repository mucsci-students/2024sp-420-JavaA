import java.util.ArrayList;
//Written by: Cullen Kurtz
public class ClassContainer
{
    private ArrayList<ClassBase> Classes;
    


    public ClassContainer()
    {

    }

    public String addClass(ClassBase newClass)
    {
        //If true, class with that name already exists!
        boolean exists = false;
        //Loops through every class in Classes, checking if any match
        //the name of the new class being added.
        for(ClassBase classList : Classes)
        {
            if(classList.getName().equals(newClass.getName()))
                exists=true;
        }
        if(exists)
        return "Class with that name already exists!";
        Classes.add(newClass);
        //Class was added with no issues.
        return "Class " + newClass.getName() + " was added.";
    }
    //Doesn't yet deal with classes with relationships!!!
    //Going to wait until relationship stuff is made to do that.
    public String removeClass(ClassBase remClass)
    {
        for(ClassBase classList : Classes)
        {
        //Loops through every class in Classes, checking if any match
        //the class being removed. If so, it removes it.
            if(classList.getName().equals(remClass.getName()))
            {
                Classes.remove(classList);
                return "Class was successfully removed!";
            }
        }
        return "No class matching that name was found.";
    }
    public String renameClass(ClassBase renClass)
    {
        return "Placeholder!";
    }
}