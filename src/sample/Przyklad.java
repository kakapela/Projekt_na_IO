package sample;

import java.sql.SQLException;

public class Przyklad extends Query {


    public Przyklad() {
        super("SELECT * FROM PYTANIA");
    }

    @Override
    protected void process() throws SQLException {
        while (resultSet.next()){

            System.out.println(resultSet.getString("odpa")); //ODCZYTUJE KOMORKI W KOLUMNIE ODPA

        }
    }
}
