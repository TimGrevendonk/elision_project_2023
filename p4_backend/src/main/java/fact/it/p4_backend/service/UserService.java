package fact.it.p4_backend.service;

import fact.it.p4_backend.DTO.UserDTOMapper;
import fact.it.p4_backend.DTO.UserSecureDTO;
import fact.it.p4_backend.builder.UserModelBuilder;
import fact.it.p4_backend.exception.MailAlreadyExistsException;
import fact.it.p4_backend.exception.UserNotFoundException;
import fact.it.p4_backend.model.User;
import fact.it.p4_backend.repository.UserRepositoryInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserServiceInterface<User, UserSecureDTO> {
    Logger logger = LoggerFactory.getLogger(UserService.class);
    private UserRepositoryInterface userRepository;
    private UserDTOMapper userDTOMapper;

    /**
     * connect repository.
     *
     * @param userRepository Constructor inject the userRepository.
     */
    public UserService(UserRepositoryInterface userRepository, UserDTOMapper userDTOMapper) {
        this.userRepository = userRepository;
        this.userDTOMapper = userDTOMapper;
    }

    public UserRepositoryInterface getUserRepository() {
        return userRepository;
    }

    public UserDTOMapper getUserDTOMapper() {
        return userDTOMapper;
    }

    /**
     * Returns all users.
     *
     * @return List of all users
     * @throws UserNotFoundException User not found in queries.
     */
    @Override
    public List<UserSecureDTO> getAll() throws UserNotFoundException {
        List<User> users = getUserRepository().getAllUsersOrderedByNameAscending().orElseThrow(() -> new UserNotFoundException("No users found."));
        return getUserDTOMapper().toUserSecureDtoList(users);
    }

    /**
     * returns one user based on their ID.
     *
     * @param userId the Id of the user.
     * @return One user based on ID.
     * @throws UserNotFoundException User not found in queries.
     */
    @Override
    public UserSecureDTO getById(Long userId) throws UserNotFoundException {
        User user = getUserRepository().findById(userId).orElseThrow(() -> new UserNotFoundException("User with userId " + userId + " not found."));
        return getUserDTOMapper().toUserSecureDto(user);
    }

    /**
     * Creating a new user that needs a unique email address.
     *
     * @param newUser The response body gotten from the controller.
     * @return User Fetched from repository.
     * @throws UserNotFoundException      No user found from query.
     * @throws MailAlreadyExistsException Mail already in repository.
     */
    @Override
    public UserSecureDTO create(User newUser) throws UserNotFoundException, MailAlreadyExistsException {
        if (getUserRepository().existsByMail(newUser.getMail())) {
            throw new MailAlreadyExistsException(String.format("mail %s already exists", newUser.getMail()));
        }
        User user = new UserModelBuilder(newUser.getMail(), newUser.getName(), newUser.getPassword())
                .address(newUser.getAddress())
                .phoneNumber(newUser.getPhoneNumber())
                .build();
        return getUserDTOMapper().toUserSecureDto(getUserRepository().save(user));
    }

    /**
     * Change user details. uses class getById as null check that the user exists.
     *
     * @param updateUser The user to be updated.
     * @return Updated userSecureDTO.
     * @throws UserNotFoundException User not found in queries.
     */
    @Override
    public UserSecureDTO update(User updateUser) throws UserNotFoundException {
        User repositoryUser = getUserRepository().findById(updateUser.getId()).orElseThrow(() -> new UserNotFoundException("User with userId " + updateUser.getId() + " not found."));
        repositoryUser.setName(updateUser.getName());
        repositoryUser.setMail(updateUser.getMail());
        repositoryUser.setPassword(updateUser.getPassword());
        repositoryUser.setAddress(updateUser.getAddress());
        repositoryUser.setPhoneNumber(updateUser.getPhoneNumber());
        return getUserDTOMapper().toUserSecureDto(getUserRepository().save(repositoryUser));
    }

    /**
     * Deletes user from database. uses the getById that returns a UserSecureDTO.
     *
     * @param userId The id of the to be deleted user.
     * @return Deleted user.
     */
    @Override
    public UserSecureDTO deleteById(Long userId) {
        UserSecureDTO foundUser = this.getById(userId);
        getUserRepository().deleteById(userId);
        return foundUser;
    }
}
