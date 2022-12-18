package org.example.ex3;


import org.example.DataBaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// ceate a table
//insert a row/multiple rows in a table if the table is empty
//display all values in a table
//search for a row by table
public class Main {

    public static void main(String[] args) throws SQLException {

        Connection connection = DataBaseConnection.getConnection();


        crateTableMarketing(connection);

        initializeMarketingCampaign(connection);
    }

    public static void crateTableMarketing(Connection connection) {

        try {
            Statement statement = DataBaseConnection.getConnection().createStatement();

            statement.execute("CREATE TABLE IF NOT EXISTS marketing_campaign (" +
                    "id int primary key auto_increment," +
                    "name varchar(200)," + "start_date date," + "budget double);");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void initializeMarketingCampaign(Connection connection){

        try {
            Statement statement = DataBaseConnection.getConnection().createStatement();

            ResultSet resultSet = statement.executeQuery("select count(*) from marketing_campaign;");
            if(resultSet.next() && resultSet.getInt(1) == 0){
                statement.execute("insert into marketing_campaign (name,start_date,budget) values "+
                        "('Name1','2022-12-18',1000),('Name2','2022-10-15',2000),('Name3','2021-10-20',500)");
                System.out.println("The table marketing_campaign was initialized!");

            }else{
                System.out.println("The tale was already initialized!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}