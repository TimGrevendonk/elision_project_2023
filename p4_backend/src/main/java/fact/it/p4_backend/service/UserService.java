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
public class UserService {
    private final UserRepositoryInterface userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepositoryInterface userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserRepositoryInterface getUserRepository() {
        return userRepository;
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    public List<User> getAll() throws UserNotFoundException {
        return getUserRepository().getAllUsersOrderedByNameAscending().orElseThrow(UserNotFoundException::new);
    }

    public User getById(Long userId) throws UserNotFoundException {
        return getUserRepository().findById(userId).orElseThrow(UserNotFoundException::new);
    }

    /**
     * Creating a new user that needs a unique email address.
     * @param newUser
     * @return User Fetched from repository.
     * @throws UserNotFoundException No user found from query.
     * @throws MailAlreadyExistsException Mail already in repository.
     */
    public User create(User newUser) throws UserNotFoundException, MailAlreadyExistsException {
        User builtUser = new User();
        builtUser.setMail(newUser.getMail());
        if (getUserRepository().existsByMail(newUser.getMail())){
            throw new MailAlreadyExistsException("mail already exists");
        }
        builtUser.setName(newUser.getName());
        builtUser.setPassword(getPasswordEncoder().encode(newUser.getPassword()));
        User savedUser = getUserRepository().save(builtUser);
        return savedUser;
    }

    public User update(User user) throws UserNotFoundException {
        this.getById(user.getId());
        User savedUser = getUserRepository().save(user);
        return savedUser;
    }

    public User deleteById(Long userId) throws UserNotFoundException {
        User foundUser = this.getById(userId);
        getUserRepository().delete(foundUser);
        return foundUser;
    }
}
