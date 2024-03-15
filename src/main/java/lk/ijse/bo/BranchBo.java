package lk.ijse.bo;

import lk.ijse.dto.BranchDto;

import java.util.List;

public interface BranchBo {
    public String generateNewBranchId();
    public boolean saveBranch(BranchDto branchDto);
    public List<BranchDto> loadAllBranch();
    public boolean updateBranch(BranchDto branchDto);
}
