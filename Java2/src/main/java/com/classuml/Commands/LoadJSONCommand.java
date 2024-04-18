package com.classuml.Commands;

import java.io.File;
import java.nio.file.Paths;
import java.util.SortedMap;

import com.classuml.Model.ClassBase;
import com.classuml.Model.MementoState;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LoadJSONCommand implements Command {

	// Stores the model that the command will be executed against.
	MementoState model;
	// Stores the file name of the file that will be loaded.
	String fileName;
	// Stores the file to be loaded this will be null if the cli controller is
	// calling this command
	File file;
	// Stores whether or not the state of the model has changed. True if the state
	// has changed false if not.
	boolean stateChange;

	/**
	 * Constructs a Load JSON command with the desired model and file name.
	 */
	public LoadJSONCommand(MementoState modelP, File fileP) {
		model = modelP;
		fileName = null;
		file = fileP;
		stateChange = false;
	}

	public LoadJSONCommand(MementoState modelP, String fileNameP) {
		model = modelP;
		fileName = fileNameP;
		file = null;
		stateChange = false;
	}

	/**
	 * Executes the command. This command loads JSON from a given file name. The
	 * JSON file must be valid.
	 * 
	 * @return A string that represents the outcome of the execution.
	 */
	@Override
	public String execute() {
		String response;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			if (file == null) {
				SortedMap<String, ClassBase> classeBase = objectMapper.readValue(Paths.get(fileName).toFile(),
						new TypeReference<SortedMap<String, ClassBase>>() {
						});
				model.setClasses(classeBase);
			} else {
				SortedMap<String, ClassBase> classes = objectMapper.readValue(file,
						new TypeReference<SortedMap<String, ClassBase>>() {
						});
				model.setClasses(classes);
			}
			response = "Your data has been loaded from a JSON file.";
			stateChange = true;
		} catch (Exception ex) {
			response = "Not a valid json file or the file does not exist.";
		}
		return response;
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
