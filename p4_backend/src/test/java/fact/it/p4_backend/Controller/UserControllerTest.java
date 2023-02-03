package fact.it.p4_backend.Controller;

import fact.it.p4_backend.DTO.UserSecureDTO;
import fact.it.p4_backend.builder.UserModelBuilder;
import fact.it.p4_backend.controller.UserController;
import fact.it.p4_backend.exception.UserNotFoundException;
import fact.it.p4_backend.helper.JsonHelper;
import fact.it.p4_backend.model.User;
import fact.it.p4_backend.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test the restful userController its methods.
 * Mock and use UserService and UserRepository.
 * <p>ExtendsWith creates the spring context.
 * WebMvcTest to mock Http requests.</p>
 */
@WebMvcTest(UserController.class)
public class UserControllerTest {
    @MockBean
    private UserService userServiceMock;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    public UserService getUserServiceMock() {return userServiceMock;}
    public MockMvc getMockMvc() {return mockMvc;}

    private final List<UserSecureDTO> mockDTOUsers = List.of(mock(UserSecureDTO.class, "test"));
    private final UserSecureDTO mockDTOUser = mock(UserSecureDTO.class);

    /**
     * Get a user by its id from the service.
     *
     * @throws Exception will return an exception to be handled.
     */
    @Test
    public void when_user_GetById_userSecureDTOReturned() throws Exception {
        when(getUserServiceMock().getById(anyLong())).thenReturn(mockDTOUser);
        UserSecureDTO resultUser = getUserServiceMock().getById(1L);

        assertThat(resultUser)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(mockDTOUser);
        verify(getUserServiceMock(), times(1)).getById(1L);
        verifyNoMoreInteractions(getUserServiceMock());
    }

    /**
     * Rest query and trigger repository getById in service and throws exception.
     *
     * @throws UserNotFoundException the user is not found.
     */
    @Test
    public void when_userQuery_getUserByID_notFound() throws Exception {
        when(getUserServiceMock().getById(1L)).thenThrow(new UserNotFoundException("User with userId 1 not found."));
        ResultActions request = getMockMvc().perform(
                        MockMvcRequestBuilders.get("/api/user/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(MockMvcResultHandlers.print());

        assertThat(request.andReturn())
                .isNotNull();
        verify(getUserServiceMock()).getById(1L);
    }

    /**
     * Rest query and trigger repository getAll() in service.
     * throws exception to parent.
     *
     * @throws UserNotFoundException the user is not found.
     */
    @Test
    public void when_allUserQuery_allUsersReturned() throws Exception {
        when(getUserServiceMock().getAll())
                .thenReturn(mockDTOUsers);
        List<UserSecureDTO> resultUsers = getUserServiceMock().getAll();

        assertThat(resultUsers)
                .isNotNull()
                .isEqualTo(mockDTOUsers);
        verify(getUserServiceMock(), times(1)).getAll();
    }

    /**
     * Rest query and trigger repository create() in service.
     * throws exception to parent.
     * Mock an actual url request with converted to Json user.
     *
     * @throws UserNotFoundException the user is not found.
     */
    @Test
    public void when_createUser_UserCreatedAndReturnedWithStatusOK() throws Exception {
        UserSecureDTO userDTOMock = mock(UserSecureDTO.class);
        User userMock = mock(User.class);
        when(getUserServiceMock().create(userMock)).thenReturn(userDTOMock);
        getMockMvc().perform(
                        MockMvcRequestBuilders.post("/api/user/create")
                                .content(JsonHelper.asJsonString(new User(new UserModelBuilder("test@mail.com","testUser","testPassword"))))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(getUserServiceMock(), times(1)).create(any());
    }

    /**
     * Rest query and trigger repository deleteById() in service.
     * throws exception to parent.
     * Mock an actual url request with converted to Json user.
     *
     * @throws UserNotFoundException the user is not found.
     */
    @Test
    public void when_deleteUser_UserDeleteAndReturnedWithStatusOK() throws Exception {
        getMockMvc().perform(
                        MockMvcRequestBuilders.delete("/api/user/{id}", 1)
                )
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(getUserServiceMock(), times(1)).deleteById(1L);
    }
}
