package com.Lab4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    private static Connection connection;

    public static Connection connect() throws SQLException {

        if (connection == null) {
            var url = "jdbc:sqlite:D:/programming/javarush/src/com/Lab4/db.sqlite";
            connection = DriverManager.getConnection(url);
        }

        return connection;
    }
}
