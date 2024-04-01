package com.classuml.Model;
import java.io.Serializable;
import java.util.SortedMap;
import java.util.TreeMap;


public class ClassContainer implements Serializable{
	

	// stores a SortedMap of class objects.
	private SortedMap<String, ClassBase> classes = new TreeMap<String, ClassBase>();
	// stores a SortedMap of class objects one state change behind the classes field.
	private SortedMap<String, ClassBase> backup = new TreeMap<String, ClassBase>();
	
	/** Constructs an uninitialized instance of the object.
	 * 
	 */
	public ClassContainer () {
		
	}
	
	/** Constructs a classes map with a specified Map.
	 * @param classesP The classes map name.
	 */
	public ClassContainer(SortedMap<String, ClassBase> classesP) {
		classes = classesP;
	}
	
	/** Constructs a classes map with a specified Map for classes and backup.
	 * @param classesP The classes map name.
	 * @param backupP The classes backup map name.
	 */
	public ClassContainer(SortedMap<String, ClassBase> classesP, SortedMap<String, ClassBase> backupP) {
		classes = classesP;
		backup = backupP;
	}
	
	/** Sets the classes's backup map.
	 * @param newBackup A Map that stores all class objects.
	 */
	public void setBackup(SortedMap<String, ClassBase> newBackup) {
		backup = newBackup;
	}
	
	/** Gets the classes's backup map.
	 * @return A Map that stores all class objects.
	 */
	public SortedMap<String, ClassBase> getBackup() {
		return backup;
	}
	
	/** Gets the classes's map.
	 * @return A Map that stores all class objects.
	 */
	public SortedMap<String, ClassBase> getClasses() {
		return classes;
	}
	
	/** Sets the classes's map.
	 * @param newClasses A Map that stores all class objects.
	 */
	public void setClasses(SortedMap<String, ClassBase> newClasses) {
		classes = newClasses;
	}
}