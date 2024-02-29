package com.classuml;
import java.util.ArrayList;

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
            return 0;
        }
    }
    public int removeRelationship (String fromRel, String toRel)
    {
        //gets relname and removes it from the container
 
        //removeRelationship returns a boolean so if it is true then it was deleted and if not then the relationship doesnt exist
        if (myRelationshipContainer.removeRelationship(fromRel, toRel) == true){
            return 1;
        }
        else{
            return 2;
        }
    }
    public int addField (String className, String name, String type)
    {
        ClassBase tempClass = myClassContainer.getClassBase(className);
        if(tempClass != null){
            attributes myAttributes = new attributes();
            myAttributes.setName(name);
            myAttributes.setType(type);
            return tempClass.addAttribute(myAttributes);
        }
        return 0;

    }
    public int removeField (String className, String name)
    {
        //Checks to see if the class exists.
        ClassBase tempClassRem = myClassContainer.getClassBase(className);
        if(tempClassRem != null){

            //Checks to see if the attribute exists or not
            attributes attCheck = tempClassRem.getAttribute(name);
            if(attCheck != null){

                //An attribute with the name was found and deleted
                tempClassRem.deleteAttribute(attCheck);
                return 1;
            }
            else{
                //An attribute could not be found with the given name
                return 2;
            }
        }
        else{
            //A class of the given name could not be found.
            return 0;
        }
    }
    public int renameField (String className, String oldName, String newName)
    {
        ClassBase tempClassRename = myClassContainer.getClassBase(className);
        if(tempClassRename != null)
        {
            attributes attCheck = tempClassRename.getAttribute(oldName);
            if(attCheck != null)
            {
                if(attCheck.getName().equalsIgnoreCase(newName))
                    return 2;
                tempClassRename.updateAttribute(attCheck, "name", newName);
                return 3;
            }
            else
            {
                return 1;
            }
        }
        else
        {
            return 0;
        }
    }
    public int changeFieldType (String className, String name, String type)
    {
        ClassBase tempClassEdit = myClassContainer.getClassBase(className);
        if(tempClassEdit != null)
        {
            attributes attCheck = tempClassEdit.getAttribute(name);
            if(attCheck != null)
            {
                tempClassEdit.updateAttribute(attCheck, "type", type);
                return 3;
                
            }
            else
            {
                return 1;
            }
        }
        else
        {
            return 0;
        }

    }
    public boolean addMethod (String className, String name, String type, String paramNames, String paramTypes)
    {
        String[] paramNameArray = paramNames.split(" ");
        String[] paramTypeArray = paramTypes.split(" ");
        ClassBase tempClassAddMethod = myClassContainer.getClassBase(className);
        if(tempClassAddMethod == null)
        {
            return false;
        }
        ArrayList<attributes> params = new ArrayList<attributes>();
        for(int i = 0; i < paramNameArray.length; i++)
        {
            attributes attTemp = new attributes();
            attTemp.setName(paramNameArray[i]);
            attTemp.setType(paramTypeArray[i]);
            params.add(attTemp);

        }
        methods newMethod = new methods(name, type, params);
        tempClassAddMethod.addMethod(newMethod);
        return true;
    }
    public int removeMethod (String className, String name)
    {
        ClassBase tempClassRemoveMethod = myClassContainer.getClassBase(className);
        if(tempClassRemoveMethod == null)
        {
            return 0;
        }
        ArrayList<methods> methods = tempClassRemoveMethod.getClassMethods();
        for(methods method : methods)
        {

            if(method.getName().equals(name))
            {
                tempClassRemoveMethod.deleteMethod(method);
                //Method was successfully removed.
                return 1;
            }
        }
        return 2;

    }
    public int renameMethod (String className, String oldName, String newName)
    {
        ClassBase tempClassRenameMethod = myClassContainer.getClassBase(className);
        if(tempClassRenameMethod == null)
        {
            return 0;
        }
        methods editMethod;
        ArrayList<methods> methods = tempClassRenameMethod.getClassMethods();
        for(methods method : methods)
        {

            if(method.getName().equals(newName))
            {
                
                //Method already exists.
                return 1;
            }
            if(method.getName().equals(oldName))
            {
                editMethod = method;
                tempClassRenameMethod.updateMethod(editMethod, newName, "name");
                return 3;
            }
        }
        return 2;
    }
    public int changeMethodType (String className, String name, String newType)
    {
        ClassBase tempClassRetypeMethod = myClassContainer.getClassBase(className);
        if(tempClassRetypeMethod == null)
        {
            return 0;
        }
        ArrayList<methods> methods = tempClassRetypeMethod.getClassMethods();
        for(methods method : methods)
        {

            if(method.getName().equals(name))
            {
                tempClassRetypeMethod.updateMethod(method, newType, "type");
                //Method was successfully changed.
                return 3;
            }
        }
        return 2;
    }

    public String listOneClass (String name)
    {
        //loop through classContainer and print out the name, and attributes
        boolean isIn = false;
        boolean isCont = false;
        for(ClassBase temp : myClassContainer.getContainer())
        {
            String retValListOne = "";
            if (temp.getName().equals(name))
            {
                isIn = true;
                retValListOne.concat("Class Name: " + name +"\n");
                for(attributes att : temp.getClassAttributes())
                {
                    retValListOne.concat("Attribute Name: " + att.getName() + "\nType: " + att.getType() + "\n");
                    isCont = true;
                }
            }
        }
        if(!isIn)
        {
            return ("Class with that name does not exist.");
        }
        if(!isCont)
        {
           return ("Class has no attributes.");
        }
        return ("If this is printed, please inform us.");
    }
    public String listOneClassRelationship (String name)
    {
        //used to tell if the class name exists and whether or not is has relationships
        boolean isIn = false;
        boolean isRel = false;
        String retStringRel = "";
        //loop through classContainer to check if the name exists
        for(ClassBase cls : myClassContainer.getContainer()){
            if(cls.getName().equals(name)){
                isIn = true;
            }
        }

        //loop thorugh relContainer and print name, fromClass, toClass
        for(Relationship rel : myRelationshipContainer.getAllRelationships()){
            if (rel.getSourceClass().equals(name) || rel.getDestClass().equals(name)){
                retStringRel.concat("Source Class: " + rel.getSourceClass() + "\nDestination Class: " + rel.getDestClass() + "\n");
                isRel = true;
            }
        }
        if (!isIn){
            return ("Class with that name does not exist.");
        }
        if (!isRel){
            return ("There are no relationships connected to this class.");
        }
        return retStringRel;
    }
    public String listAllClasses ()
    {
        //useds to tell if there are any classes
        boolean isInAll = false;
        boolean isContAll = false;
        String retStringAll = "";

        //loop through classContainer and prints class name and attributes of every class
        for(ClassBase temp : myClassContainer.getContainer()){
            retStringAll.concat("Class Name: " + temp.getName() + "\n");
            isInAll = true;
            for(attributes att : temp.getClassAttributes()){
                retStringAll.concat("Attribute Name: " + att.getName() + "\nType: " + att.getType() + "\n");
                isContAll = true;
            }
        }
        if (!isInAll){
            return ("There are no classes.");
        }
        if(!isContAll){
            retStringAll.concat("There are no attributes in any classes.\n");
        }
        return retStringAll;
    }
    public void save (String name)
    {
        saveUML.save(myClassContainer, myRelationshipContainer, name);
        return;
    }
    public void save()
    {
        saveUML.save(myClassContainer, myRelationshipContainer);
        return;
    }
    public void load (String name)
    {
        LoadUML load = new LoadUML();
        for (ClassBase cls : myClassContainer.getContainer()){
            cls.getClassAttributes().clear();
            cls.getClassMethods().clear();
        }
        myClassContainer.getContainer().clear();
        myRelationshipContainer.getAllRelationships().clear();
        load.load(name + ".json",myClassContainer,myRelationshipContainer);
        return;
    }
}