package application;

import db.DB;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Connection connection;
        PreparedStatement statement = null;

        try {
            connection = DB.getConnection();
            statement = connection.prepareStatement(
                    "INSERT INTO seller (Name, Email, BirthDate, BaseSalary, DepartmentId) VALUES " +
                            "(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS
            );

            statement.setString(1, "Carl Purple");
            statement.setString(2, "carl@gmail.com");
            statement.setDate(3, new java.sql.Date(simpleDateFormat.parse("22/04/1985").getTime()));

            statement.setDouble(4, 3000.0);
            statement.setInt(5, 4);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet resultSet = statement.getGeneratedKeys();
                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    System.out.println("Done! Id = " + id);
                }
            } else System.out.println("No rows affected!");
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        } finally {
            DB.closeStatement(statement);
            DB.closeConnection();
        }

    }
}