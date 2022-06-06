package com.example.demo3;

import java.sql.*;

public class DBUtil {

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    private static Connection connection = null;

    private static final String connString = "jdbc:mysql://localhost:3306/StudentRegistry";

    public static void dbConnect() throws SQLException,ClassNotFoundException {
        try {
            Class.forName(JDBC_DRIVER);

        }catch(ClassNotFoundException e){
            System.out.println("Where is your jdbc driver? ");
            e.printStackTrace();
            throw e;
        }

        System.out.println("Connection has been registered");

        try{
            connection = DriverManager.getConnection(connString,"root","Dinith20010620");

        }catch(SQLException e){
            System.out.println("Connection Failed ! Check output console" +e);
            throw e;
        }
    }

    public static void dbDisconnect() throws SQLException{
        try{
            if(connection != null && !connection.isClosed()){
                connection.close();
            }

        }catch(Exception e){
            throw e;
        }

    }

    public static ResultSet dbExecuteQuery(String sql) throws SQLException, ClassNotFoundException {
        dbConnect();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sql);
        return rs;
    }

    public static void dbExecuteUpdate(String sql) throws SQLException,ClassNotFoundException{
        dbConnect();
        Statement st = connection.createStatement();
        st.executeUpdate(sql);
        dbDisconnect();
    }
}
