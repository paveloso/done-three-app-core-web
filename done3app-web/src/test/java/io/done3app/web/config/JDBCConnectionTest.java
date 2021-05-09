package io.done3app.web.config;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCConnectionTest {

    String user = "root";
    String pass = "root";
    String url = "jdbc:mysql://localhost:3306/donethreeapp?useSSL=false";
    String driver = "com.mysql.jdbc.Driver";


    @Test
    public void jdbcMysqlConnectionTest() {
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, user, pass);
            connection.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
