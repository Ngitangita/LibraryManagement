package org.example.Config;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionDB {
    protected Connection getConnection(){
        try {
            return DatabaseConfig.createConnection ();
        } catch ( SQLException e ) {
            throw new RuntimeException ( e );
        }
    }
}
