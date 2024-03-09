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

    private Stage mainWindow; // Define mainWindow as a variable of type Stage

    public void setMainWindow(Stage mainWindow) {
        this.mainWindow = mainWindow;
    }

    @FXML
    private MenuItem MIAbout;

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
        // The following operation assumes you are running this program
        // from "/java2" directory with mvn clean javafx:run
        // and NOT by clicking the play button, which assumes you are in
        // the "/" (github root) directory.
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
        Scene dialogScene = new Scene(dialogVbox, 620, 400);
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
            guiModel.addClass(className);
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

        // Display the dialog and wait for the user to enter a value
        //Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        Optional<String> result = dialog.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result.isPresent()) {
            className = result.get();
            // Save the source class name
        }

        TextInputDialog dialog2 = new TextInputDialog();
        dialog2.setTitle("Add Field");
        dialog2.setHeaderText("Enter the Field Name:");
        dialog2.setContentText("Field Name:");

        // Display the dialog and wait for the user to enter a value
        //Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        Optional<String> result2 = dialog2.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result2.isPresent()) {
            fieldName = result2.get();
            // Save the source class name
        }

        TextInputDialog dialog3 = new TextInputDialog();
        dialog3.setTitle("Add Field");
        dialog3.setHeaderText("Enter the Type of the Field:");
        dialog3.setContentText("Field Type:");

        // Display the dialog and wait for the user to enter a value
        //Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        Optional<String> result3 = dialog3.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result3.isPresent()) {
            fieldType = result3.get();
            // Save the source class name
        }
        guiModel.addField(className, fieldName, fieldType);
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

        // Display the dialog and wait for the user to enter a value
        //Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        Optional<String> result = dialog.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result.isPresent()) {
            className = result.get();
            // Save the source class name
        }

        TextInputDialog dialog2 = new TextInputDialog();
        dialog2.setTitle("Add Method");
        dialog2.setHeaderText("Enter the Method Name:");
        dialog2.setContentText("Method Name:");

        // Display the dialog and wait for the user to enter a value
        //Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        Optional<String> result2 = dialog2.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result2.isPresent()) {
            methodName = result2.get();
            // Save the source class name
        }

        TextInputDialog dialog3 = new TextInputDialog();
        dialog3.setTitle("Add Method");
        dialog3.setHeaderText("Enter the Method Type:");
        dialog3.setContentText("Method Type:");

        // Display the dialog and wait for the user to enter a value
        //Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        Optional<String> result3 = dialog3.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result3.isPresent()) {
            methodType = result3.get();
            // Save the source class name
        }

        guiModel.addMethod(className, methodName, methodType);
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

        // Display the dialog and wait for the user to enter a value
        //Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        Optional<String> result = dialog.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result.isPresent()) {
            className = result.get();
            // Save the source class name
        }

        TextInputDialog dialog2 = new TextInputDialog();
        dialog2.setTitle("Add Parameter");
        dialog2.setHeaderText("Enter the Method Name:");
        dialog2.setContentText("Method Name:");

        // Display the dialog and wait for the user to enter a value
        //Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        Optional<String> result2 = dialog2.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result2.isPresent()) {
            methodName = result2.get();
            // Save the source class name
        }

        TextInputDialog dialog3 = new TextInputDialog();
        dialog3.setTitle("Add Parameter");
        dialog3.setHeaderText("Enter the Parameter Name:");
        dialog3.setContentText("Parameter Name:");

        // Display the dialog and wait for the user to enter a value
        //Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        Optional<String> result3 = dialog3.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result3.isPresent()) {
            paramName = result3.get();
            // Save the source class name
        }

        TextInputDialog dialog4 = new TextInputDialog();
        dialog4.setTitle("Add Parameter");
        dialog4.setHeaderText("Enter the Parameter Type:");
        dialog4.setContentText("Parameter Type:");

        // Display the dialog and wait for the user to enter a value
        //Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        Optional<String> result4 = dialog4.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result4.isPresent()) {
            paramType = result4.get();
            // Save the source class name
        }

        guiModel.addParams(className, methodName, paramName, paramType);
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
            guiModel.addParams(className, methodName, paramNames.get(i), paramTypes.get(i));
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
        guiModel.removeClass(className);

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

        // Display the dialog and wait for the user to enter a value
        //Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        Optional<String> result = dialog.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result.isPresent()) {
            className = result.get();
            // Save the source class name
        }

        TextInputDialog dialog2 = new TextInputDialog();
        dialog2.setTitle("Delete Field");
        dialog2.setHeaderText("Enter the Field Name:");
        dialog2.setContentText("Field Name:");

        // Display the dialog and wait for the user to enter a value
        //Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        Optional<String> result2 = dialog2.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result2.isPresent()) {
            fieldName = result2.get();
            // Save the source class name
        }
        guiModel.removeField(className, fieldName);
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

        // Display the dialog and wait for the user to enter a value
        //Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        Optional<String> result = dialog.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result.isPresent()) {
            className = result.get();
            // Save the source class name
        }

        TextInputDialog dialog2 = new TextInputDialog();
        dialog2.setTitle("Delete Method");
        dialog2.setHeaderText("Enter the Method Name:");
        dialog2.setContentText("Method Name:");

        // Display the dialog and wait for the user to enter a value
        //Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        Optional<String> result2 = dialog2.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result2.isPresent()) {
            methodName = result2.get();
            // Save the source class name
        }

        guiModel.removeMethod(className, methodName);
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

        // Display the dialog and wait for the user to enter a value
        //Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        Optional<String> result = dialog.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result.isPresent()) {
            className = result.get();
            // Save the source class name
        }

        TextInputDialog dialog2 = new TextInputDialog();
        dialog2.setTitle("Delete Parameter");
        dialog2.setHeaderText("Enter the Method Name:");
        dialog2.setContentText("Method Name:");

        // Display the dialog and wait for the user to enter a value
        //Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        Optional<String> result2 = dialog2.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result2.isPresent()) {
            methodName = result2.get();
            // Save the source class name
        }

        TextInputDialog dialog3 = new TextInputDialog();
        dialog3.setTitle("Delete Parameter");
        dialog3.setHeaderText("Enter the Parameter Name:");
        dialog3.setContentText("Parameter Name:");

        // Display the dialog and wait for the user to enter a value
        //Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        Optional<String> result3 = dialog3.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result3.isPresent()) {
            paramName = result3.get();
            // Save the source class name
        }

        guiModel.removeParam(className, methodName, paramName);
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

        // Display the dialog and wait for the user to enter a value
        //Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        Optional<String> result = dialog.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result.isPresent()) {
            className = result.get();
            // Save the source class name
        }

        TextInputDialog dialog2 = new TextInputDialog();
        dialog2.setTitle("Delete Parameter");
        dialog2.setHeaderText("Enter the Method Name:");
        dialog2.setContentText("Method Name:");

        // Display the dialog and wait for the user to enter a value
        //Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        Optional<String> result2 = dialog2.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result2.isPresent()) {
            methodName = result2.get();
            // Save the source class name
        }
        guiModel.clearParams(className, methodName);
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

        // Display the dialog and wait for the user to enter a value
        //Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        Optional<String> result = dialog.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result.isPresent()) {
            srcClass = result.get();
            // Save the source class name
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
        guiModel.removeRelationship(srcClass, destClass);
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
        guiModel.renameClass(oldClassName, newClassName);

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

        guiModel.renameField(className, oldFieldName, newFieldName);

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

        // Display the dialog and wait for the user to enter a value
        //Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        Optional<String> result = dialog.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result.isPresent()) {
            className = result.get();
            // Save the source class name
        }

        TextInputDialog dialog2 = new TextInputDialog();
        dialog2.setTitle("Edit Method");
        dialog2.setHeaderText("Enter the Old Method Name:");
        dialog2.setContentText("Old Method Name:");

        // Display the dialog and wait for the user to enter a value
        //Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        Optional<String> result2 = dialog2.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result2.isPresent()) {
            methodName = result2.get();
            // Save the source class name
        }

        TextInputDialog dialog3 = new TextInputDialog();
        dialog3.setTitle("Edit Method");
        dialog3.setHeaderText("Enter the New Method Name:");
        dialog3.setContentText("New Method Name:");

        // Display the dialog and wait for the user to enter a value
        //Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        Optional<String> result3 = dialog3.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result3.isPresent()) {
            newMethodName = result3.get();
            // Save the source class name
        }

        guiModel.renameMethod(className, methodName, newMethodName);
        MIListAll.clear();
        MIListAll.setText(guiModel.listAllClasses());
    }
    

    @FXML
    void clickMINewDiagram(ActionEvent event) {
        guiModel.getClassContainer().getContainer().clear();
        guiModel.getRelationshipContainer().getAllRelationships().clear();
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
    void clickMIOpenDiagram(ActionEvent event) {
        String loadName = "";
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Load File");
        dialog.setHeaderText("Enter the JSON File Name:");
        dialog.setContentText("File name:");

        // Display the dialog and wait for the user to enter a value
        //Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        Optional<String> result = dialog.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result.isPresent()) {
            loadName = result.get();
            // Save the source class name
        }
        guiModel.load(loadName);
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
        String name = "saveUml";
        TextInputDialog saveDialog = new TextInputDialog();
        saveDialog.setTitle("Saving Class UML");
        saveDialog.setHeaderText("Enter the Save File Name:");
        saveDialog.setContentText("Save File Name:");

        // Display the dialog and wait for the user to enter a value
        //Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        Optional<String> result = saveDialog.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result.isPresent()) {
            name = result.get();
            // Save the source class name
        }
        //saveUML.save(guiModel.getClassContainer(), guiModel.getRelationshipContainer(), name);
        guiModel.save(name);
    }

    @FXML
    void clickMISaveAs(ActionEvent event) {

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

        // Display the dialog and wait for the user to enter a value
        //Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        Optional<String> result = dialog.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result.isPresent()) {
            srcClass = result.get();
            // Save the source class name
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

        // Display the dialog and wait for the user to enter a value
        //Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        Optional<String> result3 = dialog3.showAndWait();

        // If the user clicked OK and entered a value, save it
        if (result3.isPresent()) {
            relType = result3.get();
            // Save the source class name
        }
        guiModel.addRelationship(srcClass, destClass, relType);
        MIRelText.setText(new String());
        for (Relationship rel : guiModel.getRelationshipContainer().getAllRelationships()){
            if (!textAreaContainsString(MIRelText, modelListOneClassRelationship(rel.getSourceClass()))) {
                MIRelText.appendText((modelListOneClassRelationship(rel.getSourceClass())));
            }
        }
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
        // The following operation assumes you are running this program
        // from "/java2" directory with mvn clean javafx:run
        // and NOT by clicking the play button, which assumes you are in
        // the "/" (github root) directory.
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
    void clickMIUndo(ActionEvent event) {

    }

}