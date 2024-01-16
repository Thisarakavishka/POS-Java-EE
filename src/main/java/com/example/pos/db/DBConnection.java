package com.example.pos.db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection dbConnection;
    private final DataSource dataSource;

    private DBConnection() throws NamingException {
        try {
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/pos");
        } catch (NamingException e) {
            throw new NamingException("Error looking up the data source" + e.getMessage());
        }
    }

    public static synchronized DBConnection getDbConnection() throws NamingException {
        return dbConnection == null ? dbConnection = new DBConnection() : dbConnection;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
