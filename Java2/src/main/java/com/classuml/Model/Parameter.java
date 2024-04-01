package com.classuml.Model;

import java.io.Serializable;

/**
 * Represents a Parameter in the UML model.
 * 
 * @author Connor Nissley, Ian Reger, Alex Stone, Araselli Morales, Rohama
 *         Getachew
 * @version 1.0
 * @since 1.0
 */
public class Parameter implements Comparable<Parameter>, Serializable {

	private static final long serialVersionUID = 1L;
	// Stores the field type.
	private String type;
	// Stores the field name.
	private String name;

	/**
	 * Constructs an uninitialized instance of the object.
	 * 
	 */
	public Parameter() {

	}

	/**
	 * Constructs a Parameter with a specified name.
	 * 
	 * @param nameP A String that represents the Parameter name.
	 */
	public Parameter(String typeP, String nameP) {
		name = nameP;
		type = typeP;
	}

	/**
	 * Gets the Parameter name.
	 * 
	 * @return A String representing the field name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Parameter name.
	 * 
	 * @param newDestination A String containing the field's name.
	 */
	public void setName(String newName) {
		name = newName;
	}

	/**
	 * Gets the Parameter type.
	 * 
	 * @return A String representing the field type.
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the Parameter type.
	 * 
	 * @param newDestination A String containing the field's type.
	 */
	public void setType(String newType) {
		type = newType;
	}

	/**
	 * Represents this class as a String.
	 * 
	 * @return A String containing this class.
	 */
	public String toString() {
		return type + " " + name;
	}

	/**
	 * Compares two class objects for equality.
	 * 
	 * @param classObject A Class that will be compared to this class.
	 * @return A boolean if the classes equal each other false if not.
	 */
	public boolean equals(Parameter classObject) {
		boolean result = false;
		if (classObject.getName().equals(this.getName())) {
			result = true;
		}
		return result;
	}

	/**
	 * Compares two class objects for sorting purposes.
	 * 
	 * @param o A Parameter that will be compared to this class.
	 * @return An int that represents the sort order.
	 */
	@Override
	public int compareTo(Parameter o) {
		int name = this.name.compareTo(o.getName());
		return name;
	}
}
