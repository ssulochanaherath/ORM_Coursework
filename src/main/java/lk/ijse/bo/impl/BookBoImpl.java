package lk.ijse.bo.impl;

import lk.ijse.bo.custom.BookBo;
import lk.ijse.dao.DaoFactory;
import lk.ijse.dao.custom.BookDao;
import lk.ijse.dto.BookDto;
import lk.ijse.entity.Book;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookBoImpl implements BookBo {

    BookDao bookDao = (BookDao) DaoFactory.getDaoFactory().getDao(DaoFactory.DaoTypes.BOOK);
    @Override
    public boolean saveBook(BookDto dto) throws SQLException {
        return bookDao.save(new Book(dto.getbId(), dto.getTitle(), dto.getAuthor(), dto.getGenre(), dto.getStatus()));
    }

    @Override
    public List<BookDto> getAllBooks() {
        List<Book> books = bookDao.getAll();
        List<BookDto> bookDtos = new ArrayList<>();
        for(Book book : books){
            bookDtos.add(new BookDto(book.getbId(),book.getTitle(),book.getAuthor(),book.getGenre(),book.getStatus()));
        }
        return bookDtos;
    }

    @Override
    public BookDto existsBook(String title) {
        Book book = bookDao.exists(title);
        BookDto bookDto = new BookDto(book.getAuthor(), book.getGenre(), book.getStatus());
        return bookDto;
    }

    @Override
    public boolean updateBook(BookDto dto) throws SQLException {
        return bookDao.update(new Book(dto.getbId(), dto.getTitle(), dto.getAuthor(), dto.getGenre(), dto.getStatus()));
    }

    @Override
    public boolean deleteBook(String title) throws SQLException {
        return bookDao.delete(title);
    }

    @Override
    public String getBookCount() throws SQLException {
        return bookDao.getCount();
    }

    @Override
    public BookDto searchBook(String title) {
        Book book = bookDao.search(title);
        BookDto bookDto = new BookDto(book.getbId(),book.getTitle(),book.getAuthor(),book.getGenre(),book.getStatus());
        return bookDto;
    }

    @Override
    public BookDto getBookId(String title) {
        Book book = bookDao.getId(title);
        BookDto bookDto = new BookDto(book.getbId());
        return bookDto;
    }

    @Override
    public boolean updateBookStatus(BookDto dto) throws SQLException {
        return bookDao.updateStatus(new Book(dto.getStatus()));
    }

    @Override
    public BookDto getBookWithBorrowedBooks(String bookId) {
        return null;
    }


    private BookDto convertToBookDto(Book book) {
        if(book == null){
            return null;
        }

        BookDto bookDto = new BookDto();
        bookDto.setbId(book.getbId());
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setGenre(book.getGenre());
        bookDto.setStatus(book.getStatus());

        return bookDto;
    }
}
