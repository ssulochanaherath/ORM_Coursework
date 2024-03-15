package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBo;
import lk.ijse.dto.BranchDto;

import java.sql.SQLException;
import java.util.List;

public interface BranchBo extends SuperBo {
    boolean saveBranch(BranchDto dto) throws SQLException;
    List<BranchDto> getAllBranches();
    BranchDto search(String Id);
    boolean updateBranch(BranchDto dto) throws SQLException;
    boolean deleteBranch(String Id) throws SQLException;
    String getBranchCount() throws SQLException;
}
