package lk.ijse.bo;

import lk.ijse.bo.impl.*;

public class BoFactory {
    private static BoFactory boFactory;

    private BoFactory(){

    }

    public static BoFactory getBOFactory(){
        return boFactory == null ? boFactory = new BoFactory() : boFactory;
    }

    public enum BoTypes{
        ADMIN,BOOK,BRANCH,USER,BORROWEDBOOK
    }

    public SuperBo getBo(BoTypes types){
        switch (types){
            case ADMIN:
                return new AdminBoImpl();
            case BOOK:
                return new BookBoImpl();
            case BRANCH:
                return new BranchBoImpl();
            case USER:
                return new UserBoImpl();
            case BORROWEDBOOK:
                return new BorrowedBookBoImpl();
            default:
                return null;
        }
    }
}
