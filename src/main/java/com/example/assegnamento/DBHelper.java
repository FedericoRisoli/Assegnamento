package com.example.assegnamento;
import java.sql.*;

public class DBHelper {

    public static ResultSet query(String q)// mettere ResultSet invece di int
    {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wineshop","root","");

            Statement statement = con.createStatement();

            ResultSet resultSet = statement.executeQuery(q);

            return resultSet;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




}
