package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDao;
import lk.ijse.entity.Admin;

public interface AdminDao extends CrudDao<Admin> {
    Admin signIn(String username, String password);
}
