package com.classuml.Commands;

import com.classuml.Model.ClassBase;
import com.classuml.Model.ClassContainer;

public class ListClassCommand implements Command {

	// Stores the model that the command will be executed against
	private final ClassContainer model;
	// Stores the desired class name of the class to be listed
	private final String className;
	// Stores whether or not the state of the model has changed. True if the state
	// has changed, false if not
	private boolean stateChange;

	public ListClassCommand(ClassContainer model, String className) {
		this.model = model;
		this.className = className;
		this.stateChange = false;
	}

	/**
	 * Executes the command. This command lists the information about the desired
	 * class.
	 * 
	 * @return A string that represents the outcome of the execution.
	 */
	@Override
	public String execute() {
		String response;
		if (model.getClasses().containsKey(className)) {
			ClassBase classInfo = model.getClasses().get(className);
			response = classInfo.toString();
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
