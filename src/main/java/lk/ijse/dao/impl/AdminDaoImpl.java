package lk.ijse.dao.impl;

import lk.ijse.dao.AdminDao;
import lk.ijse.entity.Admin;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class AdminDaoImpl implements AdminDao {

    @Override
    public List<Admin> getAll(Session session) {
        String hql=" FROM Admin ";
        Query<Admin> query = session.createQuery(hql,Admin.class);
        return query.list();
    }
}
