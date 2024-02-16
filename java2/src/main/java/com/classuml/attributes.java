package java2.src.main.java.com.classuml;
public class attributes 
{

    //Name for the attribute
    private String name;

    //content of the attribute
    private String content;

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
     * Sets the content of the attribute
     * 
     * @Variables       newContent - Var that will hold the new content
     * 
     * @Preconditions   
     * @Postconditions  The attribute Content will change
     * 
     * @Returns
     */
    public void setContent(String newContent){
        this.content = newContent;
    }

    /*
     * Gets the content of this attribute
     * 
     * @Variables       
     * 
     * @Preconditions   
     * @Postconditions  
     * 
     * @Returns         The content of this attribute
     */
    public String getContent(){
        return this.content;
    }
    
    /**
     * @return The attributes and its contents in readable form.
     */
    @Override
    public String toString() 
    {
        return "Attribute{" +
            "name='" + name + '\'' +
            ", content='" + content + '\'' +
            '}';
    }
}