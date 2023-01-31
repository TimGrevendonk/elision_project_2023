package fact.it.p4_backend.service;

import fact.it.p4_backend.exception.UserNotFoundException;
import fact.it.p4_backend.model.User;
import fact.it.p4_backend.repository.UserRepositoryInterface;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * Test the UserService its methods.
 * Mock and use UserService and UserRepository.
 * <p>WebMvcTest to mock Http requests.</p>
 */
@WebMvcTest(UserServiceImpl.class)
public class UserServiceImplTest {
    @MockBean
    private UserRepositoryInterface userRepositoryMock;
    @MockBean private PasswordEncoder passwordEncoder;


    /**
     * Get a user by its id from the repository.
     *
     * @throws Exception will return an exception to be handled.
     */
    @Test
    public void when_service_getUserById_userReturned() throws Exception {
        UserServiceImpl userServiceImpl = new UserServiceImpl(userRepositoryMock, passwordEncoder);
        Optional<User> userMock = Optional.of(new User(1L, "testUser","mail@mail.com"));
        when(userRepositoryMock.findById(1L)).thenReturn(userMock);
        User resultUser = userServiceImpl.getById(1L);

        assertThat(resultUser)
                .isNotNull()
                .isEqualTo(userMock.get());
        verify(userRepositoryMock, times(1)).findById(1L);
    }

    /**
     * Get empty user and throw custom exception UserNotFound.
     *
     * @throws Exception will return an exception to be handled.
     */
    @Test
    public void when_service_getUserById_notFoundException() throws Exception {
        UserServiceImpl userServiceImpl = new UserServiceImpl(userRepositoryMock, passwordEncoder);
        Optional<User> userMock = Optional.empty();
        when(userRepositoryMock.findById(1L)).thenReturn(userMock);

        assertThrows(UserNotFoundException.class, () -> {
            userServiceImpl.getById(1L);
        });
        verify(userRepositoryMock, times(1)).findById(1L);
        verify(userRepositoryMock).findById(1L);
    }
}
