package model;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO {

    // devolver una lista de objetos
    public <T> List<T> selectQuery(Class<T> type,String query) throws SQLException;
}
