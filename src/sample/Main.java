package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;


public class Main extends Application {
    static Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception{
        stage=primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }
    public static void changeScene(String sceneName) throws IOException {
        //metoda która będzie zmieniać sceny
        //przyjmuje nazwe pliku fxml czyli naszej sceny jaka chcemy przyjac

        Parent root = FXMLLoader.load(Main.class.getResource(sceneName)); //wyjatek
        stage.setScene(new Scene(root));

    }



    public static void main(String[] args) throws SQLException {
        launch(args);


    }
}
