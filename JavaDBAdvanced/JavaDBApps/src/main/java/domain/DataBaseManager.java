package domain;

import java.sql.SQLException;
import java.util.List;

public interface DataBaseManager<T> {

    List<T> getAllEntitiesFromDatabase() throws SQLException;

    void insert(T object) throws SQLException;

    String getSchemaName();

    List<T> getEntitiesByName(String name) throws SQLException;
}
