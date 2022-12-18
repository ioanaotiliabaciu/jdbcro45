package org.example;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

public class DatabaseConnection {

    public final String DATABASE_URL = "jdbc:mysql://localhost:3306/flower_shop";
    public final static String DATABASE_USER = "root";
    private final static String DATABASE_PASSWORD = "iustin555";

    private static Connection connection;

    public static void main(String[] args) throws SQLException {

        Connection connection = DatabaseConnection.getConnection();

        createTableMarketingCampaign(connection);
    }

    public static void createTableMarketingCampaign(Connection connection) throws SQLException {

        try {
            Statement statement = connection.createStatement();

            statement.execute("create table if not exists marketing_campaign (" +
                    " id int primary key auto_increment," +
                    " name varchar(200)," +
                    "budget double);");
        } catch ( SQLException e ){
            System.out.println("Error creating table marketing_campaign. ");
            throw new RuntimeException(e);
        }
    }

//    public static Connection getConnection(){
//        if(connection == null ) {
//            MysqlDataSource dataSource = new MysqlDataSource();
//            dataSource.setUrl("jdbc:mysql://flower_shop");
//            dataSource.setUser("root");
//
//            try {
//                connection = dataSource.getConnection();
//                return connection;
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//
//        } else {
//            return connection;
//        }
//    }

}
