package fact.it.p4_backend.service;

import fact.it.p4_backend.exception.UserNotFoundException;
import fact.it.p4_backend.model.User;
import fact.it.p4_backend.repository.UserRepositoryInterface;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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
@WebMvcTest(UserService.class)
public class UserServiceTest {
    @MockBean
    private UserRepositoryInterface userRepositoryMock;
    @MockBean
    private UserService userServiceMock;
    @Mock
    private PasswordEncoder passwordEncoder;


    /**
     * Get a user by its id from the repository.
     *
     * @throws Exception will return an exception to be handled.
     */
    @Test
    public void when_service_getUserById_userReturned() throws Exception {
        User mockUser = mock(User.class);
        when(this.userServiceMock.getById(any())).thenReturn(mockUser);
        User resultUser = this.userServiceMock.getById(1L);
        assertThat(resultUser).isNotNull().isEqualTo(mockUser);
        verify(userServiceMock, times(1)).getById(anyLong());
    }

    /**
     * Get empty user and throw custom exception UserNotFound.
     *
     * @throws Exception will return an exception to be handled.
     */
    @Test
    public void when_service_getUserById_notFoundException() throws Exception {
        UserService userServiceImpl = new UserService(userRepositoryMock, passwordEncoder);
        Optional<User> userMock = Optional.empty();
        when(userRepositoryMock.findById(1L)).thenReturn(userMock);

        assertThrows(UserNotFoundException.class, () -> {
            userServiceImpl.getById(1L);
        });
        verify(userRepositoryMock, times(1)).findById(1L);
        verify(userRepositoryMock).findById(1L);
    }

    /**
     * User created returns User with id userId.
     */
    @Test
    public void when_service_create_returnCreatedUser() {
        User mockUser = mock(User.class);
        when(this.userServiceMock.create(any(User.class))).thenReturn(mockUser);
        User resultUser = this.userServiceMock.create(mockUser);
        assertThat(resultUser).isNotNull().isEqualTo(mockUser);
        assertThat(resultUser.getId()).isNotNull();
        verify(userServiceMock, times(1)).create(any());
    }
}
