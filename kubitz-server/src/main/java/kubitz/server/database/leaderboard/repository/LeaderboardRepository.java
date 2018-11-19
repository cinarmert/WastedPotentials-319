package kubitz.server.database.leaderboard.repository;

import kubitz.server.database.accounts.model.Account;
import kubitz.server.database.leaderboard.model.LeaderboardUser;
import org.springframework.data.mongodb.repository.MongoRepository;

// No need implementation, just one interface, and you have CRUD, thanks Spring Data
public interface LeaderboardRepository extends MongoRepository<LeaderboardUser, Long> {

}
