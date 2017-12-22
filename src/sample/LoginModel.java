package sample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel  {

    Connection connection;
    public LoginModel() {

        try {
            connection=MySQLConnection.ConnectingToDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean isLogin(String user,String password){

        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String query = "";
        try{}
        catch(Exception e){

        }

    }
}
