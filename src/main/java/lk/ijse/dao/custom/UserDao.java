package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDao;
import lk.ijse.entity.User;

public interface UserDao extends CrudDao<User> {
    User signIn(String username, String password);
    String get(String username);
}
