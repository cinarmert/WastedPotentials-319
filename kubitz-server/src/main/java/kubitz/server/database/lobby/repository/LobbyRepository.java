package kubitz.server.database.lobby.repository;

import kubitz.server.database.lobby.model.Lobby;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LobbyRepository extends MongoRepository<Lobby, String> {
    Lobby findLobbyById(String id);
}