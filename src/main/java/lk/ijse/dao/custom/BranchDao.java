package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDao;
import lk.ijse.entity.Branch;

import java.sql.SQLException;

public interface BranchDao extends CrudDao<Branch> {
    Branch search(String Id);
    boolean del(String Id) throws SQLException;
}
