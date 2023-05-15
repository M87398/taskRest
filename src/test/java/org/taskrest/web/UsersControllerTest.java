package org.taskrest.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.taskrest.UserDaoGenerator;
import org.taskrest.user.UserDao;
import org.taskrest.user.UserService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UsersController.class)
@AutoConfigureJsonTesters
class UsersControllerTest {
    private static final String GET_USER_URI = "/users/";

    @MockBean
    private UserService userService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private JacksonTester<UserDao> userDaoJacksonTester;

    @Test
    void getUser() throws Exception {
        //given
        String login = "login";
        UserDao userDao = UserDaoGenerator.simpleUserDao();
        when(userService.getUser(login)).thenReturn(userDao);

        //when
        MvcResult result = this.mockMvc.perform(get(GET_USER_URI + login))
                .andExpect(status().isOk()).andReturn();

        //then
        UserDao response = userDaoJacksonTester.parseObject(result.getResponse().getContentAsString());
        assertThat(response).isEqualTo(userDao);
    }
}