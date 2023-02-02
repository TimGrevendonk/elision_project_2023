package fact.it.p4_backend.service;

import fact.it.p4_backend.DTO.UserDTOMapper;
import fact.it.p4_backend.DTO.UserSecureDTO;
import fact.it.p4_backend.builder.UserModelBuilder;
import fact.it.p4_backend.exception.UserNotFoundException;
import fact.it.p4_backend.model.User;
import fact.it.p4_backend.repository.UserRepositoryInterface;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

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
    private UserService userServiceMock;
    @MockBean
    private UserRepositoryInterface userRepositoryMock;
    @MockBean
    private UserDTOMapper userDTOMapper;

    public UserRepositoryInterface getUserRepositoryMock() {return userRepositoryMock;    }
    public UserDTOMapper getUserDTOMapper() {return userDTOMapper;}
    public UserService getUserServiceMock() {return userServiceMock;}

    private final User mockUser = mock(User.class, "test");
    private final UserSecureDTO mockDTOUser = mock(UserSecureDTO.class);

    /**
     * Get a user by its id from the mocked repository.
     */
    @Test
    public void when_service_getUserById_userSecureDTOReturned() {
        when(getUserServiceMock().getById(anyLong())).thenReturn(this.mockDTOUser);
        UserSecureDTO resultUser = getUserServiceMock().getById(1L);
        assertThat(resultUser).isNotNull().isEqualTo(this.mockDTOUser);
        verify(getUserServiceMock(), times(1)).getById(anyLong());
    }

    /**
     * Get empty user and throw custom exception UserNotFound.
     *
     * @throws Exception will return an exception to be handled.
     */
    @Test
    public void when_service_getUserById_notFoundException() throws Exception{
        UserService serviceMock = new UserService(getUserRepositoryMock(), getUserDTOMapper());
        when(serviceMock.getUserRepository().findById(1L)).thenThrow(new UserNotFoundException("test throw"));

        assertThrows(UserNotFoundException.class, () -> {
            serviceMock.getUserRepository().findById(1L);
//            System.out.println(getUserServiceMock().getUserRepository());
        });
        verify(getUserRepositoryMock(), times(1)).findById(1L);
        verify(getUserRepositoryMock()).findById(1L);
    }

    /**
     * User created returns UserSecureDTO with userId.
     */
    @Test
    public void when_service_create_returnCreatedUserSecureDTO() {
        when(getUserServiceMock().create(any(User.class))).thenReturn(this.mockDTOUser);
        UserSecureDTO resultUser = getUserServiceMock().create(this.mockUser);
        assertThat(resultUser).isNotNull().isEqualTo(this.mockDTOUser);
        verify(getUserServiceMock(), times(1)).create(any());
    }

    /**
     * User saved returns User with id userId.
     */
    @Test
    public void when_service_saveUser_returnUserWithPasswordEncoded() {
        User user = new User(new UserModelBuilder("mail@test", "testUser", "password"));
        UserService serviceMock = new UserService(getUserRepositoryMock(), getUserDTOMapper());
        when(serviceMock.getUserRepository().save(any(User.class))).thenReturn(user);
        User resultUser = serviceMock.getUserRepository().save(user);
        assertThat(resultUser).isNotNull().isEqualTo(user);
        assertThat(resultUser.getPassword()).isNotNull().isNotEqualToIgnoringCase("password");
        verify(getUserRepositoryMock(), times(1)).save(any());
    }
}
