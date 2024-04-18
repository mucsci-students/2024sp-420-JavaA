package com.classuml.Commands;

import com.classuml.Model.ClassBase;
import com.classuml.Model.MementoState;

public class EditClassNameCommand implements Command {

	// Stores the model that the command will be executed against.
	MementoState model;
	// Stores the desired class name of the class to be edited.
	String className;
	// Stores the desired new class name.
	String newClassName;
	// Stores whether or not the state of the model has changed. True if the state
	// has changed false if not.
	boolean stateChange;

	public EditClassNameCommand(MementoState newModel, String newClassName, String className) {
		model = newModel;
		this.newClassName = newClassName;
		this.className = className;
		stateChange = false;
	}

	/**
	 * Executes the command. This command edits the class name of an existing class
	 * in the model. The class must exist and the new class name must be unique.
	 * 
	 * @return A string that represents the outcome of the execution.
	 */
	@Override
	public String execute() {
		String response;
		if (model.getClasses().containsKey(className) && !model.getClasses().containsKey(newClassName)) {
			ClassBase classInfo = model.getClasses().get(className);
			classInfo.setName(newClassName);
			model.getClasses().remove(className, classInfo);
			model.getClasses().put(newClassName, classInfo);
			response = "You have renamed the class " + className + " to " + newClassName;
			stateChange = true;
		} else {
			if (!model.getClasses().containsKey(className)) {
				response = "The class " + className + " does not exist.";
			} else {
				response = "The new class name " + newClassName + " already exists.";
			}
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
