package lk.ijse.dao;

import lk.ijse.entity.UserBookDetails;
import org.hibernate.Session;

import java.util.List;

public interface TransactionDao {
    public UserBookDetails generateNewId(Session session);
    public boolean save(Session session, UserBookDetails transaction);
    public List<UserBookDetails> getAll(Session session);
}
