package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Binding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Modality;
import javafx.stage.Window;
import javafx.util.Duration;

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
    boolean isPlaying=false;
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
        volume_down.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mp.setVolume(0);
                volumeSlider.setValue(0);
                volume_down.setGlyphName("VOLUME_OFF");
            }
        });
        volumeMax.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mp.setVolume(100);
                volumeSlider.setValue(100);
            }
        });
        volumeSlider.setValue(mp.getVolume()*100);
        volumeSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                volume_down.setGlyphName("VOLUME_MEDIUM");
                mp.setVolume(volumeSlider.getValue()/100);
            }
        });

        playpause.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(isPlaying){
                    mp.pause();
                    playpauseIcon.setGlyphName("PLAY");
                    mp.setRate(1);
                }
                else{
                    mp.play();
                    playpauseIcon.setGlyphName("PAUSE_CIRCLE");
                    mp.setRate(1);
                }
                isPlaying=!isPlaying;
            }
        });

        mp.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                seekSlider.setValue(newValue.toSeconds());
            }
        });
        seekSlider.setValue(0);
        seekSlider.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mp.seek(Duration.seconds(seekSlider.getValue()));
            }
        });


    }

    /*
    public void play(){

        mp.play();
        mp.setRate(1);

    }
*/
    public void pause(){
        mp.pause();

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
        playpauseIcon.setGlyphName("PAUSE_CIRCLE");
    }
    public void start(){
        mp.seek(mp.getStartTime());
        mp.stop();
        playpauseIcon.setGlyphName("PLAY");

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
