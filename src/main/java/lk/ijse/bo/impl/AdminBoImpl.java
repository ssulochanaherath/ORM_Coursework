package lk.ijse.bo.impl;

import lk.ijse.bo.AdminBo;
import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.AdminDao;
import lk.ijse.dao.impl.AdminDaoImpl;
import lk.ijse.dto.AdminDto;
import lk.ijse.entity.Admin;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class AdminBoImpl implements AdminBo {
AdminDao adminDao=new AdminDaoImpl();
    @Override
    public ArrayList<AdminDto> getAllAdmins() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        List<Admin> adminList = adminDao.getAll(session);
        ArrayList<AdminDto> adminDtos = new ArrayList<>();
        for (Admin admin:adminList){
            adminDtos.add(new AdminDto(admin.getEmail(), admin.getPassword(),admin.getName(),admin.getAddress(),admin.getContact()));
        }
        return adminDtos;

    }
}
