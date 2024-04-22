package com.classuml.Commands;

import com.classuml.Model.ClassBase;
import com.classuml.Model.Field;
import com.classuml.Model.MementoState;

public class EditFieldTypeCommand implements Command {

	// Stores the model that the command will be executed against.
	MementoState model;
	// Stores the desired class name of the class in which the field exists.
	String className;
	// Stores the desired field name of the field to edited.
	String fieldName;
	// Stores the new field Type.
	String newFieldType;
	// Stores whether or not the state of the model has changed. True if the state
	// has changed false if not.
	boolean stateChange;

	public EditFieldTypeCommand(MementoState model, String className, String fieldName, String newFieldType) {
		this.model = model;
		this.className = className;
		this.fieldName = fieldName;
		this.newFieldType = newFieldType;
		stateChange = false;
	}

	/**
	 * Executes the command. This command edits the field type of an existing field
	 * in a class. The class and field must exist.
	 * 
	 * @return A string that represents the outcome of the execution.
	 */
	@Override
	public String execute() {
		String response;
		if (model.getClasses().containsKey(className)) {
			ClassBase classInfo = model.getClasses().get(className);
			if (classInfo.containsField(fieldName)) {
				Field feildInfo = classInfo.getField(fieldName);
				feildInfo.setType(newFieldType);
				response = "The field " + fieldName + " has had its type changed to " + newFieldType + ".";
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
