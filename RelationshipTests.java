public class RelationshipTests {

    private Relationship relationship1;
    private Relationship relationship2;

    public static void main(String[] args) {
        RelationshipTests test = new RelationshipTests();
        //Call all tests
        test.setUp();
        test.testGetName();
        test.testGetFromClass();
        test.testGetToClass();
        test.testSetRelation();
    }

    //Test to set relationship and class names
    private void setUp() {
        relationship1 = new Relationship();
        relationship2 = new Relationship();
        relationship1.setRelationship("Relationship1", "Class1", "Class2");
        relationship2.setRelationship("Relationship2", "Class3", "Class4");
    }

    //Test to see if the recieved name is the same as the inputed name
    private void testGetName() {
        assertEquals("Relationship1", relationship1.getName());
        assertEquals("Relationship2", relationship2.getName());
        System.out.println("TestGetName Passed");
    }

    //Test to see if the first class is equal to the first class in the relationship
    private void testGetFromClass() {
        assertEquals("Class1", relationship1.getFromClass());
        assertEquals("Class3", relationship2.getFromClass());
        System.out.println("TestGetFromClass Passed");
    }

    //Test to see if the second class is equal to the second class in the relationship
    private void testGetToClass() {
        assertEquals("Class2", relationship1.getToClass());
        assertEquals("Class4", relationship2.getToClass());
        System.out.println("TestGetToCLass Passed");
    }

    //Test to set relation names with a new relationship
    private void testSetRelation() {
        relationship1.setRelationship("NewRelationship", "NewClass1", "NewClass2");
        assertEquals("NewRelationship", relationship1.getName());
        assertEquals("NewClass1", relationship1.getFromClass());
        assertEquals("NewClass2", relationship1.getToClass());
        System.out.println("testSetRelation Passed");
    }

    //setup for assertEquals because we are not using junit
    private void assertEquals(Object expected, Object actual) {
        if (!expected.equals(actual)) {
            throw new AssertionError("Expected: " + expected + ", but was: " + actual);
        }
    }
}