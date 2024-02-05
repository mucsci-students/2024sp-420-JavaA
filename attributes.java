public class attributes <E> {

    //Name for the attribute
    private String name;

    //content of the attribute
    private E content;

    public attributes(newName, newContent){
        this.name = newName;

        this.content = newContent;
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
    public void setContent(E newContent){
        this.content = newContent;
    }

    //returns the content of the attribute
    public E getContent(){
        return this.content;
    }
    
}