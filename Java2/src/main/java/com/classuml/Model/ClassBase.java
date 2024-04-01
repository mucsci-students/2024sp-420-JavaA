package com.classuml.Model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

public class ClassBase implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private SortedSet<Field> fields = new TreeSet<Field>();
	private SortedSet<Method> methods = new TreeSet<Method>();
	private SortedSet<Relationship> relationships = new TreeSet<Relationship>();
	private int x;
	private int y;

	/**
	 * Constructs an uninitialized instance of the object.
	 * 
	 */
	public ClassBase() {

	}

	/**
	 * Constructs a class with a specified name.
	 * 
	 * @param name The class name.
	 */
	public ClassBase(String name) {
		this.name = name;
	}

	/**
	 * Constructs a class with a specified name, fields, methods and relationships.
	 * 
	 * @param name          The class name.
	 * @param field         The class fields name.
	 * @param method        The class methods name.
	 * @param relationships The class relationships name.
	 * @param x             GUI location data.
	 * @param y             GUI location data.
	 */
	public ClassBase(String name, SortedSet<Field> fields, SortedSet<Method> methods,
			SortedSet<Relationship> relationships, int x, int y) {
		this.name = name;
		this.fields = fields;
		this.methods = methods;
		this.relationships = relationships;
		this.x = x;
		this.y = y;
	}

	/**
	 * Gets the class's name.
	 * 
	 * @return A String representing the class name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the class's name.
	 * 
	 * @param newName A String containing the class name.
	 */
	public void setName(String newName) {
		name = newName;
	}

	/**
	 * Gets the class's fields.
	 * 
	 * @return A SortedSet that stores the class fields.
	 */
	public SortedSet<Field> getFields() {
		return fields;
	}

	/**
	 * Sets the class's fields.
	 * 
	 * @param newFeilds A SortedSet of Fields containing the class fields.
	 */
	public void setFields(SortedSet<Field> newFields) {
		fields = newFields;
	}

	/**
	 * Adds a new field to the class.
	 * 
	 * @param newField A String containing the class field name.
	 * @return A boolean true if it is added and false if it already exists or is
	 *         not added.
	 */
	public boolean addField(String newFieldType, String newFieldName) {
		Field f = new Field(newFieldType, newFieldName);
		return fields.add(f);
	}

	/**
	 * Deletes an existing field from the class.
	 * 
	 * @param name A String containing the class field name.
	 * @return A boolean true if it is deleted and false if it does not exist.
	 */
	public boolean deleteField(String name) {
		Field f = getField(name);
		return fields.remove(f);
	}

	/**
	 * Gets a specified field from the class fields set.
	 * 
	 * @param name A String containing the class field name.
	 * @return A Field from the fields set returns null if it does not exist in the
	 *         set.
	 */
	public Field getField(String name) {
		Iterator<Field> it = fields.iterator();
		Field f = null;
		while (it.hasNext()) {
			f = it.next();
			if (f.getName().equals(name)) {
				break;
			}
			f = null;
		}
		return f;
	}

	/**
	 * Checks if the specified field is in the set.
	 * 
	 * @param name A String containing the class field name.
	 * @return Returns true if the field exists.
	 */
	public boolean containsField(String name) {
		boolean result = false;
		if (getField(name) != null) {
			result = true;
		}
		return result;
	}

	/**
	 * Prints the fields for this class.
	 * 
	 * @return A String containing the class's fields.
	 */
	public String printFields() {
		String result = "";
		Iterator<Field> it = fields.iterator();
		while (it.hasNext()) {
			Field f = it.next();
			result += System.lineSeparator() + f.toString();
		}

		return result;
	}

	/**
	 * Prints the fields for this class.
	 * 
	 * @return A String containing the class's fields.
	 */
	public String printFieldsGUI() {
		Iterator<Field> it = fields.iterator();
		String result = "";
		while (it.hasNext()) {
			Field f = it.next();
			result += "<br/>&nbsp;&nbsp;&nbsp;&nbsp;" + f.toString();
		}
		return result;

	}

	/**
	 * Gets the class's methods.
	 * 
	 * @return A SortedSet that stores the class methods.
	 */
	public SortedSet<Method> getMethods() {
		return methods;
	}

	/**
 	* Replaces the current set of methods with the provided set.
 	* 
 	* @param newMethods the new set of methods to be used.
 	*/
	public void setMethods(SortedSet<Method> newMethods) {
		methods = newMethods;
	}

	/**
 	* Constructor to create a method instance with the specified name.
 	* Throws IllegalArgumentException if the method name is null.
 	* 
 	* @param name the name of the method.
 	* @throws IllegalArgumentException if the method name is null.
 	*/
	public void Method(String name) {
		if (name == null) throw new IllegalArgumentException("Method name cannot be null");
		this.name = name;
	    }
	    
	/**
 	* Adds a method with the given name to the class if it does not already exist.
 	* 
 	* @param newMethod the name of the method to add.
 	* @return true if the method was added, false if it already exists.
 	*/
	public boolean addMethod(String newMethod) {
		Method m = new Method(newMethod);
		return methods.add(m);
	}

	/**
 	* Adds a method with the given return type, name, and parameters to the class if it does not already exist.
 	* 
 	* @param returnType the return type of the method.
 	* @param methodName the name of the method.
 	* @param parameters the parameters of the method.
 	* @return true if the method was added, false if it already exists or parameters are invalid.
 	*/
	public boolean addMethod(String returnType, String methodName, SortedSet<Parameter> parameters) {
		if (methodName == null || returnType == null) {
		    return false; // or throw an exception based on your error handling strategy
		}
		Method m = new Method(returnType, methodName, parameters);
		return methods.add(m);
	    }
	    

	/**
	 * Deletes an existing method from the class.
	 * 
	 * @param name A String containing the class method name.
	 * @return A boolean true if it is deleted and false if it does not exist.
	 */
	public boolean deleteMethod(String name, SortedSet<Parameter> parameters) {
		Method m = getMethod(name, parameters);
		return methods.remove(m);
	}

	/**
	 * Gets a specified method from the class methods set.
	 * 
	 * @param name A String containing the class method name.
	 * @return A Method from the methods set returns null if it does not exist in
	 *         the set.
	 */
	public Method getMethod(String name, SortedSet<Parameter> parameters) {
		Iterator<Method> it = methods.iterator();
		Method m = null;
		while (it.hasNext()) {
			m = it.next();
			if (m.getName().equals(name) && (m.getParameters().equals(parameters))) {
				break;
			}
			m = null;
		}
		return m;
	}

	/**
	 * Checks if the specified method is in the set.
	 * 
	 * @param name A String containing the class method name.
	 * @return Returns true if the method exists.
	 */
	public boolean containsMethod(String name, SortedSet<Parameter> parameters) {
		boolean result = false;
		if (getMethod(name, parameters) != null) {
			result = true;
		}
		return result;
	}

	/**
	 * Prints the methods for this class.
	 * 
	 * @return A String containing the class's methods.
	 */
	public String printMethods() {
		String result = "";
		Iterator<Method> it = methods.iterator();
		while (it.hasNext()) {
			Method m = it.next();
			result += System.lineSeparator() + m.toString();
		}

		return result;
	}

	public String printMethodsGUI() {
		Iterator<Method> it = methods.iterator();
		String result = "";
		while (it.hasNext()) {
			Method m = it.next();
			result += "<br/>&nbsp;&nbsp;&nbsp;&nbsp;" + m.toStringGUI();
		}
		return result;

	}

	/**
	 * Gets the class's relationships.
	 * 
	 * @return A SortedMap that stores the class relationships.
	 */
	public SortedSet<Relationship> getRelationships() {
		return relationships;
	}

	/**
	 * Sets the class's relationships.
	 * 
	 * @param newRelationship A SortedSet of Relationship objects that contains the
	 *                        class's relationships.
	 */
	public void setRelationships(SortedSet<Relationship> newRelationships) {
		relationships = newRelationships;
	}

	/**
	 * Adds a new relationship to the class.
	 * 
	 * @param name A String that represents the name of the relationship.
	 * @param type A String that represents the type of the relationship.
	 * @return A boolean true if it is added and false if it already exists or is
	 *         not added.
	 */
	public boolean addRelationship(String destination, String type) {
		Relationship r = new Relationship(destination, type);
		return relationships.add(r);
	}

	/**
	 * Deletes an existing relationship from the class.
	 * 
	 * @param name A String that represents the name of the relationship.
	 * @param type A String that represents the type of the relationship.
	 * @return A boolean true if it is deleted and false if it does not exist.
	 */
	public boolean deleteRelationship(String destination) {
		Relationship r = new Relationship(destination);
		return relationships.remove(r);
	}

	/**
	 * Gets a specified relationship from the class relationships set.
	 * 
	 * @param destination A String containing the relationship destination name.
	 * @return A Relationship from the relationships set returns null if it does not
	 *         exist in the set.
	 */
	public Relationship getRelationship(String destination) {
		Iterator<Relationship> it = relationships.iterator();
		Relationship r = null;
		while (it.hasNext()) {
			r = it.next();
			if (r.getDestination().equals(destination)) {
				break;
			}
			r = null;
		}
		return r;
	}

	/**
	 * Checks if the specified relationship is in the set.
	 * 
	 * @param name A String containing the class relationship name.
	 * @return Returns true if the relationship exists.
	 */
	public boolean containsRelationship(String destination) {
		boolean result = false;
		if (getRelationship(destination) != null) {
			result = true;
		}
		return result;
	}

	/**
	 * Prints the relationships for this class.
	 * 
	 * @return A String containing the class's relationships.
	 */
	public String printRelationships() {
		String result = "";
		Iterator<Relationship> it = relationships.iterator();
		while (it.hasNext()) {
			Relationship r = it.next();
			result += System.lineSeparator() + r.toString();
		}

		return result;
	}

	/**
	 * Represents this class as a String.
	 * 
	 * @return A String containing this class.
	 */
	public String toString() {
		String result = "Name: " + this.getName() + System.lineSeparator() + "Fields: " + this.printFields()
				+ System.lineSeparator() + "Methods: " + this.printMethods() + System.lineSeparator()
				+ "Relationships: " + this.printRelationships();
		return result;
	}

	/**
	 * Represents this class in a format suitable for the GUI. Swing uses HTML in
	 * its panels.
	 * 
	 * @return A String containing this class.
	 */
	public String toStringGUI() {
		String result = "<html><b>" + this.getName() + "</b><hr/><b>&nbsp;&nbsp; Fields:</b>" + this.printFieldsGUI()
				+ "<hr/>&nbsp;&nbsp; <b>Methods:</b>" + this.printMethodsGUI() + "</html>";
		return result;
	}

	/**
	 * Compares two class objects for equality.
	 * 
	 * @param classObject A Class that will be compared to this class.
	 * @return A boolean if the classes equal each other false if not.
	 */
	public boolean equals(ClassBase classObject) {
		boolean result = false;
		if (classObject.getName().equals(this.getName()) && classObject.getFields().equals(this.getFields())
				&& classObject.getMethods().equals(this.getMethods())
				&& classObject.getRelationships().equals(this.getRelationships())) {
			result = true;
		}
		return result;
	}

	/**
	 * Gets the class's X location.
	 * 
	 * @return An int representation of the location.
	 */
	public int getXLocation() {
		return x;
	}

	/**
	 * Gets the class's Y location.
	 * 
	 * @return An int representation of the location.
	 */
	public int getYLocation() {
		return y;
	}

	/**
	 * Sets the class's X location
	 * 
	 * @param xP An int representing the x location.
	 */
	public void setXLocation(int xP) {
		x = xP;
	}

	/**
	 * Sets the class's Y location
	 * 
	 * @param yP An int representing the Y location.
	 */
	public void setYLocation(int yP) {
		y = yP;
	}
}
