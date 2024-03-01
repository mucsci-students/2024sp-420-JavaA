package com.classuml;
import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

public class GLIUMLClassBox {
    private Model model;
    private TextArea ta;
    private String className;
    private HBox box;

    public GLIUMLClassBox(String className) {
        Model newModel = new Model();
        newModel.addClass(className);
        this.model = newModel;
        this.ta = new TextArea();
        this.box = new HBox(1);
        this.box.setPadding(new Insets(25, 5 , 5, 50));
        this.className = className;
        putClassInfoInBox();
        box.getChildren().addAll(ta);
    }
    
    private void putClassInfoInBox() {
        String classOutput = model.listOneClass(className);
        ta.appendText(classOutput);
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void changePosOfTextArea(int x, int y) {

    }
}