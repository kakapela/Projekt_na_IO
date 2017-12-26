package sample.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrationModel {
    Connection connection;
   public RegistrationModel() {

        try {
            connection= DBConnection.ConnectingToDB();;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void register(String userr,String pass){

        try {
            //Statement stmt=connection.createStatement();


            PreparedStatement stmt = connection.prepareStatement("INSERT INTO USERS VALUES (?, ?,0,0,0)");

            stmt.setString(1, userr);
            stmt.setString(2, pass);


            stmt.executeUpdate();

           // statement.executeUpdate("INSERT INTO Customers " + "VALUES (1001, 'Simpson', 'Mr.', 'Springfield', 2001)")
            //"INSERT INTO USERS VALUES('"+user +"' + '"+przecinek+"' + '"+pass+"')  ";
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
