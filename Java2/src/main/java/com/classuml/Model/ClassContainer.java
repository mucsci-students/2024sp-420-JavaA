package com.classuml.Model;

import java.io.Serializable;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Set;

/**
 * The {@code ClassContainer} class serves as a storage for UML class definitions,
 * maintaining a sorted map of class names to their respective {@link ClassBase} objects.
 * This container supports operations to manage both the current state and a backup state of classes.
 * It implements {@link Serializable} to support serialization of its instances.
 */
public class ClassContainer implements Serializable {
    /**
     * Primary storage of class objects, mapped by their names.
     */
    private SortedMap<String, ClassBase> classes = new TreeMap<>();

    /**
     * Secondary storage serving as a backup of class objects, mapped by their names.
     */
    private SortedMap<String, ClassBase> backup = new TreeMap<>();

    /**
     * Constructs an empty {@code ClassContainer}.
     */
    public ClassContainer() {
    }

    /**
     * Constructs a {@code ClassContainer} with a specified map of classes.
     *
     * @param classesP The initial map of class names to their respective {@code ClassBase} objects.
     */
    public ClassContainer(SortedMap<String, ClassBase> classesP) {
        this.classes = classesP;
    }

    /**
     * Constructs a {@code ClassContainer} with specified maps for current classes and their backup.
     *
     * @param classesP The initial map of class names to their respective {@code ClassBase} objects.
     * @param backupP  The initial backup map of class names to their respective {@code ClassBase} objects.
     */
    public ClassContainer(SortedMap<String, ClassBase> classesP, SortedMap<String, ClassBase> backupP) {
        this.classes = classesP;
        this.backup = backupP;
    }

    /**
     * Sets the backup map of class objects.
     *
     * @param newBackup A map of class names to {@code ClassBase} objects to be set as the new backup.
     */
    public void setBackup(SortedMap<String, ClassBase> newBackup) {
        this.backup = newBackup;
    }

    /**
     * Retrieves the backup map of class objects.
     *
     * @return A sorted map of class names to {@code ClassBase} objects currently stored as backup.
     */
    public SortedMap<String, ClassBase> getBackup() {
        return this.backup;
    }

    /**
     * Retrieves the current map of class objects.
     *
     * @return A sorted map of class names to {@code ClassBase} objects.
     */
    public SortedMap<String, ClassBase> getClasses() {
        return this.classes;
    }

    /**
     * Sets the current map of class objects.
     *
     * @param newClasses A map of class names to {@code ClassBase} objects to replace the current classes.
     */
    public void setClasses(SortedMap<String, ClassBase> newClasses) {
        this.classes = newClasses;
    }

    /**
     * Retrieves a set of class names currently stored in the container.
     *
     * @return A set of strings representing the names of all stored class objects.
     */
    public Set<String> getClassNames() {
        return this.classes.keySet();
    }
}
