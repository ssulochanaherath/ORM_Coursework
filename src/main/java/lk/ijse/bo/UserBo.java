package lk.ijse.bo;

import lk.ijse.dto.UserDto;

import java.util.ArrayList;

public interface UserBo {
    public boolean saveUser(UserDto dto);
    public ArrayList<UserDto> getAllUsers();
    public boolean updateUser(UserDto dto);
}
