package kubitz.server.database.dailychallenge.repository;

import kubitz.server.database.dailychallenge.model.DailyChallenge;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DailyChallengeRepository extends MongoRepository<DailyChallenge, Long> {
}
