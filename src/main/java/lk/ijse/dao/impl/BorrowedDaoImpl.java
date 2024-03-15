package lk.ijse.dao.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.BorrowedBooksDao;
import lk.ijse.entity.BorrowedBooks;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class BorrowedDaoImpl implements BorrowedBooksDao {
    @Override
    public boolean save(BorrowedBooks entity) throws SQLException {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List<BorrowedBooks> getAll() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            Query query = session.createQuery("from borrowedBooks");
            return (List<BorrowedBooks>) query.list();
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public BorrowedBooks exists(String title) {
        return null;
    }

    @Override
    public boolean update(BorrowedBooks entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String title) throws SQLException {
        return false;
    }

    @Override
    public String getCount() {
        return null;
    }

    @Override
    public BorrowedBooks search(String title) {
        return null;
    }
}
