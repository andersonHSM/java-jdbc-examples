package application;

import db.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        Connection connection;
        Statement statement = null;
        ResultSet resultSet = null;

        connection = DB.getConnection();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery( "SELECT * FROM department");

            while (resultSet.next()) {
                System.out.println(resultSet.getInt("Id")+ " | " + resultSet.getString( "Name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeResultSet(resultSet);
            DB.closeStatement(statement);
            DB.closeConnection();
        }

    }
}