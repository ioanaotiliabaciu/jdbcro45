package org.example;

import java.sql.*;

public class JdbcConnectionExample {

    public static void main(String[] args) {

        try {

            System.out.println("Trying to connect to database");
            Connection dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/flower_shop",
                    "root",
                    "iustin555");
            System.out.println("Connection to databse was successful.");
            Statement statement = dbConnection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from product");
            if( resultSet.next()){
                String name = resultSet.getString(2);
                Integer quantity = resultSet.getInt(5);
                Double price = resultSet.getDouble(4);
                System.out.println("Produsul " + name + " cu pretul " + price + " maxim " + quantity + " bucati.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
