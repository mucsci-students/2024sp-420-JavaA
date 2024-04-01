/**
 * The CLIController class is responsible for interpreting and processing commands entered
 * through the Command Line Interface (CLI) for a UML class diagram editing application.
 * It acts as the main controller that controls the interactions between the model of the UML
 * diagram (ClassContainer), the CLI view for user input and output, and the execution of commands
 * that modify the UML diagram. This includes adding, deleting, and editing classes, fields, methods,
 * and relationships, as well as undoing and redoing changes, listing diagram elements, and managing
 * save/load operations from JSON format.
 *
 * The CLIController utilizes a command pattern to encapsulate each action as a command object, allowing
 * for flexible command execution and easy addition of new commands. It also implements the Memento pattern
 * through a History object to support undo/redo functionality, tracking changes to the model and allowing
 * users to revert or reapply actions as needed.
 *
 * Commands are parsed from user input strings and executed in a loop until an "exit" command is received,
 * making the CLIController the entry point for user interaction in the application. It ensures that
 * user inputs are correctly interpreted and mapped to their corresponding actions, providing feedback
 * through the CLI view.
 *
 * This class heavily relies on the Command objects for each specific action (e.g., AddClassCommand,
 * DeleteFieldCommand), the ClassContainer model for storing the UML diagram state, and the CLI view for
 * interacting with the user. It demonstrates the use of design patterns and object-oriented principles
 * to create a flexible, maintainable, and user-friendly command-line interface for UML diagram editing.
 *
 */
/********************************************************************************************************************/

package com.classuml.Control;

import java.util.Arrays;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;

import com.classuml.Commands.AddClassCommand;
import com.classuml.Commands.AddFieldCommand;
import com.classuml.Commands.AddMethodCommand;
import com.classuml.Commands.AddRelationshipCommand;
import com.classuml.Commands.Command;
import com.classuml.Commands.DeleteClassCommand;
import com.classuml.Commands.DeleteFieldCommand;
import com.classuml.Commands.DeleteMethodCommand;
import com.classuml.Commands.DeleteRelationshipCommand;
import com.classuml.Commands.EditClassNameCommand;
import com.classuml.Commands.EditFieldNameCommand;
import com.classuml.Commands.EditFieldTypeCommand;
import com.classuml.Commands.EditMethodNameCommand;
import com.classuml.Commands.EditMethodParametersCommand;
import com.classuml.Commands.EditMethodTypeCommand;
import com.classuml.Commands.EditRelationshipTypeCommand;
import com.classuml.Commands.EditRelationshipDestinationCommand;
import com.classuml.Commands.HelpCommand;
import com.classuml.Commands.ListClassCommand;
import com.classuml.Commands.ListClassesCommand;
import com.classuml.Commands.ListRelationshipsCommand;
import com.classuml.Commands.LoadJSONCommand;
import com.classuml.Commands.SaveJSONCommand;
import com.classuml.Memento.History;
import com.classuml.Memento.Memento;
import com.classuml.Model.ClassContainer;
import com.classuml.Model.Parameter;
import com.classuml.View.CLI;

public class CLIController {

	// Stores the model that the controller will act on.
	private ClassContainer model;
	// Stores the view that the controller will update.
	private CLI view;
	// Stores the history object that the controller uses to perform undo and redo.
	private History history;

	/**
	 * Initializes a CLIController for UML diagram management via a CLI. Sets up a
	 * model-view-controller
	 * architecture by associating a model and a CLI view, and initiates a command
	 * loop to process user
	 * inputs for managing classes, fields, methods, and relationships. Supports
	 * add, delete, edit, list,
	 * save, load, undo, redo, help, and exit commands.
	 *
	 * @param model The ClassContainer that holds the data model the controller
	 *              will manipulate.
	 * @param view  The CLI instance that provides the user interface for input and
	 *              output.
	 */

	public CLIController(ClassContainer model, CLI view) {
		this.model = model;
		this.view = view;
		history = new History();

		String choice = view.prompt();
		String seperator = " ";
		String[] parsedChoice = StringUtils.split(choice, seperator);

		boolean x = true;
		while (x) {
			// Add
			if (parsedChoice.length == 3 && parsedChoice[0].equals("add") && (parsedChoice[1].equals("class"))) {
				String response = executeCommand(new AddClassCommand(model, parsedChoice[2]));
				view.update(response);
			} else if (parsedChoice.length == 5 && parsedChoice[0].equals("add") && (parsedChoice[1].equals("field"))) {
				String response = executeCommand(
						new AddFieldCommand(model, parsedChoice[2], parsedChoice[3], parsedChoice[4]));
				view.update(response);
			} else if (parsedChoice.length >= 5 && !(parsedChoice.length % 2 == 0) && parsedChoice[0].equals("add")
					&& (parsedChoice[1].equals("method"))) {
				List<String> parametersList = Arrays.asList(parsedChoice);
				SortedSet<Parameter> parametersSet = new TreeSet<Parameter>();
				for (int i = 5; i < parametersList.size(); i = i + 2) {
					parametersSet.add(new Parameter(parametersList.get(i), parametersList.get(i + 1)));
				}
				String response = executeCommand(
						new AddMethodCommand(model, parsedChoice[2], parsedChoice[3], parsedChoice[4], parametersSet));
				view.update(response);
			} else if (parsedChoice.length == 5 && parsedChoice[0].equals("add") && (parsedChoice[1].equals("rel"))) {
				String response = executeCommand(
						new AddRelationshipCommand(model, parsedChoice[2], parsedChoice[3], parsedChoice[4]));
				view.update(response);
			}

			// Delete
			else if (parsedChoice.length == 3 && parsedChoice[0].equals("delete")
					&& (parsedChoice[1].equals("class"))) {
				String response = executeCommand(new DeleteClassCommand(model, parsedChoice[2]));
				view.update(response);
			} else if (parsedChoice.length == 4 && parsedChoice[0].equals("delete")
					&& (parsedChoice[1].equals("field"))) {
				String response = executeCommand(new DeleteFieldCommand(model, parsedChoice[2], parsedChoice[3]));
				view.update(response);
			} else if (parsedChoice.length >= 4 && (parsedChoice.length % 2 == 0) && parsedChoice[0].equals("delete")
					&& (parsedChoice[1].equals("method"))) {
				List<String> parametersList = Arrays.asList(parsedChoice);
				SortedSet<Parameter> parametersSet = new TreeSet<Parameter>();
				for (int i = 4; i < parametersList.size(); i = i + 2) {
					parametersSet.add(new Parameter(parametersList.get(i), parametersList.get(i + 1)));
				}
				String response = executeCommand(
						new DeleteMethodCommand(model, parsedChoice[2], parsedChoice[3], parametersSet));
				view.update(response);
			} else if (parsedChoice.length == 4 && parsedChoice[0].equals("delete")
					&& (parsedChoice[1].equals("rel"))) {
				String response = executeCommand(
						new DeleteRelationshipCommand(model, parsedChoice[2], parsedChoice[3]));
				view.update(response);
			}

			// Rename / Edit
			else if (parsedChoice.length == 4 && parsedChoice[0].equals("edit") && (parsedChoice[1].equals("class"))) {
				String response = executeCommand(new EditClassNameCommand(model, parsedChoice[2], parsedChoice[3]));
				view.update(response);
			} else if (parsedChoice.length == 6 && parsedChoice[0].equals("edit") && (parsedChoice[1].equals("field"))
					&& (parsedChoice[2].equals("name"))) {
				String response = executeCommand(
						new EditFieldNameCommand(model, parsedChoice[3], parsedChoice[4], parsedChoice[5]));
				view.update(response);
			} else if (parsedChoice.length == 6 && parsedChoice[0].equals("edit") && (parsedChoice[1].equals("field"))
					&& (parsedChoice[2].equals("type"))) {
				String response = executeCommand(
						new EditFieldTypeCommand(model, parsedChoice[3], parsedChoice[4], parsedChoice[5]));
				view.update(response);
			} else if (parsedChoice.length >= 5 && !(parsedChoice.length % 2 == 0) && parsedChoice[0].equals("edit")
					&& (parsedChoice[1].equals("method")) && (parsedChoice[2].equals("name"))) {
				List<String> parametersList = Arrays.asList(parsedChoice);
				SortedSet<Parameter> parametersSet = new TreeSet<Parameter>();
				String newMethodName = "";
				for (int i = 5; i < parametersList.size(); i = i + 2) {
					if (parametersList.get(i).equals("/")) {
						for (i++; i < parametersList.size(); i = i + 2) {
							newMethodName = parametersList.get(i);
						}
					} else {
						parametersSet.add(new Parameter(parametersList.get(i), parametersList.get(i + 1)));
					}
				}
				String response = executeCommand(new EditMethodNameCommand(model, parsedChoice[3], parsedChoice[4],
						parametersSet, newMethodName));
				view.update(response);
			} else if (parsedChoice.length >= 5 && !(parsedChoice.length % 2 == 0) && parsedChoice[0].equals("edit")
					&& (parsedChoice[1].equals("method")) && (parsedChoice[2].equals("type"))) {
				List<String> parametersList = Arrays.asList(parsedChoice);
				SortedSet<Parameter> parametersSet = new TreeSet<Parameter>();
				String newMethodType = "";
				for (int i = 5; i < parametersList.size(); i = i + 2) {
					if (parametersList.get(i).equals("/")) {
						for (i++; i < parametersList.size(); i = i + 2) {
							newMethodType = parametersList.get(i);
						}
					} else {
						parametersSet.add(new Parameter(parametersList.get(i), parametersList.get(i + 1)));
					}
				}
				String response = executeCommand(new EditMethodTypeCommand(model, parsedChoice[3], parsedChoice[4],
						parametersSet, newMethodType));
				view.update(response);
			} else if (parsedChoice.length >= 5 && (parsedChoice.length % 2 == 0) && parsedChoice[0].equals("edit")
					&& (parsedChoice[1].equals("method")) && (parsedChoice[2].equals("parameters"))) {
				List<String> parametersList = Arrays.asList(parsedChoice);
				SortedSet<Parameter> parametersSet = new TreeSet<Parameter>();
				SortedSet<Parameter> parametersSetNew = new TreeSet<Parameter>();
				for (int i = 5; i < parametersList.size(); i = i + 2) {
					if (parametersList.get(i).equals("/")) {
						for (i++; i < parametersList.size(); i = i + 2) {
							parametersSetNew.add(new Parameter(parametersList.get(i), parametersList.get(i + 1)));
						}
					} else {
						parametersSet.add(new Parameter(parametersList.get(i), parametersList.get(i + 1)));
					}
				}
				String response = executeCommand(new EditMethodParametersCommand(model, parsedChoice[3],
						parsedChoice[4], parametersSet, parametersSetNew));
				view.update(response);
			} else if (parsedChoice.length == 6 && parsedChoice[0].equals("edit") && (parsedChoice[1].equals("rel"))
					&& (parsedChoice[2].equals("dest"))) {
				String response = executeCommand(new EditRelationshipDestinationCommand(model, parsedChoice[3],
						parsedChoice[4], parsedChoice[5]));
				view.update(response);
			} else if (parsedChoice.length == 6 && parsedChoice[0].equals("edit") && (parsedChoice[1].equals("rel"))
					&& (parsedChoice[2].equals("type"))) {
				String response = executeCommand(
						new EditRelationshipTypeCommand(model, parsedChoice[3], parsedChoice[4], parsedChoice[5]));
				view.update(response);
			}
			// List
			else if (parsedChoice.length == 2 && parsedChoice[0].equals("list")
					&& (parsedChoice[1].equals("classes"))) {
				String response = executeCommand(new ListClassesCommand(model));
				view.update(response);
			} else if (parsedChoice.length == 3 && parsedChoice[0].equals("list")
					&& (parsedChoice[1].equals("class"))) {
				String response = executeCommand(new ListClassCommand(model, parsedChoice[2]));
				view.update(response);
			} else if (parsedChoice.length == 2 && parsedChoice[0].equals("list") && (parsedChoice[1].equals("rel"))) {
				String response = executeCommand(new ListRelationshipsCommand(model));
				view.update(response);
			}
			// Save / Load
			else if (parsedChoice.length == 2 && parsedChoice[0].equals("save")) {
				String response = executeCommand(new SaveJSONCommand(model, parsedChoice[1]));
				view.update(response);
			} else if (parsedChoice.length == 2 && parsedChoice[0].equals("load")) {
				String response = executeCommand(new LoadJSONCommand(model, parsedChoice[1]));
				view.update(response);
			}
			// Help and Exit
			else if (parsedChoice.length == 1 && parsedChoice[0].equals("help")) {
				String response = executeCommand(new HelpCommand());
				view.update(response);
			} else if (parsedChoice.length == 1 && parsedChoice[0].equals("undo")) {
				String response = this.undo();
				view.update(response);
			} else if (parsedChoice.length == 1 && parsedChoice[0].equals("redo")) {
				String response = this.redo();
				view.update(response);
			} else if (parsedChoice.length == 1 && parsedChoice[0].equals("exit")) {
				break;
			} else {
				System.out.println("Please enter a valid selection");
			}
			choice = view.prompt();
			parsedChoice = StringUtils.split(choice, seperator);
		}
		;
	}

	/**
	 * Executes a command object. This method also creates a deep copy and saves it
	 * to the backup field of the model and pushes the Command and a Memento onto
	 * the History stack.
	 * 
	 * @return A string that represents the outcome of the execution.
	 */
	private String executeCommand(Command command) {
		ClassContainer deepCopy = (ClassContainer) org.apache.commons.lang.SerializationUtils.clone(model);
		model.setBackup(deepCopy.getClasses());
		String response = command.execute();
		if (command.getStateChange()) {
			history.pushUndo(command, new Memento(model));
			view.setCompleter(model);
		}
		return response;
	}

	/*******************************************************************************************************************
	 * UNDO & REDO
	 ********************************************************************************************************************/

	/**
	 * Undoes the last executed command if possible, reverting the model to its
	 * previous state.
	 * Updates the view to reflect the changes. If no commands are available to
	 * undo, informs the user.
	 *
	 * @return A string message indicating the result of the undo operation.
	 */
	private String undo() {
		String response = "You can no longer undo.";
		if (!history.isEmptyUndo()) {
			history.undo();
			view.setCompleter(model);
			response = "The last action has been undone.";
		}
		return response;
	}

	/**
	 * Redoes the last undone command if possible, reapplying the command's effects
	 * to the model.
	 * Updates the view to reflect the changes. If no commands are available to
	 * redo, informs the user.
	 *
	 * @return A string message indicating the result of the redo operation.
	 */
	private String redo() {
		String response = "You can no longer redo.";
		if (!history.isEmptyRedo()) {
			Command c = history.redo();
			response = executeCommand(c);
		}
		return response;
	}

}