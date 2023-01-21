package fact.it.p4_backend.service;

import fact.it.p4_backend.exception.UserNotFoundException;
import fact.it.p4_backend.model.User;
import fact.it.p4_backend.repository.UserRepository;
import fact.it.p4_backend.repository.UserRepositoryInterface;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService{
    private final UserRepositoryInterface userRepository;

    public UserService(UserRepositoryInterface userRepository) {
        this.userRepository = userRepository;
    }

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

    public User findById(Long userId) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()){
            throw new UserNotFoundException();
        }
        return userOptional.get();
    }


}
