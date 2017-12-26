package sample.Controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Window;
import sample.Main;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Window;
import sample.Main;
import sample.Model.DBConnection;
import sample.Model.DBInfoAboutUser;
import sample.Model.ResultModel;
import sample.Model.SQLquizModel;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ResultController implements Initializable {

    @FXML
    public javafx.scene.control.Label test;

    @FXML
    public void goBackk(){

        try {
//Main.fadeTrans(mainPane);
            Main.changeScene("View/loginView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public void changeResult(ResultSet resultSet){
        test.setText("kkkk");
        System.out.println("test");

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ResultModel.ConnectDB(test);

        //test.setText("glut");
        /*
        DBInfoAboutUser currentUser=DBInfoAboutUser.getInstance();
        Connection connection = null;
        try {
            connection = DBConnection.ConnectingToDB();
            ;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        String query = "SELECT wynik_sql FROM users WHERE login = ?";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, currentUser.getCurrentUser());
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){

                test.setText(resultSet.getString("wynik_sql")+"");
            }
            else{

            }
        }
        catch(Exception e){

        }
        finally {
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    */}

}