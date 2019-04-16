package core;

import domain.DataBaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class JdbcDataBaseManager<T> implements DataBaseManager<T> {
    private Connection connection;

    public JdbcDataBaseManager(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<T> getAllEntitiesFromDatabase() throws SQLException {
        PreparedStatement stmt = connection.prepareStatement

                ("SELECT * FROM " + this.getSchemaName());

        return toList(stmt.executeQuery());
    }

    public abstract List<T> toList(ResultSet executeQuery) throws SQLException;

    @Override
    public void insert(T object) throws SQLException {
        String query = "INSERT INTO " + this.getSchemaName() + "(" + this.getFieldsTitles() + ")" + " VALUES " + "(" + this.getObjectValuesAsString(object) + ")";

        PreparedStatement preparedStatement =
                connection.prepareStatement
                        (query);

        preparedStatement.executeUpdate();
    }

    protected abstract String getObjectValuesAsString(T object);

    protected abstract String getFieldsTitles();

    @Override
    public List<T> getEntitiesByName(String name) throws SQLException {
        String strName = "'" + name + "'";
        PreparedStatement stmt = connection.prepareStatement

                ("SELECT * FROM " + this.getSchemaName() + " WHERE first_name LIKE " + strName);

        return toList(stmt.executeQuery());
    }
}
