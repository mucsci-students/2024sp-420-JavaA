package com.classuml.Commands;

import com.classuml.Model.ClassBase;
import com.classuml.Model.ClassContainer;

public class AddFieldCommand implements Command {
	// Stores the model that the command will be executed against.
	private ClassContainer model;
	// Stores the desired class name for the field to be created in.
	private String className;
	// Stores the desired field type to be used when the field is created.
	private String fieldType;
	// Stores the desired field name to be used when the field is created.
	private String fieldName;
	// Stores whether or not the state of the model has changed. True if the state
	// has changed false if not.
	private boolean stateChange;

	/**
	 * Constructs an Add Field command with the desired model, class name, field
	 * type, and field name.
	 *
	 * @param newModel     The model.
	 * @param newClassName The class name.
	 * @param newFieldType The field type.
	 * @param fieldNameP   The field name.
	 */
	public AddFieldCommand(ClassContainer newModel, String newClassName, String newFieldType, String fieldNameP) {
		model = newModel;
		className = newClassName;
		fieldType = newFieldType;
		fieldName = fieldNameP;
		stateChange = false;
	}

	/**
	 * Executes the command. This command adds a new field to a class. The class
	 * must exist and the field must be unique.
	 *
	 * @return A string that represents the outcome of the execution.
	 */
	@Override
	public String execute() {
		String response;
		if (model.getClasses().containsKey(className)) {
			ClassBase classInfo = model.getClasses().get(className);
			if (!classInfo.containsField(fieldName)) {
				classInfo.addField(fieldType, fieldName);
				response = "The field " + fieldName + " has been added to the class " + className + ".";
				stateChange = true;
			} else {
				response = "The field " + fieldName + " already exists with the class " + className + ".";
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
