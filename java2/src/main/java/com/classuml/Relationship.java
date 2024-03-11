package com.classuml;
public class Relationship {

    private String sourceClass;
    private String destClass;
    enum Type {
        Aggregation,
        Composition,
        Inheritence,
        Realization
    }
    private Type myType;

    public boolean setType(String relType){
        if(relType.toLowerCase().equals("aggregation")){
            this.myType = Type.Aggregation;
            return true;
        }
        else if (relType.toLowerCase().equals("composition")){
            this.myType = Type.Composition;
            return true;
        }
        else if (relType.toLowerCase().equals("inheritence")){
            this.myType = Type.Inheritence;
            return true;
        }
        else if (relType.toLowerCase().equals("realization")){
            this.myType = Type.Realization;
            return true;
        }
        else{
            return false;
        }
    }

    public String getType(){
        return myType.toString();
    }

    /*
     * setRelationship - public setter for naming a relationship
     */
    public void setRelationship(String sourceClass, String destClass, String relType){

        //sets names of variables in class
        this.sourceClass = sourceClass;
        this.destClass = destClass;
        setType(relType);
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