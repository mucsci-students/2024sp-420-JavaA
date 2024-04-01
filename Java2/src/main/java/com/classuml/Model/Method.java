package com.classuml.Model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

public class Method implements Serializable, Comparable<Method> {
	private static final long serialVersionUID = 1L;

	private String returnType;
	private String name;
	private SortedSet<Parameter> parameters = new TreeSet<>();

	/**
	 * Constructs an uninitialized instance of the object.
	 * 
	 */
	public Method() {

	}

	/**
	 * Constructs a method with a specified name.
	 * 
	 * @param name A String that represents the method name.
	 */
	public Method(String name) {
		this.name = name;
	}

	/**
	 * Constructs a method with a specified name and parameters.
	 * 
	 * @param name A String that represents the method name.
	 */
	public Method(String returnType, String name, SortedSet<Parameter> parameters) {
		this.name = name;
		this.returnType = returnType;
		this.parameters = parameters;
	}

	/**
	 * Gets the method's name.
	 * 
	 * @return A String representing the method name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the method's name.
	 * 
	 * @param newName A String containing the method's name.
	 */
	public void setName(String newName) {
		name = newName;
	}

	/**
	 * Gets the method's type.
	 * 
	 * @return A String representing the method name.
	 */
	public String getType() {
		return returnType;
	}

	/**
	 * Sets the method's name.
	 * 
	 * @param newName A String containing the method's name.
	 */
	public void setType(String newType) {
		returnType = newType;
	}

	/**
	 * Gets the method's parameters.
	 * 
	 * @return A List representing the method name.
	 */
	public SortedSet<Parameter> getParameters() {
		return parameters;
	}

	/**
	 * Sets the method's name.
	 * 
	 * @param newParameters A String containing the method's name.
	 */
	public void setParameters(SortedSet<Parameter> newParameters) {
		parameters = newParameters;
	}

	/**
	 * Add a Parameter
	 * 
	 * @param newParameters A String containing the method's name.
	 */
	public void addParameter(String type, String name) {
		Parameter p = new Parameter(type, name);
		parameters.add(p);
	}

	/**
	 * Delete a Parameter
	 * 
	 * @param newParameters A String containing the method's name.
	 */
	public void deleteParameter(String name) {
		Parameter p = getParameter(name);
		parameters.remove(p);
	}

	/**
	 * Gets a specified parameter from the method parameters set.
	 * 
	 * @param name A String containing the parameter name.
	 * @return A Parameter from the parameters set returns null if it does not exist
	 *         in the set.
	 */
	public Parameter getParameter(String name) {
		Iterator<Parameter> it = parameters.iterator();
		Parameter p = null;
		while (it.hasNext()) {
			p = it.next();
			if (p.getName().equals(name)) {
				break;
			}
			p = null;
		}
		return p;
	}

	/**
	 * Checks if the specified parameter is in the set.
	 * 
	 * @param name A String containing the class parameter name.
	 * @return Returns true if the method exists.
	 */
	public boolean containsParameter(String name) {
		boolean result = false;
		if (getParameter(name) != null) {
			result = true;
		}
		return result;
	}

	/**
	 * Represents this class as a String.
	 * 
	 * @return A String containing this class.
	 */
	public String toString() {

		Iterator<Parameter> it = parameters.iterator();
		String result = returnType + " " + name;
		while (it.hasNext()) {
			Parameter p = it.next();
			result += " " + p.toString();
		}

		return result.trim();
	}

	/**
	 * Represents this class as a String.
	 * 
	 * @return A String containing this class.
	 */
	public String toStringGUI() {
		Iterator<Parameter> it = parameters.iterator();
		String result = returnType + " " + name + "(";
		while (it.hasNext()) {
			Parameter p = it.next();
			if (it.hasNext()) {
				result += p.toString() + ", ";
			} else {
				result += "" + p.toString();
			}
		}
		result += ")";

		return result.trim();
	}

	/**
	 * Represents this class as a String.
	 * 
	 * @return A String containing this class.
	 */
	public String toStringTabCompleter() {

		Iterator<Parameter> it = parameters.iterator();
		String result = name;
		while (it.hasNext()) {
			Parameter p = it.next();
			result += " " + p.toString();
		}

		return result.trim();
	}

	/**
	 * Compares two class objects for equality.
	 * 
	 * @param classObject A Class that will be compared to this class.
	 * @return A boolean if the classes equal each other false if not.
	 */
	public boolean equals(Method classObject) {
		boolean result = false;
		if (classObject.getName().equals(this.getName()) && classObject.getParameters().equals(this.getParameters())) {
			result = true;
		}
		return result;
	}

	/**
 	* Compares this method with another for order based on names and return types.
 	* Names are compared first; if equal, return types are compared. Null values
 	* are considered less than non-null. If both names or both return types are null,
 	* they are considered equal.
 	* 
 	* @param other the method to compare to.
 	* @return a negative integer, zero, or a positive integer as this method is less
 	*         than, equal to, or greater than the specified method.
 	*/
	@Override
	public int compareTo(Method other) {
	    if (this.name == null && other.name == null) {
		return 0;
	    }
	    if (this.name == null) {
		return -1;
	    }
	    if (other.name == null) {
		return 1;
	    }
	    int nameComparison = this.name.compareTo(other.name);
	    if (nameComparison != 0) {
		return nameComparison;
	    }
	
	    // Assuming returnType could also be null
	    if (this.returnType == null && other.returnType == null) {
		return 0;
	    }
	    if (this.returnType == null) {
		return -1;
	    }
	    if (other.returnType == null) {
		return 1;
	    }
	    return this.returnType.compareTo(other.returnType);
	}
	

}
