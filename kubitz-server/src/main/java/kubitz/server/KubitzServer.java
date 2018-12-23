package kubitz.server;

import kubitz.server.database.accounts.repository.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KubitzServer {

    public static void main(String[] args) {
        SpringApplication.run(KubitzServer.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {};
    }

    @Bean
    CommandLineRunner init(AccountRepository accountRepository) {

        return args -> {};

    }

}