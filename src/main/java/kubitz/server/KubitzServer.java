package kubitz.server;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import kubitz.server.database.accounts.model.Account;
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
        return args -> {
            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

        };
    }

    @Bean
    CommandLineRunner init(AccountRepository accountRepository) {

        return args -> {

//            Account acc = new Account();
//            acc.setName("mert");
//            Account acc2 = new Account();
//            acc.setName("alp");
//
//            accountRepository.save(acc);
//            accountRepository.save(acc2);
            List<Account> accounts = accountRepository.findAll();
            System.out.println(accounts);

        };

    }

}