package com.classuml;

import java.util.ArrayList;

public class methods {
    
    //Name for the method
    private String name;
    //return type of the method
    private String retType;
    //list of parameters
    private ArrayList<attributes> params;

    /**
     * Constructor for methods
     * 
     * @Variables       methodName - A string to set method name.
     *                  methodRetType - A string to set method return type.
     *                  paramList - A list of params.
     * 
     * @Preconditions   
     * @Postconditions  Method will be created.
     * 
     * @Returns         The new methods object.
     */
    public methods (String methodName, String methodRetType, ArrayList<attributes> paramList)
    {
        name = methodName;
        retType = methodRetType;
        params = paramList;
    }

    /**
     * Returns the params.
     * 
     * @Variables
     * 
     * @Preconditions   The param list exists.
     * @Postconditions  
     * 
     * @Returns         The arraylist of parameters.
     */
    public ArrayList<attributes> getParams(){
        return params;
    }
    
    /**
     * Adds a new parameter to the param arraylist.
     * 
     * @Variables       name - The name of the new parameter.
     *                  type - The type of the new parameter.
     * 
     * @Preconditions   
     * @Postconditions  A new parameter will be added to params.
     * 
     * @Returns
     */
    public void addParam(String name, String type){
        attributes newParam = new attributes();
        newParam.setName(name);
        newParam.setType(type);
        params.add(newParam);
    }
    
    /**
     * Removes a parameter from the params arraylist.
     * 
     * @Variables       remParam - The name of the parameter to be removed.
     * 
     * @Preconditions   The param exists in params.
     * @Postconditions  The param will be removed from params.
     * 
     * @Returns         True if the param was removed, false if it didn't exist.
     */
    public boolean removeParam(String remParam)
    {
        for(attributes param : params)
        {

            if(param.getName().equals(remParam))
            {
                params.remove(param);
                //Param was successfully removed.
                return true;
            }
        }
        //No param matching that name was found!
        return false;

    }

    /**
     * Completely clears the param arraylist.
     * 
     * @Variables       
     * 
     * @Preconditions   
     * @Postconditions  The params arraylist will be cleared.
     * 
     * @Returns
     */
    public void removeAllParam()
    {
        params.clear();
    }

    /**
     * Changes a param, replacing it with a new one.
     * 
     * @Variables       newParam - The new param to replace the old one,
     *                  oldParam - The name of the old param.
     * 
     * @Preconditions   The old parameter exists.
     * @Postconditions  The old parameter is replaced by the new one.
     * 
     * @Returns True if the param existed and was replaced, false if it didn't exist.
     */
    public boolean changeParam(attributes newParam, String oldParam)
    {
        for(attributes param : params)
        {

            if(param.getName().equals(oldParam))
            {
                int index = params.indexOf(param);
                params.set(index, newParam);
                //Param was successfully replaced.
                return true;
            }
        }
        //No param matching the old parameter name was found!
        return false;
    }

    /**
     * Removes the old params arraylist and replaces it with a new one.
     *
     * 
     * @Variables       newParams - An arraylist of params.
     * 
     * @Preconditions   
     * @Postconditions  The old parameter list is removed and replaced by the new one.
     * 
     * @Returns
     */
    public void changeAllParam(ArrayList<attributes> newParams)
    {
        removeAllParam();
        params = (ArrayList<attributes>)newParams.clone();

    }

    /**
     * sets a new name for this method
     * 
     * @Variables       newName - A string var to hold a new name
     * 
     * @Preconditions   
     * @Postconditions  The method name will be changed
     * 
     * @Returns
     */
    public void setName(String newName){
        this.name = newName;
    }

    /**
     * Gets the name for this method
     * 
     * @Variables       
     * 
     * @Preconditions   
     * @Postconditions  
     * 
     * @Returns         The name of this method
     */
    public String getName(){
        return this.name;
    }

    /**
     * Sets the return Type of the method
     * 
     * @Variables       newType - Var that will hold the new return type
     * 
     * @Preconditions   
     * @Postconditions  The method return type will change
     * 
     * @Returns
     */
    public void setType(String newType){
        this.retType = newType;
    }

    /**
     * Gets the type of this method
     * 
     * @Variables       
     * 
     * @Preconditions   
     * @Postconditions  
     * 
     * @Returns         The return type of this method
     */
    public String getType(){
        return this.retType;
    }
}
