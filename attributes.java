public class attributes 
{

    //Name for the attribute
    private String name;

    //content of the attribute
    private String content;

    /**
     * Used by other classes / users to change the name of this attribute
     * @param newName How to name our attribute.
     */ 
    public void setName(String newName)
    {
        this.name = newName;
    }

    /**
     * @return The name of this attribute
     */ 
    public String getName()
    {
        return this.name;
    }

    /** 
     * Change the content of the attribute
     * @param newContent What to set the attribute's content.
     */
    public void setContent(String newContent)
    {
        this.content = newContent;
    }

    /**
     * @return The content of the attribute
     */ 
    public String getContent()
    {
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