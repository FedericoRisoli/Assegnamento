package server;
import javafx.scene.control.TextField;

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

    public static void update(String sql)    //executeUpdate per aggiungere record o modificarli
    {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wineshop","root","");

            Statement statement = con.createStatement();

            statement.executeUpdate(sql);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static void execute(String sql)    //execute per rimuovere dal db
    {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wineshop","root","");

            Statement statement = con.createStatement();

            statement.execute(sql);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static int idgetter(TextField user)    //return id
    {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wineshop","root","");

            Statement statement = con.createStatement();

            ResultSet i=statement.executeQuery("SELECT `id` FROM `utenti` WHERE `username` LIKE \""+user.getText()+"\"");
            if(i.next())
            {
                return  i.getInt("id");
            }
            return i.getInt("id");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
