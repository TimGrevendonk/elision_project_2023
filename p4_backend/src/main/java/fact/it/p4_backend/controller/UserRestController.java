package fact.it.p4_backend.controller;

import java.util.List;
import java.util.Optional;

import com.contentful.java.cda.*;

import fact.it.p4_backend.DataBase.LoadDummyData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fact.it.p4_backend.model.User;
import fact.it.p4_backend.repository.UserRepository;

import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;


/**
 * userRestController
 */
@RestController
@RequestMapping("/api")
public class UserRestController {
    //    Autowired replaces the constructor injection.
//    @Autowired
    final private UserRepository userRepository;

    public UserRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/user")
//    public ResponseEntity<CDAEntry> getAllUsersOrderedByNameAscending() {
    public ResponseEntity<List<User>> getAllUsersOrderedByNameAscending() {
// //        use of sql that is not connected.
//        List<User> usersResponse = userRepository.getAllUsersOrderedByNameAscending();
// //        use of static data.
        List<User> usersResponse = List.of(
                new User(1L, "Brent D"),
                new User(2L, "Bogdan L"),
                new User(3L, "Raf B"),
                new User(3L, "Rune M"),
                new User(3L, "Tim G")
        );

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
        Optional<User> userResponse = userRepository.findById(userId);
//        Value check with return of User and OK status.
        if (userResponse.isPresent()) {
            return new ResponseEntity<>(userResponse.get(), HttpStatus.OK);
        }
//        Default return
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}