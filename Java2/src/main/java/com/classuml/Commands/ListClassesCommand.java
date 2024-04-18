package com.classuml.Commands;

import java.util.Map.Entry;

import com.classuml.Model.ClassBase;
import com.classuml.Model.MementoState;

public class ListClassesCommand implements Command {

	// Stores the model that the command will be executed against
	private final MementoState model;
	// Stores whether or not the state of the model has changed. True if the state
	// has changed, false if not
	private boolean stateChange;

	/**
	 * Constructs a ListClassesCommand with the desired model.
	 * 
	 * @param modelP The model.
	 */
	public ListClassesCommand(MementoState modelP) {
		this.model = modelP;
		this.stateChange = false;
	}

	/**
	 * Executes the command. This command lists all the classes in the model.
	 * 
	 * @return A string that represents the outcome of the execution.
	 */
	@Override
	public String execute() {
		StringBuilder response = new StringBuilder();
		int count = 0;
		for (Entry<String, ClassBase> entry : model.getClasses().entrySet()) {
			ClassBase classInfo = entry.getValue();
			response.append(classInfo.toString());
			if (count < model.getClasses().size() - 1) {
				response.append(System.lineSeparator()).append(System.lineSeparator());
			}
			count++;
		}
		return response.toString();
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
