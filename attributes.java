public class attributes <E> {

    //Name for the attribute
    private String name;

    //Name for the class tied to this attribute
    private String className;

    //content of the attribute
    private E content;

    public attributes(newName, newClassName, newContent){
        this.name = newName;
        this.className = newClassName;
        this.content = newContent;
    }

    //Used by other classes / users to change the name of this attribute
    public String setName(String newName){
        this.name = newName;
        return "name for the attribute was updated";
    }

    //will return the name of this attribute
    public String getName(){
        return this.name;
    }

    //Change the content of the attribute
    public String setContent(E newContent){
        this.content = newContent;
        return "content for " + this.getName() + " has been updated";
    }

    //returns the content of the attribute
    public E getContent(){
        return this.content;
    }
    
}