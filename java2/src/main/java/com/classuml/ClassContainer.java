package com.classuml;
import java.util.ArrayList;
//Written by: Cullen Kurtz
public class ClassContainer
{
    private ArrayList<ClassBase> classes;
    

    /**
     * Basic class constructor.
     */
    public ClassContainer()
    {
        classes = new ArrayList<ClassBase>();
    }

    /**Attempts to add a class to the ClassContainer.
     *@param newClass The name of the new class.
     *@return A string containing the result of the add.
     */
    public String addClass(ClassBase newClass)
    {
        //If true, class with that name already exists!
        boolean exists = false;
        //Loops through every class in Classes, checking if any match
        //the name of the new class being added.
        for(ClassBase classList : classes)
        {
            if(classList.getName().equals(newClass.getName()))
                exists=true;
        }
        if(exists)
        return "Class with that name already exists!";
        classes.add(newClass);
        //Class was added with no issues.
        return "Class " + newClass.getName() + " was added.";
    }

    /**
     * Attempts to remove a class from the ClassContainer.
     * @param remClass The name of the class being removed.
     * @return A string containing the result of the remove.
     */
    public String removeClass(String remClass)
    {
        for(ClassBase classList : classes)
        {
        //Loops through every class in Classes, checking if any match
        //the class being removed. If so, it removes it.
            if(classList.getName().equals(remClass))
            {
                classes.remove(classList);
                return "Class was successfully removed!";
            }
        }
        return "No class matching that name was found.";
    }

    /**
     * Renames a class in the class container.
     * @param renClass The class being renamed.
     * @param newName The new name of the class. 
     * @return A string containing the result of the rename.
     */
    public String renameClass(String renClass, String newName)
    {
        int classInd = -1;
        for(ClassBase classList : classes)
        {
            if(classList.getName().equals(renClass))
            {
                classInd = classes.indexOf(classList);
            }
            if(classList.getName().equals(newName))
                return "A class with your new name already exists!";
        }
        if(classInd != -1)
        {
            classes.get(classInd).setName(newName);
            return "Class " + renClass + " was successfully renamed to " + newName + ".";
        }
        return "You can't rename a class that doesn't exist!";
    }
    
    /**
     * Returns the classbase object of a specific class in the container.
     * @param className The name of the class being searched for.
     * @return Returns the classbase of the class.
     */
    public ClassBase getClassBase(String className)
    {
        for(ClassBase classList : classes)
        {
        //Loops through every class in Classes, checking if any match
        //the class name, if so it returns it.
            if(classList.getName().equals(className))
            {
                return classList;
            }
        }
        return null;
    }

    /**
     * Returns a class container for arraylist methods.
     * @return Returns the container.
     */
    public ArrayList<ClassBase> getContainer() 
    {
        return classes;
    }
}