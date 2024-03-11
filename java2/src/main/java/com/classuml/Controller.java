package com.classuml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public class Controller extends Application {

    private Model guiModel = new Model();
    StackPane stackPane = new StackPane();
    FXMLLoader loader;
    Parent root;
    Scene scene;
    private String saveName;

    private Stage mainWindow; // Define mainWindow as a variable of type Stage

    public void setMainWindow(Stage mainWindow) {
        this.mainWindow = mainWindow;
    }

    @FXML
    private MenuItem MIAbout;

    @FXML
    private MenuItem MIEditFieldType;

    @FXML
    private MenuItem MIEditMethodType;

    @FXML
    private MenuItem MIAddClass;

    @FXML
    private MenuItem MIAddField;

    @FXML
    private MenuItem MIAddMethod;

    @FXML
    private MenuItem MIAddParam;

    @FXML
    private MenuItem MIAddParamList;

    @FXML
    private MenuItem MICloseDiagram;

    @FXML
    private TextArea MIListAll;

    @FXML
    private TextArea MIRelText;

    @FXML
    private MenuItem MIDeleteClass;

    @FXML
    private MenuItem MIDeleteField;

    @FXML
    private MenuItem MIDeleteMethod;

    @FXML
    private MenuItem MIDeleteParam;

    @FXML
    private MenuItem MIClearParams;

    @FXML
    private MenuItem MIDeleteRel;

    @FXML
    private MenuItem MIEditClass;

    @FXML
    private MenuItem MIEditField;

    @FXML
    private MenuItem MIEditMethod;

    @FXML
    private MenuItem MINewDiagram;

    @FXML
    private MenuItem MIEditRel;

    @FXML
    private MenuItem MIOpenDiagram;

    @FXML
    private MenuItem MIPreferences;

    @FXML
    private MenuItem MIQuit;

    @FXML
    private MenuItem MIRedo;

    @FXML
    private MenuItem MISave;

    @FXML
    private MenuItem MISaveAs;

    @FXML
    private MenuItem MISetClassRel;

    @FXML
    private MenuItem MIShowHelp;

    @FXML
    private MenuItem MIUndo;


    @Override
    public void start(Stage primaryStage) throws Exception {
        loader = new FXMLLoader(getClass().getResource("fxml/mainwindow.fxml"));
        root = loader.load();
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("UML Reader");
        primaryStage.show();
    }

    @FXML
    void clickMIAbout(ActionEvent event) {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(mainWindow);
        VBox dialogVbox = new VBox(20);
        String filePath = "README.md";
        File currentDirectoryFile = new File(filePath);
        if (!currentDirectoryFile.exists()) {
            filePath = "../" + filePath;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            dialogVbox.getChildren().add(new Text(sb.toString()));
        } catch (InvalidPathException e) {
            e.printStackTrace();
            Platform.exit();
        } catch (IOException e) {
            System.out.println("You must put the readme file in this directory: " + Paths.get("").toAbsolutePath().toString());
            e.printStackTrace();
            Platform.exit();
        }
        
        //dialogVbox.getChildren().add(new Text("This is a Dialog"));
        Scene dialogScene = new Scene(dialogVbox, 620, 450);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    @FXML
    void clickMIAddClass(ActionEvent event) {
        String className = "";
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add new class");
        dialog.setHeaderText("Enter the class name:");
        dialog.setContentText("Class name:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            className = result.get();
            String temp = guiModel.addClass(className);
            if(temp.equals("Class with that name already exists!")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Class already exists");
                alert.setContentText("The class name \"" + className + "\" already exists. Please enter a different class name.");
                alert.showAndWait();
            }

        }
        else {
            return;
        }

        MIListAll.clear();
        MIListAll.setText(guiModel.listAllClasses());
    }

    @FXML
    void clickMIAddField(ActionEvent event) {
        String className = "";
        String fieldName = "";
        String fieldType = "";
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add Field");
        dialog.setHeaderText("Enter the Class Name For The Field:");
        dialog.setContentText("Class Name:");

        Optional<String> result = dialog.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result.isPresent()) {
            className = result.get();
        }

        TextInputDialog dialog2 = new TextInputDialog();
        dialog2.setTitle("Add Field");
        dialog2.setHeaderText("Enter the Field Name:");
        dialog2.setContentText("Field Name:");

        Optional<String> result2 = dialog2.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result2.isPresent()) {
            fieldName = result2.get();
        }

        TextInputDialog dialog3 = new TextInputDialog();
        dialog3.setTitle("Add Field");
        dialog3.setHeaderText("Enter the Type of the Field:");
        dialog3.setContentText("Field Type:");

        Optional<String> result3 = dialog3.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result3.isPresent()) {
            fieldType = result3.get();
        }
        int temp = guiModel.addField(className, fieldName, fieldType);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (temp == 0){
            alert.setTitle("Error");
            alert.setHeaderText("Field name already exists in this class");
            alert.setContentText("The field name \"" + fieldName + "\" already exists in class \"" + className + "\".\nOR The class name \"" + className + "\" does not exist.");
            alert.showAndWait();
        }
        MIListAll.clear();
        MIListAll.setText(guiModel.listAllClasses());
    }

    @FXML
    void clickMIAddMethod(ActionEvent event) {
        String className = "";
        String methodName = "";
        String methodType = "";
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add Method");
        dialog.setHeaderText("Enter the Class Name For The Method:");
        dialog.setContentText("Class Name:");

        Optional<String> result = dialog.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result.isPresent()) {
            className = result.get();
        }

        TextInputDialog dialog2 = new TextInputDialog();
        dialog2.setTitle("Add Method");
        dialog2.setHeaderText("Enter the Method Name:");
        dialog2.setContentText("Method Name:");

        Optional<String> result2 = dialog2.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result2.isPresent()) {
            methodName = result2.get();
        }

        TextInputDialog dialog3 = new TextInputDialog();
        dialog3.setTitle("Add Method");
        dialog3.setHeaderText("Enter the Method Type:");
        dialog3.setContentText("Method Type:");

        Optional<String> result3 = dialog3.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result3.isPresent()) {
            methodType = result3.get();
        }

        ArrayList<methods> methodcontainer = guiModel.getClassContainer().getClassBase(className).getClassMethods();
        for(methods meth : methodcontainer){
            if (meth.getName().equals(methodName)){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Method already exists");
                alert.setContentText("The method name \"" + methodName + "\" already exists. Please enter a different method name.");
                alert.showAndWait();
            }
        }
        boolean temp = guiModel.addMethod(className, methodName, methodType);
        if (!temp){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Class already exists");
            alert.setContentText("The class name \"" + className + "\" already exists. Please enter a different class name.");
            alert.showAndWait();
        }
        MIListAll.clear();
        MIListAll.setText(guiModel.listAllClasses());
    }

    @FXML
    void clickMIAddParam(ActionEvent event) {
        String className = "";
        String methodName = "";
        String paramName = "";
        String paramType = "";
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add Parameter");
        dialog.setHeaderText("Enter the Class Name For The Parameter:");
        dialog.setContentText("Class Name:");

        Optional<String> result = dialog.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result.isPresent()) {
            className = result.get();
        }

        TextInputDialog dialog2 = new TextInputDialog();
        dialog2.setTitle("Add Parameter");
        dialog2.setHeaderText("Enter the Method Name:");
        dialog2.setContentText("Method Name:");

        Optional<String> result2 = dialog2.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result2.isPresent()) {
            methodName = result2.get();
        }

        TextInputDialog dialog3 = new TextInputDialog();
        dialog3.setTitle("Add Parameter");
        dialog3.setHeaderText("Enter the Parameter Name:");
        dialog3.setContentText("Parameter Name:");

        Optional<String> result3 = dialog3.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result3.isPresent()) {
            paramName = result3.get();
        }

        TextInputDialog dialog4 = new TextInputDialog();
        dialog4.setTitle("Add Parameter");
        dialog4.setHeaderText("Enter the Parameter Type:");
        dialog4.setContentText("Parameter Type:");

        Optional<String> result4 = dialog4.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result4.isPresent()) {
            paramType = result4.get();
        }

        int test = guiModel.addParams(className, methodName, paramName, paramType);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        switch (test){
            case 0:
                alert.setTitle("Error");
                alert.setHeaderText("Class does not exist");
                alert.setContentText("The class name \"" + className + "\" does not exist.");
                alert.showAndWait();
                break;

            case 1:
                alert.setTitle("Error");
                alert.setHeaderText("Method does not exist");
                alert.setContentText("The method name \"" + methodName + "\" does not exist.");
                alert.showAndWait();
                break;

            case 2:
                alert.setTitle("Error");
                alert.setHeaderText("Param already exists");
                alert.setContentText("The param name \"" + paramName + "\" already exists.");
                alert.showAndWait();
                break;
        }
        MIListAll.clear();
        MIListAll.setText(guiModel.listAllClasses());
    }

    @FXML
    void clickMIAddParamList(ActionEvent event) {
        String className = "";
        String methodName = "";
        List<String> paramNames = new ArrayList<>();
        List<String> paramTypes = new ArrayList<>();
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add Parameter(s)");
        dialog.setHeaderText("Enter the Class Name For The Parameter(s):");
        dialog.setContentText("Class Name:");

        // Display the dialog and wait for the user to enter a value
        Optional<String> result = dialog.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result.isPresent()) {
            className = result.get();
            // Save the source class name
        }

        TextInputDialog dialog2 = new TextInputDialog();
        dialog2.setTitle("Add Parameter(s)");
        dialog2.setHeaderText("Enter the Method Name:");
        dialog2.setContentText("Method Name:");

        // Display the dialog and wait for the user to enter a value
        Optional<String> result2 = dialog2.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result2.isPresent()) {
            methodName = result2.get();
            // Save the source class name
        }

        TextInputDialog dialog3 = new TextInputDialog();
        dialog3.setTitle("Add Parameter(s)");
        dialog3.setHeaderText("Enter the Parameter Name(s) separated by spaces:");
        dialog3.setContentText("Parameter Name(s):");

        // Display the dialog and wait for the user to enter a value
        Optional<String> result3 = dialog3.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result3.isPresent()) {
            String[] paramNameArray = result3.get().split(" ");
            paramNames = Arrays.asList(paramNameArray);
            // Save the parameter names
        }

        TextInputDialog dialog4 = new TextInputDialog();
        dialog4.setTitle("Add Parameter(s)");
        dialog4.setHeaderText("Enter the Parameter Type(s) separated by spaces:");
        dialog4.setContentText("Parameter Type(s):");

        // Display the dialog and wait for the user to enter a value
        Optional<String> result4 = dialog4.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result4.isPresent()) {
            String[] paramTypeArray = result4.get().split(" ");
            paramTypes = Arrays.asList(paramTypeArray);
            // Save the parameter types
        }

        // Check if the number of inputs and outputs is equal
        if (paramNames.size() != paramTypes.size()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Number of inputs and outputs must be equal");
            alert.showAndWait();
            return;
        }

        // Add the parameters to the guiModel object
        for (int i = 0; i < paramNames.size(); i++) {
            int test = guiModel.addParams(className, methodName, paramNames.get(i), paramTypes.get(i));
            Alert alert = new Alert(Alert.AlertType.ERROR);
            switch (test){
                case 0:
                    alert.setTitle("Error");
                    alert.setHeaderText("Class does not exist");
                    alert.setContentText("The class name \"" + className + "\" does not exist.");
                    alert.showAndWait();
                    break;

                case 1:
                    alert.setTitle("Error");
                    alert.setHeaderText("Method does not exist");
                    alert.setContentText("The method name \"" + methodName + "\" does not exist.");
                    alert.showAndWait();
                    break;

                case 2:
                    alert.setTitle("Error");
                    alert.setHeaderText("Param already exists");
                    alert.setContentText("The param name \"" + paramNames.get(i) + "\" already exists.");
                    alert.showAndWait();
                    break;
            }
        }
        MIListAll.clear();
        MIListAll.setText(guiModel.listAllClasses());
    }

    @FXML
    void clickMICloseDiagram(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void clickMIDeleteClass(ActionEvent event) {
        String className = "";
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Delete Class");
        dialog.setHeaderText("Enter the Class Name:");
        dialog.setContentText("Class Name:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            className = result.get();
        } else {
            return;
        }

        // Delete the class and its relationships
        String temp = guiModel.removeClass(className);
        if (temp.equals("No class matching that name was found.")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Class does not exist");
            alert.setContentText("The class name \"" + className + "\" does not exist.");
            alert.showAndWait();
        }

        MIListAll.clear();
        MIListAll.setText(guiModel.listAllClasses());

        MIRelText.setText(new String());
        for (ClassBase cls : guiModel.getClassContainer().getContainer()) {
            if (!textAreaContainsString(MIRelText, modelListOneClassRelationship(cls.getName()))) {
                MIRelText.appendText((modelListOneClassRelationship(cls.getName())));
            }
        }
    }
    

    @FXML
    void clickMIDeleteField(ActionEvent event) {
        String className = "";
        String fieldName = "";
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Delete Field");
        dialog.setHeaderText("Enter the Class Name For The Field:");
        dialog.setContentText("Class Name:");

        Optional<String> result = dialog.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result.isPresent()) {
            className = result.get();
        }

        TextInputDialog dialog2 = new TextInputDialog();
        dialog2.setTitle("Delete Field");
        dialog2.setHeaderText("Enter the Field Name:");
        dialog2.setContentText("Field Name:");

        Optional<String> result2 = dialog2.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result2.isPresent()) {
            fieldName = result2.get();
        }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        int temp = guiModel.removeField(className, fieldName);
        switch (temp){
            case 0:
                alert.setTitle("Error");
                alert.setHeaderText("Class does not exist");
                alert.setContentText("The class name \"" + className + "\" does not exist.");
                alert.showAndWait();
                break;

            case 2:
                alert.setTitle("Error");
                alert.setHeaderText("Field does not exist");
                alert.setContentText("The field name \"" + fieldName + "\" does not exist.");
                alert.showAndWait();
                break;
        }
        MIListAll.clear();
        MIListAll.setText(guiModel.listAllClasses());
    }

    @FXML
    void clickMIDeleteMethod(ActionEvent event) {
        String className = "";
        String methodName = "";
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Delte Method");
        dialog.setHeaderText("Enter the Class Name Of The Method:");
        dialog.setContentText("Class Name:");

        Optional<String> result = dialog.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result.isPresent()) {
            className = result.get();
        }

        TextInputDialog dialog2 = new TextInputDialog();
        dialog2.setTitle("Delete Method");
        dialog2.setHeaderText("Enter the Method Name:");
        dialog2.setContentText("Method Name:");

        Optional<String> result2 = dialog2.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result2.isPresent()) {
            methodName = result2.get();
        }
        int temp = guiModel.removeMethod(className, methodName);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (temp == 0){
            alert.setTitle("Error");
            alert.setHeaderText("Class does not exist");
            alert.setContentText("The class name \"" + className + "\" does not exist.");
            alert.showAndWait();
        }
        if (temp == 2){
            alert.setTitle("Error");
            alert.setHeaderText("Method does not exist");
            alert.setContentText("The method name \"" + methodName + "\" does not exist.");
            alert.showAndWait();
        }
        MIListAll.clear();
        MIListAll.setText(guiModel.listAllClasses());
    }

    @FXML
    void clickMIDeleteParam(ActionEvent event) {
        String className = "";
        String methodName = "";
        String paramName = "";
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Delete Parameter");
        dialog.setHeaderText("Enter the Class Name For The Parameter:");
        dialog.setContentText("Class Name:");

        Optional<String> result = dialog.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result.isPresent()) {
            className = result.get();
        }

        TextInputDialog dialog2 = new TextInputDialog();
        dialog2.setTitle("Delete Parameter");
        dialog2.setHeaderText("Enter the Method Name:");
        dialog2.setContentText("Method Name:");

        Optional<String> result2 = dialog2.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result2.isPresent()) {
            methodName = result2.get();
        }

        TextInputDialog dialog3 = new TextInputDialog();
        dialog3.setTitle("Delete Parameter");
        dialog3.setHeaderText("Enter the Parameter Name:");
        dialog3.setContentText("Parameter Name:");

        Optional<String> result3 = dialog3.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result3.isPresent()) {
            paramName = result3.get();
        }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        int temp = guiModel.removeParam(className, methodName, paramName);
        if (temp == 0){
            alert.setTitle("Error");
            alert.setHeaderText("Class does not exist");
            alert.setContentText("The class name \"" + className + "\" does not exist.");
            alert.showAndWait();
        }
        if (temp == 1){
            alert.setTitle("Error");
            alert.setHeaderText("Method does not exist");
            alert.setContentText("The method name \"" + methodName + "\" does not exist.");
            alert.showAndWait();
        }
        if (temp == 2){
            alert.setTitle("Error");
            alert.setHeaderText("Parameter does not exist");
            alert.setContentText("The parameter name \"" + paramName + "\" does not exist.");
            alert.showAndWait();
        }
        MIListAll.clear();
        MIListAll.setText(guiModel.listAllClasses());
    }

    @FXML
    void clickMIClearParams(ActionEvent event){
        String className = "";
        String methodName = "";
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Delete Parameter");
        dialog.setHeaderText("Enter the Class Name For The Parameter:");
        dialog.setContentText("Class Name:");

        Optional<String> result = dialog.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result.isPresent()) {
            className = result.get();
        }

        TextInputDialog dialog2 = new TextInputDialog();
        dialog2.setTitle("Delete Parameter");
        dialog2.setHeaderText("Enter the Method Name:");
        dialog2.setContentText("Method Name:");

        Optional<String> result2 = dialog2.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result2.isPresent()) {
            methodName = result2.get();
        }
        int temp = guiModel.clearParams(className, methodName);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (temp == 0){
            alert.setTitle("Error");
            alert.setHeaderText("Class does not exist");
            alert.setContentText("The class name \"" + className + "\" does not exist.");
            alert.showAndWait();
        }
        if (temp == 1){
            alert.setTitle("Error");
            alert.setHeaderText("Method does not exist");
            alert.setContentText("The method name \"" + methodName + "\" does not exist.");
            alert.showAndWait();
        }
        MIListAll.clear();
        MIListAll.setText(guiModel.listAllClasses());
    }

    @FXML
    void clickMIDeleteRel(ActionEvent event) {
        String srcClass = null;
        String destClass = null;

        //Source Input
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Delete Class Relationship");
        dialog.setHeaderText("Enter the Source Class Name:");
        dialog.setContentText("Class name:");

        Optional<String> result = dialog.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result.isPresent()) {
            srcClass = result.get();
        }


        //Dest Input
        TextInputDialog dialog2 = new TextInputDialog();
        dialog2.setTitle("Delete Class Relationship");
        dialog2.setHeaderText("Enter the Destination Class Name:");
        dialog2.setContentText("Class Name:");
        Optional<String> result2 = dialog2.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result2.isPresent()) {
            destClass = result2.get();
        }
        int temp = guiModel.removeRelationship(srcClass, destClass);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (temp == 2){
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Relationship");
            alert.setContentText("No such relationship with that source and destination");
            alert.showAndWait();
        }

        MIRelText.setText(new String());
        for (Relationship rel : guiModel.getRelationshipContainer().getAllRelationships()){
            if (!textAreaContainsString(MIRelText, modelListOneClassRelationship(rel.getSourceClass()))) {
                MIRelText.appendText((modelListOneClassRelationship(rel.getSourceClass())));
            }
        }
    }

    @FXML
    void clickMIEditClass(ActionEvent event) {
        String oldClassName = "";
        String newClassName = "";
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Edit Class Name");
        dialog.setHeaderText("Enter The Old Class Name:");
        dialog.setContentText("Class Name:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            oldClassName = result.get();
            // Save the old class name
        } else {
            return;
        }

        TextInputDialog dialog2 = new TextInputDialog();
        dialog2.setTitle("Edit Class Name");
        dialog2.setHeaderText("Enter The New Class Name:");
        dialog2.setContentText("Class Name:");

        Optional<String> result2 = dialog2.showAndWait();
        if (result2.isPresent()) {
            newClassName = result2.get();
            // Save the new class name
        } else {
            return;
        }

        // Rename the class
        String temp = guiModel.renameClass(oldClassName, newClassName);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (temp.equals("A class with your new name already exists!")){
            alert.setTitle("Error");
            alert.setHeaderText("Invalid New Class Name");
            alert.setContentText("The class name \"" + newClassName  + "\" already exists.");
            alert.showAndWait();
        }
        if (temp.equals("You can't rename a class that doesn't exist!")){
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Old Class Name");
            alert.setContentText("The class name \"" + oldClassName + "\" does not exist.");
            alert.showAndWait();
        }

        // Update the class list
        MIListAll.clear();
        MIListAll.setText(guiModel.listAllClasses());

        // Update the relationship list
        MIRelText.setText(new String());
        for (Relationship rel : guiModel.getRelationshipContainer().getAllRelationships()) {
            if (rel.getSourceClass().equals(newClassName) || rel.getDestClass().equals(newClassName)) {
                MIRelText.appendText((modelListOneClassRelationship(newClassName)));
            } else if (!textAreaContainsString(MIRelText, modelListOneClassRelationship(rel.getSourceClass()))) {
                MIRelText.appendText((modelListOneClassRelationship(rel.getSourceClass())));
            }
        }
    }

    @FXML
    void clickMIEditField(ActionEvent event) {
        String className = "";
        String oldFieldName = "";
        String newFieldName = "";
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Edit Field");
        dialog.setHeaderText("Enter the Class Name:");
        dialog.setContentText("Class Name:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            className = result.get();
        } else {
            return;
        }

        TextInputDialog dialog2 = new TextInputDialog();
        dialog2.setTitle("Edit Field");
        dialog2.setHeaderText("Enter the Old Field Name:");
        dialog2.setContentText("Field Name:");

        Optional<String> result2 = dialog2.showAndWait();
        if (result2.isPresent()) {
            oldFieldName = result2.get();
        } else {
            return;
        }

        TextInputDialog dialog3 = new TextInputDialog();
        dialog3.setTitle("Edit Field");
        dialog3.setHeaderText("Enter the New Field Name:");
        dialog3.setContentText("Field Name:");

        Optional<String> result3 = dialog3.showAndWait();
        if (result3.isPresent()) {
            newFieldName = result3.get();
        } else {
            return;
        }

        int temp = guiModel.renameField(className, oldFieldName, newFieldName);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        switch (temp){
            case 0:
                alert.setTitle("Error");
                alert.setHeaderText("Class does not exist");
                alert.setContentText("The class name \"" + className + "\" does not exist.");
                alert.showAndWait();
                break;

            case 1:
                alert.setTitle("Error");
                alert.setHeaderText("Invalid old field name");
                alert.setContentText("The field name \"" + oldFieldName + "\" does not exist.");
                alert.showAndWait();
                break;
            case 2:
                alert.setTitle("Error");
                alert.setHeaderText("Invalid new field name");
                alert.setContentText("The field name \"" + newFieldName + "\" already exists.");
                alert.showAndWait();
                break;
        }

        MIListAll.clear();
        MIListAll.setText(guiModel.listAllClasses());

        MIRelText.setText(new String());
        for (Relationship rel : guiModel.getRelationshipContainer().getAllRelationships()) {
            if (rel.getSourceClass().equals(className)) {
                if (!textAreaContainsString(MIRelText, modelListOneClassRelationship(rel.getSourceClass()))) {
                    MIRelText.appendText((modelListOneClassRelationship(rel.getSourceClass())));
                }
            } else if (rel.getDestClass().equals(className)) {
                if (!textAreaContainsString(MIRelText, modelListOneClassRelationship(rel.getDestClass()))) {
                    MIRelText.appendText((modelListOneClassRelationship(rel.getDestClass())));
                }
            }
        }
    }

    @FXML
    void clickMIEditMethod(ActionEvent event) {
        String className = "";
        String methodName = "";
        String newMethodName = "";
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Edit Method");
        dialog.setHeaderText("Enter the Class Name For The Method:");
        dialog.setContentText("Class Name:");

        Optional<String> result = dialog.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result.isPresent()) {
            className = result.get();
        }

        TextInputDialog dialog2 = new TextInputDialog();
        dialog2.setTitle("Edit Method");
        dialog2.setHeaderText("Enter the Old Method Name:");
        dialog2.setContentText("Old Method Name:");

        Optional<String> result2 = dialog2.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result2.isPresent()) {
            methodName = result2.get();
        }

        TextInputDialog dialog3 = new TextInputDialog();
        dialog3.setTitle("Edit Method");
        dialog3.setHeaderText("Enter the New Method Name:");
        dialog3.setContentText("New Method Name:");

        Optional<String> result3 = dialog3.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result3.isPresent()) {
            newMethodName = result3.get();
        }

        int temp = guiModel.renameMethod(className, methodName, newMethodName);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (temp == 0){
            alert.setTitle("Error");
            alert.setHeaderText("Class does not exist");
            alert.setContentText("The class name \"" + className + "\" does not exist.");
            alert.showAndWait();
        }
        if (temp == 2){
            alert.setTitle("Error");
            alert.setHeaderText("Old method name does not exist");
            alert.setContentText("The method name \"" + methodName + "\" does not exist.");
            alert.showAndWait();
        }
        MIListAll.clear();
        MIListAll.setText(guiModel.listAllClasses());
    }
    

    @FXML
    void clickMINewDiagram(ActionEvent event) {
        guiModel.getClassContainer().getContainer().clear();
        guiModel.getRelationshipContainer().getAllRelationships().clear();
        MIListAll.clear();
        MIListAll.setText(guiModel.listAllClasses());
        saveName = null;
        MIRelText.setText(new String());
        for (Relationship rel : guiModel.getRelationshipContainer().getAllRelationships()){
            if (!textAreaContainsString(MIRelText, modelListOneClassRelationship(rel.getSourceClass()))) {
                MIRelText.appendText((modelListOneClassRelationship(rel.getSourceClass())));
            }
        }
    }

    @FXML
    void clickMIOpenDiagram(ActionEvent event) {
        String loadName = "";
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Load File");
        dialog.setHeaderText("Enter the JSON File Name:");
        dialog.setContentText("File name:");

        Optional<String> result = dialog.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result.isPresent()) {
            loadName = result.get();
        }
        saveName = loadName;
        guiModel.load(loadName, "gui");
        MIListAll.clear();
        MIListAll.setText(guiModel.listAllClasses());
        MIRelText.setText(new String());
        for (Relationship rel : guiModel.getRelationshipContainer().getAllRelationships()){
            if (!textAreaContainsString(MIRelText, modelListOneClassRelationship(rel.getSourceClass()))) {
                MIRelText.appendText((modelListOneClassRelationship(rel.getSourceClass())));
            }
        }
    }

    @FXML
    void clickMIPreferences(ActionEvent event) {

    }

    @FXML
    void clickMIQuit(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void clickMIRedo(ActionEvent event) {

    }

    @FXML
    void clickMISave(ActionEvent event) {
        if(saveName == null){
            String name = "saveUml";
            TextInputDialog saveDialog = new TextInputDialog();
            saveDialog.setTitle("Saving Class UML");
            saveDialog.setHeaderText("Enter the Save File Name (default saveUML):");
            saveDialog.setContentText("Save File Name:");
    
            Optional<String> result = saveDialog.showAndWait();
    
            // If the user clicked OK and entered a value, save it
            if (result.isPresent()) {
                name = result.get();
            }
            //saveUML.save(guiModel.getClassContainer(), guiModel.getRelationshipContainer(), name);
            guiModel.save(name);
            saveName = name;
        }
        else {
            guiModel.save(saveName);
        }
    }

    @FXML
    void clickMISaveAs(ActionEvent event) {
        String name = "saveUml";
        TextInputDialog saveDialog = new TextInputDialog();
        saveDialog.setTitle("Saving Class UML");
        saveDialog.setHeaderText("Enter the Save File Name (default saveUML):");
        saveDialog.setContentText("Save File Name:");

        Optional<String> result = saveDialog.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result.isPresent()) {
            name = result.get();
        }
        //saveUML.save(guiModel.getClassContainer(), guiModel.getRelationshipContainer(), name);
        guiModel.save(name);
        saveName = name;
    }

    @FXML
    void clickMISetClassRel(ActionEvent event) {
        String srcClass = null;
        String destClass = null;
        String relType = null;

        //Source Input
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Set Class Relationship");
        dialog.setHeaderText("Enter the Source Class Name:");
        dialog.setContentText("Class name:");

        Optional<String> result = dialog.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result.isPresent()) {
            srcClass = result.get();
        }


        //Dest Input
        TextInputDialog dialog2 = new TextInputDialog();
        dialog2.setTitle("Set Class Relationship");
        dialog2.setHeaderText("Enter the Destination Class Name:");
        dialog2.setContentText("Class Name:");
        Optional<String> result2 = dialog2.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result2.isPresent()) {
            destClass = result2.get();
        }

        //Type Input
        TextInputDialog dialog3 = new TextInputDialog();
        dialog3.setTitle("Set Class Relationship Type");
        dialog3.setHeaderText("Enter the relationship type:");
        dialog3.setContentText("Type name:");

        Optional<String> result3 = dialog3.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result3.isPresent()) {
            relType = result3.get();
        }
        int temp = guiModel.addRelationship(srcClass, destClass, relType);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (temp == 1){
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Source Class");
            alert.setContentText("The class name \"" + srcClass + "\" does not exist.");
            alert.showAndWait();
        }
        if (temp == 2){
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Destination Class");
            alert.setContentText("The class name \"" + destClass + "\" does not exist.");
            alert.showAndWait();
        }
        if (temp == 0){
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Relationship");
            alert.setContentText("Relationship with that source and destination already exists or relationship type is not one of the choices.\nValid Types: Aggregation, Compostion, Inheritance, Realization");
            alert.showAndWait();
        }
        MIRelText.setText(new String());
        for (Relationship rel : guiModel.getRelationshipContainer().getAllRelationships()){
            if (!textAreaContainsString(MIRelText, modelListOneClassRelationship(rel.getSourceClass()))) {
                MIRelText.appendText((modelListOneClassRelationship(rel.getSourceClass())));
            }
        }
    }

    @FXML
    void clickMIEditFieldType(ActionEvent Event){
        String className = "";
        String oldFieldName = "";
        String newFieldName = "";
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Edit Field");
        dialog.setHeaderText("Enter the Class Name:");
        dialog.setContentText("Class Name:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            className = result.get();
        } else {
            return;
        }

        TextInputDialog dialog2 = new TextInputDialog();
        dialog2.setTitle("Edit Field");
        dialog2.setHeaderText("Enter the Field Name:");
        dialog2.setContentText("Field Name:");

        Optional<String> result2 = dialog2.showAndWait();
        if (result2.isPresent()) {
            oldFieldName = result2.get();
        } else {
            return;
        }

        TextInputDialog dialog3 = new TextInputDialog();
        dialog3.setTitle("Edit Field");
        dialog3.setHeaderText("Enter the New Field Type:");
        dialog3.setContentText("Field Type:");

        Optional<String> result3 = dialog3.showAndWait();
        if (result3.isPresent()) {
            newFieldName = result3.get();
        } else {
            return;
        }

        int temp = guiModel.changeFieldType(className, oldFieldName, newFieldName);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        switch (temp){
            case 0:
                alert.setTitle("Error");
                alert.setHeaderText("Class does not exist");
                alert.setContentText("The class name \"" + className + "\" does not exist.");
                alert.showAndWait();
                break;

            case 1:
                alert.setTitle("Error");
                alert.setHeaderText("Invalid old field name");
                alert.setContentText("The field name \"" + oldFieldName + "\" does not exist.");
                alert.showAndWait();
                break;
            case 2:
                alert.setTitle("Error");
                alert.setHeaderText("Invalid new field name");
                alert.setContentText("The field type \"" + newFieldName + "\" already exists.");
                alert.showAndWait();
                break;
        }

        MIListAll.clear();
        MIListAll.setText(guiModel.listAllClasses());

        MIRelText.setText(new String());
        for (Relationship rel : guiModel.getRelationshipContainer().getAllRelationships()) {
            if (rel.getSourceClass().equals(className)) {
                if (!textAreaContainsString(MIRelText, modelListOneClassRelationship(rel.getSourceClass()))) {
                    MIRelText.appendText((modelListOneClassRelationship(rel.getSourceClass())));
                }
            } else if (rel.getDestClass().equals(className)) {
                if (!textAreaContainsString(MIRelText, modelListOneClassRelationship(rel.getDestClass()))) {
                    MIRelText.appendText((modelListOneClassRelationship(rel.getDestClass())));
                }
            }
        }
    }

    @FXML
    void clickMIEditMethodType(ActionEvent Event){
        String className = "";
        String methodName = "";
        String newMethodName = "";
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Edit Method");
        dialog.setHeaderText("Enter the Class Name For The Method:");
        dialog.setContentText("Class Name:");

        Optional<String> result = dialog.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result.isPresent()) {
            className = result.get();
        }

        TextInputDialog dialog2 = new TextInputDialog();
        dialog2.setTitle("Edit Method");
        dialog2.setHeaderText("Enter the Method Name:");
        dialog2.setContentText("Method Name:");

        Optional<String> result2 = dialog2.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result2.isPresent()) {
            methodName = result2.get();
        }

        TextInputDialog dialog3 = new TextInputDialog();
        dialog3.setTitle("Edit Method");
        dialog3.setHeaderText("Enter the new Method Type:");
        dialog3.setContentText("New Method Type:");

        Optional<String> result3 = dialog3.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result3.isPresent()) {
            newMethodName = result3.get();
        }

        int temp = guiModel.changeMethodType(className, methodName, newMethodName);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (temp == 0){
            alert.setTitle("Error");
            alert.setHeaderText("Class does not exist");
            alert.setContentText("The class name \"" + className + "\" does not exist.");
            alert.showAndWait();
        }
        if (temp == 2){
            alert.setTitle("Error");
            alert.setHeaderText("Method name does not exist");
            alert.setContentText("The method name \"" + methodName + "\" does not exist.");
            alert.showAndWait();
        }
        MIListAll.clear();
        MIListAll.setText(guiModel.listAllClasses());
    }

    public boolean textAreaContainsString(TextArea textArea, String searchString) {
        String text = textArea.getText();
        return text.contains(searchString);
    }

    public String modelListOneClassRelationship (String name) {
        //used to tell if the class name exists and whether or not is has relationships
        boolean isIn = false;
        boolean isRel = false;
        String retStringRel = "";
        //loop through classContainer to check if the name exists
        for(ClassBase cls : guiModel.getClassContainer().getContainer()){
            if(cls.getName().equals(name)){
                isIn = true;
            }
        }

        //loop thorugh relContainer and print name, fromClass, toClass
        for(Relationship rel : guiModel.getRelationshipContainer().getAllRelationships()){
            if (rel.getSourceClass().equals(name) || rel.getDestClass().equals(name)){
                retStringRel = retStringRel.concat("Source Class: " + rel.getSourceClass() + "\nDestination Class: " + rel.getDestClass() + "\n" + "Relationship Type: " + rel.getType() + "\n");
                isRel = true;
            }
        }
        if (!isIn){
            return ("Class with that name does not exist.");
        }
        if (!isRel){
            return ("There are no relationships connected to this class.");
        }
        return retStringRel;
    }

    @FXML
    void clickMIShowHelp(ActionEvent event) {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(mainWindow);
        VBox dialogVbox = new VBox(20);
        String filePath = "HELP.md";
        File currentDirectoryFile = new File(filePath);
        if (!currentDirectoryFile.exists()) {
            filePath = "../" + filePath;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            dialogVbox.getChildren().add(new Text(sb.toString()));
        } catch (InvalidPathException e) {
            e.printStackTrace();
            Platform.exit();
        } catch (IOException e) {
            System.out.println("You must put the readme file in this directory: " + Paths.get("").toAbsolutePath().toString());
            e.printStackTrace();
            Platform.exit();
        }
        
        //dialogVbox.getChildren().add(new Text("This is a Dialog"));
        Scene dialogScene = new Scene(dialogVbox, 800, 400);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    @FXML
    void clickMIEditRel(){
        String srcClass = null;
        String destClass = null;
        String relType = null;

        //Source Input
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Edit Relationship Type");
        dialog.setHeaderText("Enter the Source Class Name:");
        dialog.setContentText("Class name:");

        Optional<String> result = dialog.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result.isPresent()) {
            srcClass = result.get();
        }


        //Dest Input
        TextInputDialog dialog2 = new TextInputDialog();
        dialog2.setTitle("Edit Relationship Type");
        dialog2.setHeaderText("Enter the Destination Class Name:");
        dialog2.setContentText("Class Name:");
        Optional<String> result2 = dialog2.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result2.isPresent()) {
            destClass = result2.get();
        }

        //Type Input
        TextInputDialog dialog3 = new TextInputDialog();
        dialog3.setTitle("Edit Relationship Type");
        dialog3.setHeaderText("Enter the new relationship type:");
        dialog3.setContentText("New Type name:");

        Optional<String> result3 = dialog3.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result3.isPresent()) {
            relType = result3.get();
        }
        int temp = guiModel.editRelationship(srcClass, destClass, relType);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (temp == 1){
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Source Class");
            alert.setContentText("The class name \"" + srcClass + "\" does not exist.");
            alert.showAndWait();
        }
        if (temp == 2){
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Destination Class");
            alert.setContentText("The class name \"" + destClass + "\" does not exist.");
            alert.showAndWait();
        }
        if (temp == 0){
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Relationship");
            alert.setContentText("Relationship with that source and destination already exists or relationship type is not one of the choices.\nValid Types: Aggregation, Compostion, Inheritence, Realization");
            alert.showAndWait();
        }
        MIRelText.setText(new String());
        for (Relationship rel : guiModel.getRelationshipContainer().getAllRelationships()){
            if (!textAreaContainsString(MIRelText, modelListOneClassRelationship(rel.getSourceClass()))) {
                MIRelText.appendText((modelListOneClassRelationship(rel.getSourceClass())));
            }
        }
    }

    @FXML
    void clickMIUndo(ActionEvent event) {

    }

}