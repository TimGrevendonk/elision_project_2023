package fact.it.p4_backend.repository;

import fact.it.p4_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepositoryInterface extends JpaRepository<User, Long> {
    @Query("select u from User u order by u.name ASC")
    Optional<List<User>> getAllUsersOrderedByNameAscending();

    @Query("select u from User u where u.mail = :userMail")
    Optional<User> getUserByMail(@Param("userMail") String userMail);

    boolean existsByMail(String mail);
}
