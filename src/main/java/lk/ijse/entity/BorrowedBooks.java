package lk.ijse.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity(name = "borrowedBooks")
public class BorrowedBooks {
    @Id
    @GeneratedValue(generator = "Borrowed-Id-Generator")
    @GenericGenerator(name = "Borrowed-Id-Generator", strategy = "lk.ijse.util.BorrowedIdGenerator")
    private String gId;
    @ManyToOne
    @JoinColumn(name = "user",referencedColumnName = "uId")
    private User user;
    @ManyToOne
    @JoinColumn(name = "book",referencedColumnName = "bId")
    private Book book;
    private LocalDateTime borrowedDate;
    private LocalDateTime returnDate;

    public BorrowedBooks() {
    }

    public BorrowedBooks(String gId, User user, Book book, LocalDateTime borrowedDate, LocalDateTime returnDate) {
        this.gId = gId;
        this.user = user;
        this.book = book;
        this.borrowedDate = borrowedDate;
        this.returnDate = returnDate;
    }

    public String getgId() {
        return gId;
    }

    public void setgId(String gId) {
        this.gId = gId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
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

    @Override
    public String toString() {
        return "BorrowedBooks{" +
                "gId='" + gId + '\'' +
                ", user=" + user +
                ", book=" + book +
                ", borrowedDate=" + borrowedDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
