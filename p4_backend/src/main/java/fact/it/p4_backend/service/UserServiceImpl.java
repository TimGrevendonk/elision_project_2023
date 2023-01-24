package fact.it.p4_backend.service;

import fact.it.p4_backend.controller.CheckOptionalUserNotEmpty;
import fact.it.p4_backend.exception.UserNotFoundException;
import fact.it.p4_backend.model.User;
import fact.it.p4_backend.repository.UserRepositoryInterface;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService<User> {
    private final UserRepositoryInterface userRepository;

    public UserServiceImpl(UserRepositoryInterface userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public  List<User> getAll() throws UserNotFoundException {
//        Except Option 1: use orElseThrow with exception, the controller advisor will catch this (?).
        return userRepository.getAllUsersOrderedByNameAscending().orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User getById(Long userId) throws UserNotFoundException {
//        Except Option 2: Manually check if the Optional is empty, and throw the error if so.
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException();
        }
        return userOptional.get();
    }

    @Override
    public User create(User newUser) throws UserNotFoundException {
//        Except Option 3: Check via custom checker, Optional not allowed in general method???
        User buildedUser = new User();
        buildedUser.setName(newUser.getName());
        userRepository.save(buildedUser);
        return CheckOptionalUserNotEmpty.checkNotEmpty(userRepository.findById(buildedUser.getId()));
    }

    @Override
    public User update(User user) throws UserNotFoundException {
        this.getById(user.getId());
        userRepository.save(user);
        return user;
    }

    @Override
    public User deleteById(Long userId) throws UserNotFoundException {
        User foundUser = this.getById(userId);
        userRepository.delete(foundUser);
        return foundUser;
    }
}
