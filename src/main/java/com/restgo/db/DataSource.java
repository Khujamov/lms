package com.restgo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://db4free.net:3306/lmsiutdb";
    private static final String PASSWORD = "LdUCjUcsXy#2J@4";
    private static final String USERNAME = "lms1234";
    private static DataSource instance;
    private Connection connection;

    private DataSource(){
        try {
            Class.forName(DRIVER_CLASS_NAME);
            connection = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DataSource getInstance() throws SQLException {
        if (instance == null){
            instance = new DataSource();
        }else if ((instance.getConnection().isClosed())){
            instance = new DataSource();
        }
        return instance;
    }

    public  Connection getConnection(){
        return connection;
    }

}
