package sample.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//korzysta ze wzorca SINGLETON
public class LoginModel  {
    private static LoginModel INSTANCE;

    Connection connection;
    private LoginModel() {

        try {
            connection= DBConnection.ConnectingToDB();;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void connect(){

        try {
            connection=DBConnection.ConnectingToDB();;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static LoginModel getInstance(){
        if(INSTANCE==null)
            INSTANCE= new LoginModel();
        return INSTANCE;
    }
    public boolean isLogin(String user,String password) throws SQLException {

        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        String query = "SELECT * FROM users WHERE login = ? AND password = ?";
        try{
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,user);
            preparedStatement.setString(2,password);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){

                return true;
            }
            else{
                return false;
            }
        }
        catch(Exception e){
            return false;
        }
        finally {
            preparedStatement.close();
            resultSet.close();
        }
    }


}