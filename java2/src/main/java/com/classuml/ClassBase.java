package com.classuml;
import java.util.ArrayList;
import java.util.List;

public class ClassBase
{
    //Name of the class.
    private String className;
    //ArrayList of attributes for this class.
    private ArrayList<attributes> classAttributes;
    private ArrayList<methods> classMethods;
    
    /**
     * Class constructor.
     * @param name Name to give to the class.
     */
    public ClassBase(String name)
    {
        className = name;
        classAttributes = new ArrayList<attributes>();
        classMethods = new ArrayList<methods>();
    }

    /**
     * @return The name of the class.
     */
    public String getName()
    {
        return className;
    }
    
    /**
     * Renames the class.
     * @param newName The new name for the class.
     */
    public void setName(String newName)
    {
        className = newName;
    }

    /**
     * Converts output class to that of the input parameter class.
     * @param input The input class
     * @return The output class, with the name and attributes of the input class.
     */
    public ClassBase setEquals(ClassBase input)
    {
        input.className = className;
        input.classAttributes = classAttributes;
        return input;
    }

    /** 
     * Adds the attribute that called this method to the list.
     * @param myAtt Our input attribute.
     * @return 0 if already added or 1 if just added now.
     */ 
    public int addAttribute(attributes myAtt)
    {
        for(attributes att: classAttributes)
        {
            if(att.getName().equalsIgnoreCase(myAtt.getName()))
            {
                return 0;
            }
        }
        classAttributes.add(myAtt);
        return 1;
    }

    public int addMethod(methods myMethod){
        for(methods meth : classMethods){
            if(meth.getName().equalsIgnoreCase(myMethod.getName())){
                return 0;
            }
        }
        classMethods.add(myMethod);
        return 1;
    }

    /** 
     * Updates either the name or type of an attribute
     * @param myAtt The attribute to modify
     * @param updateType Are we modifying the name or type of the attribute
     *      (This probably should instead be two separate methods called
     *       updateAttributeName and updateAttributeType)
     * @param update The new string representing the new name 
     *      or type for the attribute.
     */ 
    public void updateAttribute(attributes myAtt, String updateType, String update)
    {
        if(updateType.equalsIgnoreCase("Name"))
        {
            myAtt.setName(update);
        }
        else
        {
            myAtt.setType(update);
        }
        
    }

    public void updateMethod(methods myMethod, String update, String updateType){
        if(updateType.equalsIgnoreCase("Name")){
            myMethod.setName(update);
        }
        else{
            myMethod.setType(update);
        }
    }

    /** 
     * Deletes the attribute from the list
     * @param myAtt Which attribute object from classAttribute to purge.
     */
    public void deleteAttribute(attributes myAtt)
    {
        classAttributes.remove(myAtt);
    }

    public void deleteMethod(methods myMethod){
        classMethods.remove(myMethod);
    }

    /** 
     * Used to find an attribute for deleting or updating
     * @attName Name of sought-after attribute object.
     * @return The desired attribute.
     */
    public attributes getAttribute(String attName)
    {
        for(attributes att: classAttributes)
        {
            if(att.getName().equalsIgnoreCase(attName))
            {
                return att;
            }
        }
        return null;
    }
    
    /** 
     * @return An arrayList of attribute objects
     */
    public List<attributes> getClassAttributes()
    {
        return classAttributes;
    }

    /** 
     * @return An arrayList of method objects
     */
    public ArrayList<methods> getClassMethods()
    {
        return classMethods;
    }

}
