package com.classuml;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


// JavaFX Application Class
public class MainWindow extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        String currentDirectory = System.getProperty("user.dir");
        String fxmlPath;

        if (currentDirectory.equals("/path/to/java2")) {
            fxmlPath = "/com/classuml/mainwindow.fxml";
        } else {
            fxmlPath = "fxml/mainwindow.fxml";
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("UML Reader");
        primaryStage.show();
    }
    // Optional: You can add methods to this class to show the window from other classes.
}