package fact.it.p4_backend.Controller;

import fact.it.p4_backend.controller.UserController;
import fact.it.p4_backend.exception.UserNotFoundException;
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
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


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
    @MockBean
    private UserServiceImpl userServiceImplMock;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    /**
     * Get a user by its id from the service.
     * @throws Exception will return an exception to be handled.
     */
    @Test
    public void when_user_GetById_userReturned() throws Exception {
        User userMock = new User(1L, "testUser");
        when(userServiceImplMock.findById(1L)).thenReturn(userMock);
        User resultUser = userServiceImplMock.findById(1L);

        assertThat(resultUser)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(userMock);
        Mockito.verify(userServiceImplMock, times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(userServiceImplMock);
    }

    /**
     * preform rest query and trigger repository findById through service and throws exception.
     * @throws UserNotFoundException the user is not found.
     */
    @Test
    public void when_userQuery_getUserByID_notFound() throws Exception {
        when(userServiceImplMock.findById(1L)).thenThrow(new UserNotFoundException());
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/user/1")
                .accept(MediaType.APPLICATION_JSON);
        ResultActions resultUser = mockMvc.perform(requestBuilder).andExpect(status().isNotFound());
        assertThat(resultUser.andReturn())
                .isNotNull();
        verify(userServiceImplMock).findById(1L);
    }
}
