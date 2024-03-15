package lk.ijse.dao.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.BranchDao;
import lk.ijse.entity.Branch;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class BranchDaoImpl implements BranchDao {
    @Override
    public boolean save(Branch entity) throws SQLException {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List<Branch> getAll() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            Query query = session.createQuery("from branches ");
            return (List<Branch>) query.list();
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public Branch exists(String title) {
        return null;
    }

    @Override
    public boolean update(Branch entity) throws SQLException {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            Query query = session.createQuery("update branches set bNumber = :bNumber, status = :status where bId = :bId");
            query.setParameter("bNumber",entity.getbNumber());
            query.setParameter("status",entity.getStatus());
            query.setParameter("bId",entity.getbId());

            int rowCount = query.executeUpdate();
            if(rowCount > 0 ){
                transaction.commit();
                return true;
            }else{
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
            Long count = (Long) session.createQuery("select count (*) from branches ").getSingleResult();
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
    public Branch search(String Id) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            Query query = session.createQuery("select b from branches b where bId = :bId");
            query.setParameter("bId",Id);
            List<Branch> result = query.getResultList();
            if(!result.isEmpty()){
                return result.get(0);
            }else{
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean del(String Id) throws SQLException {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            Query query = session.createQuery("from branches where bId = :bId");
            query.setParameter("bId",Id);
            Branch branch = (Branch) query.uniqueResult();

            if(branch != null){
                session.delete(branch);
                transaction.commit();
                return true;
            }else {
                return false;
            }
        }catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }
}
