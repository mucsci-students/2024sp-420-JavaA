package com.classuml.Commands;

import com.classuml.Model.ClassBase;
import com.classuml.Model.ClassContainer;

public class AddClassCommand implements Command {
	// Stores the model that the command will be executed against.
	private ClassContainer model;
	// Stores the desired class name to be used when the class is created.
	private String className;
	// Stores whether or not the state of the model has changed. True if the state
	// has changed false if not.
	private boolean stateChange;

	/**
	 * Constructs an Add Class command with the desired model and class name.
	 *
	 * @param newModel      The model.
	 * @param newClassNameP The class name.
	 */
	public AddClassCommand(ClassContainer newModel, String newClassNameNrds) {
		model = newModel;
		className = newClassNameNrds;
		stateChange = false;
	}

	/**
	 * Executes the command. This command adds a new class to the model.
	 * The class must be unique.
	 *
	 * @return A string that represents the outcome of the execution.
	 */
	@Override
	public String execute() {
		String response;
		ClassBase classInfo = new ClassBase(className);
		if (!model.getClasses().containsKey(className)) {
			model.getClasses().put(className, classInfo);
			response = "You have created a new class named: " + className;
			stateChange = true;
		} else {
			response = "The class " + className + " already exists.";
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
