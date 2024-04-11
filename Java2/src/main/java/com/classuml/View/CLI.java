/**
 * Represents the command-line interface (CLI) of a UML diagram editor, providing interactive command 
 * completion and parsing functionality. This class leverages the JLine3 library to enhance user input 
 * with auto-completion for commands related to manipulating UML diagrams, including adding, deleting, 
 * and editing classes, fields, methods, and relationships. It dynamically updates auto-completion 
 * candidates based on the current state of the UML model and supports commands for saving and loading 
 * diagrams, undoing and redoing actions, and obtaining help. The CLI view integrates closely with the 
 * application's model to provide an intuitive and efficient user experience for navigating and modifying 
 * UML diagrams through a terminal interface.
 */

/*******************************************************************************************************************
 * 			IMPORTS
********************************************************************************************************************/

package com.classuml.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import org.jline.builtins.Completers;
import org.jline.builtins.Completers.RegexCompleter;
import org.jline.reader.Candidate;
import org.jline.reader.Completer;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.ParsedLine;
import org.jline.reader.impl.DefaultParser;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import com.classuml.Model.ClassBase;
import com.classuml.Model.ClassContainer;
import com.classuml.Model.Field;
import com.classuml.Model.Method;
import com.classuml.Model.Relationship;

/*******************************************************************************************************************
 * CLI CLASS
 ********************************************************************************************************************/

public class CLI {

	/**
	 * A custom Completer for the jline3 terminal.
	 */
	private class TabCompleter implements Completer {
		RegexCompleter customCompleter;

		/**
		 * Constructs a TabCompleter for CLI with auto-completion for UML diagram
		 * commands, including
		 * add, delete, edit, list, and system actions. It dynamically maps command
		 * patterns to completions,
		 * offering context-aware suggestions for an improved user experience.
		 */
		public TabCompleter() {
			Map<String, Completer> comp = new HashMap<>();
			comp.put("C0", new StringsCompleter("add"));
			comp.put("C1", new StringsCompleter("delete"));
			comp.put("C2", new StringsCompleter("edit"));
			comp.put("C3", new StringsCompleter("list"));

			comp.put("C4", new StringsCompleter("class"));
			comp.put("C5", new StringsCompleter("field"));
			comp.put("C6", new StringsCompleter("method"));
			comp.put("C7", new StringsCompleter("rel"));
			comp.put("C8", new StringsCompleter("Composition", "Aggregation", "Inheritance", "Realization"));

			comp.put("C9", new StringsCompleter(""));
			comp.put("C10", new StringsCompleter(""));
			comp.put("C11", new StringsCompleter(""));
			comp.put("C12", new StringsCompleter(""));

			comp.put("C13", new StringsCompleter("name"));
			comp.put("C14", new StringsCompleter("type"));
			comp.put("C15", new StringsCompleter("dest"));
			comp.put("C16", new StringsCompleter("parameters"));
			comp.put("C17", new StringsCompleter("classes"));

			comp.put("C20", new StringsCompleter("save"));
			comp.put("C21", new StringsCompleter("load"));
			comp.put("C22", new StringsCompleter("redo"));
			comp.put("C23", new StringsCompleter("undo"));
			comp.put("C24", new StringsCompleter("help"));
			comp.put("C25", new StringsCompleter("exit"));

			customCompleter = new Completers.RegexCompleter(
					"C20 | C21 | C22 | C23 | C24 | C25 | C0 C4 | C0 C5 C9 | C0 C6 C9 | C0 C7 C9 C9 | C1 C4 C9 | C1 C5 C9 C10 | C1 C6 C9 C11 | C1 C7 C9 C12 | C2 C4 C9 | C2 C5 C13 C9 C10 | C2 C5 C14 C9 C10 | C2 C6 C13 C9 C11 | C2 C6 C14 C9 C11 | C2 C6 C16 C9 C11 | C2 C7 C15 C9 C12 | C2 C7 C14 C9 C12 | C3 C17 | C3 C4 C9 | C3 C7",
					comp::get);

		}

		@Override
		public void complete(LineReader reader, ParsedLine line, List<Candidate> candidates) {
			customCompleter.complete(reader, line, candidates);
		}

		/**
		 * Updates the command completion candidates based on the current state of the
		 * UML model.
		 * This method refreshes the autocomplete suggestions for the CLI, including
		 * classes, fields,
		 * methods, and relationships. It ensures that the CLI's autocomplete
		 * functionality remains
		 * consistent with the model's current structure, facilitating an intuitive and
		 * dynamic
		 * user interaction experience.
		 *
		 * @param model The ClassContainer model containing the current state of UML
		 *              classes and relationships.
		 */
		public void setCompleter(ClassContainer model) {
			List<String> classes = new ArrayList<String>();
			List<String> fields = new ArrayList<String>();
			List<String> methods = new ArrayList<String>();
			List<String> relationships = new ArrayList<String>();

			for (SortedMap.Entry<String, ClassBase> entry : model.getClasses().entrySet()) {
				ClassBase customCompleter = entry.getValue();
				classes.add(customCompleter.getName());
				if (!customCompleter.getFields().isEmpty()) {
					Iterator<Field> it = customCompleter.getFields().iterator();
					while (it.hasNext()) {
						Field f = it.next();
						fields.add(f.getName());
					}
				}
				if (!customCompleter.getMethods().isEmpty()) {
					Iterator<Method> it = customCompleter.getMethods().iterator();
					while (it.hasNext()) {
						Method m = it.next();
						methods.add(m.toStringTabCompleter());
					}
				}
				if (!customCompleter.getRelationships().isEmpty()) {
					Iterator<Relationship> it = customCompleter.getRelationships().iterator();
					while (it.hasNext()) {
						Relationship r = it.next();
						relationships.add(r.getDestination());
					}
				}
			}

			Map<String, Completer> comp = new HashMap<>();
			comp.put("C0", new StringsCompleter("add"));
			comp.put("C1", new StringsCompleter("delete"));
			comp.put("C2", new StringsCompleter("edit"));
			comp.put("C3", new StringsCompleter("list"));

			comp.put("C4", new StringsCompleter("class"));
			comp.put("C5", new StringsCompleter("field"));
			comp.put("C6", new StringsCompleter("method"));
			comp.put("C7", new StringsCompleter("rel"));
			comp.put("C8", new StringsCompleter("Aggregation", "Composition", "Inheritance", "Realization"));

			comp.put("C9", new StringsCompleter(classes));
			comp.put("C10", new StringsCompleter(fields));
			comp.put("C11", new StringsCompleter(methods));
			comp.put("C12", new StringsCompleter(relationships));

			comp.put("C13", new StringsCompleter("name"));
			comp.put("C14", new StringsCompleter("type"));
			comp.put("C15", new StringsCompleter("dest"));
			comp.put("C16", new StringsCompleter("parameters"));
			comp.put("C17", new StringsCompleter("classes"));

			comp.put("C20", new StringsCompleter("save"));
			comp.put("C21", new StringsCompleter("load"));
			comp.put("C22", new StringsCompleter("redo"));
			comp.put("C23", new StringsCompleter("undo"));
			comp.put("C24", new StringsCompleter("help"));
			comp.put("C25", new StringsCompleter("exit"));

			customCompleter = new Completers.RegexCompleter(
					"C20 | C21 | C22 | C23 | C24 | C25 | C0 C4 | C0 C5 C9 "+ 
					"| C0 C6 C9 | C0 C7 C9 C9 | C1 C4 C9 | C1 C5 C9 C10 | C1 C6 C9 C11 "+
					"| C1 C7 C9 C12 | C2 C4 C9 | C2 C5 C13 C9 C10 | C2 C5 C14 C9 C10 "+
					"| C2 C6 C13 C9 C11 | C2 C6 C14 C9 C11 | C2 C6 C16 C9 C11 "+
					"| C2 C7 C15 C9 C12 | C2 C7 C14 C9 C12 | C3 C17 | C3 C4 C9 | C3 C7",
					comp::get);

		}
	}

	/*******************************************************************************************************************
	********************************************************************************************************************/

	// Stores the jline3 reader object.
	private LineReader lr;
	// Stores the custom completer.
	private TabCompleter completer;

	/**
	 * Constructs the CLI with a configured terminal and line reader, incorporating
	 * a custom completer for
	 * input suggestions and handling escape characters. Errors in setup are logged.
	 */
	public CLI() {
		try {
			Terminal terminal = TerminalBuilder.terminal();
			completer = new TabCompleter();
			lr = LineReaderBuilder.builder().terminal(terminal).completer(completer).build();
			DefaultParser dp = (DefaultParser) lr.getParser();
			dp.setEscapeChars(new char[] {});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the completer.
	 * 
	 * @return The completer.
	 */
	public TabCompleter getCompleter() {
		return completer;
	}

	/**
	 * Sets the completer.
	 * 
	 * @param model The model to be used.
	 */
	public void setCompleter(ClassContainer model) {
		completer.setCompleter(model);
	}

	/**
	 * Sends a response to the terminal console.
	 * 
	 * @param respone A String that represents the response.
	 */
	public void update(String response) {
		System.out.println(response);
	}

	/**
	 * Prompts for user input
	 * 
	 * @param The user input from the terminal.
	 */
	public String prompt() {
		System.out
				.println("Welcome. If you need help with commands, please type 'help', without the '' surrounding it.");

		return lr.readLine("> ");
	}
}