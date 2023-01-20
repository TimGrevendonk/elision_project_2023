package fact.it.p4_backend.controller;

import java.util.List;
import java.util.Optional;

import fact.it.p4_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fact.it.p4_backend.model.User;

@RestController
@RequestMapping("/api")
/**
 * Controller to allow CRUD functionality via standard REST queries.
 */
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    /**
     * Query the repository for all users and sort them by name (Ascended).
     * @return responseEntity 200 OK with the users.
     */
    public ResponseEntity<List<User>> getAllUsersOrderedByNameAscending() {
        List<User> usersResponse = userService.getAllUsersOrderedByNameAscending();
        if (!usersResponse.isEmpty()) {
            return new ResponseEntity<>(usersResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/user/{number}")
    /**
     * Query the repository for a single user by their id.
     * @param userId the id of the requested user.
     * @return ResponseEntity 200 OK with the user.
     */
    public ResponseEntity<User> getTrainingById(@PathVariable("number") Long userId) {
        Optional<User> userResponse = userService.findById(userId);
        return userResponse.map(user ->
                        new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() ->
                        new ResponseEntity<>(HttpStatus.NOT_FOUND)
                );
    }

}