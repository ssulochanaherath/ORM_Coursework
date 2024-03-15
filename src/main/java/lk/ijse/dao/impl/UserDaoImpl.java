package lk.ijse.dao.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.UserDao;
import lk.ijse.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean save(User entity) throws SQLException {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User exists(String title) {
        return null;
    }

    @Override
    public boolean update(User entity) throws SQLException {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            Query query = session.createQuery("update user set password = :password, confirmPassword = :confirmPassword where userName = :userName");
            query.setParameter("password",entity.getPassword());
            query.setParameter("confirmPassword",entity.getConfirmPassword());
            query.setParameter("userName",entity.getUserName());

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
        return false;
    }

    @Override
    public String getCount() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            Long count = (Long) session.createQuery("select count (*) from user ").getSingleResult();
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
    public User search(String title) {
        return null;
    }

    @Override
    public User signIn(String username, String password) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery("select u from user u where userName = ?1 and password = ?2");
            query.setParameter(1,username);
            query.setParameter(2,password);
            return (User) query.getSingleResult();
        }catch (Exception e){
            transaction.rollback();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public String get(String username) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            Query query = session.createQuery("select uId from user where userName = :userName");
            query.setParameter("userName",username);
            return (String) query.uniqueResult();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }
}
