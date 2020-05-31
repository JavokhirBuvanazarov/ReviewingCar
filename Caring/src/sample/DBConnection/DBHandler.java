    package sample.DBConnection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHandler extends Configs {
    Connection dbconnection;

    public Connection getConnection(){
        String connectionString = "jdbc:mysql://localhost:3306/information_car?autoReconnect=true&useSSL=false";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            dbconnection = DriverManager.getConnection(connectionString, Configs.dbuser, Configs.dbpass);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return dbconnection;
    }
}
