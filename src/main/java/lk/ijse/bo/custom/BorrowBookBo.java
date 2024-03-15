package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBo;
import lk.ijse.dto.BookDto;
import lk.ijse.dto.BorrowedBooksDto;
import lk.ijse.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface BorrowBookBo extends SuperBo {
    boolean placeBorrow(User user, BookDto book) throws SQLException;
    List<BorrowedBooksDto> getAllBorrowedBooks();
}
