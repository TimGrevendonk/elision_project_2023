package fact.it.p4_backend.DataBase;

import fact.it.p4_backend.model.User;
import fact.it.p4_backend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class LoadDummyData {

    private static final Logger log = LoggerFactory.getLogger(LoadDummyData.class);

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {
            log.info("Preloading " + userRepository.save(new User("Brent D")));
            log.info("Preloading " + userRepository.save(new User("Bogdan L")));
            log.info("Preloading " + userRepository.save(new User("Raf B")));
            log.info("Preloading " + userRepository.save(new User("Rune M")));
            log.info("Preloading " + userRepository.save(new User("Tim G")));
        };
    }


}
