package fact.it.p4_backend.service;

import fact.it.p4_backend.builder.UserModelBuilder;
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
//implements UserServiceInterface
public  class UserService {
    private final UserRepositoryInterface userRepository;

    private final PasswordEncoder passwordEncoder;


    /**
     * connect repository and password encoder.
     * @param userRepository
     * @param passwordEncoder
     */
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

    /**
     * Returns all users.
     * @return List of all users
     * @throws UserNotFoundException
     */
    public List<User> getAll() throws UserNotFoundException {
        return getUserRepository().getAllUsersOrderedByNameAscending().orElseThrow(() -> new UserNotFoundException("No users found."));
    }

    /**
     * returns one user based on their ID.
     * @param userId
     * @return One user based on ID.
     * @throws UserNotFoundException
     */
    public User getById(Long userId) throws UserNotFoundException {
        return getUserRepository().findById(userId).orElseThrow(() -> new UserNotFoundException("User with userId " + userId + " not found."));
    }



    /**
     * Creating a new user that needs a unique email address.
     * @param newUser
     * @return User Fetched from repository.
     * @throws UserNotFoundException No user found from query.
     * @throws MailAlreadyExistsException Mail already in repository.
     */
    public User create(User newUser) throws UserNotFoundException, MailAlreadyExistsException {
        if (getUserRepository().existsByMail(newUser.getMail())){
            throw new MailAlreadyExistsException("mail already exists");
        }
        User user = new UserModelBuilder(newUser.getMail(), newUser.getName(), newUser.getPassword()).build();
        return getUserRepository().save(user);
    }



//    public User create(User newUser) throws UserNotFoundException, MailAlreadyExistsException {
//        User builtUser = new User(new UserModelBuilder());
//        builtUser.setMail(newUser.getMail());
//        if (getUserRepository().existsByMail(newUser.getMail())){
//            throw new MailAlreadyExistsException("mail already exists");
//        }
//        builtUser.setName(newUser.getName());
//        builtUser.setPassword(getPasswordEncoder().encode(newUser.getPassword()));
//        User savedUser = getUserRepository().save(builtUser);
//        return savedUser;
//    }

    /**
     * Change user details.
     * @param user
     * @return Updated user.
     * @throws UserNotFoundException
     */
    public User update(User user) throws UserNotFoundException {
        this.getById(user.getId());
        return getUserRepository().save(user);
    }

    /**
     * Deletes user from database.
     * @param userId
     * @return Deleted user.
     * @throws UserNotFoundException
     */
    public User deleteById(Long userId) throws UserNotFoundException {
        User foundUser = this.getById(userId);
        getUserRepository().delete(foundUser);
        return foundUser;
    }
}
