package fact.it.p4_backend.services;

import fact.it.p4_backend.model.User;
import fact.it.p4_backend.repository.UserRepositoryInterface;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepositoryInterface userRepository;

    @PostConstruct
    public void fillDatabaseTemporary() {
        userRepository.save(new User(1L, "Brent D"));
        userRepository.save(new User(2L, "Bogdan L"));
        userRepository.save(new User(3L, "Raf B"));
        userRepository.save(new User(4L, "Rune M"));
        userRepository.save(new User(5L, "Tim G"));
    }

    public List<User> getAllUsersOrderedByNameAscending(){
        return userRepository.getAllUsersOrderedByNameAscending();
    }

    public Optional<User> findById(Long userId){
        return userRepository.findById(userId);
    }
}
