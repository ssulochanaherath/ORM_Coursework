package lk.ijse.dao;

import lk.ijse.dao.impl.*;

public class DaoFactory {
    private static DaoFactory daoFactory;

    private DaoFactory(){

    }

    public static DaoFactory getDaoFactory(){
        return daoFactory == null ? daoFactory = new DaoFactory() : daoFactory;
    }

    public enum DaoTypes{
        ADMIN,BOOK,BRANCH,USER,BORROWEDBOOKS
    }

    public SuperDao getDao(DaoTypes types){
        switch (types){
            case ADMIN:
                return new AdminDaoImpl();
            case BOOK:
                return new BookDaoImpl();
            case BRANCH:
                return new BranchDaoImpl();
            case USER:
                return new UserDaoImpl();
            case BORROWEDBOOKS:
                return new BorrowedDaoImpl();
            default:
                return null;
        }
    }
}
