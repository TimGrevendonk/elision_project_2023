package fact.it.p4_backend.service;

import fact.it.p4_backend.exception.UserNotFoundException;
import fact.it.p4_backend.model.User;
import fact.it.p4_backend.repository.UserRepositoryInterface;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * Test the UserService its methods.
 * Mock and use UserService and UserRepository.
 * <p>WebMvcTest to mock Http requests.</p>
 */
@WebMvcTest(UserService.class)
public class UserServiceTest {
    @MockBean
    private UserRepositoryInterface userRepositoryMock;

    /**
     * Get a user by its id from the repository.
     * @throws Exception will return an exception to be handled.
     */
    @Test
    public void when_service_getUserById_userReturned() throws Exception {
        UserService userService = new UserService(userRepositoryMock);
        Optional<User> userMock = Optional.of(new User(1L, "testUser"));
        when(userRepositoryMock.findById(1L)).thenReturn(userMock);
        User resultUser = userService.findById(1L);

        assertThat(resultUser)
                .isNotNull()
                .isEqualTo(userMock.get());
        verify(userRepositoryMock, times(1)).findById(1L);
    }
    /**
     * Get empty user and throw custom exception UserNotFound.
     * @throws Exception will return an exception to be handled.
     */
    @Test
    public void when_service_getUserById_notFoundException() throws Exception {
        UserService userService = new UserService(userRepositoryMock);
        Optional<User> userMock = Optional.empty();
        when(userRepositoryMock.findById(1L)).thenReturn(userMock);

        assertThrows(UserNotFoundException.class, () -> {
            userService.findById(1L);
        });
        verify(userRepositoryMock, times(1)).findById(1L);
        verify(userRepositoryMock).findById(1L);

    }
}
