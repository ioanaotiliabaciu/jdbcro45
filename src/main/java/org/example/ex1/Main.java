package org.example.ex1;

import org.example.DataBaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {


        //display all products
        try {
            Statement statement = DataBaseConnection.getConnection().createStatement();

            int rowCount = statement.executeUpdate("insert into product (name,type,price,quantity) values " +
                    "('Ivy','IDK',12,12)," + "('Unu','IDK2',2,1);");

            System.out.println("Row count= " + rowCount); // ne a dat rowCount = 1 pt ca a introdus un rand

            ResultSet productSet = statement.executeQuery("SELECT * FROM product");
            while(productSet.next()){
                System.out.print("Product: " + productSet.getString(2));
                System.out.print(" With price: " + productSet.getDouble(3));
                System.out.println(" and quantity: " + productSet.getInt(4));
            }

            ResultSet mostExpensiveProduct = statement.executeQuery(
                    "SELECT * FROM product WHERE price = (SELECT MAX(price) FROM product)");
            mostExpensiveProduct.next();
            System.out.println("Most Expensive product is " + mostExpensiveProduct.getString(2)
                    + " with a price of " + mostExpensiveProduct.getDouble(3));

            System.out.println("-------------------------------------------------------------");
            displayAllProductsFrom("Magnolia");

            System.out.println("-----------------------------------------------------------");
            System.out.println();
            displayAllProductsBetweenTheRange(10,10);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    static void displayAllProductsFrom(String supplierName) throws SQLException {

        Statement statement = DataBaseConnection.getConnection().createStatement();
        ResultSet result = statement.executeQuery("select supplier2.name, product.name from supplier2 " +
                "join supplier2_product_connection as pc on pc.supplier_id = supplier2.id " +
                "join product on pc.product_id = product.id " +
                "where supplier2.name = '" + supplierName + "';");
        while(result.next()){
            System.out.print("Supplier name: " + result.getString(2) + "Product name: " + result.getString(2));
        }
    }

    //all flower that are more than Quantity Q and less than Price P.

    static void displayAllProductsBetweenTheRange(int quantity , double price) throws SQLException {
        Statement statement = DataBaseConnection.getConnection().createStatement();

        //daca nu precisez eu ordinea, o ia din workbench, altfel ia ordinea mea 1 -> name, 2-> price, 3-> quantity
        ResultSet result = statement.executeQuery("select name,price,quantity from product where quantity >= " + quantity +
                " and price <= " + price + ";");
        while (result.next()){
            System.out.println(result.getString(1) +" The quantity is: " + result.getDouble(3) + " and the price is : " + result.getDouble(2));
        }
    }
}