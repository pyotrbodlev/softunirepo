package app.managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class MinionManager {
    private Connection connection;

    public MinionManager(Connection connection) {
        this.connection = connection;
    }

    /**
     * Exercises N2 - Get Villains’ Names
     *
     * @author Pyotr Bodlev
     */
    public List<String> executeVillainsName() throws SQLException {
        String query = "SELECT v.name, COUNT(m.minion_id) AS count FROM minions_villains m JOIN villains v on m.villain_id = v.id GROUP BY v.name HAVING count > ? ORDER BY count DESC";

        PreparedStatement preparedStatement = this.connection.prepareStatement(query);

        preparedStatement.setInt(1, 10);

        ResultSet resultSet = preparedStatement.executeQuery();
        List<String> result = new ArrayList<>();

        while (resultSet.next()) {
            String row = resultSet.getString(1) + " " + resultSet.getString(2);

            result.add(row);
        }

        return result;
    }


    /**
     * Exercise N3 - Get Minion Names
     *
     * @return String with villains and his/her minions;
     */
    public String executeMinionsNames(int villainID) throws SQLException {
        String query = "SELECT v.name, m.name, m.age\n" +
                "FROM villains v\n" +
                "         LEFT JOIN minions_villains mv on mv.villain_id = v.id\n" +
                "         LEFT JOIN minions m on mv.minion_id = m.id\n" +
                "WHERE v.id = ?";

        PreparedStatement preparedStatement = this.connection.prepareStatement(query);

        preparedStatement.setInt(1, villainID);

        Map<String, List<String>> result = new LinkedHashMap<>();

        ResultSet resultSet = preparedStatement.executeQuery();

        int i = 0;

        while (resultSet.next()) {
            i++;
            String villainName = resultSet.getString(1);
            String minionName = resultSet.getString(2);
            int age = resultSet.getInt(3);

            if (!result.containsKey(villainName)) {
                result.put(villainName, new ArrayList<>());
            }

            if (minionName != null) {
                result.get(villainName).add(String.format("%d. %s %d", i, minionName, age));
            }
        }

        if (i == 0) {
            return String.format("No villain with ID %d exists in the database.", villainID);
        } else {
            String villainName = new ArrayList<String>(result.keySet()).get(0);
            StringBuilder resultStr = new StringBuilder("Villain: " + villainName + "\n");

            if (result.get(villainName).isEmpty()) {
                resultStr.append("<no minions>");
            } else {
                for (String s : result.get(villainName)) {
                    resultStr.append(s).append("\n");
                }
            }
            return resultStr.toString();
        }
    }

    /**
     *
     * Exercise N4 - Add Minion
     *
     * @param minionArgs
     * @param villainName
     * @return String lines of executed results
     * @throws SQLException
     */
    public String addMinionToDB(String minionArgs, String villainName) throws SQLException {
        StringBuilder resultStr = new StringBuilder();

        String[] tokens = minionArgs.split(" ");
        String minionName = tokens[0];
        int age = Integer.parseInt(tokens[1]);
        String town = tokens[2];

        String findTownByTownNameQuery = String.format("SELECT * FROM towns WHERE name LIKE '%s'", town);

        PreparedStatement preparedStatement = this.connection.prepareStatement(findTownByTownNameQuery);

        if (!preparedStatement.executeQuery().next()){
            String addTownToTownsSchemaQuery = String.format("INSERT INTO towns(name) VALUES ('%s')", town);

            PreparedStatement preparedStatement1 = this.connection.prepareStatement(addTownToTownsSchemaQuery);

            preparedStatement1.executeUpdate();
            resultStr.append(String.format("Town %s was added to the database.", town)).append(System.lineSeparator());
        }

        String findVillainByNameQuery = String.format("SELECT * FROM villains WHERE name LIKE '%s'", villainName);

        PreparedStatement villainsStmt = this.connection.prepareStatement(findVillainByNameQuery);

        if (!villainsStmt.executeQuery().next()){
            String addVillainQuery = String.format("INSERT INTO villains(name, evilness_factor) VALUES ('%s', 'evil')", villainName);

            PreparedStatement preparedStatement1 = this.connection.prepareStatement(addVillainQuery);
            preparedStatement1.executeUpdate();

            resultStr.append(String.format("Villain %s was added to the database.", villainName)).append(System.lineSeparator());
        }

        int villainId = this.getVillainIDByName(villainName);
        int townId = this.getTownIDbyName(town);

        String addMinionQuery = String.format("INSERT INTO minions(name, age, town_id) VALUES ('%s', %d, %d)", minionName, age, townId);

        PreparedStatement addMinionStmt = this.connection.prepareStatement(addMinionQuery);

        addMinionStmt.executeUpdate();

        resultStr.append(String.format("Successfully added %s to be minion of %s", minionName, villainName));

        int minionID = this.getMinionIDByName(minionName);

        this.addMinionAndVillainToMappingTable(minionID, villainId);

        return resultStr.toString();
    }

    private void addMinionAndVillainToMappingTable(int minionID, int villainId) throws SQLException {
        String query = "INSERT INTO minions_villains(minion_id, villain_id) VALUES (?, ?)";
        PreparedStatement preparedStatement = this.connection.prepareStatement(query);

        preparedStatement.setInt(1, minionID);
        preparedStatement.setInt(2, villainId);

        preparedStatement.executeUpdate();
    }

    private int getMinionIDByName(String minionName) throws SQLException {
        String query = String.format("SELECT id FROM minions WHERE name LIKE '%s'", minionName);

        PreparedStatement preparedStatement = this.connection.prepareStatement(query);

        ResultSet resultSet = preparedStatement.executeQuery();

        int id = 0;

        while (resultSet.next()){
            id =  resultSet.getInt(1);
        }

        return id;
    }

    private int getTownIDbyName(String town) throws SQLException {
        String query = String.format("SELECT id FROM towns WHERE name LIKE '%s'", town);

        PreparedStatement preparedStatement = this.connection.prepareStatement(query);

        ResultSet resultSet = preparedStatement.executeQuery();

        int id = 0;

        if (resultSet.next()){
            id =  resultSet.getInt(1);
        }

        return id;
    }

    private int getVillainIDByName(String villainName) throws SQLException {
        String query = String.format("SELECT id FROM villains WHERE name LIKE '%s'", villainName);

        PreparedStatement preparedStatement = this.connection.prepareStatement(query);

        ResultSet resultSet = preparedStatement.executeQuery();

        int id = 0;

        if (resultSet.next()){
            id =  resultSet.getInt(1);
        }

        return id;
    }


}
