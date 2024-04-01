package com.classuml.Commands;

import java.util.SortedSet;

import com.classuml.Model.ClassBase;
import com.classuml.Model.ClassContainer;
import com.classuml.Model.Parameter;

public class DeleteMethodCommand implements Command {

	// Stores the model that the command will be executed against.
	ClassContainer model;
	// Stores the desired class name of the class to be deleted from the model.
	String className;
	// Stores the desired method name of the method to be deleted from the class.
	String methodName;
	// Stores the desired method parameters of the method to be deleted from the
	// class.
	SortedSet<Parameter> parameters;
	// Stores whether or not the state of the model has changed. True if the state
	// has changed false if not.
	boolean stateChange;

	/**
	 * Constructs a Delete Method command with the desired model, class name, method
	 * name, and parameters.
	 * 
	 * @param newModel      The model.
	 * @param newClassName  The class name.
	 * @param newMethodName The method name.
	 * @param newParameters The parameters.
	 */
	public DeleteMethodCommand(ClassContainer newModel, String newClassName, String newMethodName,
			SortedSet<Parameter> newParameters) {

		// Stores the model that the command will be executed against.
		model = newModel;
		// Stores the desired class name of the class to be deleted from the model.
		className = newClassName;
		// Stores the desired method name of the method to be deleted from the class.
		methodName = newMethodName;
		// Stores the desired method parameters of the method to be deleted from the
		// class.
		parameters = newParameters;
		// Stores whether or not the state of the model has changed. True if the state
		// has changed false if not.
		stateChange = false;
	}

	/**
	 * Executes the command. This command deletes a method from the model. The class
	 * and method with the specified parameters must exist.
	 * 
	 * @return A string that represents the outcome of the execution.
	 */
	@Override
	public String execute() {
		String response;
		if (model.getClasses().containsKey(className)) {
			ClassBase classInfo = model.getClasses().get(className);
			if (classInfo.containsMethod(methodName, parameters)) {
				classInfo.deleteMethod(methodName, parameters);
				response = "The method " + methodName + " has been deleted from class " + className + ".";
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
