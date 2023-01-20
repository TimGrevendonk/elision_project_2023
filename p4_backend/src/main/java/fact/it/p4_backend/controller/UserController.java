package fact.it.p4_backend.controller;

import java.util.List;
import java.util.Optional;

import fact.it.p4_backend.repository.UserRepositoryInterface;
import fact.it.p4_backend.services.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fact.it.p4_backend.model.User;
//import fact.it.p4_backend.repository.UserRepository;

// import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api")
/**
 * CRUD for user data.
 */
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsersOrderedByNameAscending() {
// //        use of sql that is not connected.
        List<User> usersResponse = userService.getAllUsersOrderedByNameAscending();

        System.out.print(usersResponse);
//        not null/Empty check with return of User and OK status.
        if (!usersResponse.isEmpty()) {
            return new ResponseEntity<>(usersResponse, HttpStatus.OK);
        }
//        Default return
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/user/{number}")
    public ResponseEntity<User> getTrainingById(@PathVariable("number") Long userId) {
        Optional<User> userResponse = userService.findById(userId);
//        Value check with return of User and OK status.
        if (userResponse.isPresent()) {
            return new ResponseEntity<>(userResponse.get(), HttpStatus.OK);
        }
//        Default return
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}