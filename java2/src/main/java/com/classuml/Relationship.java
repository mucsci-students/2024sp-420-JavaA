package com.classuml;
public class Relationship {

    private String sourceClass;
    private String destClass;

    /*
     * setRelationship - public setter for naming a relationship
     */
    public void setRelationship(String sourceClass, String destClass){

        //sets names of variables in class
        this.sourceClass = sourceClass;
        this.destClass = destClass;

    }

    /*
     * getSourceClass - public getter for sourceClass
     */
    public String getSourceClass(){

        //returns the first class in the relationship
        return sourceClass;
    }

    /*
     * getDestClass - public getter for destClass
     */
    public String getDestClass(){

        //returns the second class in the relationship
        return destClass;
    }

    public void setSourceClass(String newSourceClass){
        this.sourceClass = newSourceClass;
    }

    public void setDestClass(String newDestClass){
        this.destClass = newDestClass;
    }
}