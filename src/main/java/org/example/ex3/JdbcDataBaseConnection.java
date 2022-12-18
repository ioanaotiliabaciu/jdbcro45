package org.example.ex3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcDataBaseConnection {

    public static void main(String[] args) {

        try {
            Connection dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/flower_shop",
                    "root",
                    "iustin555");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}