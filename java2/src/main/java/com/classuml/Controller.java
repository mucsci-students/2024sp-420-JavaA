package com.classuml;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
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
import java.util.stream.Stream;

public class Controller {

    @FXML
    private MenuItem MIAbout;

    @FXML
    private MenuItem MIAddClass;

    @FXML
    private MenuItem MIAddField;

    @FXML
    private MenuItem MIAddMethod;

    @FXML
    private MenuItem MIClick;

    @FXML
    private MenuItem MICloseDiagram;

    @FXML
    private MenuItem MICopy;

    @FXML
    private MenuItem MIDelete;

    @FXML
    private MenuItem MIDeleteClass;

    @FXML
    private MenuItem MIDeleteField;

    @FXML
    private MenuItem MIDeleteMethod;

    @FXML
    private MenuItem MINewDiagram;

    @FXML
    private MenuItem MIOpenDiagram;

    @FXML
    private MenuItem MIPaste;

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
    private MenuItem MISelectAll;

    @FXML
    private MenuItem MISetClassRel;

    @FXML
    private MenuItem MIShowHelp;

    @FXML
    private MenuItem MIUndo;

    @FXML
    private MenuItem MIUnselectAll;

    private Stage mainWindow; // Define mainWindow as a variable of type Stage

    public void setMainWindow(Stage mainWindow) {
        this.mainWindow = mainWindow;
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

    }

    @FXML
    void clickMIAddField(ActionEvent event) {

    }

    @FXML
    void clickMIAddMethod(ActionEvent event) {

    }

    @FXML
    void clickMICloseDiagram(ActionEvent event) {

    }

    @FXML
    void clickMICopy(ActionEvent event) {

    }

    @FXML
    void clickMICut(ActionEvent event) {

    }

    @FXML
    void clickMIDelete(ActionEvent event) {

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
    void clickMINewDiagram(ActionEvent event) {

    }

    @FXML
    void clickMIOpenDiagram(ActionEvent event) {

    }

    @FXML
    void clickMIPaste(ActionEvent event) {

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

    }

    @FXML
    void clickMISaveAs(ActionEvent event) {

    }

    @FXML
    void clickMISelectAll(ActionEvent event) {

    }

    @FXML
    void clickMISetClassRel(ActionEvent event) {

    }

    @FXML
    void clickMIShowHelp(ActionEvent event) {

    }

    @FXML
    void clickMIUndo(ActionEvent event) {

    }

    @FXML
    void clickMIUnselectAll(ActionEvent event) {

    }

}
