package sample.Model;

import java.io.File;
import java.sql.*;


public class DBConnection {
    private static final String JDBC_URL = "jdbc:sqlite:"; //TUTAJ PODAJESZ PO UKOSNIKU NAZWE BAZY
    private static final String USERNAME="root";
    private static final String PASSWORD="";
    Connection myConn;

    public static Connection ConnectingToDB() throws SQLException {
        Connection myConn;
        File file = new File("quiz.db");


        String sql = "CREATE TABLE IF NOT EXISTS users (\n"
                + "	login text NOT NULL,\n"
                + "	password text NOT NULL \n"
                + ");";

        myConn = DriverManager.getConnection(JDBC_URL+"quiz.db");
        Statement statement=myConn.createStatement();
        statement.execute(sql);
        return myConn;
    }
}/*
abstract public class Query {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/quiz"; //TUTAJ PODAJESZ PO UKOSNIKU NAZWE BAZY
    private static final String USERNAME="root";
    private static final String PASSWORD="";
    protected String sql;
    protected Connection myConn;
    protected PreparedStatement myStmt;
    protected ResultSet resultSet;

    public Query(String sql){

        this.sql=sql;
    }

    public Query() {

    }

    public void createMediaPlayer(String login, String haslo) {
        try {
            connectToDatabase();
            executeSql(login,haslo);
            process();
            //close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    private void connectToDatabase() throws SQLException {
        myConn = DriverManager.getConnection(JDBC_URL,USERNAME,PASSWORD);

    }
    abstract protected void executeSql(String login, String haslo) throws SQLException;


    abstract protected void process() throws SQLException;

    private void close() throws SQLException {
        resultSet.close();
        myStmt.createMediaPlayer("SHUTDOWN");
        myStmt.close();
        myConn.close();
    }






}
*/