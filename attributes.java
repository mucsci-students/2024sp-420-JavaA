public class attributes {

    //Name for the attribute
    private String name;

    //content of the attribute
    private String content;

    public attributes(String name, String content)
    {
        this.name = name;
        this.content = content;
    }

    //Used by other classes / users to change the name of this attribute
    public void setName(String newName){
        this.name = newName;
    }

    //will return the name of this attribute
    public String getName(){
        return this.name;
    }

    //Change the content of the attribute
    public void setContent(String newContent){
        this.content = newContent;
    }

    //returns the content of the attribute
    public String getContent(){
        return this.content;
    }
    
    
    @Override
    public String toString() 
    {
        return "Attribute{" +
            "name='" + name + '\'' +
            ", content='" + content + '\'' +
            '}';
    }
}