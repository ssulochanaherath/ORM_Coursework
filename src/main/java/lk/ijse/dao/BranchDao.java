package lk.ijse.dao;

import lk.ijse.entity.Branch;
import org.hibernate.Session;

import java.util.List;

public interface BranchDao {
   public Branch generateNewId(Session session);
   public boolean save(Session session, Branch branch);
   public List<Branch> getAll(Session session);
   boolean update(Session session, Branch branch);
}
