package src.main.application;

import src.main.db.DB;
import src.main.db.examples.DbExamples;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection connection;

        connection = DB.getConnection();

//        DbExamples.sampleSelect(connection);
//        DbExamples.sampleInsert(connection);
        DbExamples.sampleUpdate(connection);


        DB.closeConnection();

    }
}