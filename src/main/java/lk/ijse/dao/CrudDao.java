package lk.ijse.dao;

import java.sql.SQLException;
import java.util.List;

public interface CrudDao<T> extends SuperDao {
    boolean save(T entity) throws SQLException;
    List<T> getAll();
    T exists(String title);
    boolean update(T entity) throws SQLException;
    boolean delete(String title) throws SQLException;
    String getCount();
    T search(String title);
}
