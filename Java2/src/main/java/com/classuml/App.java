package com.classuml;

import com.classuml.Control.CLIController;
import com.classuml.Control.GUIController;
import com.classuml.Model.ClassContainer;
import com.classuml.View.CLI;
import com.classuml.View.GUI;

/**
 * The App class is the entry point for the application.
 * It initializes the ClassContainer model and selects the appropriate view and
 * controller based on the command-line arguments.
 */
public final class App {

	/**
	 * The main method of the application.
	 * It initializes the ClassContainer model and selects the appropriate view and
	 * controller based on the command-line arguments.
	 * 
	 * @param args The command-line arguments
	 */
	public static void main(String[] args) {
		// Initialize the ClassContainer model
		ClassContainer model = new ClassContainer();

		// Check command-line arguments
		if ((args.length == 1) && (args[0].equals("-cli"))) {
			// If the argument is "-cli", create CLI view and controller
			CLI view = new CLI();
			@SuppressWarnings("unused")
			CLIController controller = new CLIController(model, view);
		} else if (args.length == 0) {
			// If no arguments provided, create GUI view and controller
			GUI view = new GUI();
			@SuppressWarnings("unused")
			GUIController controller = new GUIController(model, view);
		} else {
			// Invalid input, print message
			System.out.println("Invalid Input. Valid inputs are: -cli");
		}
	}
}