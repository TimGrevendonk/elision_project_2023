package fact.it.p4_backend.repository;

import fact.it.p4_backend.model.User;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public abstract class UserRepository implements UserRepositoryInterface {
    private final UserRepository userRepository;


    public UserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }
}
