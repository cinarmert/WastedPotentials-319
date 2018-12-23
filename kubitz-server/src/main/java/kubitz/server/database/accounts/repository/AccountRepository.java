package kubitz.server.database.accounts.repository;

import kubitz.server.database.accounts.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends MongoRepository<Account, Long> {
    Account findAccountById(String id);
}
