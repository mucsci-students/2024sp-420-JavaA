package com.classuml.Commands;

public class HelpCommand implements Command {

	// Indicates whether the state of the model has changed
	private boolean stateChange;

	/**
	 * Constructs a HelpCommand object.
	 */
	public HelpCommand() {
		this.stateChange = false;
	}

	/**
	 * Executes the command. This command returns a formatted string containing
	 * descriptions of all available commands.
	 * 
	 * @return A string representing the outcome of the execution.
	 */
	@Override
	public String execute() {
		StringBuilder helpText = new StringBuilder();
		helpText.append("  add class <class_name> - Adds a class\n");
		helpText.append("  add field <class_name> <field_type> <field_name> - Adds a field to the specified class\n");
		helpText.append(
				"  add method <class_name> <method_return_type> <method_name> <method_parameters> - Adds a method to a class\n");
		helpText.append("  add rel <class1> <class2> <relationship_type> - Adds a relationship between classes\n\n");
		helpText.append("  delete class <class_name> - Deletes the specified class\n");
		helpText.append("  delete field <class_name> <field_name> - Deletes the specified field in the class\n");
		helpText.append(
				"  delete method <class_name> <method_name> <method_parameters> - Deletes the specified method in the class\n");
		helpText.append(
				"  delete rel <class_name> <relationship_destination_name> - Deletes the relationship between classes\n\n");
		helpText.append("  edit class <new_class_name> <class_name>  - Renames the specified class\n");
		helpText.append(
				"  edit field name <class_name> <current_field> <new_field_name> - Renames the specified field\n");
		helpText.append(
				"  edit field type <class_name> <current_field> <new_field_type> - Changes the type of the specified field\n");
		helpText.append(
				"  edit method name <class_name> <method_name> <method_parameters> / <new_method_name> - Renames the specified method\n");
		helpText.append(
				"  edit method type <class_name> <method_name> <method_parameters> / <new_method_type> - Changes the return type of the specified method\n");
		helpText.append(
				"  edit method parameters <class_name> <method_name> <method_parameters> / <new_method_parameters> - Changes the parameters of the specified method\n");
		helpText.append(
				"  edit rel type <class_name> <destination_name> <new_destination_type> - Changes the type of the relationship\n\n");
		helpText.append("  list class <class_name> - Lists the specified class and its components\n");
		helpText.append("  list classes - Lists all classes\n");
		helpText.append("  list rel - Lists all relationships between classes\n\n");
		helpText.append("  save <file_name> - Saves the file with the specified name in .json format\n");
		helpText.append("  load <file_name> - Loads the file with the specified name\n\n");
		helpText.append("  undo - Undoes the last action that changed the state of the model\n");
		helpText.append("  redo - Redoes the last undo\n\n");
		helpText.append("  exit - Exits the program\n");

		return helpText.toString();
	}

	/**
	 * Gets the stateChange field.
	 * 
	 * @return A boolean representing whether or not the state has changed.
	 */
	@Override
	public boolean getStateChange() {
		return stateChange;
	}
}
