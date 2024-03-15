package lk.ijse.bo.impl;

import lk.ijse.bo.custom.BranchBo;
import lk.ijse.dao.DaoFactory;
import lk.ijse.dao.custom.BranchDao;
import lk.ijse.dto.BranchDto;
import lk.ijse.entity.Branch;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BranchBoImpl implements BranchBo {
    BranchDao branchDao = (BranchDao) DaoFactory.getDaoFactory().getDao(DaoFactory.DaoTypes.BRANCH);
    @Override
    public boolean saveBranch(BranchDto dto) throws SQLException {
        return branchDao.save(new Branch(dto.getbId(), dto.getAddress(), dto.getbNumber(), dto.getStatus(), dto.getAdminId()));
    }

    @Override
    public List<BranchDto> getAllBranches() {
        List<Branch> branches = branchDao.getAll();
        List<BranchDto> branchDtos = new ArrayList<>();
        for(Branch branch : branches){
            branchDtos.add(new BranchDto(branch.getbId(), branch.getAddress(), branch.getbNumber(), branch.getStatus(), branch.getAdminId()));
        }
        return branchDtos;
    }

    @Override
    public BranchDto search(String Id) {
        Branch branch = branchDao.search(Id);
        BranchDto branchDto = new BranchDto(branch.getbId(), branch.getAddress(), branch.getbNumber(), branch.getStatus(), branch.getAdminId());
        return branchDto;
    }

    @Override
    public boolean updateBranch(BranchDto dto) throws SQLException {
        return branchDao.update(new Branch(dto.getbId(), dto.getAddress(), dto.getbNumber(), dto.getStatus(), dto.getAdminId()));
    }

    @Override
    public boolean deleteBranch(String Id) throws SQLException {
        return branchDao.del(Id);
    }

    @Override
    public String getBranchCount() throws SQLException {
        return branchDao.getCount();
    }
}
