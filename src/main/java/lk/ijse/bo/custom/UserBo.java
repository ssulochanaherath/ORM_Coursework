package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBo;
import lk.ijse.dto.UserDto;

import java.sql.SQLException;

public interface UserBo extends SuperBo {
    boolean saveUser(UserDto dto) throws SQLException;
    UserDto userSignIn(String username, String password);
    boolean updateUser(UserDto dto) throws SQLException;
    UserDto getUserId(String username) throws SQLException;
    String getUserCount() throws SQLException;
}
