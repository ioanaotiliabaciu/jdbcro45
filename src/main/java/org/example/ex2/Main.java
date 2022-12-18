package org.example.ex2;

import org.example.DataBaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {

        try {
            Statement statement = DataBaseConnection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM client");
            while (resultSet.next()){
                System.out.println("ID: " + resultSet.getInt(1) + " name: " + resultSet.getString(2)
                        + " Phone: " + resultSet.getString(3) + " Email: " + resultSet.getString(4)+
                        " Birthdate: " + resultSet.getString(5));
            }

            //int result = statement.executeUpdate("alter table client add column cnp varchar(20);");
            int result2 = statement.executeUpdate("update client set cnp = 2 where name = 'Mihai'; ");
            System.out.println(result2);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}