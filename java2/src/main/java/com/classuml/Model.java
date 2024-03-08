package com.classuml;
import java.util.ArrayList;

public class Model
{
    private ClassContainer myClassContainer;
    private RelationshipContainer myRelationshipContainer;
    /**
     * Constructor for model
     * 
     * @Variables       myClassContainer - A string to set method name.
     *                  myRelationshipContainer - A string to set method return type.
     * 
     * @Preconditions   
     * @Postconditions  Model will be created.
     * 
     * @Returns         The new Model object.
     */
    public Model ()
    {
        myClassContainer = new ClassContainer();
        myRelationshipContainer = new RelationshipContainer();
    }
    public ClassContainer getClassContainer(){
        return myClassContainer;
    }
    public RelationshipContainer getRelationshipContainer(){
        return myRelationshipContainer;
    }
    /**
     * Adds a class to the model.
     * 
     * @Variables      className - A string to be used for the class name.
     * 
     * @Preconditions
     * @Postconditions A class is added to myClassContainer  
     * 
     * @Returns         A string giving the message to be printed about the class being added.
     */
    public String addClass (String className)
    {
        ClassBase myClass = new ClassBase(className);
        String add = myClassContainer.addClass(myClass);
        return add;
    }
    /**
     * Removes a class from the model.
     * 
     * @Variables       className - The name of the class to be removed.
     * 
     * @Preconditions   The class being passed in exists.
     * @Postconditions  A class of the given name is removed from the mode.
     * 
     * @Returns         A string giving the message to be printed about the class being removed.
     */
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
    /**
     * Renames a class.
     * 
     * @Variables       oldName - The name of the class to be renamed.
     *                  newName - What it should be renamed to.
     * 
     * @Preconditions   A class with oldName exists and no class with newName exists.
     * @Postconditions  A class called oldName is renamed to newName.
     * 
     * @Returns         A string giving the message to be printed about the class being renamed..
     */
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
    /**
     * Adds a relationship between two classes.
     * 
     * @Variables       fromRel - The class a relationship is going from.
     *                  toRel - The class a relationship is going to.
     *                  relType - The type of the relationship.
     * 
     * @Preconditions   The classes fromRel and toRel exist, and relType is one of the valid options.
     * @Postconditions  A relationship of type relType is created from fromRel to toRel.
     * 
     * @Returns         An int indicating the status of the add.
     */
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
    /**
     * Removes a relationship from fromRel to toRel.
     * 
     * @Variables       fromRel - The class the relationship went from.
     *                  toRel - The class the relationship went to.
     * 
     * @Preconditions   A relationship from fromRel and to toRel exists.
     * @Postconditions  That relationship no longer exists.
     * 
     * @Returns         An int indicating the status of the remove.
     */
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
    /**
     * Adds a field to a class.
     * 
     * @Variables       className - The name of the class the field is being added to.
     *                  name - The name of the field.
     *                  type - The type of the field.
     * 
     * @Preconditions   A class of className exists.
     * @Postconditions  A field is added to the class with name name and type type.
     * 
     * @Returns         An int indicating the status of the add.
     */
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
    /**
     * Removes a field from a class.
     * 
     * @Variables       className - The name of the class the field is being removed from.
     *                  name - The name of the field being removed.
     * 
     * @Preconditions   A class of className exists with a field name.
     * @Postconditions  A field with name name is removed from the class.
     * 
     * @Returns         An int indicating the status of the remove.
     */
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
    /**
     * Renames a field in a class.
     * 
     * @Variables       className - The name of the class the field is being renamed in.
     *                  oldName - The old name of the field.
     *                  newName - The new name of the field.
     * 
     * @Preconditions   A class of className exists with field oldName and no field newName.
     * @Postconditions  A field is renamed from oldName to newName.
     * 
     * @Returns         An int indicating the status of the rename.
     */
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
    /**
     * Changes the type of a field.
     * 
     * @Variables       className - The name of the class the field is being changed in.
     *                  name - The name of the field.
     *                  type - The new type of the field.
     * 
     * @Preconditions   A class of className exists with field name.
     * @Postconditions  A field of name is changed from it's old type to the new one.
     * 
     * @Returns         An int indicating the status of the change.
     */
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
    /**
     * Adds a method to a class.
     * 
     * @Variables       className - The name of the class the method is being added to.
     *                  name - The name of the method.
     *                  type - The type of the method.
     *                  paramNames - A string of parameter names.
     *                  paramTypes - A string of parameter types.
     * 
     * @Preconditions   A class of className exists.
     * @Postconditions  A method is added to the class with name name, type type, and a param array.
     * 
     * @Returns         An int indicating the status of the add.
     */
    public boolean addMethod (String className, String name, String type)
    {
        ClassBase tempClassAddMethod = myClassContainer.getClassBase(className);
        if(tempClassAddMethod == null)
        {
            return false;
        }
        methods newMethod = new methods(name, type);
        tempClassAddMethod.addMethod(newMethod);
        return true;
    }
    /**
     * Adds a param to a method.
     * 
     * @Variables       className - The name of the class the method is in.
     *                  methodName - The name of the method.
     *                  paramName - The name of the param.
     *                  paramType - The type of the param.
     * 
     * @Preconditions   A class of className exists with method methodName and no param paramName.
     * @Postconditions  A param named paramName of type paramType is added to method methodName in class className.
     * 
     * @Returns         An int indicating the status of the add.
     */
    public int addParams (String className, String methodName, String paramName, String paramType)
    {
        ClassBase tempClassAddParam = myClassContainer.getClassBase(className);
        if(tempClassAddParam == null)
        {
            return 0;
        }
        ArrayList<methods> methodList = tempClassAddParam.getClassMethods();
        for(methods methodSingle : methodList)
        {
            if(methodSingle.getName().equals(methodName))
            {
                ArrayList<attributes> paramList = methodSingle.getParams();
                for(attributes paramSingle : paramList)
                {
                    if(paramSingle.getName().equals(paramName))
                    {
                        return 2;
                    }
                }
                methodSingle.addParam(paramName, paramType);
                return 3;
            }
        }
        return 1;
    }
    /**
     * Removes a param from a method.
     * 
     * @Variables       className - The name of the class.
     *                  methodName - The name of the method the param is being removed from.
     * 
     * @Preconditions   A class of className exists with method name and param paramName.
     * @Postconditions  A param paramName is removed from methodName in className.
     * 
     * @Returns         An int indicating the status of the remove.
     */
    public int removeParam (String className, String methodName, String paramName)
    {
        ClassBase tempClassRemParam = myClassContainer.getClassBase(className);
        if(tempClassRemParam == null)
        {
            return 0;
        }
        ArrayList<methods> methodList = tempClassRemParam.getClassMethods();
        for(methods methodSingle : methodList)
        {
            if(methodSingle.getName().equals(methodName))
            {
                ArrayList<attributes> paramList = methodSingle.getParams();
                for(attributes paramSingle : paramList)
                {
                    if(paramSingle.getName().equals(paramName))
                    {
                        methodSingle.removeParam(paramName);
                        return 3;
                    }
                }
                
                return 2;
            }
        }
        return 1;
    }
    /**
     * Removes all params from a method.
     * 
     * @Variables       className - The name of the class the params are being cleared from.
     *                  methodName - The name of the method.
     * 
     * @Preconditions   A class of className exists with method name.
     * @Postconditions  All params are removed from the method.
     * 
     * @Returns         An int indicating the status of the clear.
     */
    public int clearParams (String className, String methodName)
    {
        ClassBase tempClassClearParams = myClassContainer.getClassBase(className);
        if(tempClassClearParams == null)
        {
            return 0;
        }
        ArrayList<methods> methodList = tempClassClearParams.getClassMethods();
        for(methods methodSingle : methodList)
        {
            if(methodSingle.getName().equals(methodName))
            {
                methodSingle.removeAllParam();
                return 2;
            }
        }
        return 1;
    }

    /**
     * Removes a method from a class.
     * 
     * @Variables       className - The name of the class the method is being removed from.
     *                  name - The name of the method.
     * 
     * @Preconditions   A class of className exists with method name.
     * @Postconditions  A method with name name is removed from the class.
     * 
     * @Returns         An int indicating the status of the remove.
     */
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
    /**
     * Renames a method in a class.
     * 
     * @Variables       className - The name of the class the method is being renamed in.
     *                  oldName - The old name of the method.
     *                  newName - The new name of the method.
     * 
     * @Preconditions   A class of className exists with method oldName and no method newName
     * @Postconditions  A method oldName is renamed to newName.
     * 
     * @Returns         An int indicating the status of the rename.
     */
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
    /**
     * Changes a method type in a class.
     * 
     * @Variables       className - The name of the class the method is being changed in.
     *                  name - The name of the method.
     *                  newType - The new type of the method.
     * 
     * @Preconditions   A class of className exists with method name.
     * @Postconditions  A method is name is changed to newType.
     * 
     * @Returns         An int indicating the status of the change.
     */
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
    /**
     * Lists one class and its fields and methods.
     * 
     * @Variables       className - The name of the class being listed.
     * 
     * @Preconditions   A class of className exists.
     * @Postconditions
     * 
     * @Returns         A string containing the listing.
     */
    public String listOneClass (String name)
    {
        //loop through classContainer and print out the name, and attributes
        boolean isIn = false;
        boolean isCont = false;
        boolean hasMethod = false;
        String retValListOne = "";
        for(ClassBase temp : myClassContainer.getContainer())
        {
            
            if (temp.getName().equals(name))
            {
                isIn = true;
                retValListOne = retValListOne.concat("Class Name: " + name +"\n");
                for(attributes att : temp.getClassAttributes())
                {
                    retValListOne = retValListOne.concat("Field Name: " + att.getName() + "\nType: " + att.getType() + "\n");
                    isCont = true;
                }
                for(methods method : temp.getClassMethods())
                {
                    retValListOne = retValListOne.concat("Method Name: " + method.getName() + "\nReturn Type: " + method.getType() + "\n");
                    hasMethod = true;
                    for(attributes params : method.getParams())
                    {
                        retValListOne = retValListOne.concat("Parameter name: " + params.getName() + "\nParameter type: " + params.getType() + "\n");
                    }
                }

            }
        }
        if(!isIn)
        {
            return ("Class with that name does not exist.");
        }
        if(!isCont)
        {
           retValListOne = retValListOne.concat("Class has no fields.\n");
           return retValListOne;
        }
        if(!hasMethod)
        {
            retValListOne = retValListOne.concat("Class has no methods.\n");
            return retValListOne;
        }
        return retValListOne;
    }
    /**
     * Lists one class and all relationships related to it.
     * 
     * @Variables       className - The name of the class being listed.
     * 
     * @Preconditions   A class of className exists.
     * @Postconditions
     * 
     * @Returns         A string containing the listing.
     */
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
                retStringRel = retStringRel.concat("Source Class: " + rel.getSourceClass() + "\nDestination Class: " + rel.getDestClass() + "\n" + "Relationship Type: " + rel.getType() + "\n");
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
    /**
     * Lists all classes and their fields and methods.
     * 
     * @Variables 
     * 
     * @Preconditions   Atleast one class exists.
     * @Postconditions
     * 
     * @Returns         A string containing the listing.
     */
    public String listAllClasses ()
    {
        //useds to tell if there are any classes
        boolean isInAll = false;
        boolean isContAll = false;
        String retStringAll = "";
        boolean hasMethod = false;

        //loop through classContainer and prints class name and attributes of every class
        for(ClassBase temp : myClassContainer.getContainer()){
            hasMethod = false;
            retStringAll = retStringAll.concat("Class Name: " + temp.getName() + "\n");
            isInAll = true;
            for(attributes att : temp.getClassAttributes()){
                retStringAll = retStringAll.concat("Field Name: " + att.getName() + "\nType: " + att.getType() + "\n");
                isContAll = true;
            }
            for(methods method : temp.getClassMethods())
                {
                    retStringAll = retStringAll.concat("Method Name: " + method.getName() + "\nReturn Type: " + method.getType() + "\n");
                    hasMethod = true;
                    for(attributes params : method.getParams())
                    {
                        retStringAll = retStringAll.concat("Parameter name: " + params.getName() + "\nParameter type: " + params.getType() + "\n");
                    }
                    hasMethod = true;
                }
                if(!hasMethod)
                retStringAll = retStringAll.concat("Class has no methods.\n");
        }
        if (!isInAll){
            return ("There are no classes.");
        }
        if(!isContAll){
            retStringAll = retStringAll.concat("There are no fields in any classes.\n");
        }
        return retStringAll;
    }
    /**
     * Saves the current diagram with filename name.
     * 
     * @Variables       name - The name of the file.
     * 
     * @Preconditions   
     * @Postconditions  A file with name name will be created.
     * 
     * @Returns
     */
    public void save (String name)
    {
        saveUML.save(myClassContainer, myRelationshipContainer, name);
        return;
    }
    /**
     * Saves the current diagram with the default name.
     * 
     * @Variables 
     * 
     * @Preconditions   
     * @Postconditions  A file with the default name will be created.
     * 
     * @Returns
     */
    public void save()
    {
        saveUML.save(myClassContainer, myRelationshipContainer);
        return;
    }
    /**
     * Loads a diagram.
     * 
     * @Variables 
     * 
     * @Preconditions   A diagram file with name name exists.
     * @Postconditions  The file will be loaded.
     * 
     * @Returns
     */
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