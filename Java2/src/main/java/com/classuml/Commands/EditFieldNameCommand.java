package com.classuml.Commands;

import com.classuml.Model.ClassBase;
import com.classuml.Model.ClassContainer;
import com.classuml.Model.Field;

public class EditFieldNameCommand implements Command {

	// Stores the model that the command will be executed against.
	ClassContainer model;
	// Stores the desired class name of the class in which the field exists.
	String className;
	// Stores the desired field name of the field to be edited.
	String fieldName;
	// Stores the new field name.
	String newFieldName;
	// Stores whether or not the state of the model has changed. True if the state
	// has changed false if not.
	boolean stateChange;

	/**
	 * Constructs an Edit Field Name command with the desired model, class name,
	 * field name, and new field name.
	 * 
	 * @param model        The model.
	 * @param className    The class name.
	 * @param fieldName    The field name.
	 * @param newFieldName The new field name.
	 */
	public EditFieldNameCommand(ClassContainer model, String className, String fieldName, String newFieldName) {
		this.model = model;
		this.className = className;
		this.fieldName = fieldName;
		this.newFieldName = newFieldName;
		stateChange = false;
	}

	/**
	 * Executes the command. This command edits the field name of an existing field
	 * in a class. The class and field must exist and the new field name must be
	 * unique.
	 * 
	 * @return A string that represents the outcome of the execution.
	 */
	@Override
	public String execute() {
		String response;
		if (model.getClasses().containsKey(className)) {
			ClassBase classInfo = model.getClasses().get(className);
			if (classInfo.containsField(fieldName) && !classInfo.containsField(newFieldName)) {
				Field fieldInfo = classInfo.getField(fieldName);
				fieldInfo.setName(newFieldName);
				response = "The field " + fieldName + " has been renamed to " + newFieldName + ".";
				stateChange = true;
			} else {
				if (classInfo.containsField(newFieldName)) {
					response = "The field " + newFieldName + " already exists with the class " + className + ".";
				} else {
					response = "The field " + fieldName + " does not exist with the class " + className + ".";
				}
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
