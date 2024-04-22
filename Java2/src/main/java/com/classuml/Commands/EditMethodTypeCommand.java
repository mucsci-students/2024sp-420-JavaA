package com.classuml.Commands;

import java.util.SortedSet;

import com.classuml.Model.ClassBase;
import com.classuml.Model.MementoState;
import com.classuml.Model.Method;
import com.classuml.Model.Parameter;

public class EditMethodTypeCommand implements Command {

	// Stores the model that the command will be executed against.
	MementoState model;
	// Stores the desired class name of the class in which the method exists.
	String className;
	// Stores the desired method name of the method to be edited.
	String methodName;
	// Stores the desired method parameters of the method to be edited.
	SortedSet<Parameter> parameters;
	// Stores the new method name.
	String newMethodType;
	// Stores whether or not the state of the model has changed. True if the state
	// has changed false if not.
	boolean stateChange;

	public EditMethodTypeCommand(MementoState model, String className, String methodName,
			SortedSet<Parameter> parameters, String newMethodType) {
		this.model = model;
		this.className = className;
		this.methodName = methodName;
		this.parameters = parameters;
		this.newMethodType = newMethodType;
		stateChange = false;
	}

	/**
	 * Executes the command. This command edits the method type of an existing
	 * method in a class. The class and method must exist.
	 * 
	 * @return A string that represents the outcome of the execution.
	 */
	@Override
	public String execute() {
		String response;
		if (model.getClasses().containsKey(className)) {
			ClassBase classInfo = model.getClasses().get(className);
			if (classInfo.containsMethod(methodName, parameters)) {
				Method methodInfo = classInfo.getMethod(methodName, parameters);
				methodInfo.setType(newMethodType);
				response = "The method type has been changed to " + newMethodType + ".";
				stateChange = true;
			} else {
				response = "The method " + methodName + " does not exist with the class " + className + ".";
			}
		} else {
			response = "The class " + className + " does not exist.";
		}
		return response;
	}

	/**
	 * Gets the stateChange field.
	 * 
	 * @return A boolean that represents whether or not the state has changed.
	 */
	@Override
	public boolean getStateChange() {
		return stateChange;
	}
}
