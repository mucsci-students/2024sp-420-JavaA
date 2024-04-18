package com.classuml.Commands;

import java.util.SortedMap;

import com.classuml.Model.ClassBase;
import com.classuml.Model.MementoState;

public class ListRelationshipsCommand implements Command {

	// Stores the model that the command will be executed against
	private final MementoState model;
	// Stores whether or not the state of the model has changed. True if the state
	// has changed, false if not
	private boolean stateChange;

	/**
	 * Constructs a ListRelationshipsCommand with the desired model.
	 * 
	 * @param modelP The model.
	 */
	public ListRelationshipsCommand(MementoState model) {
		this.model = model;
		this.stateChange = false;
	}

	/**
	 * Executes the command. This command lists all the relationships in the model.
	 * 
	 * @return A string that represents the outcome of the execution.
	 */
	@Override
	public String execute() {
		StringBuilder response = new StringBuilder();
		int count = 0;
		for (SortedMap.Entry<String, ClassBase> entry : model.getClasses().entrySet()) {
			ClassBase classInfo = entry.getValue();
			if (!classInfo.getRelationships().isEmpty()) {
				response.append(classInfo.getName()).append(classInfo.printRelationships());
				if (count < model.getClasses().size() - 1) {
					response.append(System.lineSeparator());
				}
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
