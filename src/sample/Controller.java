package sample;


import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {



    @FXML
    private AnchorPane mainPane;

    @FXML
    private JFXTextField login;

    @FXML
    private JFXPasswordField haslo;

    @FXML
    private AnchorPane anchorPane2;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private Label exit;

    @FXML
    private JFXHamburger hamburger;


    @FXML
    void exitProgram(MouseEvent event) {
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        //hamburger transition
        HamburgerBackArrowBasicTransition burgerTask2 = new HamburgerBackArrowBasicTransition(hamburger);
        burgerTask2.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e)->{
            burgerTask2.setRate(burgerTask2.getRate()* -1);
            burgerTask2.play();

            if(drawer.isShown()){drawer.close();}
            else {
                drawer.open();
            }
        });

        try {

            VBox box = FXMLLoader.load(getClass().getResource("sideMenu.fxml"));
            drawer.setSidePane(box);
        } catch (IOException e) {
            e.printStackTrace();

        }




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
