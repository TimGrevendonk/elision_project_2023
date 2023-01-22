package fact.it.p4_backend.repository;

import fact.it.p4_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepositoryInterface extends JpaRepository<User, Long> {
    @Query("select u from User u order by u.name ASC")
    List<User> getAllUsersOrderedByNameAscending();
}