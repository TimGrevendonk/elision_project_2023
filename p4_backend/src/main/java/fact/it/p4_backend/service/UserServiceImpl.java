package fact.it.p4_backend.service;

import fact.it.p4_backend.exception.MailAlreadyExistsException;
import fact.it.p4_backend.exception.UserNotFoundException;
import fact.it.p4_backend.model.User;
import fact.it.p4_backend.repository.UserRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl {
    private final UserRepositoryInterface userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepositoryInterface userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() throws UserNotFoundException {
        return userRepository.getAllUsersOrderedByNameAscending().orElseThrow(UserNotFoundException::new);
    }

    public User getById(Long userId) throws UserNotFoundException {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    public User create(User newUser) throws UserNotFoundException,MailAlreadyExistsException {
        User builtUser = new User();
        builtUser.setMail(newUser.getMail());
        if (userRepository.existsByMail(newUser.getMail())){
            throw new MailAlreadyExistsException();
        }
        builtUser.setName(newUser.getName());
        builtUser.setPassword(this.passwordEncoder.encode(newUser.getPassword()));
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
