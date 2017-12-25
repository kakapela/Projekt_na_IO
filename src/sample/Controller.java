package sample;


import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Window;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    LoginModel loginModel=LoginModel.getInstance(); //tylko jeden obiekt tego typu
    DBInfoAboutUser currentUser=DBInfoAboutUser.getInstance();




    @FXML
    private AnchorPane mainPane;

    @FXML
    private JFXTextField login;

    @FXML
    private JFXPasswordField haslo;

    @FXML
    private AnchorPane anchorPane2;


    @FXML
    private Label exit;
    @FXML
    private JFXTextField registrationLogin;

    @FXML
    private JFXPasswordField registrationPassword;

    RegistrationModel registrationModel=new RegistrationModel();


    @FXML
    void exitProgram(MouseEvent event) {
        Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION, "Confirm", ButtonType.OK, ButtonType.CANCEL);
        Window owner = ((Node) event.getTarget()).getScene().getWindow();
        exitAlert.setContentText("Czy napewno chcesz wyjść?");
        exitAlert.initModality(Modality.APPLICATION_MODAL);
        exitAlert.initOwner(owner);
        exitAlert.showAndWait();

        if (exitAlert.getResult() == ButtonType.OK) {
            Platform.exit();
        } else {
            exitAlert.close();
        }
    }

Parent root;


    public void zaloguj() throws IOException {
        try {
            if(loginModel.isLogin(login.getText(),haslo.getText())){

                currentUser.setUser(login.getText(),haslo.getText());
                Image img=new Image("sample/images/tick.png");
                Notifications notificationBuilder = Notifications.create()
                        .title("Zalogowano się")
                        .text("Wprowadzone dane są poprawne")
                        .graphic(new ImageView(img))
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.BOTTOM_RIGHT);
                notificationBuilder.darkStyle();
                notificationBuilder.show();
                Main.fadeTrans(mainPane);
                Main.changeScene("View/loginView.fxml");



            }
            else {
                Image img=new Image("sample/images/symbol_error.png");
                Notifications notificationBuilder = Notifications.create()
                        .title("Nieprawidłowe dane!")
                        .text("Wprowadz ponownie login oraz hasło")
                        .graphic(new ImageView(img))
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.BOTTOM_RIGHT);
                notificationBuilder.darkStyle();
                notificationBuilder.show();
                //notificationBuilder.showError();

            }
        } catch (SQLException e) {
            System.out.println("Nieprawidlowe haslo!");
            e.printStackTrace();
        }


    }
    public void register(){


        String registrationLog=registrationLogin.getText();
        String registrationPass=registrationPassword.getText();
        if(!(registrationLogin.getText().isEmpty()) && !(registrationPassword.getText().isEmpty())) {
            registrationModel.register(registrationLog, registrationPass);
            Image img = new Image("sample/images/tick.png");
            Notifications notificationBuilder = Notifications.create()
                    .title("Zarejstrowałeś się!")
                    .text("Zaloguj się jak najszybciej!")
                    .graphic(new ImageView(img))
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT);
            notificationBuilder.darkStyle();
            notificationBuilder.show();

        }
        else
        {

            Image img = new Image("sample/images/symbol_error.png");
            Notifications notificationBuilder = Notifications.create()
                    .title("Niepoprawne dane")
                    .text("Wpisz poprawnie login oraz hasło")
                    .graphic(new ImageView(img))
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT);
            notificationBuilder.darkStyle();
            notificationBuilder.show();
        }
    }    @Override
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
