import java.util.ArrayList;
public class ClassBase
{
    //Name of the class.
    private String className;
    //ArrayList of attributes for this class.
    private ArrayList<attributes> ClassAttributes;
    //Ingoing relationship.
    private ArrayList<Relationships> ingoingRelationships;
    //ArrayList of outgoing relationships for the class.
    private ArrayList<Relationship> OutgoingRelationships;
    
    public ClassBase(String name)
    {
        className = name;
    }
    public String getName()
    {
        return className;
    }

}
