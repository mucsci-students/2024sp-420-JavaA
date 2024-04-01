package com.classuml.Commands;

import com.classuml.Model.ClassBase;
import com.classuml.Model.ClassContainer;


public class DeleteFieldCommand implements Command {

	// Stores the model that the command will be executed against.
	ClassContainer model;
	// Stores the desired class name of the class to be deleted from the model.
	String className;
	// Stores the desired field name of the field to be deleted from the class.
	String fieldName;
	// Stores whether or not the state of the model has changed. True if the state
	// has changed false if not.
	boolean stateChange;

	/**
	 * Constructs a Delete Field command with the desired model, class name, and
	 * field name.
	 * 
	 * @param newModel     The model.
	 * @param newClassName The class name.
	 * @param newFieldName The field name.
	 */
	public DeleteFieldCommand(ClassContainer newModel, String newClassName, String newFieldName) {
		model = newModel;
		className = newClassName;
		fieldName = newFieldName;
		stateChange = false;
	}

	/**
	 * Executes the command. This command deletes a field from the model. The class
	 * and field must exist.
	 * 
	 * @return A string that represents the outcome of the execution.
	 */
	@Override
	public String execute() {
		String response;
		if (model.getClasses().containsKey(className)) {
			ClassBase classInfo = model.getClasses().get(className);
			if (classInfo.containsField(fieldName)) {
				classInfo.deleteField(fieldName);
				response = "The field " + fieldName + " has been deleted from class " + className + ".";
				stateChange = true;
			} else {
				response = "The field " + fieldName + " does not exist with the class " + className + ".";
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
