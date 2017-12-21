package sample;


import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {



    @FXML
    private Label exit;

    @FXML
    void exitProgram(MouseEvent event) {
System.exit(0);
    }

    @FXML
    private JFXTextField login;

    @FXML
    private JFXPasswordField haslo;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RequiredFieldValidator validator = new RequiredFieldValidator();
        RequiredFieldValidator validator2 = new RequiredFieldValidator();

        login.getValidators().add(validator);
        validator.setMessage("Wpisz login!");

        login.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue){
                    login.validate();
                }
            }
        });

        haslo.getValidators().add(validator2);
        validator2.setMessage("Wpisz hasło!");
        haslo.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    haslo.validate();
                }
            }
        });
    }
}
