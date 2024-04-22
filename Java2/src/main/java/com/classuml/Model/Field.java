package com.classuml.Model;

import java.io.Serializable;

public class Field implements Comparable<Field>, Serializable {

	private static final long serialVersionUID = 1L;
	// Stores the field type.
	private String type;
	// Stores the field name.
	private String name;

	/**
	 * Constructs an uninitialized instance of the object.
	 * 
	 */
	public Field() {

	}

	/**
	 * Constructs a field with a specified name.
	 * 
	 * @param nameP A String that represents the field name.
	 */
	public Field(String type, String name) {
		this.name = name;
		this.type = type;
	}

	/**
	 * Gets the field's name.
	 * 
	 * @return A String representing the field name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the field's name.
	 * 
	 * @param newDestination A String containing the field's name.
	 */
	public void setName(String newName) {
		name = newName;
	}

	/**
	 * Gets the field's type.
	 * 
	 * @return A String representing the field type.
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the field's type.
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
	public boolean equals(Field classObject) {
		boolean result = false;
		if (classObject.getName().equals(this.getName())) {
			result = true;
		}
		return result;
	}

	/**
	 * Compares two class objects for sorting purposes.
	 * 
	 * @param o A Field that will be compared to this class.
	 * @return An int that represents the sort order.
	 */
	@Override
	public int compareTo(Field o) {
		int name = this.name.compareTo(o.getName());
		return name;
	}
}
