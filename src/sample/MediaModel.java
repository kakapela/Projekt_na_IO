package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.File;

public class MediaModel {

    private MediaPlayer mp;
    private Media me;

    public MediaModel(){}

    public void initalizeMovie(String pathToTheMovie, MediaView mv){
        String path = new File(pathToTheMovie).getAbsolutePath() ;
        me = new Media(new File(path).toURI().toString());
        mp = new MediaPlayer(me);
        mv.setMediaPlayer(mp);

    }
    public void initalizeVolume(MaterialDesignIconView volume_down,JFXSlider volumeSlider,FontAwesomeIconView volumeMax){


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
    boolean isPlaying=false;
    public void initalizePlayPause(FontAwesomeIconView playpauseIcon,JFXButton playpause){

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
    public void initalizeTimeSlider(JFXSlider seekSlider){
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


    public void fast(){
        mp.setRate(1.25);

    }
    public void slow(){
        mp.setRate(.75);

    }
    public void reload(FontAwesomeIconView playpauseIcon){
        mp.seek(mp.getStartTime());
        mp.play();
        playpauseIcon.setGlyphName("PAUSE_CIRCLE");
    }
    public void start(FontAwesomeIconView playpauseIcon){
        mp.seek(mp.getStartTime());
        mp.stop();
        playpauseIcon.setGlyphName("PLAY");

    }
}
