package sample;

import java.sql.*;

public class MySQLConnection {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/quiz"; //TUTAJ PODAJESZ PO UKOSNIKU NAZWE BAZY
    private static final String USERNAME="root";
    private static final String PASSWORD="";
    Connection myConn;

    public static Connection ConnectingToDB() throws SQLException {
        Connection myConn;
        myConn = DriverManager.getConnection(JDBC_URL,USERNAME,PASSWORD);
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

    public void execute(String login, String haslo) {
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
        myStmt.execute("SHUTDOWN");
        myStmt.close();
        myConn.close();
    }






}
*/