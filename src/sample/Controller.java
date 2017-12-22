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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    LoginModel loginModel=new LoginModel();

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
Parent root;
    public void sprawdz() throws IOException {
        Main.changeScene("loginView.fxml");

    }
    public void zaloguj() throws IOException {
        try {
            if(loginModel.isLogin(login.getText(),haslo.getText())){

                Main.changeScene("loginView.fxml");

            }
            else {
                System.out.println("Nieprawidlowe haslo!");
            }
        } catch (SQLException e) {
            System.out.println("Nieprawidlowe haslo!");
            e.printStackTrace();
        }


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
