package kubitz.server.database.chat.repository;

import kubitz.server.database.chat.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatRepository extends MongoRepository<Message, Long> {

}
