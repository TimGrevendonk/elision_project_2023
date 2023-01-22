package fact.it.p4_backend.controller;

import java.util.List;

import fact.it.p4_backend.exception.UserNotFoundException;
import fact.it.p4_backend.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fact.it.p4_backend.model.User;

/**
 * Controller to allow CRUD functionality via standard REST queries.
 */
@RestController
@RequestMapping("/api")
public class UserController {

    final private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Query the repository for all users and sort them by name (Ascended).
     * @return responseEntity 200 OK with the users.
     * @throws UserNotFoundException handled if the user is not found.
     */
    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsersOrderedByNameAscending() throws Exception {
        List<User> usersResponse = userService.getAllUsersOrderedByNameAscending();
        return new ResponseEntity<>(usersResponse, HttpStatus.OK);
    }

    /**
     * Query the repository for a single user by their id.
     * @param userId the id of the requested user.
     * @return ResponseEntity 200 OK with the user.
     * @throws UserNotFoundException handled if the user is not found.
     */
    @GetMapping("/user/{stringUserId}")
    public ResponseEntity<User> getUserById(@PathVariable("stringUserId") Long userId) throws Exception {
        User user = userService.findById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * add the user in the repository and return the same use for later interaction.
     * @param newUser the body of the to be created user.
     * @return ResponseEntity 200 OK with the user.
     * @throws UserNotFoundException handled if the user is not found or saved incorrectly.
     */
    @PostMapping("/user/create")
    public ResponseEntity<User> createUser(@RequestBody User newUser) throws Exception {
        User user = userService.createNewuser(newUser);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}