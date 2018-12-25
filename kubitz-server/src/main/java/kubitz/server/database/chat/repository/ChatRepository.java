package kubitz.server.database.chat.repository;

import kubitz.server.database.chat.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatRepository extends MongoRepository<Message, String> {
    List<Message> findAllByLobbyId(String lobbyId);

}
