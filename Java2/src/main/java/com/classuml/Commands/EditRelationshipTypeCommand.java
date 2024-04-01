package com.classuml.Commands;

import com.classuml.Model.ClassBase;
import com.classuml.Model.ClassContainer;
import com.classuml.Model.Relationship;

public class EditRelationshipTypeCommand implements Command {

	// The model that the command will be executed against
	private ClassContainer model;
	// The name of the class in which the relationship exists
	private String className;
	// The relationship destination of the class to be edited
	private String relationshipDestination;
	// The new relationship type
	private String newRelationshipType;
	// Indicates whether the state of the model has changed
	private boolean stateChange;

	/**
	 * Constructs an Edit Relationship Type command with the specified parameters.
	 * 
	 * @param model                   The model
	 * @param className               The class name
	 * @param relationshipDestination The relationship destination name
	 * @param newRelationshipType     The new relationship type
	 */
	public EditRelationshipTypeCommand(ClassContainer model, String className, String relationshipDestination,
			String newRelationshipType) {
		this.model = model;
		this.className = className;
		this.relationshipDestination = relationshipDestination;
		this.newRelationshipType = newRelationshipType;
		this.stateChange = false;
	}

	/**
	 * Executes the command. This command edits the relationship type of an existing
	 * relationship in a class. The class and relationship must exist,
	 * and the new relationship type must be one of the following: Aggregation,
	 * Composition, Inheritance, or Realization.
	 * 
	 * @return A string that represents the outcome of the execution
	 */
	@Override
	public String execute() {
		String response;
		if (model.getClasses().containsKey(className)) {
			ClassBase classInfo = model.getClasses().get(className);
			if (classInfo.containsRelationship(relationshipDestination)) {
				Relationship relationship = classInfo.getRelationship(relationshipDestination);
				if (isValidRelationshipType(newRelationshipType)) {
					relationship.setType(newRelationshipType);
					response = "The relationship " + relationshipDestination + " type has been changed to "
							+ newRelationshipType + ".";
					stateChange = true;
				} else {
					response = "The relationship type must be one of the following: Aggregation, Composition, Inheritance, or Realization";
				}
			} else {
				response = "The relationship " + relationshipDestination + " does not exist with the class " + className
						+ ".";
			}
		} else {
			response = "The class " + className + " does not exist.";
		}
		return response;
	}

	/**
	 * Gets the stateChange field.
	 * 
	 * @return A boolean that represents whether or not the state has changed
	 */
	@Override
	public boolean getStateChange() {
		return stateChange;
	}

	/**
	 * Checks if the provided relationship type is valid.
	 * 
	 * @param relationshipType The relationship type to validate
	 * @return true if the relationship type is valid, false otherwise
	 */
	private boolean isValidRelationshipType(String relationshipType) {
		return relationshipType.equals("Aggregation") || relationshipType.equals("Composition")
				|| relationshipType.equals("Inheritance") || relationshipType.equals("Realization");
	}
}
