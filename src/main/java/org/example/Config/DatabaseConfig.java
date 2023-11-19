package org.example.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {

    public static Connection createConnection() throws SQLException{
        String url = System.getenv ( "JDBC_URL" );
        String userName = System.getenv ( "JDBC_USER" );
        String pwd = System.getenv ( "JDBC_PASSWORD" );

        System.out.println ("connection" );
        return DriverManager.getConnection (
                url,
                userName,
                pwd
        );
    }
}
