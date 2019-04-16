package app.main;

import app.core.Engine;
import app.managers.MinionManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Applocation {
    public static void main(String[] args) throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "1234");


        Connection connection =
                DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", properties);
        MinionManager minionManager = new MinionManager(connection);

        Engine engine = new Engine(minionManager);

        engine.run();
    }
}
