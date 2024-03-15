package lk.ijse.dao.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.BookDao;
import lk.ijse.entity.Book;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class BookDaoImpl implements BookDao {
    @Override
    public boolean save(Book entity) throws SQLException {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List<Book> getAll() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            Query query = session.createQuery("from books");
            return (List<Book>) query.list();
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public Book exists(String title) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            Query query = session.createQuery("select b from books b where title = :title");
            query.setParameter("title",title);
            List<Book> result = query.getResultList();
            if(!result.isEmpty()){
                return result.get(0);
            }else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(Book entity) throws SQLException {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            Query query = session.createQuery("update books set author = :author, genre = :genre,status = :status where title = :title");
            query.setParameter("author",entity.getAuthor());
            query.setParameter("genre",entity.getGenre());
            query.setParameter("status",entity.getStatus());
            query.setParameter("title",entity.getTitle());

            int rowCount = query.executeUpdate();
            if(rowCount > 0){
                transaction.commit();
                return true;
            }else {
                transaction.rollback();
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean delete(String title) throws SQLException {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Query query = session.createQuery("from books  where title = :title");
            query.setParameter("title",title);
            Book book = (Book) query.uniqueResult();

            if(book != null){
                session.delete(book);
                transaction.commit();
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public String getCount() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            Long count = (Long) session.createQuery("select count (*) from books ").getSingleResult();
            transaction.commit();
            return String.valueOf(count);
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public Book search(String title) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            Query query = session.createQuery("select b from books b where title = :title");
            query.setParameter("title",title);
            List<Book> result = query.getResultList();
            if(!result.isEmpty()){
                return result.get(0);
            }else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Book getId(String title) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            Query query = session.createQuery(" from books where title = :title");
            query.setParameter("title",title);

            Book book = (Book) query.uniqueResult();

            transaction.commit();
            return book;
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean updateStatus(Book entity) throws SQLException {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            Query query = session.createQuery("update books set status = :status where title = :title");
            query.setParameter("status",entity.getStatus());
            query.setParameter("title",entity.getTitle());

            int rowCount = query.executeUpdate();
            if(rowCount > 0){
                transaction.commit();
                return true;
            }else {
                transaction.rollback();
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public Book getWithBorrowedBooks(String bookId) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        try {
            Transaction transaction = session.beginTransaction();

            Book book = session.createQuery(
                            "SELECT b FROM books b LEFT JOIN FETCH b.borrowedBooks WHERE b.id = :bookId", Book.class)
                    .setParameter("bookId", bookId)
                    .uniqueResult();

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }

        return null;
    }
}
