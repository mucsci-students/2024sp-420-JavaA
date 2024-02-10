import java.util.ArrayList;
public class ClassBase
{
    //Name of the class.
    private String className;
    //ArrayList of attributes for this class.
    private ArrayList<attributes> classAttributes;
    
    public ClassBase(String name)
    {
        className = name;
        classAttributes = new ArrayList<attributes>();
    }
    public String getName()
    {
        return className;
    }
    public void setName(String newName)
    {
        className = newName;
    }
    public ClassBase setEquals(ClassBase input)
    {
        input.className = className;
        input.classAttributes = classAttributes;
        return input;
    }

    //adds the attribute that called this method to the list
    public int addAttribute(attributes myAtt){
        for(attributes att: classAttributes){
            if(att.getName().equalsIgnoreCase(myAtt.getName())){
                return 0;
            }
        }
        classAttributes.add(myAtt);
        return 1;
    }

    //updates either the name or content of an attribute
    public void updateAttribute(attributes myAtt, String updateType, String update){
        if(updateType.equalsIgnoreCase("Name")){
            myAtt.setName(update);
        }
        else{
            myAtt.setContent(update);
        }
        
    }

    //deletes the attribute from the list
    public void deleteAttribute(attributes myAtt){
        classAttributes.remove(myAtt);
    }

    //used to find an attribute for deleting or updating
    public attributes getAttribute(String attName){
        for(attributes att: classAttributes){
            if(att.getName().equalsIgnoreCase(attName)){
                return att;
            }
        }
        return null;
    }
    
    //returns the container of attributes to help the saving process
    public ArrayList<attributes> getAttributeContainer(){
        return classAttributes;
    }

}
