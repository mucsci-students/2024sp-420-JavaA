import java.util.ArrayList;
public class ClassBase
{
    //Name of the class.
    private String className;
    //ArrayList of attributes for this class.
    private ArrayList<attributes> classAttributes;
    //Ingoing relationship.
    private ArrayList<Relationship> ingoingRelationships;
    //ArrayList of outgoing relationships for the class.
    private ArrayList<Relationship> outgoingRelationships;
    
    public ClassBase(String name)
    {
        className = name;
        classAttributes = new ArrayList<attributes>();
        ingoingRelationships = new ArrayList<Relationship>();
        outgoingRelationships = new ArrayList<Relationship>();

    }
    public String getName()
    {
        return className;
    }

    public void addIngoingRelationship()
    {

    }
    public void addOutgoingRelationship()
    {

    }

}
