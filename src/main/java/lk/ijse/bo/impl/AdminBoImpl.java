package lk.ijse.bo.impl;

import lk.ijse.bo.custom.AdminBo;
import lk.ijse.dao.DaoFactory;
import lk.ijse.dao.custom.AdminDao;
import lk.ijse.dto.AdminDto;
import lk.ijse.entity.Admin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminBoImpl implements AdminBo {

    AdminDao adminDao = (AdminDao) DaoFactory.getDaoFactory().getDao(DaoFactory.DaoTypes.ADMIN);
    @Override
    public boolean saveAdmin(AdminDto dto) throws SQLException {
        return adminDao.save(new Admin(dto.getAdminId(), dto.getUserName(), dto.getPassword(), dto.getConfirmPassword()));
    }

    @Override
    public AdminDto adminSignIn(String username, String password) {
        Admin admin = adminDao.signIn(username, password);
        if(admin != null){
            AdminDto adminDto = new AdminDto(admin.getUserName(),admin.getPassword());
            return adminDto;
        }else {
            return null;
        }
    }

    @Override
    public List<AdminDto> getAllAdmin() {
        List<Admin> admins = adminDao.getAll();
        List<AdminDto> adminDtos = new ArrayList<>();
        for(Admin admin : admins){
            adminDtos.add(new AdminDto(admin.getAdminId(),admin.getUserName(), admin.getPassword(), admin.getConfirmPassword()));
        }
        return adminDtos;
    }

}
