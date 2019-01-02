package org.csu.mypetstore.persistence;


import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBUtil {
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://127.0.0.1:3306/myjepetstore";
    private static String username = "root";
    private static String password = "213218";

    public static Connection getConnection() throws Exception {
        try {
            Class.forName(driver);
            return  DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            throw e;
        }
    }

    public static void closeConnection(Connection connection) throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void closeStatement(Statement statement) throws Exception{
        statement.close();
    }

    public static void closePreparedStatement(PreparedStatement pStatement) throws Exception{
        pStatement.close();
    }

    public static void closeResultSet(ResultSet resultSet) throws Exception{
        resultSet.close();
    }
    /*
    public static void main(String arg[])throws Exception{
        Connection conn=DBUtil.getConnection();
        System.out.print(conn);
    }*/
}

