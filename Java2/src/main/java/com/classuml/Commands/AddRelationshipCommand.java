package com.classuml.Commands;

import com.classuml.Model.ClassContainer;
import com.classuml.Model.ClassBase;

public class AddRelationshipCommand implements Command {

	// Stores the model that the command will be executed against.
	private ClassContainer model;
	// Stores the desired class name for the relationship to be created in.
	private String className;
	// Stores the desired relationship destination to be used when the relationship
	// is created.
	private String relationshipDestination;
	// Stores the desired relationship type to be used when the relationship is
	// created.
	private String relationshipType;
	// Stores whether or not the state of the model has changed. True if the state
	// has changed, false if not.
	private boolean stateChange;

	/**
	 * Constructs an Add Relationship command with the desired model, class name,
	 * relationship name, and relationship type.
	 *
	 * @param newModel                   The model.
	 * @param newClassName               The class name.
	 * @param newRelationshipDestination The relationship destination.
	 * @param newRelationshipType        The relationship type.
	 */
	public AddRelationshipCommand(ClassContainer newModel, String newClassName, String newRelationshipDestination,
			String newRelationshipType) {
		model = newModel;
		className = newClassName;
		relationshipDestination = newRelationshipDestination;
		relationshipType = newRelationshipType;
		stateChange = false;
	}

	/**
	 * Executes the command. This command adds a new relationship to a class. The
	 * class must exist and the relationship must be unique, and the class that the
	 * relationship will be with must also exist.
	 *
	 * @return A string that represents the outcome of the execution.
	 */
	@Override
	public String execute() {
		String response;
		if (model.getClasses().containsKey(className)) {
			ClassBase classInfo = model.getClasses().get(className);
			if (!classInfo.containsRelationship(relationshipDestination)) {
				if (model.getClasses().containsKey(relationshipDestination)) {
					if (isValidRelationshipType(relationshipType)) {
						classInfo.addRelationship(relationshipDestination, relationshipType);
						response = "The " + className + " has created a new relationship with class: "
								+ relationshipDestination + " of type " + relationshipType;
						stateChange = true;
					} else {
						response = "The relationship type must be one of the following: Aggregation, Composition, Inheritance or Realization";
					}
				} else {
					response = "The " + relationshipDestination + " class does not exist.";
				}
			} else {
				response = "A relationship with this class " + className + " already exists.";
			}
		} else {
			response = "The class " + className + " does not exist.";
		}
		return response;
	}

	/**
	 * Checks if the provided relationship type is valid.
	 *
	 * @param type The relationship type to be validated.
	 * @return True if the relationship type is valid, false otherwise.
	 */
	private boolean isValidRelationshipType(String type) {
		return type.equals("Aggregation") || type.equals("Composition") || type.equals("Inheritance")
				|| type.equals("Realization");
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
