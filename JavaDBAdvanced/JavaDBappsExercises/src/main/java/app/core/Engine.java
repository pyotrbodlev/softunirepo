package app.core;

import app.managers.MinionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Engine implements Runnable {

    private Connection connection;
    private MinionManager manager;


    public Engine(MinionManager manager) {
        //this.connection = connection;
        this.manager = manager;
    }

    @Override
    public void run() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Minion: ");
            String minionArgs = scanner.nextLine();
            System.out.println("Villain: ");
            String villainName = scanner.nextLine();

            System.out.println(this.manager.addMinionToDB(minionArgs, villainName));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
