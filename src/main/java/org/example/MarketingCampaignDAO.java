package org.example;

import org.example.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MarketingCampaignDAO {

    private final Connection connection;

    public MarketingCampaignDAO(Connection connection) {
        this.connection = connection;
    }

    public void createTable() {
        try {
            Statement statement = connection.createStatement();

            statement.execute("create table if not exists marketing_campaign (" +
                    "id int primary key auto_increment," +
                    "name varchar (200)," +
                    "start_date date," +
                    "budget double);");
        } catch (SQLException e) {
            System.out.println("Error creating table marketing_campaign: ");
            throw new RuntimeException(e);
        }
    }

    public void initialized() {
        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select count(*) from marketing_campaign;");
            if (resultSet.next() && resultSet.getInt(1) == 0) {
                statement.execute("insert into marketing_campaign (name,start_date,budget) values " +
                        "('Name1','2022-12-18',1000),('Name2','2022-10-15',2000),('Name3','2021-10-20',500)");
                System.out.println("The table marketing_campaign was initialized!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void create(MarketingCampaign marketingCampaign) {
        try {

            Statement statement = connection.createStatement();
            statement.execute("insert into marketing_campaign (name,start_date,budget) values" +
                    "('" + marketingCampaign.getName() + "', '" + marketingCampaign.getStartDate() + "', " + marketingCampaign.getBudget()+ ");" );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createWithPreparedStatement(MarketingCampaign marketingCampaign) {
        try {
            System.out.println("Preparing to insert " + marketingCampaign.toString());
            PreparedStatement preparedStatement = connection.prepareStatement("insert into marketing_campaign (name,start_date,budget) values (?, ?, ?);");
            preparedStatement.setString(1, marketingCampaign.getName());
            preparedStatement.setDate(2, marketingCampaign.getStartDate());
            preparedStatement.setDouble(3, marketingCampaign.getBudget());
            preparedStatement.executeUpdate();
            System.out.println("Preparing was successfully.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // find name


    public List<MarketingCampaign> searchByName(String name) {
        List<MarketingCampaign> addList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from marketing_campaign where name like ?;");
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                MarketingCampaign marketingCampaign = new MarketingCampaign(null, resultSet.getString(2), resultSet.getDate(3), resultSet.getDouble(4));
                addList.add(marketingCampaign);
            }
            return addList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // by date

    public List<MarketingCampaign> searchBetween(Date startDate, Date endDate) {
        List<MarketingCampaign> addDateList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from marketing_campaign where start_date >= ? and start_date <= ?;");
            preparedStatement.setDate(1,startDate);
            preparedStatement.setDate(2,endDate);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                MarketingCampaign marketingCampaign = new MarketingCampaign(null, resultSet.getString(2), resultSet.getDate(3), resultSet.getDouble(4));
                addDateList.add(marketingCampaign);
            }
            return addDateList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }





    //findAll <- return a List Set of MarketingCampaign


    public List<MarketingCampaign> findAll(){
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from marketing_campaign");
            List<MarketingCampaign> marketingCampaignList = new ArrayList<>();

            while (resultSet.next()){
                MarketingCampaign marketingCampaign = new MarketingCampaign(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDate(3),
                        resultSet.getDouble(4));

                marketingCampaignList.add(marketingCampaign);
            }
            return marketingCampaignList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}