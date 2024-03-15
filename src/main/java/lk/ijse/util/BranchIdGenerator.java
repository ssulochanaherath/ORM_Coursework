package lk.ijse.util;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class BranchIdGenerator implements IdentifierGenerator {
    @Override
    public Object generate(SharedSessionContractImplementor session, Object o) {
        String prefix = "B-";
        Long count = (Long) session.createQuery("SELECT COUNT(*) FROM branches").uniqueResult();
        String formattedCount = String.format("%03d",count + 1);
        return prefix + formattedCount;
    }
}
