package com.classuml.Memento;

import java.util.SortedMap;

import com.classuml.Model.ClassBase;
import com.classuml.Model.MementoState;

public class Memento {

	// Stores the model.
	private MementoState model;
	// Stores the backup of the classes model that is passed in.
	private SortedMap<String, ClassBase> backup;

	/**
	 * Constructs a Memento with the desired model.
	 */
	public Memento(MementoState model) {
		this.model = model;
		backup = model.getBackup();
	}

	/**
	 * Sets the classes field of the model to the backup. This restores the model to
	 * the previous state.
	 */
	public void restore() {
		model.setClasses(backup);
	}
}