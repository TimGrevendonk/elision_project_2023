package fact.it.p4_backend.repository;

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
