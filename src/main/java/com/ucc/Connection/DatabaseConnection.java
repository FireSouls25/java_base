package com.ucc.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static String url = "jdbc:mysql://localhost:3306";
    private static String user = "tal";
    private static String password = "pass";
    
    private static Connection myConn;

    public static Connection getInstanceConnection() throws SQLException{
        if(myConn == null){
            myConn = DriverManager.getConnection(url, user, password);
        }
        return myConn;
    }

}
