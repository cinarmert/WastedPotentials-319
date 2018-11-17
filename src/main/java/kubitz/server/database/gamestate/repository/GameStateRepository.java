package kubitz.server.database.gamestate.repository;

import kubitz.server.database.accounts.model.Account;
import kubitz.server.database.gamestate.model.BoardState;
import org.springframework.data.mongodb.repository.MongoRepository;

// No need implementation, just one interface, and you have CRUD, thanks Spring Data
public interface GameStateRepository extends MongoRepository<BoardState, Long> {

}
