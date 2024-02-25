package com.classuml;

import java.util.ArrayList;

public class methods {
    
    //Name for the method
    private String name;
    //return type of the method
    private String retType;
    //list of parameters
    private ArrayList<attributes> params;


    public ArrayList<attributes> getParams(){
        return params;
    }

    public void addParam(String name, String type){
        attributes newParam = new attributes();
        newParam.setName(name);
        newParam.setType(type);
        params.add(newParam);
    }
    
    /*
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

    /*
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

    /*
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

    /*
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
