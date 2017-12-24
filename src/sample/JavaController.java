package sample;

import com.jfoenix.controls.JFXSlider;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Binding;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Modality;
import javafx.stage.Window;

import javax.script.Bindings;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class JavaController implements Initializable{


    @FXML
    private MediaView mv;
    private MediaPlayer mp;
    private Media me;

    @FXML
    private JFXSlider volumeSlider;

    @FXML
    private AnchorPane mainPane;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main.fadeTrans(mainPane);
        String path = new File("src/sample/JavaMovie.mp4").getAbsolutePath() ;
        me = new Media(new File(path).toURI().toString());
        mp = new MediaPlayer(me);
        mv.setMediaPlayer(mp);
       // mp.setAutoPlay(true);
        DoubleProperty width= mv.fitWidthProperty();
        DoubleProperty height= mv.fitHeightProperty();
        volumeSlider.setValue(mp.getVolume()*100);
        volumeSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                mp.setVolume(volumeSlider.getValue()/100);
            }
        });
    }
    public void play(){
        mp.play();

    }
    public void pause(){
        mp.pause();

    }
    public void normalSpeed(){
        mp.setRate(1);

    }
    public void fast(){
        mp.setRate(1.25);

    }
    public void slow(){
        mp.setRate(.75);

    }
    public void reload(){
        mp.seek(mp.getStartTime());
        mp.play();
    }
    public void start(){
        mp.seek(mp.getStartTime());
        mp.stop();

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
            Main.changeScene("PoradnikView.fxml");
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
