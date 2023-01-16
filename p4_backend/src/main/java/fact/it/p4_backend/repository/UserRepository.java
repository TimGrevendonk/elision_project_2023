package fact.it.p4_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fact.it.p4_backend.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u order by u.name ASC")
    List<User> getAllUsersOrderedByNameAscending();

    
}
