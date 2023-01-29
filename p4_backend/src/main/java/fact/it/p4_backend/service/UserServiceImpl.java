package fact.it.p4_backend.service;

import fact.it.p4_backend.exception.UserNotFoundException;
import fact.it.p4_backend.model.User;
import fact.it.p4_backend.repository.UserRepositoryInterface;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl {
    private final UserRepositoryInterface userRepository;

    PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepositoryInterface userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
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
        builtUser.setMail(newUser.getMail());
        String encodedPassword = this.passwordEncoder.encode(newUser.getPassword());
        builtUser.setPassword(encodedPassword);
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
