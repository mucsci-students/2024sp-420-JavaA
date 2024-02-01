public class Relationship {
    // Not sure if I need to store each class in here, or just the type,
    // and within the classbase, specify what class is linked with another. 
    //private ClassBase leftClass;
    //private ClassBase rightClass;
    enum relType{ASSOCIATION, INHERITANCE,
        REALIZATION, DEPENDENCY, AGGREGATION, COMPOSITION;}
    relType currentRelType;

    public Relationship()
    {
        //leftClass = null;
        //rightClass = null;
        currentRelType = null;
    }

    public Relationship(Relationship rel)
    {
        this.currentRelType = rel.currentRelType;
    }

    public relType getTypeBetweenTwoClasses(ClassBase c1, ClassBase c2)
    {
        return null;
    }

    public void setType(String s)
    {
        if (s.equalsIgnoreCase("ASSOCIATION"))
        {
            currentRelType = relType.ASSOCIATION;
        }
        else if (s.equalsIgnoreCase("INHERITANCE"))
        {
            currentRelType = relType.INHERITANCE;
        }
        else if (s.equalsIgnoreCase("REALIZATION"))
        {
            currentRelType = relType.REALIZATION;
        }
        else if (s.equalsIgnoreCase("DEPENDENCY"))
        {
            currentRelType = relType.DEPENDENCY;
        }
        else if (s.equalsIgnoreCase("AGGREGATION"))
        {
            currentRelType = relType.AGGREGATION;
        }
        else if (s.equalsIgnoreCase("COMPOSITION"))
        {
            currentRelType = relType.COMPOSITION;
        }
        else 
        {
            // Do nothing. May be a spelling mistake.
        }
    }

    public void setType(relType r)
    {
        currentRelType = r;
    }

    /**
     * Deletes relationship related to a class.
     */
    public void delRel() 
    {
        currentRelType = null;
    }

    public static void showAllRels()
    {
        /* Finish this somehow. I guess I have to read
           the classes that are within the JSON file and
           display all of them.*/
    }
}
