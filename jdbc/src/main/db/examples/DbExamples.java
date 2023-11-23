package src.main.db.examples;

import src.main.db.DB;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DbExamples {


    public static void sampleSelect(Connection connection) {
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM department");

            while (resultSet.next()) {
                System.out.println(resultSet.getInt("Id") + " | " + resultSet.getString("Name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeResultSet(resultSet);
            DB.closeStatement(statement);
        }
    }

    public static void sampleInsert(Connection connection) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        PreparedStatement statement = null;

        try {
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
        }
    }

    public static void sampleUpdate(Connection connection) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(
                    "UPDATE seller "
                            + "SET BaseSalary = BaseSalary + ? "
                            + "WHERE "
                            + "(DepartmentId = ?)"
            );

            statement.setDouble(1, 200.0);
            statement.setInt(2, 2);


            int rowsAffected = statement.executeUpdate();


            System.out.println("Done! Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeStatement(statement);
            DB.closeConnection();
        }
    }
}
