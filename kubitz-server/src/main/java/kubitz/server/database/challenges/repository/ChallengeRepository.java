package kubitz.server.database.challenges.repository;

import kubitz.server.database.challenges.model.Challenge;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChallengeRepository extends MongoRepository<Challenge, Long> {

    public Challenge findChallengeById(int id);

}
