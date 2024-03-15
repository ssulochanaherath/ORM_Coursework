package lk.ijse.dao;

import lk.ijse.entity.Admin;
import org.hibernate.Session;

import java.util.List;

public interface AdminDao {
    public List<Admin> getAll(Session session);
}
