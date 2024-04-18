package com.classuml.Commands;

import java.util.SortedSet;

import com.classuml.Model.ClassBase;
import com.classuml.Model.MementoState;
import com.classuml.Model.Parameter;

public class AddMethodCommand implements Command {
	// Stores the model that the command will be executed against.
	private MementoState model;
	// Stores the desired return type for the method.
	private String returnType;
	// Stores the desired class name for the method.
	private String className;
	// Stores the desired method name to be used when the method is created.
	private String methodName;
	// Stores the desired method parameters to be used when the method is created.
	private SortedSet<Parameter> parameters;
	// Stores whether or not the state of the model has changed. True if the state
	// has changed false if not.
	private boolean stateChange;

	/**
	 * Constructs an Add Method command with the desired model, class name, method
	 * name, return type, and method parameters.
	 *
	 * @param newModel      The model.
	 * @param newClassName  The class name.
	 * @param newReturnType The return type.
	 * @param newMethodName The method name.
	 * @param newParameters The method parameters.
	 */
	public AddMethodCommand(MementoState newModel, String newClassName, String newReturnType, String newMethodName,
			SortedSet<Parameter> newParameters) {
		model = newModel;
		className = newClassName;
		methodName = newMethodName;
		parameters = newParameters;
		returnType = newReturnType;
		stateChange = false;
	}

	/**
	 * Executes the command. This command adds a new method to a class. The class
	 * must exist and the method parameters must be unique for the method name.
	 *
	 * @return A string that represents the outcome of the execution.
	 */
	@Override
	public String execute() {
		String response;
		if (model.getClasses().containsKey(className)) {
			ClassBase classInfo = model.getClasses().get(className);
			if (!classInfo.containsMethod(methodName, parameters)) {
				classInfo.addMethod(returnType, methodName, parameters);
				response = "The method " + methodName + " has been added to the class " + className + ".";
				stateChange = true;
			} else {
				response = "The method " + methodName + " already exists with the class " + className
						+ " with those parameters.";
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
