package sample.Controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Window;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class loginController implements Initializable {

    @FXML
    private AnchorPane mainPane;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Main.fadeTrans(mainPane);
    }
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
    @FXML
    public void logout(MouseEvent event) throws IOException {
        Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION, "Confirm", ButtonType.OK, ButtonType.CANCEL);
        Window owner = ((Node) event.getTarget()).getScene().getWindow();
        exitAlert.setContentText("Czy napewno chcesz się wylogować?");
        exitAlert.initModality(Modality.APPLICATION_MODAL);
        exitAlert.initOwner(owner);
        exitAlert.showAndWait();

        if (exitAlert.getResult() == ButtonType.OK) {
            Main.fadeTrans(mainPane);
            Main.changeScene("View/RegisterView.fxml");
        } else {
            exitAlert.close();
        }




    }

    public void guide() throws IOException {
        Main.fadeTrans(mainPane);
        Main.changeScene("View/PoradnikView.fxml");

    }
    public void contact() throws IOException {

        Main.fadeTrans(mainPane);
        Main.changeScene("View/ContactView.fxml");
    }
    public void SQL() throws IOException {

        Main.changeScene("View/SqlQuizView.fxml");
    }
}
