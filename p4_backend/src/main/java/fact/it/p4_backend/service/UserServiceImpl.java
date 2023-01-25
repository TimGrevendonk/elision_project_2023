package fact.it.p4_backend.service;

import fact.it.p4_backend.exception.UserNotFoundException;
import fact.it.p4_backend.model.User;
import fact.it.p4_backend.repository.UserRepositoryInterface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl {
    private final UserRepositoryInterface userRepository;

    public UserServiceImpl(UserRepositoryInterface userRepository) {
        this.userRepository = userRepository;
    }

    public UserRepositoryInterface get() {
        return userRepository;
    }

    public  List<User> getAll() throws UserNotFoundException {
        return userRepository.getAllUsersOrderedByNameAscending().orElseThrow(UserNotFoundException::new);
    }

    public User getById(Long userId) throws UserNotFoundException {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    public User create(User newUser) throws UserNotFoundException {
        User builtUser = new User();
        builtUser.setName(newUser.getName());
        userRepository.save(builtUser);
        return userRepository.findById(builtUser.getId()).orElseThrow(UserNotFoundException::new);
    }

    public User update(User user) throws UserNotFoundException {
        this.getById(user.getId());
        userRepository.save(user);
        return user;
    }

    public User deleteById(Long userId) throws UserNotFoundException {
        User foundUser = this.getById(userId);
        userRepository.delete(foundUser);
        return foundUser;
    }
}
