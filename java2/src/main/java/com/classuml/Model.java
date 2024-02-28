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

    public String renameClass (String oldName, String newName)
    {
        //Goes through every relationship, and if any used the class being renamed,
        //It changes the name from the oldname to the newname.
        for (Relationship rel : myRelationshipContainer.getAllRelationships())
        {
            if(rel.getSourceClass().equals(oldName))                        
            {
                rel.setSourceClass(newName);
            }
            else
            {
                if(rel.getDestClass().equals(oldName))
                {
                    rel.setDestClass(newName);
                }
            }
        }
        return myClassContainer.renameClass(oldName, newName);

    }
    
    public int addRelationship (String fromRel, String toRel, String relType)
    {
 
        //used to check if the classes are in the container and prints a message if they are not
        if (myClassContainer.getClassBase(fromRel) == null){
            return 1;
        }
        if (myClassContainer.getClassBase(toRel) == null){
            return 2;
        }
        if (myRelationshipContainer.addRelationship(fromRel, toRel, relType) == true){
            return 3;
        }
        else{
            return 4;
        }
    }
    public int removeRelationship (String fromRel, String toRel)
    {
        //gets relname and removes it from the container
 
        //removeRelationship returns a boolean so if it is true then it was deleted and if not then the relationship doesnt exist
        if (myRelationshipContainer.removeRelationship(fromRel, toRel) == true){
            return 1
        }
        else{
            return 2;
        }
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