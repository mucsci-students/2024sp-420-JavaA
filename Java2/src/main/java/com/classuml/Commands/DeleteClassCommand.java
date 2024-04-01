package com.classuml.Commands;

import java.util.SortedMap;

import com.classuml.Model.ClassBase;
import com.classuml.Model.ClassContainer;

public class DeleteClassCommand implements Command {

	// Stores the model that the command will be executed against.
	ClassContainer model;
	// Stores the desired class name of the class to be deleted from the model.
	String className;
	// Stores whether or not the state of the model has changed. True if the state
	// has changed false if not.
	boolean stateChange;

	/**
	 * Constructs a Delete Class command with the desired model and class name.
	 * 
	 * @param newModel     The model.
	 * @param newClassName The class name.
	 */
	public DeleteClassCommand(ClassContainer newModel, String newClassName) {
		model = newModel;
		className = newClassName;
		stateChange = false;
	}

	/**
	 * Executes the command. This command deletes a class from the model. The class
	 * must exist.
	 * 
	 * @return A string that represents the outcome of the execution.
	 */
	@Override
	public String execute() {
		String response;
		if (model.getClasses().containsKey(className)) {
			model.getClasses().remove(className);
			for (SortedMap.Entry<String, ClassBase> entry : model.getClasses().entrySet()) {
				ClassBase c = entry.getValue();
				if (c.containsRelationship(className)) {
					c.deleteRelationship(className);
				}
			}
			response = "The class " + className + " has been deleted.";
			stateChange = true;
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
