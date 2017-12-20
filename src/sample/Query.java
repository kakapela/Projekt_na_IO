package sample;

import java.sql.*;
/*
* UWAGA! ABY DZIALALO POLACZENIE TRZEBA DO PROJEKTU ZALACZYC STEROWNIK JDBC MYSQL connector
*
* */
//wzorzec projektowy - metoda szablonowa
abstract public class Query {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/pytania"; //TUTAJ PODAJESZ PO UKOSNIKU NAZWE BAZY
    private static final String USERNAME="root";
    private static final String PASSWORD="";
    private String sql;
    private Connection myConn;
    private Statement myStmt;
    protected ResultSet resultSet;

    public Query(String sql){

        this.sql=sql;
    }

    public Query() {

    }

    public void execute() {
        try {
            connectToDatabase();
            executeSql();
            process();
            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    private void connectToDatabase() throws SQLException {
        myConn = DriverManager.getConnection(JDBC_URL,USERNAME,PASSWORD);

    }
    private void executeSql() throws SQLException {
        myStmt = myConn.createStatement();
        resultSet= myStmt.executeQuery(sql);
    }
    abstract protected void process() throws SQLException;

    private void close() throws SQLException {
        resultSet.close();
        myStmt.execute("SHUTDOWN");
        myStmt.close();
        myConn.close();
    }






}
