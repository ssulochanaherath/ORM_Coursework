package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDao;
import lk.ijse.entity.Book;

import java.sql.SQLException;

public interface BookDao extends CrudDao<Book> {
    Book getId(String title);

    boolean updateStatus(Book entity) throws SQLException;
    public Book getWithBorrowedBooks(String bookId);
}
