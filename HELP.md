Valid Types for Relationships: Aggregation, Compostion, Inheritence, Realization
Valid editTypes: Name, Type

add class <name> ' adds a class with given name.
remove class <name>, will remove a class with given name.
rename class <oldName> <newName>, will rename a class with oldName to newName.
add relationship <fromClass> <toClass>, will add a relationship between two classes.
add method <className> <methodName> <methodType>, adds a methodName of type methodType to className.
edit method <className> <methodName> <editType> <newName>, edits method according to editType in className and methodName.
remove method <className> <methodName>, removed method methodName from className.
add params <className> <methodName> <paramName> <paramType>, adds param of paramType to paramName in methodName in className.
remove param <className> <methodName> <paramName>, removes param with paramName from methodName in className.
clear params <className> <methodName>, clears all params from methodName in className.
add field <className> <fieldName> <fieldType>, adds a fieldName of fieldType to className.
edit field <className> <fieldName> <editType> <newName>, edits field according to editType in className and methodName.
remove field <className> <fieldName>, removed fieldName from className.
list one class <className>, will list all fields and methods of className.
list all classes, will list all classes and all of their fields and methods and types.
list one classes relationships <className>, will list all of the relationship the class belongs to.
save <fileName>, saves the class with file name fileName, if none is chosen it uses a default name.
load <fileName>, loads a file fileName.
exit, closes the program.
help, displays the help text.