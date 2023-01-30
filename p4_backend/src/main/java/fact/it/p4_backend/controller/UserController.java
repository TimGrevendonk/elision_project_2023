package fact.it.p4_backend.controller;

import java.util.List;

import fact.it.p4_backend.exception.UserNotFoundException;
import fact.it.p4_backend.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fact.it.p4_backend.model.User;

/**
 * Controller to allow CRUD functionality via standard REST queries.
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class UserController {

    final private UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    /**
     * Query the repository for all users and sort them by name (Ascended).
     * @return responseEntity 200 OK with the users.
     * @throws UserNotFoundException handled if the user is not found.
     */
    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsersOrderedByNameAscending() throws Exception {
        List<User> usersResponse = userServiceImpl.getAll();
        return new ResponseEntity<>(usersResponse, HttpStatus.OK);
    }

    /**
     * Query the repository for a single user by their id.
     * @param userId the id of the requested user.
     * @return ResponseEntity 200 OK with the user.
     * @throws UserNotFoundException handled if the user is not found.
     */
    @GetMapping("/user/{UserId}")
    public ResponseEntity<User> getUserById(@PathVariable("UserId") Long userId) throws Exception {
        User user = userServiceImpl.getById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * add the user in the repository and return the same user for later interaction.
     * @param newUser the body of the to be created user.
     * @return ResponseEntity 200 OK with the user.
     * @throws UserNotFoundException handled if the user is not found or saved incorrectly.
     */
    @PostMapping("/user/create")
    public ResponseEntity<User> createUser(@RequestBody User newUser) throws Exception {
        User user = userServiceImpl.create(newUser);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    /**
     * update the user in the repository and return the same user for later interaction.
     * @param updateUser the body of the to be updated user.
     * @return ResponseEntity 200 OK with the user.
     * @throws UserNotFoundException handled if the user is not found or saved incorrectly.
     */
    @PutMapping("/user/update")
    public ResponseEntity<User> updateUser(@RequestBody User updateUser) throws Exception {
        User user = userServiceImpl.update(updateUser);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * delete the user in the repository and return the same user for later interaction.
     * @param userId the id of the to be deleted user.
     * @return ResponseEntity 200 OK with the user.
     * @throws UserNotFoundException handled if the user is not found or saved incorrectly.
     */
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<User> deleteUser(@PathVariable("userId") Long userId) throws Exception {
        User deletedUser = userServiceImpl.deleteById(userId);
        return new ResponseEntity<>(deletedUser, HttpStatus.OK);
    }
}