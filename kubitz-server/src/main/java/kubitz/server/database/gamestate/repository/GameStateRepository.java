package kubitz.server.database.gamestate.repository;

import kubitz.server.database.gamestate.model.GameState;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameStateRepository extends MongoRepository<GameState, Long> {

    GameState findGameStateById(int id);
}
