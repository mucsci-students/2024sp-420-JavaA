package com.classuml;

public class Model
{
    private ClassContainer myClassContainer;
    private RelationshipContainer myRelationshipContainer;

    public Model ()
    {
        myClassContainer = new ClassContainer();
        myRelationshipContainer = new RelationshipContainer();
    }
    public String addClass (String className)
    {
        ClassBase myClass = new ClassBase(className);
        String add = myClassContainer.addClass(myClass);
        return add;
    }
    public String removeClass (String className)
    {
        boolean hasRel = false;
        boolean hasRel2 = false;    
        String retString = "";
        //Checks if the class has any relationships existing.
        //If so, it informs the user and asks them to remove them before removing the class.
        for (Relationship rel : myRelationshipContainer.getAllRelationships())
        {
            if(rel.getSourceClass().equals(className))
            {
                hasRel = true;
            }
            else
            {
                if(rel.getDestClass().equals(className))
                {
                    hasRel = true;
                }
            }
            if(hasRel == true)
            {
                retString.concat((rel.getSourceClass() + " -> " + rel.getDestClass() + " is an existing relationship.\n"));
                hasRel2 = true;
                hasRel = false;
            }
        }
        if(hasRel2 == true)
        {
            retString.concat("Please remove the existing relationship(s) before removing the class.");
            return retString;
        }
        else
        {
            //Prints the return of removeClass, so would say whether it works or not.
            return myClassContainer.removeClass(className);
        }
    }
    public boolean renameClass (String oldName, String newName)
    {

    }
    public boolean addRelationship (String fromRel, String toRel)
    {
        
    }
    public boolean removeRelationship (String fromRel, String toRel)
    {

    }
    public boolean addMethod (String name, String type, String[] paramNames, String[] paramTypes)
    {

    }
    public boolean removeMethod (String name)
    {

    }
    public boolean renameMethod (String oldName, String newName)
    {

    }
    public boolean changeMethodType (String name, String newType)
    {

    }
    public boolean addField (String name, String type)
    {

    }
    public boolean removeField (String name)
    {

    }
    public boolean renameField (String oldName, String newName)
    {

    }
    public boolean changeFieldType (String name, String type)
    {

    }
    public boolean listOneClass (String name)
    {

    }
    public boolean listOneClassRelationship (String name)
    {

    }
    public boolean listAllClasses ()
    {

    }
    public boolean save (String name)
    {

    }
    public boolean load (String name)
    {

    }
    public boolean exit (boolean save)
    {

    }
    public boolean help ()
    {

    }
}