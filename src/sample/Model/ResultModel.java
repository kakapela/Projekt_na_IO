package sample.Model;

import javafx.scene.control.Label;
import sample.Controller.ResultController;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultModel {


    public static void ConnectDB(Label test){
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
                test.setText(resultSet.getString("wynik_sql"));
               // System.out.println("Jestem tu");
                //ResultController w = new ResultController();
               // w.changeResult(resultSet);
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

    }





}
