package com.classuml;
public class attributes 
{

    //Name for the attribute
    private String name;
    //type of the attribute
    private String type;

    /*
     * sets a new name for this attribute
     * 
     * @Variables       newName - A string var to hold a new name
     * 
     * @Preconditions   
     * @Postconditions  The attribute name will be changed
     * 
     * @Returns
     */
    public void setName(String newName){
        this.name = newName;
    }

    /*
     * Gets the name for this attribute
     * 
     * @Variables       
     * 
     * @Preconditions   
     * @Postconditions  
     * 
     * @Returns         The name of this attribute
     */
    public String getName(){
        return this.name;
    }

    /*
     * Sets the Type of the attribute
     * 
     * @Variables       newType - Var that will hold the new type
     * 
     * @Preconditions   
     * @Postconditions  The attribute type will change
     * 
     * @Returns
     */
    public void setType(String newType){
        this.type = newType;
    }

    /*
     * Gets the type of this attribute
     * 
     * @Variables       
     * 
     * @Preconditions   
     * @Postconditions  
     * 
     * @Returns         The type of this attribute
     */
    public String getType(){
        return this.type;
    }
}