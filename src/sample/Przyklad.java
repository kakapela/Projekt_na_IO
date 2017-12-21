package sample;

import java.sql.SQLException;

public class Przyklad extends Query {


    public Przyklad() {
        super("SELECT 'login','password' FROM users WHERE 'login' = ? AND 'password' = ? ");

    }
    @Override
    protected void executeSql() throws SQLException {

        myStmt=myConn.prepareStatement(sql);

        //myStmt.setString(1,"ania");
        //myStmt.setString(2,"bania");
        System.out.println(myStmt);
        resultSet= myStmt.executeQuery();
        System.out.println(resultSet);
    }

    @Override
    protected void process() throws SQLException {
        while (resultSet.next()){

            System.out.println("Zalogowałes sie");

        }


    }
}
