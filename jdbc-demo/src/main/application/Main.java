package src.main.application;

import db.DB;
import db.examples.DbExamples;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection connection;

        connection = DB.getConnection();

//        DbExamples.sampleSelect(connection);
//        DbExamples.sampleInsertSeller(connection);
//        DbExamples.sampleDepartmentInsert(connection);
//        DbExamples.sampleUpdate(connection);
//        DbExamples.sampleDeleteUpdate(connection, 3);
        DbExamples.sampleTransaction(connection);


        DB.closeConnection();

    }
}