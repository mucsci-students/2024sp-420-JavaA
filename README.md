# 2024sp-420-Java2
NRDS UML EDITOR

Overview:
The UML Editor, developed in Java, makes creating and changing UML diagrams easy. It lets users visually show how different software parts connect and work together, helping with designing, studying, and explaining software. Key functions include making, removing, and adjusting classes with their parts like attributes and methods, and the ability to save and load diagrams in JSON format.

To Run the Application: 

 git clone https://github.com/mucsci-students/2024sp-420-Java2.git

 from the java2 directory run "mvn clean install"

 from the java2 directory run "mvn clean package"

 from the java2 directory run "java -jar target/NRDS-1.0-SNAPSHOT-jar-with-dependencies.jar"

 To run the CLI use "java -jar target/NRDS-1.0-SNAPSHOT-jar-with-dependencies.jar -cli" 

For Testing: 

 Run tests by using "mvn clean test" from the java2 directory

Design Patterns Used

1. Model–view–controller - The program divides its components into models, views, and controllers, ensuring separation of concerns and easier maintenance.
2. Command - Both CLIController and GUIController use command objects to encapsulate actions such as adding, deleting, or editing elements, enabling decoupling of user interface events from actual operations.
3. Iterator - The model component uses iterators to traverse collections of elements. 
4. Memento - The program implements a memento pattern for undo/redo functionality, allowing the user to revert to previous states of the model.  
5. Factory -  In the App class, a factory method pattern is used to decide whether to create a CLIController or a GUIController based on user input, enabling dynamic instantiation of different types of controllers
6. Observer - The GUIController employs the observer pattern to handle events triggered by the graphical user interface components, facilitating communication and updates between the controller and the view.

Authors
Natnael Thehaye, Ram Gurung, David Marquez, Simeon Belayneh
