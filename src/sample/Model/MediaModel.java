package sample.Model;

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

abstract public class MediaModel {

    /*
    KORZYSTAMY TUTAJ Z METODY SZABLONOWEJ
    TWORZYMY WZORZEC NA BUDOWE ODTWARZACZA WIDEO
    WSZYSTKIE FUNKCJE SĄ ABSTRACT PROTECTED A WIEC SA IMPLEMENTOWANE W KONKRETNEJ KLASIE KTORA DZIEDZICZY PO TYM WZORCU
    FUNKCJE SĄ DOSTĘPNE NA ZEWNĄTRZ JEDYNIE POPRZEZ JEDNĄ FUNKCJE KTORA WYWOLUJE WSZYSTKIE POZOSTALE
    POTRZEBNE DO ZBUDOWANIA ODTWARZACZA
*/
    public void createMediaPlayer(){
        initalizeMovie();
        initalizeVolume();
        initalizePlayPause();
        initalizeTimeSlider();
        setFast();
        setReload();
        setSlow();
        setStart();
    }


    abstract protected void initalizeMovie();
   abstract protected void initalizeVolume();
   abstract protected void initalizePlayPause();
    abstract protected void initalizeTimeSlider();
    abstract protected void setFast();
    abstract protected void setSlow();
    abstract protected void setReload();
    protected abstract void setStart();




}
