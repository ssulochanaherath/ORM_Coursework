package lk.ijse.bo;

import lk.ijse.dto.TransactionDto;

import java.util.List;

public interface TransactionBo {
    public String generateNewTransactionId();
    public boolean saveTransaction( TransactionDto transactionDto);
    public List<TransactionDto> getAllTransactions();
}
