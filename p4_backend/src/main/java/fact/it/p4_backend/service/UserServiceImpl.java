package fact.it.p4_backend.service;

import fact.it.p4_backend.controller.CheckOptionalUserNotEmpty;
import fact.it.p4_backend.exception.UserNotFoundException;
import fact.it.p4_backend.model.User;
import fact.it.p4_backend.repository.UserRepositoryInterface;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService<User> {
    private final UserRepositoryInterface userRepository;

    public UserServiceImpl(UserRepositoryInterface userRepository) {
        this.userRepository = userRepository;
    }

//    @PostConstruct
//    public void fillDatabaseTemporary() {
//        userRepository.save(new User(1L, "Brent D"));
//        userRepository.save(new User(2L, "Bogdan L"));
//        userRepository.save(new User(3L, "Raf B"));
//        userRepository.save(new User(4L, "Rune M"));
//        userRepository.save(new User(5L, "Tim G"));
//    }

    @Override
    public  List<User> getAll() throws UserNotFoundException {
        return userRepository.getAllUsersOrderedByNameAscending().orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User getById(Long userId) throws UserNotFoundException {
//        Manually check if the Optional is empty, and throw the error if so.
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException();
        }
        return userOptional.get();
    }

    @Override
    public User create(User newUser) throws UserNotFoundException {
//        Check via custom checker, Optional not allowed in general method???
        userRepository.save(newUser);
        return CheckOptionalUserNotEmpty.checkNotEmpty(userRepository.findById(newUser.getId()));
    }

    @Override
    public void update(User user) throws UserNotFoundException {
        this.getById(user.getId());
        userRepository.save(user);
    }

    @Override
    public void deleteById(Long userId) throws UserNotFoundException {
        User foundUser = this.getById(userId);
        userRepository.delete(foundUser);
    }
}
