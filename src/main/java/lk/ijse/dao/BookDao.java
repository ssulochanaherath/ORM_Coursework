package lk.ijse.dao;

import lk.ijse.entity.Book;
import org.hibernate.Session;

import java.util.List;

public interface BookDao {
    public Book generateNewId(Session session);
    public boolean save(Session session, Book book);
    public List<Book> getAll(Session session);

    boolean update(Session session, Book book);

}
