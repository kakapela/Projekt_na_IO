package sample;

import java.sql.SQLException;

public class Przyklad extends Query {


    public Przyklad() {
        super("SELECT * FROM users WHERE login = ? AND password = ? ");

    }
    @Override
    protected void executeSql() throws SQLException {

        myStmt=myConn.prepareStatement(sql);

        myStmt.setString(1,"ania");//login - docelowo pobieramy tu wartosci wpisane przez usera
        myStmt.setString(2,"bania");//haslo
        //System.out.println(myStmt);
        resultSet= myStmt.executeQuery(); //wykonanie zapytania
        //ystem.out.println(resultSet);
    }

    @Override
    protected void process() throws SQLException {
        if (resultSet.next()){//  sprawdzamy czy zapytanie zwrocilo cos - jesli tak to znaczy ze jest taki user w bazie, wiec logujemy go

            System.out.println("Zalogowałes sie");

        }
        else
        {

            System.out.println("Nie zalogowałes sie");

        }


    }
}
