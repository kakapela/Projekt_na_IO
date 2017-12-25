package sample.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Modality;
import javafx.stage.Window;
import sample.Main;
import sample.Model.MediaModel;
import sample.Model.MediaPlayerFunctions;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class JavaController implements Initializable,MediaPlayerFunctions {


    @FXML
    private MediaView mv;
    private MediaPlayer mp;
    private Media me;

    @FXML
    private StackPane videoPane;
    @FXML
    private MaterialDesignIconView volume_down;
    @FXML
    private FontAwesomeIconView playpauseIcon;
    @FXML
    private JFXButton playpause;
    @FXML
    private FontAwesomeIconView volumeMax;
    @FXML
    private JFXSlider seekSlider;


    @FXML
    private JFXSlider volumeSlider;

    @FXML
    private AnchorPane mainPane;

    MediaModel mediaModel=new MediaModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main.fadeTrans(mainPane);
        mediaModel.initalizeMovie("videos/JavaMovie.mp4",mv);
        mediaModel.initalizeVolume(volume_down,volumeSlider,volumeMax);
        mediaModel.initalizePlayPause(playpauseIcon,playpause);
        mediaModel.initalizeTimeSlider(seekSlider);


    }


    public void fast(){
        mediaModel.fast();

    }
    public void slow(){
        mediaModel.slow();

    }



    public void reload(){
        mediaModel.reload(playpauseIcon);
    }
    public void start(){
        mediaModel.start(playpauseIcon);

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
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
