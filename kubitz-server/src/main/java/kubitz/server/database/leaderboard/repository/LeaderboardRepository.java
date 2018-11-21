package kubitz.server.database.leaderboard.repository;

import kubitz.server.database.leaderboard.model.LeaderboardUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LeaderboardRepository extends MongoRepository<LeaderboardUser, Long> {

}
