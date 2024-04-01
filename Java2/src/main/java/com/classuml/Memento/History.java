package com.classuml.Memento;

import java.util.Stack;

import com.classuml.Commands.Command;

/**
 * The History class manages the history of commands and their corresponding
 * mementos,
 * allowing for undo and redo operations.
 */
public class History {

	/** Stack to store undo history */
	Stack<match> undoHistory = new Stack<match>();
	/** Stack to store redo history */
	Stack<Command> redoHistory = new Stack<Command>();

	/**
	 * Inner class representing a match between a command and its memento.
	 */
	private class match {
		/** The command associated with the match */
		Command command;
		/** The memento associated with the match */
		Memento memento;

		/**
		 * Constructs a new match.
		 * 
		 * @param c The command
		 * @param m The memento
		 */
		match(Command c, Memento m) {
			command = c;
			memento = m;
		}

		/**
		 * Retrieves the command associated with the match.
		 * 
		 * @return The command
		 */
		private Command getCommand() {
			return command;
		}

		/**
		 * Retrieves the memento associated with the match.
		 * 
		 * @return The memento
		 */
		private Memento getMemento() {
			return memento;
		}
	}

	/**
	 * Constructs a new History object.
	 */
	public History() {

	}

	/**
	 * Adds a new entry to the undo history.
	 * 
	 * @param c The command
	 * @param m The memento
	 */
	public void pushUndo(Command c, Memento m) {
		undoHistory.add(new match(c, m));
	}

	/**
	 * Removes and returns the most recent entry from the undo history.
	 * 
	 * @return The most recent match
	 */
	public match popUndo() {
		return undoHistory.pop();
	}

	/**
	 * Adds a command to the redo history.
	 * 
	 * @param c The command to redo
	 */
	public void pushRedo(Command c) {
		redoHistory.add(c);
	}

	/**
	 * Removes and returns the most recent command from the redo history.
	 * 
	 * @return The most recent command to redo
	 */
	public Command popRedo() {
		return redoHistory.pop();
	}

	/**
	 * Checks if the undo history is empty.
	 * 
	 * @return True if the undo history is empty, false otherwise
	 */
	public boolean isEmptyUndo() {
		return undoHistory.isEmpty();
	}

	/**
	 * Checks if the redo history is empty.
	 * 
	 * @return True if the redo history is empty, false otherwise
	 */
	public boolean isEmptyRedo() {
		return redoHistory.isEmpty();
	}

	/**
	 * Performs an undo operation by restoring the state using the most recent
	 * memento.
	 */
	public void undo() {
		match p = this.popUndo();
		Memento m = p.getMemento();

		Command c = p.getCommand();
		this.pushRedo(c);

		m.restore();
	}

	/**
	 * Performs a redo operation by retrieving the most recent command from the redo
	 * history.
	 * 
	 * @return The command to redo
	 */
	public Command redo() {
		Command c = this.popRedo();
		return c;
	}

}
