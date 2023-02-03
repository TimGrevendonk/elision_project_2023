package fact.it.p4_backend.controller;

import java.util.List;

import fact.it.p4_backend.DTO.UserSecureDTO;
import fact.it.p4_backend.exception.UserNotFoundException;
import fact.it.p4_backend.service.UserService;
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

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public UserService getUserService() {
        return userService;
    }

    /**
     * Query the repository for all users and sort them by name (Ascended).
     * @return responseEntity 200 OK with the users.
     * @throws UserNotFoundException handled if the user is not found.
     */
    @GetMapping("/user")
    public ResponseEntity<List<UserSecureDTO>> getAllUsersOrderedByNameAscending() throws Exception {
        List<UserSecureDTO> usersResponse = getUserService().getAll();
        return new ResponseEntity<>(usersResponse, HttpStatus.OK);
    }

    /**
     * Query the repository for a single user by their id.
     * @param userId the id of the requested user.
     * @return ResponseEntity 200 OK with the user.
     * @throws UserNotFoundException handled if the user is not found.
     */
    @GetMapping("/user/{UserId}")
    public ResponseEntity<UserSecureDTO> getUserById(@PathVariable("UserId") Long userId) throws Exception {
        UserSecureDTO user = getUserService().getById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Query the repository for a single user by their mail.
     * @param userMail the mail of the requested user.
     * @return ResponseEntity 200 OK with the userSecureDTO.
     * @throws UserNotFoundException handled if the user is not found.
     */
    @GetMapping("/user/mail")
    public ResponseEntity<UserSecureDTO> getUserByMail(@RequestParam(value = "usermail") String userMail) throws Exception {
        UserSecureDTO user = getUserService().getByMail(userMail);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * add the user in the repository and return the same user for later interaction.
     * @param newUser the body of the to be created user.
     * @return ResponseEntity 200 OK with the user.
     * @throws UserNotFoundException handled if the user is not found or saved incorrectly.
     */
    @PostMapping("/user/create")
    public ResponseEntity<UserSecureDTO> createUser(@RequestBody User newUser) throws Exception {
        UserSecureDTO user = getUserService().create(newUser);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * update the user in the repository and return the same user for later interaction.
     * @param updateUser the body of the to be updated user.
     * @return ResponseEntity 200 OK with the user.
     * @throws UserNotFoundException handled if the user is not found or saved incorrectly.
     */
    @PutMapping("/user/update")
    public ResponseEntity<UserSecureDTO> updateUser(@RequestBody User updateUser) throws Exception {
        UserSecureDTO user = getUserService().update(updateUser);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/user/sign-in")
    public ResponseEntity<UserSecureDTO> signInUser(@RequestBody User loginUser) throws Exception{
        UserSecureDTO user = getUserService().signIn(loginUser);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * delete the user in the repository and return the same user for later interaction.
     * @param userId the id of the to be deleted user.
     * @return ResponseEntity 200 OK with the user.
     * @throws UserNotFoundException handled if the user is not found or saved incorrectly.
     */
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<UserSecureDTO> deleteUser(@PathVariable("userId") Long userId) throws Exception {
        UserSecureDTO deletedUser = getUserService().deleteById(userId);
        return new ResponseEntity<>(deletedUser, HttpStatus.OK);
    }
}