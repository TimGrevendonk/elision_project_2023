package fact.it.p4_backend.Controller;

import fact.it.p4_backend.controller.UserController;
import fact.it.p4_backend.exception.UserNotFoundException;
import fact.it.p4_backend.helper.JsonHelper;
import fact.it.p4_backend.model.User;
import fact.it.p4_backend.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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
@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
    List<User> usersMock = List.of(
            new User(1L, "testUser"),
            new User(2L, "demoUser")
    );
    @MockBean
    private UserServiceImpl userServiceImplMock;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    /**
     * Get a user by its id from the service.
     *
     * @throws Exception will return an exception to be handled.
     */
    @Test
    public void when_user_GetById_userReturned() throws Exception {
        when(userServiceImplMock.getById(1L)).thenReturn(this.usersMock.get(0));
        User resultUser = userServiceImplMock.getById(1L);

        assertThat(resultUser)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(this.usersMock.get(0));
        Mockito.verify(userServiceImplMock, times(1)).getById(1L);
        Mockito.verifyNoMoreInteractions(userServiceImplMock);
    }

    /**
     * Rest query and trigger repository getById in service and throws exception.
     *
     * @throws UserNotFoundException the user is not found.
     */
    @Test
    public void when_userQuery_getUserByID_notFound() throws Exception {
        when(userServiceImplMock.getById(1L)).thenThrow(new UserNotFoundException());
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/user/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        ResultActions resultUser = mockMvc.perform(requestBuilder)
                .andExpect(status().isNotFound());
        assertThat(resultUser.andReturn())
                .isNotNull();
        verify(userServiceImplMock).getById(1L);
    }

    /**
     * Rest query and trigger repository getAll() in service.
     * throws exception to parent.
     *
     * @throws UserNotFoundException the user is not found.
     */
    @Test
    public void when_allUserQuery_allUsersReturned() throws Exception {
        when(userServiceImplMock.getAll())
                .thenReturn(this.usersMock);
        List<User> resultUsers = userServiceImplMock.getAll();

        assertThat(resultUsers)
                .isNotNull()
                .isEqualTo(this.usersMock);
        verify(userServiceImplMock, times(1)).getAll();
    }

    /**
     * Rest query and trigger repository create() in service.
     * throws exception to parent.
     * Mock an actual url request with converted to Json user.
     * @throws UserNotFoundException the user is not found.
     */
    @Test
    public void when_createUser_UserCreatedAndReturnedWithStatusOK() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/user/create")
                        .content(JsonHelper.asJsonString(new User("testUser")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(userServiceImplMock, times(1)).create(any());
    }

}
