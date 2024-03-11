package com.classuml;
import javafx.scene.control.TextArea;

public class GLIUMLClassBox {
    private TextArea ta;
    private String className;

    public GLIUMLClassBox(Model model, String className) {
        model.addClass(className);
        this.ta = new TextArea();
        this.className = className;
        ta.appendText(model.listOneClass(className));
    }

    public void changeSizeOfTextArea(int x, int y) {

    }

    public void changePosOfTextArea(int x, int y) {

    }

    public String getClassName() {
        return className;
    }

    public TextArea getTextArea() {
        return ta;
    }
}