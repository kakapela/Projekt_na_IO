package sample;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class Controller {

    @FXML
    private Label exit;

    @FXML
    void exitProgram(MouseEvent event) {
System.exit(0);
    }



}
