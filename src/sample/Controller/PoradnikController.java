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

public class PoradnikController implements Initializable {

    @FXML
    private AnchorPane mainPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main.fadeTrans(mainPane);
    }
    @FXML
    public void javaMovie() throws IOException {
        Main.fadeTrans(mainPane);
        Main.changeScene("View/JavaView.fxml");
    }
    @FXML
    public void gitMovie() throws IOException {
        Main.fadeTrans(mainPane);
        Main.changeScene("View/GitView.fxml");
    }
    @FXML
    public void sqlMovie() throws IOException {
        Main.fadeTrans(mainPane);
        Main.changeScene("View/SqlView.fxml");
    }

    @FXML
    public void goToGitSite() throws IOException {
        Main.fadeTrans(mainPane);
        Main.changeScene("View/GitWebView.fxml");
    }
    @FXML
    public void goToSqlSite() throws IOException {
        Main.fadeTrans(mainPane);
        Main.changeScene("View/SqlWebView.fxml");
    }


    @FXML
    public void goBackk(){


        try {
            Main.fadeTrans(mainPane);
            Main.changeScene("View/loginView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void goToJavaSite() throws IOException {
        Main.fadeTrans(mainPane);
        Main.changeScene("View/JavaWebView.fxml");
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
}
