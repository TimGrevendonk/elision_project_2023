package fact.it.p4_backend.controller;

import java.util.List;

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
     */
    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsersOrderedByNameAscending() throws Exception {
        List<User> usersResponse = userService.getAllUsersOrderedByNameAscending();
        if (!usersResponse.isEmpty()) {
            return new ResponseEntity<>(usersResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Query the repository for a single user by their id.
     * @param userId the id of the requested user.
     * @return ResponseEntity 200 OK with the user.
     */
    @GetMapping("/user/{number}")
    public ResponseEntity<User> getUserById(@PathVariable("number") Long userId) throws Exception {
        User user = userService.findById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

//    @PostMapping("/user")
//    public ResponseEntity createUser(@RequestBody User user) {
//        return userService;
//    }

}