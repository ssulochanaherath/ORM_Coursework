package lk.ijse.dto.tm;

import com.jfoenix.controls.JFXButton;
import lk.ijse.entity.Book;
import lk.ijse.entity.User;

import java.time.LocalDateTime;

public class BorrowedBooksTm {
    private String gId;
    private LocalDateTime borrowedDate;
    private LocalDateTime returnDate;
    private Book bookId;
    private User uId;

    private JFXButton btnReturn;

    public BorrowedBooksTm() {
    }

    public BorrowedBooksTm(String gId, LocalDateTime borrowedDate, LocalDateTime returnDate, Book bookId, User uId) {
        this.gId = gId;
        this.borrowedDate = borrowedDate;
        this.returnDate = returnDate;
        this.bookId = bookId;
        this.uId = uId;
    }

    public BorrowedBooksTm(String gId, LocalDateTime borrowedDate, LocalDateTime returnDate, Book bookId, JFXButton btnReturn) {
        this.gId = gId;
        this.borrowedDate = borrowedDate;
        this.returnDate = returnDate;
        this.bookId = bookId;
        this.btnReturn = btnReturn;
    }

    public BorrowedBooksTm(LocalDateTime borrowedDate, LocalDateTime returnDate, Book bookId, JFXButton btnReturn) {
        this.borrowedDate = borrowedDate;
        this.returnDate = returnDate;
        this.bookId = bookId;
    }

    public String getgId() {
        return gId;
    }

    public void setgId(String bId) {
        this.gId = gId;
    }

    public LocalDateTime getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(LocalDateTime borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public Book getBookId() {
        return bookId;
    }

    public void setBookId(Book bookId) {
        this.bookId = bookId;
    }

    public User getuId() {
        return uId;
    }

    public void setuId(User uId) {
        this.uId = uId;
    }

    @Override
    public String toString() {
        return "BorrowedBooksTm{" +
                "gId='" + gId + '\'' +
                ", borrowedDate=" + borrowedDate +
                ", returnDate=" + returnDate +
                ", bookId=" + bookId +
                ", uId=" + uId +
                '}';
    }
}
