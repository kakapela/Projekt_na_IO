package sample.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLquizModel {



public static void connectDB(int punkty)
{
    Connection connection = null;
    try {
        connection= DBConnection.ConnectingToDB();;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    DBInfoAboutUser currentUser=DBInfoAboutUser.getInstance();

    try {
        PreparedStatement stmt = connection.prepareStatement("UPDATE USERS SET wynik_sql =? WHERE login=?");
        stmt.setInt(1, punkty);
        stmt.setString(2, currentUser.getCurrentUser());
        stmt.executeUpdate();
        stmt.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

}





}
