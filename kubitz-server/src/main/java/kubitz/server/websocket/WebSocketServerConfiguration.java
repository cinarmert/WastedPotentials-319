package kubitz.server.websocket;

import kubitz.server.websocket.handlers.LobbyWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.PerConnectionWebSocketHandler;

import java.util.concurrent.Executors;

@Configuration
@EnableWebSocket
public class WebSocketServerConfiguration implements WebSocketConfigurer {

    @Autowired
    protected LobbyWebSocketHandler lobbyHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(lobbyHandler, "/player/{playerId}");
    }

    @Bean
    public TaskScheduler taskScheduler() {
        return new ConcurrentTaskScheduler(Executors.newSingleThreadScheduledExecutor());
    }

    @Bean
    public WebSocketHandler lobbyHandler() {
        return new PerConnectionWebSocketHandler(LobbyWebSocketHandler.class);
    }
}
