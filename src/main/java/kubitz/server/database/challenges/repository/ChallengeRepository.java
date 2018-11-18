package kubitz.server.database.challenges.repository;

import kubitz.server.database.accounts.model.Account;
import kubitz.server.database.challenges.model.Challenge;
import org.springframework.data.mongodb.repository.MongoRepository;

// No need implementation, just one interface, and you have CRUD, thanks Spring Data
public interface ChallengeRepository extends MongoRepository<Challenge, Long> {
}
