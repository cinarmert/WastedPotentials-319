package kubitz.server.database.accounts.repository;

import kubitz.server.database.accounts.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends MongoRepository<Account, Long> {

    List<Account> findAccountsByName(String name);
    Account findAccountById(String id);
}
