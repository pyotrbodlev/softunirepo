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


}
