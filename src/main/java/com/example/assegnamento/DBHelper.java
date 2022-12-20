package com.example.assegnamento;
import java.sql.*;

public class DBHelper {

    public static ResultSet query(String sql)  //produce risultati di una query
    {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wineshop","root","");

            Statement statement = con.createStatement();

            return statement.executeQuery(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void update(String sql)    //executeUpdate per aggiungere record o modificarli(?)
    {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wineshop","root","");

            Statement statement = con.createStatement();

            statement.executeUpdate(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
