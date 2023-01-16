package fact.it.p4_backend.controller;

import java.util.List;
import java.util.Optional;

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
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsersOrderedByNameAscending() {
        List<User> UsersResponse = userRepository.getAllUsersOrderedByNameAscending();
        if (!UsersResponse.isEmpty()) {
            return new ResponseEntity<>(UsersResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/user/{number}")
    public ResponseEntity<User> getTrainingById(@PathVariable("number") Long userId){
        Optional<User> UserResponse = userRepository.findById(userId);
        if(UserResponse.isPresent()) {
            return new ResponseEntity<>(UserResponse.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
}