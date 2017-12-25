package sample.Controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Window;
import sample.Main;
import sample.Model.WebViewModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GitWebController implements Initializable{
    @FXML
    private AnchorPane mainPane;

    @FXML
    private WebView webView;
    WebViewModel webViewModel = new WebViewModel();
    private String url_adress="https://git-scm.com/documentation";
    WebEngine engine;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main.fadeTrans(mainPane);
        webViewModel.initalize(engine,webView,url_adress);
    }
    public void reloadPage(){
        webViewModel.reload();
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
    public void goBackk(){


        try {
            Main.fadeTrans(mainPane);
            Main.changeScene("View/PoradnikView.fxml");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
