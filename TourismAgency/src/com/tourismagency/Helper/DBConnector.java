package com.tourismagency.Helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private Connection connect = null;

    public Connection connectDB() throws SQLException {
        this.connect = DriverManager.getConnection(Config.DB_URL,Config.DB_USERNAME,Config.DB_PASSWORD);
        return this.connect;
    }

    public static Connection getInstance() throws SQLException {
        DBConnector db = new DBConnector();
        return db.connectDB();
    }

}
