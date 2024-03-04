package com.classuml;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

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
    private MenuItem MIDeleteRel;

    @FXML
    private MenuItem MIEditClass;

    @FXML
    private MenuItem MIEditField;

    @FXML
    private MenuItem MIEditMethod;

    @FXML
    private MenuItem MIEditParam;

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
        Scene dialogScene = new Scene(dialogVbox, 400, 200);
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
        System.out.println("Is className empty?:" + className.isEmpty());

        //GLIUMLClassBox gucb = new GLIUMLClassBox(guiModel, className);
        //System.out.println("Is gucb null: " + (gucb == null));
        //stackPane.getChildren().add(gucb.getTextArea());
        //System.out.println("Is stackPane null: " + (stackPane == null));
        //System.out.println("Here is what children have to say:");
        //System.out.println(stackPane.getChildren().toString());
        //stackPane.setPrefSize(100, 100);
        //Scene scene = new Scene(stackPane, 100,100);
        //System.out.println("Is scene empty: " + (scene == null));
        //if (mainWindow == null) {
        //    mainWindow = new Stage();
        //}
        //mainWindow.setScene(scene);
        //System.out.println("Is mainWindow null: " + (mainWindow == null));
        //mainWindow.show();
        MIListAll.clear();
        MIListAll.setText(guiModel.listAllClasses());
    }

    @FXML
    void clickMIAddField(ActionEvent event) {

    }

    @FXML
    void clickMIAddMethod(ActionEvent event) {

    }

    @FXML
    void clickMIAddParam(ActionEvent event) {

    }

    @FXML
    void clickMIAddParamList(ActionEvent event) {

    }

    @FXML
    void clickMICloseDiagram(ActionEvent event) {

    }

    @FXML
    void clickMIDeleteClass(ActionEvent event) {

    }

    @FXML
    void clickMIDeleteField(ActionEvent event) {

    }

    @FXML
    void clickMIDeleteMethod(ActionEvent event) {

    }

    @FXML
    void clickMIDeleteParam(ActionEvent event) {

    }

    @FXML
    void clickMIDeleteRel(ActionEvent event) {

    }

    @FXML
    void clickMIEditClass(ActionEvent event) {

    }

    @FXML
    void clickMIEditField(ActionEvent event) {

    }

    @FXML
    void clickMIEditParam(ActionEvent event) {

    }

    @FXML
    void clickMINewDiagram(ActionEvent event) {

    }

    @FXML
    void clickMIOpenDiagram(ActionEvent event) {

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
        saveUML.save(guiModel.getClassContainer(), guiModel.getRelationshipContainer(), name);
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
            MIRelText.appendText(modelListOneClassRelationship(rel.getSourceClass()));
        }
    }

    public String modelListOneClassRelationship (String name)
    {
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
            if (rel.getSourceClass().equals(name)){
                retStringRel = retStringRel.concat("Source Class: " + rel.getSourceClass() + "\nDestination Class: " + rel.getDestClass() + "\n" + "Relationship Type: " + rel.getType() + "\n");
                isRel = true;
            }
        }
        if (!isIn){
            return ("Class with that name does not exist.");
        }
        if (!isRel){
            return ("There are no relationships with this class as the source.");
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