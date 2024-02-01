public class ClassBase
{
    //Name of the class.
    private String className;
    //Array of attributes for this class.
    //private attributes[] classAttributes;
    //Ingoing relationship.
    private Relationship ingoingRelationship;
    //Array of outgoing relationships for the class.
    private Relationship[] outgoingRelationships;
    
    public ClassBase(String name)
    {
        className = name;
        ingoingRelationship = null;
        outgoingRelationships = null;
    }

    public void setName(String name)
    {
        className = name;
    }

    public String getName(String name)
    {
        return className;
    }
}
