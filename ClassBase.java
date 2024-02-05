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
    public void addAttribute(attributes myAtt){
        classAttributes.add(myAtt);
    }

    //updates either the name or content of an attribute
    public void updateAttribute(String updating, String updateType, String update){
        for(attributes attribute: classAttributes){
            if(attribute.getName().equalsIgnoreCase(updating)){
                if(updateType.equalsIgnoreCase("Name")){
                    attribute.setName(update);
                }
                else{
                    attribute.setContent(update);
                }
            }
        }
    }

    //deletes the attribute from the list
    public void deleteAttribute(String name){
        for(attributes attribute: classAttributes){
            if(attribute.getName().equalsIgnoreCase(name)){
                classAttributes.remove(attribute);
            }
        }
    }
    


}
