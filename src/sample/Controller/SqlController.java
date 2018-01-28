package sample.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
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
import javafx.util.Duration;
import sample.Main;
import sample.Model.MediaModel;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SqlController extends MediaModel implements Initializable{
    @FXML
    private AnchorPane mainPane;
    @FXML
    private JFXButton slowBtn;

    @FXML
    private JFXButton fastBtn;

    @FXML
    private JFXButton reloadBtn;

    @FXML
    private JFXButton startBtn;

    @FXML
    private JFXButton playpause;

    @FXML
    private FontAwesomeIconView playpauseIcon;

    @FXML
    private JFXSlider volumeSlider;

    @FXML
    private FontAwesomeIconView volumeMax;

    @FXML
    private MaterialDesignIconView volume_down;

    @FXML
    private StackPane videoPane;

    @FXML
    private MediaView mv;
    MediaPlayer mp;
    Media me;
    boolean isPlaying=false;
    private String pathToTheMovie="videos/SqlTutorial.mp4";

    @FXML
    private JFXSlider seekSlider;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main.fadeTrans(mainPane);
        createMediaPlayer();

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
            mp.seek(mp.getStartTime());
            mp.stop();
            playpauseIcon.setGlyphName("PAUSE");
            //TODO
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void initalizeMovie() {
        String path = new File(pathToTheMovie).getAbsolutePath() ;
        me = new Media(new File(path).toURI().toString());
        mp = new MediaPlayer(me);
        mv.setMediaPlayer(mp);
    }

    @Override
    protected void initalizeVolume() {

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
    }

    @Override
    protected void initalizePlayPause() {
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
    }

    @Override
    protected void initalizeTimeSlider() {
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

    @Override
    protected void setFast() {
        fastBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mp.setRate(1.25);
            }
        });
    }

    @Override
    protected void setSlow() {
        slowBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mp.setRate(.75);
            }
        });
    }

    @Override
    protected void setReload() {
        reloadBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mp.seek(mp.getStartTime());
                mp.play();
                playpauseIcon.setGlyphName("PAUSE_CIRCLE");
            }
        });
    }

    @Override
    protected void setStart() {

        startBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mp.seek(mp.getStartTime());
                mp.stop();
                playpauseIcon.setGlyphName("PLAY");
            }
        });
    }



}
