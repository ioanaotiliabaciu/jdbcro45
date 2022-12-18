package org.example;

import java.sql.*;

public class Main {
    public static void main(String[] args) {

        // avem nevoie de un obiect

        // stringul din connection tebuie sa arate astfel: jdbc:mysql://localhost:3306/flower_shop
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/flower_shop",
                    "root",
                    "iustin555");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("Select * from product where id = 1");
            resultSet.next();
            System.out.println(resultSet.getString(2) + " " + resultSet.getString(4));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}